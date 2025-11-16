package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietMuaSamDTO {


    @JsonProperty("ma_phieu_mua")
    private String maMua;

    @JsonProperty("ten_thiet_bi")
    private String tenThietBi;

    @JsonProperty("ma_tb")
    private String maTb;

    @JsonProperty("ma_loai")
    private String maLoai;

    @JsonProperty("ten_loai")
    private String tenLoai;

    @JsonProperty("so_luong")
    private Integer soLuong;

    @JsonProperty("don_gia")
    private BigDecimal donGia;

    @JsonProperty("thoi_gian_bao_hanh")
    private Integer thoiGianBaoHanh;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("ma_kho_nhap")
    private String maKhoNhap;

    @JsonProperty("ten_kho_nhap")
    private String tenKhoNhap;

    @JsonProperty("thanh_tien")
    private BigDecimal thanhTien;




}
