package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.models.DonVi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonViResponse {

    private String maDonVi;
    private String maCode;
    private String tenDonVi;
    private String moTa;
    private int soLuongTB;
    public static DonViResponse fromDonVi(DonVi donVi) {
        if (donVi == null) return null;

        return DonViResponse.builder()
                .maDonVi(donVi.getMaDonVi())
                .maCode(donVi.getMaCode())
                .tenDonVi(donVi.getTenDonVi())
                .moTa(donVi.getMoTa())
                .build();
    }
}
