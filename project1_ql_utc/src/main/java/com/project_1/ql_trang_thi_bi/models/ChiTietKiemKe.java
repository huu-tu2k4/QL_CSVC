package com.project_1.ql_trang_thi_bi.models;


import com.project_1.ql_trang_thi_bi.enums.TrangThaiKiemKe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chi_tiet_kiem_ke")
public class ChiTietKiemKe {

     @Id
     @Column(name = "ma_ctkk")
      private String maCTKK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_phieu_kk", nullable = false)
    private PhieuKiemKe phieuKiemKe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_thiet_bi", nullable = false)
    private ThietBi thietBi;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_kiem_ke", nullable = false)
    private TrangThaiKiemKe trangThaiKiemKe;

    @Column(name = "ghi_chu")
    private String ghiChu;
}
