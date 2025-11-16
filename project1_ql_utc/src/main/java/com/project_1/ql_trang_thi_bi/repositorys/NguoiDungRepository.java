package com.project_1.ql_trang_thi_bi.repositorys;

import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    boolean existsByTenDangNhap(String tenDangNhap);

    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
}
