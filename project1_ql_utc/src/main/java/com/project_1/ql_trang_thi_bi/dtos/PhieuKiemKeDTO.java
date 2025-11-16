package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhieuKiemKeDTO {
    @JsonProperty("ma_phieu_kk")
    private String maPhieuKK;

    @JsonProperty("so_phieu_kk")
    private String soPhieuKk;

    @JsonProperty("ma_don_vi")
    private String maDonVi;

//    @JsonProperty("ten_don_vi")
//    private String tenDonVi;

    @JsonProperty("nguoi_kiem_ke")
    private String nguoiKiemKe;

//    @JsonProperty("ten_nguoi_kiem_ke")
//    private String tenNguoiKiemKe;

    @JsonProperty("ngay_kiem_ke")
    private LocalDateTime ngayKiemKe;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("chi_tiet_kiem_ke")
    private List<ChiTietKiemKeDTO> chiTietKiemKeList;
}
