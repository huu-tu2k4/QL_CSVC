package com.project_1.ql_trang_thi_bi.repositorys;

import com.project_1.ql_trang_thi_bi.models.ChiTietKiemKe;
import com.project_1.ql_trang_thi_bi.models.PhieuKiemKe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhieuKiemKeRepository extends JpaRepository<PhieuKiemKe, String> {
//    List<PhieuKiemKe> findByMaPhieuKK(String maPhieuKK);
}
