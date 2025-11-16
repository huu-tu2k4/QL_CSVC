package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NhaCungCapDTO {
    @JsonProperty("ma_ncc")
    private String maNCC;

    @JsonProperty("ten_ncc")
    private String tenNcc;

    @JsonProperty("nguoi_lien_he")
    private String nguoiLienHe;

    @JsonProperty("so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("email")
    private String email;

    @JsonProperty("dia_chi")
    private String diaChi;
}
