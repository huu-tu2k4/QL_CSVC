package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiKiemKe;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietKiemKeDTO {

    @JsonProperty("ma_ctkk")
    private String maCTKK;

    @JsonProperty("ma_phieu_kk")
    private String phieuKiemKe;

    @JsonProperty("ma_thiet_bi")
    private String thietBi;

    @JsonProperty("tinh_trang_kiem_ke")
    private TrangThaiKiemKe tinhTrang;

    @JsonProperty("ghi_chu")
    private String ghiChu;



}
