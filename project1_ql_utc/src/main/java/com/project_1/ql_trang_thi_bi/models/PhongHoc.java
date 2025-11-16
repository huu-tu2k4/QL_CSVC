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
@Table(name = "phong_hoc")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhongHoc {


    @Id
    @Column(name = "ma_phong")
    private String maPhong;


    @Column(name = "ma_code", unique = true, nullable = false)
    private String maCode;

    @Column(name = "ten_phong", nullable = false)
    private String tenPhong;

    @Column(name = "loai_phong")
    private String loaiPhong;

    @Column(name = "toa_nha")
    private String toaNha;

    @Column(name = "tang")
    private Integer tang;

    @Column(name = "suc_chua")
    private Integer sucChua;

    @OneToMany(mappedBy = "phongHoc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ThietBi> thietBis;

    @ManyToOne
    @JoinColumn(name = "ma_don_vi")
    private DonVi donVi;

    @Column(name = "ghi_chu")
    private String ghiChu;
}

