package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.models.PhieuMuaSam;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PhieuMuaSamResponse {

    private String maPhieu;
    private String soPhieu;
    private String nhaCungCap;
    private String nguoiTao;
    private LocalDateTime ngayTao;
    private BigDecimal tongTien;
    private String ghiChu;
    private List<ChiTietResponse> chiTietList;

    @Data
    @Builder
    public static class ChiTietResponse {
        private String maChiTiet;
        private String tenThietBi;
        private String maTb;
        private String loaiThietBi;
        private Integer soLuong;
        private BigDecimal donGia;
        private BigDecimal thanhTien;
        private String khoNhap;
        private Integer thoiGianBaoHanh;
        private String ghiChu;
    }


    public static PhieuMuaSamResponse fromMuaSam(PhieuMuaSam p) {
        return PhieuMuaSamResponse.builder()
                .maPhieu(p.getMaPhieu())
                .soPhieu(p.getSoPhieu())
                .nhaCungCap(p.getNhaCungCap() != null ? p.getNhaCungCap().getTenNcc() : null)
                .nguoiTao(p.getNguoiTao() != null ? p.getNguoiTao().getHoTen() : null)
                .ngayTao(p.getNgayTao())
                .tongTien(p.getTongTien())
                .ghiChu(p.getGhiChu())
                .chiTietList(
                        p.getChiTietMuaSamList() == null ? List.of() :
                                p.getChiTietMuaSamList().stream().map(ct ->
                                        ChiTietResponse.builder()
                                                .maChiTiet(ct.getMaCT())
                                                .tenThietBi(ct.getTenThietBi())
                                                .maTb(ct.getMaTb())
                                                .loaiThietBi(ct.getLoaiThietBi() != null ? ct.getLoaiThietBi().getTenLoai() : null)
                                                .soLuong(ct.getSoLuong())
                                                .donGia(ct.getDonGia())
                                                .thanhTien(ct.getThanhTien())
                                                .khoNhap(ct.getKhoNhap() != null ? ct.getKhoNhap().getTenKho() : null)
                                                .thoiGianBaoHanh(ct.getThoiGianBaoHanh())
                                                .ghiChu(ct.getGhiChu())
                                                .build()
                                ).toList()
                )
                .build();
    }
}
