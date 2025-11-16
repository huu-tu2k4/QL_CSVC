package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThietBiDTO {

    @JsonProperty("ma_thiet_bi")
    private String maThietBi;

    @JsonProperty("ten_thiet_bi")
    private String tenThietBi;

    @JsonProperty("ma_loai")
    private String maLoai;

//    @JsonProperty("ten_loai")
//    private String tenLoai;

    @JsonProperty("so_serial")
    private String soSerial;

    @JsonProperty("model")
    private String model;

    @JsonProperty("hang_sx")
    private String hangSx;

    @JsonProperty("ma_ncc")
    private String maNcc;

    @JsonProperty("ten_ncc")
    private String tenNcc;

    @JsonProperty("ma_kho")
    private String maKho;

    @JsonProperty("ten_kho")
    private String tenKho;

    @JsonProperty("ngay_mua")
    private LocalDateTime ngayMua;

    @JsonProperty("gia_mua")
    private BigDecimal giaMua;

    @JsonProperty("bao_hanh_den")
    private LocalDateTime baoHanhDen;

    @JsonProperty("vi_tri")
    private String viTri;

    @JsonProperty("ma_phong")
    private String maPhong;

    @JsonProperty("ten_phong")
    private String tenPhong;

    @JsonProperty("ma_don_vi_so_huu")
    private String maDonViSoHuu;

    @JsonProperty("ten_don_vi_so_huu")
    private String tenDonViSoHuu;

    @JsonProperty("ma_nguoi_su_dung")
    private String maNguoiSuDung;

    @JsonProperty("ten_nguoi_su_dung")
    private String tenNguoiSuDung;

    @JsonProperty("trang_thai")
    private TrangThaiThietBi trangThai;

    @JsonProperty("tinh_trang")
    private String tinhTrang;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("ngay_tao")
    private LocalDateTime ngayTao;

    @JsonProperty("ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;
}
