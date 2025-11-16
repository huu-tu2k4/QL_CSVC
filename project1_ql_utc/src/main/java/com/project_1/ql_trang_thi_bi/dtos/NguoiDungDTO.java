package com.project_1.ql_trang_thi_bi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO {

    @JsonProperty("ten_dang_nhap")
    private String tenDangNhap;

    @JsonProperty("mat_khau")
    private String matKhau;

    @JsonProperty("ho_ten")
    private String hoTen;

    @JsonProperty("email")
    private String email;

    @JsonProperty("so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("ma_vai_tro")
    private String maVaiTro;   // ánh xạ VaiTro (nếu chỉ cần ID)

    @JsonProperty("trang_thai")
    private Boolean trangThai;

    @JsonProperty("ngay_tao")
    private LocalDateTime ngayTao;

    @JsonProperty("ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;
}
