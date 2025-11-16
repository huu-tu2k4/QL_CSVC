package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.enums.TrangThaiKiemKe;
import com.project_1.ql_trang_thi_bi.models.ChiTietKiemKe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietKiemKeResponse {
    private String maCTKK;
    private String soPhieuKK;
    private String tenThietBi;
    private TrangThaiKiemKe trangThaiKiemKe;
    private String ghiChu;

    public static ChiTietKiemKeResponse fromChiTietKK(ChiTietKiemKe chiTietKiemKe){
        return ChiTietKiemKeResponse.builder()
                .maCTKK(chiTietKiemKe.getMaCTKK())
                .soPhieuKK(chiTietKiemKe.getPhieuKiemKe() != null ? chiTietKiemKe.getPhieuKiemKe().getSoPhieuKk() : null)
                .tenThietBi(chiTietKiemKe.getThietBi() != null ? chiTietKiemKe.getThietBi().getTenThietBi(): null)
                .trangThaiKiemKe(chiTietKiemKe.getTrangThaiKiemKe())
                .ghiChu(chiTietKiemKe.getGhiChu())

                .build();
    }
}
