package com.project_1.ql_trang_thi_bi.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phieu_thanh_ly")
public class PhieuThanhLy {

    @Id
    @Column(name = "ma_phieu_tl")
    private String maPhieuTL;

    @Column(name = "so_phieu_tl", unique = true, nullable = false)
    private String soPhieuTl;

    @ManyToOne
    @JoinColumn(name = "ma_thiet_bi")
    private ThietBi thietBi;

    @Column(name = "ly_do")
    private String lyDo;

    @Column(name = "ngay_tl")
    private LocalDateTime ngayThanhLy;

    @ManyToOne
    @JoinColumn(name = "nguoi_duyet")
    private NguoiDung nguoiDuyet;

    @Column(name = "ngay_duyet")
    private LocalDateTime ngayDuyet;

    @Column(name = "gia_tri_goc")
    private BigDecimal giaTriGoc;

    @Column(name = "gia_tri_thanh_ly")
    private BigDecimal giaTriThanhLy;

    @Column(name = "ghi_chu")
    private String ghiChu;
}
