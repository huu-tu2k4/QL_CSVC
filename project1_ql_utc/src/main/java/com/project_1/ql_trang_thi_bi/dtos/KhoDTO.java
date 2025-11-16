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
public class KhoDTO {

    @JsonProperty("ma_kho")
    private String maKho;

    @JsonProperty("ma_code")
    private String maCode;

    @JsonProperty("ten_kho")
    private String tenKho;

    @JsonProperty("dia_chi")
    private String diaChi;

    @JsonProperty("nguoi_quan_ly")
    private String nguoiQuanLy; // Mã hoặc ID người quản lý


    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("danh_sach_thiet_bi")
    private List<ThietBiDTO> danhSachThietBi; // Liên kết tới DTO của thiết bị
}
