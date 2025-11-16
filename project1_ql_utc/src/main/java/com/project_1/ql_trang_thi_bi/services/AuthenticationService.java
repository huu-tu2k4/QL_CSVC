package com.project_1.ql_trang_thi_bi.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project_1.ql_trang_thi_bi.dtos.AuthenticationDTO;
import com.project_1.ql_trang_thi_bi.dtos.IntrospectDTO;
import com.project_1.ql_trang_thi_bi.exceptions.AppException;
import com.project_1.ql_trang_thi_bi.exceptions.ErrorCode;
import com.project_1.ql_trang_thi_bi.models.InvalidatedToken;
import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.VaiTro;
import com.project_1.ql_trang_thi_bi.repositorys.InvalidatedTokenRepository;
import com.project_1.ql_trang_thi_bi.repositorys.NguoiDungRepository;
import com.project_1.ql_trang_thi_bi.response.AuthenticationResponse;
import com.project_1.ql_trang_thi_bi.response.IntrospectResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    NguoiDungRepository nguoiDungRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;


    public AuthenticationResponse authenticationResponse(AuthenticationDTO dto) {
        var user = nguoiDungRepository.findByTenDangNhap(dto.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(dto.getPassword(), user.getMatKhau())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var accessToken = generateAccessToken(user);
        var refreshToken = generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }

    // ==================== GENERATE ACCESS TOKEN ====================
    public String generateAccessToken(NguoiDung nguoiDung) {
        return generateToken(nguoiDung, VALID_DURATION, "access");
    }

    // ==================== GENERATE REFRESH TOKEN ====================
    public String generateRefreshToken(NguoiDung nguoiDung) {
        return generateToken(nguoiDung, REFRESHABLE_DURATION, "refresh");
    }

    // ==================== GENERATE TOKEN CHUNG ====================
    private String generateToken(NguoiDung nguoiDung, long duration, String type) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(nguoiDung.getTenDangNhap())
                .issuer("ok")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(duration, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(nguoiDung))
                .claim("type", type)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Không thể tạo token", e);
            throw new RuntimeException(e);
        }
    }


    private String buildScope(NguoiDung nguoiDung) {
        StringJoiner joiner = new StringJoiner(" ");
        if (nguoiDung.getVaiTro() != null) {
            joiner.add("ROLE_" + nguoiDung.getVaiTro().getMaVaiTro());
            if (nguoiDung.getVaiTro().getPermissions() != null) {
                nguoiDung.getVaiTro().getPermissions()
                        .forEach(p -> joiner.add(p.getCode()));
            }
        }
        return joiner.toString();
    }


    public IntrospectResponse introspectResponse(IntrospectDTO dto) throws JOSEException, ParseException {
        try {
            verifyToken(dto.getToken(), false);
            return IntrospectResponse.builder().valid(true).build();
        } catch (AppException e) {
            return IntrospectResponse.builder().valid(false).build();
        }
    }


    public SignedJWT verifyToken(String token, boolean allowExpiredForRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = allowExpiredForRefresh
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);
        if (!verified || !expiryTime.after(new Date())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        if (invalidatedTokenRepository.existsById(jti)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }


    public void logout(String token) throws ParseException, JOSEException {
        try {
            var signedJWT = verifyToken(token, false);
            String jti = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();

            invalidatedTokenRepository.save(InvalidatedToken.builder()
                    .id(jti)
                    .expiryTime(expiry)
                    .build());
        } catch (AppException e) {
            log.info("Token đã hết hạn hoặc không hợp lệ: {}", e.getMessage());
        }
    }


    public AuthenticationResponse refreshToken(String refreshToken) throws ParseException, JOSEException {

        var signedJWT = verifyToken(refreshToken, true);

        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();


        invalidatedTokenRepository.save(InvalidatedToken.builder()
                .id(jti)
                .expiryTime(expiry)
                .build());

        String username = signedJWT.getJWTClaimsSet().getSubject();
        var user = nguoiDungRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));


        var newAccessToken = generateAccessToken(user);
        var newRefreshToken = generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .authenticated(true)
                .build();
    }


    public long getRefreshableDuration() {
        return REFRESHABLE_DURATION;
    }
}