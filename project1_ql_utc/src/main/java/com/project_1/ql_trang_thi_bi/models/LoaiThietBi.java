package com.project_1.ql_trang_thi_bi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loai_thiet_bi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class LoaiThietBi {

    @Id
    @Column(name = "ma_loai")
    private String maLoai;


    @Column(name = "ma_code", unique = true, nullable = false)
    private String maCode;

    @Column(name = "ten_loai", nullable = false)
    private String tenLoai;

    @Column(name = "mo_ta")
    private String moTa;
}
