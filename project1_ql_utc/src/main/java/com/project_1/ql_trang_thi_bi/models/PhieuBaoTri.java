package com.project_1.ql_trang_thi_bi.models;

import com.project_1.ql_trang_thi_bi.enums.MucUuTien;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiBaoTri;
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
@Table(name = "phieu_bao_tri")
public class PhieuBaoTri {


    @Id
    @Column(name = "ma_phieu_bt")
    private String maPhieuBT;

    @Column(name = "so_phieu_bt", unique = true, nullable = false)
    private String soPhieuBt;

    @ManyToOne
    @JoinColumn(name = "ma_thiet_bi")
    private ThietBi thietBi;

    @ManyToOne
    @JoinColumn(name = "nguoi_de_xuat")
    private NguoiDung nguoiDeXuat;

    @ManyToOne
    @JoinColumn(name = "ma_don_vi_de_xuat")
    private DonVi donViDeXuat;

    @Column(name = "ngay_de_xuat")
    private LocalDateTime ngayDeXuat;

    @Column(name = "mo_ta_su_co")
    private String moTaSuCo;

    @Enumerated(EnumType.STRING)
    @Column(name = "muc_uu_tien")
    private MucUuTien mucUuTien;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    private TrangThaiBaoTri trangThai;

    @ManyToOne
    @JoinColumn(name = "nguoi_phu_trach")
    private NguoiDung nguoiPhuTrach;

    @Column(name = "ngay_hoan_tat")
    private LocalDateTime ngayHoanTat;

    @Column(name = "chi_phi")
    private BigDecimal chiPhi;

    @Column(name = "ghi_chu")
    private String ghiChu;

}
