package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.models.PhieuThanhLy;
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
public class ThanhLyResponse {


    private String maPhieuTL;
    private String soPhieuTL;
    private String thietBi;
    private String lyDo;
    private LocalDateTime ngayThanhLy;
    private String nguoiDuyet;
    private LocalDateTime ngayDuyet;
    private BigDecimal giaTriGoc;
    private BigDecimal giaTriThanhLy;
    private String ghiChu;

    public static ThanhLyResponse fromThanhLy(PhieuThanhLy phieuThanhLy){
        return ThanhLyResponse.builder()
                .maPhieuTL(phieuThanhLy.getMaPhieuTL())
                .soPhieuTL(phieuThanhLy.getSoPhieuTl())
                .thietBi(phieuThanhLy.getThietBi().getTenThietBi())
                .lyDo(phieuThanhLy.getLyDo())
                .ngayThanhLy(phieuThanhLy.getNgayThanhLy())
                .nguoiDuyet(phieuThanhLy.getNguoiDuyet().getHoTen())
                .ngayDuyet(phieuThanhLy.getNgayDuyet())
                .giaTriGoc(phieuThanhLy.getGiaTriGoc())
                .giaTriThanhLy(phieuThanhLy.getGiaTriThanhLy())
                .ghiChu(phieuThanhLy.getGhiChu())

                .build();
    }
}
