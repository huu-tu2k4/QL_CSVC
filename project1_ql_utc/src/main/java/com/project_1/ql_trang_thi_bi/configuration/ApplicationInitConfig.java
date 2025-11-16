package com.project_1.ql_trang_thi_bi.configuration;

import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.VaiTro;
import com.project_1.ql_trang_thi_bi.repositorys.NguoiDungRepository;
import com.project_1.ql_trang_thi_bi.repositorys.VaiTroRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    NguoiDungRepository nguoiDungRepository;
    VaiTroRepository vaiTroRepository;

    @NonFinal
    @Value("${admin.password}")
    protected String adPassword;
    @EventListener(ApplicationReadyEvent.class)
    public void initAdmin() {
        String adminUsername = "admin";
        String adminPassword = passwordEncoder.encode(adPassword);


        if (nguoiDungRepository.findByTenDangNhap(adminUsername).isEmpty()) {
            log.info("Tao tk Admin");


            VaiTro adminRole = vaiTroRepository.findByMaVaiTro("ADMIN")
                    .orElseGet(() -> {
                        VaiTro role = VaiTro.builder()
                                .maVaiTro("ADMIN")
                                .build();
                        return vaiTroRepository.save(role);
                    });

            NguoiDung adminUser = NguoiDung.builder()
                    .tenDangNhap(adminUsername)
                    .matKhau(adminPassword)
                    .hoTen("Admin")
                    .trangThai(true)
                    .vaiTro(adminRole)
                    .build();

            nguoiDungRepository.save(adminUser);

        } else {
            log.info("Admin da ton tai");
        }
    }
}
