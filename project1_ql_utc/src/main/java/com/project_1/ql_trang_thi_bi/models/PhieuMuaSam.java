package com.project_1.ql_trang_thi_bi.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phieu_mua_sam")
public class PhieuMuaSam {

    @Id
    @Column(name = "ma_phieu")
    private String maPhieu;

    @Column(name = "so_phieu", unique = true, nullable = false)
    private String soPhieu;

    @ManyToOne
    @JoinColumn(name = "ma_ncc")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "nguoi_tao")
    private NguoiDung nguoiTao;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @OneToMany(mappedBy = "phieuMuaSam", cascade = CascadeType.ALL)
    private List<ChiTietMuaSam> chiTietMuaSamList;
}
