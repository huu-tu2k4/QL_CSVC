package com.project_1.ql_trang_thi_bi.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.project_1.ql_trang_thi_bi.enums.MucUuTien;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiBaoTri;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhieuBaoTriDTO {

    @JsonProperty("ma_phieu_bt")
    private String maPhieuBT;

    @JsonProperty("so_phieu_bt")
    private String soPhieuBt;

    @JsonProperty("ma_thiet_bi")
    private String maThietBi;

//    @JsonProperty("ten_thiet_bi")
//    private String tenThietBi; // giúp hiển thị dễ hơn khi trả về API

//    @JsonProperty("nguoi_de_xuat")
//    private String nguoiDeXuat;

    @JsonProperty("ma_don_vi_de_xuat")
    private String maDonViDeXuat;

//    @JsonProperty("ten_don_vi_de_xuat")
//    private String tenDonViDeXuat;

    @JsonProperty("ngay_de_xuat")
    private LocalDateTime ngayDeXuat;

    @JsonProperty("mo_ta_su_co")
    private String moTaSuCo;

    @JsonProperty("muc_uu_tien")
    private MucUuTien mucUuTien;

    @JsonProperty("trang_thai")
    private TrangThaiBaoTri trangThai;

    @JsonProperty("nguoi_phu_trach")
    private String nguoiPhuTrach;

    @JsonProperty("ngay_hoan_tat")
    private LocalDateTime ngayHoanTat;

    @JsonProperty("chi_phi")
    private BigDecimal chiPhi;

    @JsonProperty("ghi_chu")
    private String ghiChu;
}
