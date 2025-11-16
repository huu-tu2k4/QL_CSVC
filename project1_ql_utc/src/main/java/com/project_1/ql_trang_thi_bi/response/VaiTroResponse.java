package com.project_1.ql_trang_thi_bi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaiTroResponse {
  private String maVaiTro;
  private String tenVaiTro;
  private String moTa;
}
