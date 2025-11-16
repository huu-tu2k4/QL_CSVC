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
@Table(name = "nha_cung_cap")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class NhaCungCap {

    @Id
    @Column(name = "ma_ncc")
    private String maNCC;

    @Column(name = "ten_ncc", nullable = false)
    private String tenNcc;

    @Column(name = "nguoi_lien_he")
    private String nguoiLienHe;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "dia_chi")
    private String diaChi;
}
