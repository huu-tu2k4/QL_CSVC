package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonViDTO {

    @JsonProperty("ma_don_vi")
    private String maDonVi;

    @JsonProperty("ma_code")
    private String maCode;

    @JsonProperty("ten_don_vi")
    private String tenDonVi;

    @JsonProperty("mo_ta")
    private String moTa;
}
