package com.project_1.ql_trang_thi_bi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kho")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Kho {

    @Id
    @Column(name = "ma_kho", length = 50)
    private String maKho;

    @Column(name = "ma_code", unique = true, nullable = false, length = 50)
    private String maCode;

    @Column(name = "ten_kho", nullable = false, length = 100)
    private String tenKho;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_quan_ly")
    private NguoiDung nguoiQuanLy;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    // --- Liên kết với bảng Thiết bị ---
    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ThietBi> danhSachThietBi;
}
