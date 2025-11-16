package com.project_1.ql_trang_thi_bi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
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
@Table(name = "thiet_bi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ThietBi {

    @Id
    @Column(name = "ma_thiet_bi")
    private String maThietBi;

    @Column(name = "ten_thiet_bi", nullable = false)
    private String tenThietBi;

    @ManyToOne
    @JoinColumn(name = "ma_loai")
    private LoaiThietBi loaiThietBi;

    @Column(name = "so_serial")
    private String soSerial;

    @Column(name = "model")
    private String model;

    @Column(name = "hang_sx")
    private String hangSx;

    @ManyToOne
    @JoinColumn(name = "ma_ncc")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "ma_kho")
    private Kho kho;

    @Column(name = "ngay_mua")
    private LocalDateTime ngayMua;

    @Column(name = "gia_mua")
    private BigDecimal giaMua;

    @Column(name = "bao_hanh_den")
    private LocalDateTime baoHanhDen;

    @Column(name = "vi_tri")
    private String viTri;

    @ManyToOne
    @JoinColumn(name = "ma_phong")
    private PhongHoc phongHoc;

    @ManyToOne
    @JoinColumn(name = "ma_don_vi_so_huu")
    private DonVi donViSoHuu;

    @ManyToOne
    @JoinColumn(name = "ma_nguoi_su_dung")
    private NguoiDung nguoiSuDung;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    private TrangThaiThietBi trangThai;

    @Column(name = "tinh_trang")
    private String tinhTrang;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;
}
