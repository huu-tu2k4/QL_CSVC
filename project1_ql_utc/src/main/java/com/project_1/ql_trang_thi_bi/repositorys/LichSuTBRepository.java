package com.project_1.ql_trang_thi_bi.repositorys;

import com.project_1.ql_trang_thi_bi.models.LichSuThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LichSuTBRepository extends JpaRepository<LichSuThietBi, String> {
}
