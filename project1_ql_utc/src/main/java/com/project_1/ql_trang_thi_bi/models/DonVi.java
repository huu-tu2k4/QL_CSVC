package com.project_1.ql_trang_thi_bi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "don_vi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class DonVi {

    @Id
    @Column(name = "ma_don_vi")
    private String maDonVi;

    @Column(name = "ma_code", unique = true, nullable = false)
    private String maCode;

    @Column(name = "ten_don_vi", nullable = false)
    private String tenDonVi;

//    @ManyToOne
//    @JoinColumn(name = "ma_don_vi_cha")
//    private DonVi donViCha;

    @Column(name = "mo_ta")
    private String moTa;

}
