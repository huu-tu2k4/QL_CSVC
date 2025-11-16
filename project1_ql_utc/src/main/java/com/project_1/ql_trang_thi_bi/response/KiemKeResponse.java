package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.models.PhieuKiemKe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KiemKeResponse {
    private String maKK;
    private String soPhieu;
    private String tenDonVi;
    private String nguoiKiemKe;
    private LocalDateTime ngayKiemKe;
    private int tongTB;
    private int soTot;
    private int soThieu;
    private int soHong;
    private int soChuyenViTri;

    public static KiemKeResponse fromKiemKe(PhieuKiemKe phieuKiemKe){
        int tot = 0, thieu = 0, hong = 0, chuyen = 0;
        if(phieuKiemKe.getChiTietKiemKeList() != null){
            for(var ct : phieuKiemKe.getChiTietKiemKeList()){
                switch (ct.getTrangThaiKiemKe()){
                    case TOT -> tot++;
                    case THIEU -> thieu++;
                    case HONG -> hong++;
                    case CHUYEN_VI_TRI -> chuyen++;
                }
            }
        }

        return KiemKeResponse.builder()
                .maKK(phieuKiemKe.getMaPhieuKK())
                .soPhieu(phieuKiemKe.getSoPhieuKk())
                .tenDonVi(phieuKiemKe.getDonVi().getTenDonVi())
                .nguoiKiemKe(phieuKiemKe.getNguoiKiemKe().getHoTen())
                .ngayKiemKe(phieuKiemKe.getNgayKiemKe())
                .tongTB(phieuKiemKe.getChiTietKiemKeList() != null ? phieuKiemKe.getChiTietKiemKeList().size() : 0)
                .soTot(tot)
                .soThieu(thieu)
                .soHong(hong)
                .soChuyenViTri(chuyen)
                .build();
    }

}
