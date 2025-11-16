package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiLichSu;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LichSuThietBiDTO {

    @JsonProperty("ma_lich_su")
    private String maLichSu;

    @JsonProperty("ma_thiet_bi")
    private String maThietBi;

//    @JsonProperty("ten_thiet_bi")
//    private String tenThietBi;

    @JsonProperty("loai_su_kien")
    private TrangThaiLichSu loaiSuKien;

    @JsonProperty("ngay_su_kien")
    private LocalDateTime ngaySuKien;

    @JsonProperty("nguoi_thuc_hien")
    private String nguoiThucHien; // mã người thực hiện

    @JsonProperty("tu_don_vi")
    private String tuDonVi;

//    @JsonProperty("ten_tu_don_vi")
//    private String tenTuDonVi;

    @JsonProperty("den_don_vi")
    private String denDonVi;

//    @JsonProperty("ten_den_don_vi")
//    private String tenDenDonVi;

    @JsonProperty("tu_nguoi_dung")
    private String tuNguoiDung;

//    @JsonProperty("ten_tu_nguoi_dung")
//    private String tenTuNguoiDung;

    @JsonProperty("den_nguoi_dung")
    private String denNguoiDung;

//    @JsonProperty("ten_den_nguoi_dung")
//    private String tenDenNguoiDung;

    @JsonProperty("tu_kho")
    private String tuKho;

//    @JsonProperty("ten_tu_kho")
//    private String tenTuKho;

    @JsonProperty("den_kho")
    private String denKho;

//    @JsonProperty("ten_den_kho")
//    private String tenDenKho;

    @JsonProperty("so_phieu")
    private String soPhieu;

    @JsonProperty("chi_phi")
    private BigDecimal chiPhi;

    @JsonProperty("mo_ta")
    private String moTa;
}
