package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.enums.TrangThaiLichSu;
import com.project_1.ql_trang_thi_bi.models.LichSuThietBi;
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
public class LichSuTBResponse {

    private String maLichSu;
    private String thietBi;
    private TrangThaiLichSu loaiSuKien;
    private LocalDateTime ngaySuKien;
    private String nguoiThucHien;
    private String tuDonVi;
    private String denDonVi;
    private String tuNguoiDung;
    private String denNguoiDung;
    private String tuKho;
    private String denKho;
    private String soPhieu;
    private BigDecimal chiPhi;
    private String moTa;

    public static LichSuTBResponse fromLichSu(LichSuThietBi lichSuThietBi) {
        return LichSuTBResponse.builder()
                .maLichSu(lichSuThietBi.getMaLichSu())
                .thietBi(lichSuThietBi.getThietBi() != null
                        ? lichSuThietBi.getThietBi().getTenThietBi()
                        : "Không xác định")
                .loaiSuKien(lichSuThietBi.getLoaiSuKien())
                .ngaySuKien(lichSuThietBi.getNgaySuKien())

                // Người thực hiện (admin, nhân viên hệ thống)
                .nguoiThucHien(lichSuThietBi.getNguoiThucHien() != null
                        ? lichSuThietBi.getNguoiThucHien().getHoTen()
                        : "Hệ thống")

                // Từ đơn vị
                .tuDonVi(lichSuThietBi.getTuDonVi() != null
                        ? lichSuThietBi.getTuDonVi().getTenDonVi()
                        : null)

                // Đến đơn vị
                .denDonVi(lichSuThietBi.getDenDonVi() != null
                        ? lichSuThietBi.getDenDonVi().getTenDonVi()
                        : null)

                // Từ người dùng (người đang dùng thiết bị trước đó) – có thể null
                .tuNguoiDung(lichSuThietBi.getTuNguoiDung() != null
                        ? lichSuThietBi.getTuNguoiDung().getHoTen()
                        : null)

                // Đến người dùng (người nhận mới) – có thể null
                .denNguoiDung(lichSuThietBi.getDenNguoiDung() != null
                        ? lichSuThietBi.getDenNguoiDung().getHoTen()
                        : null)

                // Từ kho
                .tuKho(lichSuThietBi.getTuKho() != null
                        ? lichSuThietBi.getTuKho().getTenKho()
                        : null)

                // Đến kho
                .denKho(lichSuThietBi.getDenKho() != null
                        ? lichSuThietBi.getDenKho().getTenKho()
                        : null)

                .soPhieu(lichSuThietBi.getSoPhieu())
                .chiPhi(lichSuThietBi.getChiPhi())
                .moTa(lichSuThietBi.getMoTa())
                .build();
    }
}