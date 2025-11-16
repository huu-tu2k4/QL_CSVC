package com.project_1.ql_trang_thi_bi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phieu_kiem_ke")
public class PhieuKiemKe {

    @Id
    @Column(name = "ma_phieu_kk")
    private String maPhieuKK;


    @Column(name = "so_phieu_kk", unique = true, nullable = false)
    private String soPhieuKk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_don_vi", nullable = false)
    private DonVi donVi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_kiem_ke", nullable = false)
    private NguoiDung nguoiKiemKe;

    @Column(name = "ngay_kiem_ke")
    private LocalDateTime ngayKiemKe;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @OneToMany(mappedBy = "phieuKiemKe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietKiemKe> chiTietKiemKeList;

}
