package com.project_1.ql_trang_thi_bi.repositorys;

import com.project_1.ql_trang_thi_bi.models.PhieuBaoTri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaoTriRepository extends JpaRepository<PhieuBaoTri,String> {
}
