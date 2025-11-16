package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.NguoiDungDTO;
import com.project_1.ql_trang_thi_bi.exceptions.AppException;
import com.project_1.ql_trang_thi_bi.exceptions.ErrorCode;
import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.VaiTro;
import com.project_1.ql_trang_thi_bi.repositorys.NguoiDungRepository;
import com.project_1.ql_trang_thi_bi.repositorys.VaiTroRepository;
import com.project_1.ql_trang_thi_bi.response.NguoiDungResponse;
import com.project_1.ql_trang_thi_bi.iservices.INguoiDungService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NguoiDungService implements INguoiDungService {

    NguoiDungRepository nguoiDungRepository;
    VaiTroRepository vaiTroRepository;
    PasswordEncoder passwordEncoder;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public NguoiDungResponse createNguoiDung(NguoiDungDTO dto) {
        // Kiểm tra trùng tên đăng nhập
        if (nguoiDungRepository.existsByTenDangNhap(dto.getTenDangNhap())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // Encode mật khẩu
        String encodedPassword = passwordEncoder.encode(dto.getMatKhau());

        // Tạo người dùng
        NguoiDung nd = NguoiDung.builder()
                .tenDangNhap(dto.getTenDangNhap())
                .matKhau(encodedPassword)
                .hoTen(dto.getHoTen())
                .email(dto.getEmail())
                .soDienThoai(dto.getSoDienThoai())
                .trangThai(dto.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();

        // Gán vai trò nếu có
        if (dto.getMaVaiTro() != null) {
            VaiTro vt = vaiTroRepository.findById(dto.getMaVaiTro())
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
            nd.setVaiTro(vt);
        }

        nguoiDungRepository.save(nd);
        return NguoiDungResponse.fromNguoiDung(nd);
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public NguoiDungResponse updateNguoiDung(String maNguoiDung, NguoiDungDTO dto) {
        NguoiDung nd = nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (dto.getHoTen() != null) nd.setHoTen(dto.getHoTen());
        if (dto.getEmail() != null) nd.setEmail(dto.getEmail());
        if (dto.getSoDienThoai() != null) nd.setSoDienThoai(dto.getSoDienThoai());
        if (dto.getTrangThai() != null) nd.setTrangThai(dto.getTrangThai());
        nd.setNgayCapNhat(LocalDateTime.now());

        if (dto.getMatKhau() != null && !dto.getMatKhau().isEmpty()) {
            nd.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));
        }

        if (dto.getMaVaiTro() != null) {
            VaiTro vt = vaiTroRepository.findById(dto.getMaVaiTro())
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
            nd.setVaiTro(vt);
        }

        nguoiDungRepository.save(nd);
        return NguoiDungResponse.fromNguoiDung(nd);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteNguoiDung(String maNguoiDung) {
        NguoiDung nd = nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        nguoiDungRepository.delete(nd);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public NguoiDungResponse getNguoiDungById(String maNguoiDung) {
        NguoiDung nd = nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return NguoiDungResponse.fromNguoiDung(nd);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Page<NguoiDungResponse> getAllNguoiDung(Pageable pageable) {
        return nguoiDungRepository.findAll(pageable)
                .map(NguoiDungResponse::fromNguoiDung);
    }
    @Override
    public NguoiDungResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        NguoiDung nd = nguoiDungRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return NguoiDungResponse.fromNguoiDung(nd);
    }
}
