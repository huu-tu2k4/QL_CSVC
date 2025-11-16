package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhieuMuaSamDTO {

    @JsonProperty("ma_phieu")
    private String maPhieu;

    @JsonProperty("so_phieu")
    private String soPhieu;

    @JsonProperty("ma_ncc")
    private String maNCC;

    @JsonProperty("ten_ncc")
    private String tenNCC;

    @JsonProperty("nguoi_tao")
    private String nguoiTao;

//    @JsonProperty("ten_nguoi_tao")
//    private String tenNguoiTao;

    @JsonProperty("ngay_tao")
    private LocalDateTime ngayTao;

    @JsonProperty("tong_tien")
    private BigDecimal tongTien;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("chi_tiet_mua_sam")
    private List<ChiTietMuaSamDTO> chiTietMuaSamList;
}
