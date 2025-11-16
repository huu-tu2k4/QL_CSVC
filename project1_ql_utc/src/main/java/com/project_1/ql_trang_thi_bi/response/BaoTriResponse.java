package com.project_1.ql_trang_thi_bi.response;
import com.project_1.ql_trang_thi_bi.enums.MucUuTien;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiBaoTri;
import com.project_1.ql_trang_thi_bi.models.PhieuBaoTri;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaoTriResponse {

    private String maPhieuBT;
    private String soPhieuBt;
    private ThietBiResponse thietBi;
    private DonViResponse donViDeXuat;
    private LocalDateTime ngayDeXuat;
    private String moTaSuCo;
    private MucUuTien mucUuTien;
    private NguoiDungResponse nguoiPhuTrach;
    private LocalDateTime ngayHoanTat;
    private TrangThaiBaoTri trangThai;
    private BigDecimal chiPhi;
    private String ghiChu;

    public static BaoTriResponse fromPhieuBaoTri(PhieuBaoTri phieu) {
        return BaoTriResponse.builder()
                .maPhieuBT(phieu.getMaPhieuBT())
                .soPhieuBt(phieu.getSoPhieuBt())
                .thietBi(ThietBiResponse.fromThietBi(phieu.getThietBi()))
                .donViDeXuat(DonViResponse.fromDonVi(phieu.getDonViDeXuat()))
                .ngayDeXuat(phieu.getNgayDeXuat())
                .moTaSuCo(phieu.getMoTaSuCo())
                .nguoiPhuTrach(NguoiDungResponse.fromNguoiDung(phieu.getNguoiPhuTrach()))
                .ngayHoanTat(phieu.getNgayHoanTat())
                .mucUuTien(phieu.getMucUuTien())
                .trangThai(phieu.getTrangThai())
                .chiPhi(phieu.getChiPhi())
                .ghiChu(phieu.getGhiChu())
                .build();
    }
}
