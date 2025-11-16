package com.project_1.ql_trang_thi_bi.models;

import com.project_1.ql_trang_thi_bi.enums.TrangThaiLichSu;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lich_su_thiet_bi")
public class LichSuThietBi {

    @Id
    @Column(name = "ma_lich_su")
    private String maLichSu;

    @ManyToOne
    @JoinColumn(name = "ma_thiet_bi")
    private ThietBi thietBi;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_su_kien")
    private TrangThaiLichSu loaiSuKien;

    @Column(name = "ngay_su_kien")
    private LocalDateTime ngaySuKien;

    @ManyToOne
    @JoinColumn(name = "nguoi_thuc_hien")
    private NguoiDung nguoiThucHien;

    @ManyToOne
    @JoinColumn(name = "tu_don_vi")
    private DonVi tuDonVi;

    @ManyToOne
    @JoinColumn(name = "den_don_vi")
    private DonVi denDonVi;

    @ManyToOne
    @JoinColumn(name = "tu_nguoi_dung")
    private NguoiDung tuNguoiDung;

    @ManyToOne
    @JoinColumn(name = "den_nguoi_dung")
    private NguoiDung denNguoiDung;

    @ManyToOne
    @JoinColumn(name = "tu_kho")
    private Kho tuKho;

    @ManyToOne
    @JoinColumn(name = "den_kho")
    private Kho denKho;

    @Column(name = "so_phieu")
    private String soPhieu;

    @Column(name = "chi_phi")
    private BigDecimal chiPhi;

    @Column(name = "mo_ta")
    private String moTa;

}
