package com.project_1.ql_trang_thi_bi.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chi_tiet_mua_sam")
public class ChiTietMuaSam {

    @Id
    @Column(name = "ma_ct")
    private String maCT;

    @ManyToOne
    @JoinColumn(name = "ma_phieu")
    private PhieuMuaSam phieuMuaSam;

    @Column(name = "ten_thiet_bi")
    private String tenThietBi;

    @Column(name = "ma_tb")
    private String maTb;

    @ManyToOne
    @JoinColumn(name = "ma_loai")
    private LoaiThietBi loaiThietBi;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "thoi_gian_bao_hanh")
    private Integer thoiGianBaoHanh;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_nhap_kho")
    private Kho khoNhap;

    @Transient // Không lưu trong DB, chỉ dùng để tính toán khi trả về DTO
    public BigDecimal getThanhTien() {
        if (donGia == null || soLuong == null) return BigDecimal.ZERO;
        return donGia.multiply(BigDecimal.valueOf(soLuong));
    }
}
