package com.project_1.ql_trang_thi_bi.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhieuThanhLyDTO {

    @JsonProperty("ma_phieu_tl")
    private String maPhieuTL;

    @JsonProperty("so_phieu_tl")
    private String soPhieuTl;

    @JsonProperty("ma_thiet_bi")
    private String maThietBi;

    @JsonProperty("ten_thiet_bi")
    private String tenThietBi;

    @JsonProperty("ly_do")
    private String lyDo;

    @JsonProperty("ngay_thanh_ly")
    private LocalDateTime ngayThanhLy;

    @JsonProperty("nguoi_duyet")
    private String nguoiDuyet;

//    @JsonProperty("ten_nguoi_duyet")
//    private String tenNguoiDuyet;

    @JsonProperty("ngay_duyet")
    private LocalDateTime ngayDuyet;

    @JsonProperty("gia_tri_goc")
    private BigDecimal giaTriGoc;

    @JsonProperty("gia_tri_thanh_ly")
    private BigDecimal giaTriThanhLy;

    @JsonProperty("ghi_chu")
    private String ghiChu;
}
