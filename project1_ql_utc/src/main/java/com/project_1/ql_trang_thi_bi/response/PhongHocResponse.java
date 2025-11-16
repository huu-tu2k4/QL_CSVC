package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.models.PhongHoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhongHocResponse {

    private String maPhong;
    private String maCode;
    private String tenPhong;
    private String loaiPhong;
    private Integer sucChua;
    private Integer tang;
    private String toaNha;
    private String maDonVi;
    private String ghiChu;

    private List<ThietBiResponse> thietBis;

    public static PhongHocResponse fromPhong(PhongHoc phongHoc){
        return PhongHocResponse.builder()
                .maPhong(phongHoc.getMaPhong())
                .maCode(phongHoc.getMaCode())
                .tenPhong(phongHoc.getTenPhong())
                .loaiPhong(phongHoc.getLoaiPhong())
                .sucChua(phongHoc.getSucChua())
                .tang(phongHoc.getTang())
                .toaNha(phongHoc.getToaNha())
                .maDonVi(phongHoc.getDonVi().getTenDonVi())
                .ghiChu(phongHoc.getGhiChu())
                .thietBis(
                        phongHoc.getThietBis() != null
                        ? phongHoc.getThietBis().stream()
                                .map(ThietBiResponse::fromThietBi)
                                .collect(Collectors.toList())
                                : null
                )
                .build();
    }

}
