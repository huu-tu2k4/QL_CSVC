package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaiTroDTO {

    @JsonProperty("maVaiTro")
    private String maVaiTro;

    @JsonProperty("tenVaiTro")
    private String tenVaiTro;

    @JsonProperty("moTa")
    private String moTa;
    @JsonProperty("permissionIds")
    private Set<Long> permissionIds;
}
