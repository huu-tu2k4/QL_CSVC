package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhongHocDTO {

    @JsonProperty("ma_phong")
    private String maPhong;

    @JsonProperty("ma_code")
    private String maCode;

    @JsonProperty("ten_phong")
    private String tenPhong;

    @JsonProperty("loai_phong")
    private String loaiPhong;

    @JsonProperty("toa_nha")
    private String toaNha;

    @JsonProperty("tang")
    private Integer tang;

    @JsonProperty("suc_chua")
    private Integer sucChua;

    @JsonProperty("ma_don_vi")
    private String maDonVi;

//    @JsonProperty("ten_don_vi")
//    private String tenDonVi;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("thiet_bis")
    private List<ThietBiDTO> thietBis;
}
