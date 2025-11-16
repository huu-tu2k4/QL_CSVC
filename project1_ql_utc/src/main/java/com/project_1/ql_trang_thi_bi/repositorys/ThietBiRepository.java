package com.project_1.ql_trang_thi_bi.repositorys;

import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
import com.project_1.ql_trang_thi_bi.models.ThietBi;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;


public interface ThietBiRepository extends JpaRepository<ThietBi, String> {
    @Query("""
        SELECT tb FROM ThietBi tb
        WHERE (:keyword = '' OR LOWER(tb.tenThietBi) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(tb.soSerial) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(tb.maThietBi) LIKE LOWER(CONCAT('%', :keyword, '%')))
          AND (:trangThai IS NULL OR tb.trangThai = :trangThai)
    """)
    Page<ThietBi> findAllWithFilter(
            @Param("keyword") String keyword,
            @Param("trangThai") TrangThaiThietBi trangThai,
            Pageable pageable
    );

    @Query("SELECT COUNT(t) FROM ThietBi t WHERE t.donViSoHuu.maDonVi = :maDonVi")
    int countByDonVi(@Param("maDonVi") String maDonVi);




}
