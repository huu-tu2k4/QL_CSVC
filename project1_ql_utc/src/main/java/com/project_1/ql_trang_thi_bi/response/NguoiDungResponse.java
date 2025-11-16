package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.dtos.NguoiDungDTO;
import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.VaiTro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguoiDungResponse {

    private String maNguoiDung;
    private String tenDangNhap;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private VaiTro maVaiTro;

    private Boolean trangThai;
    private LocalDateTime ngayTao;

    public static NguoiDungResponse fromNguoiDung(NguoiDung nd) {
        return  NguoiDungResponse.builder()
                .maNguoiDung(nd.getMaNguoiDung())
                .tenDangNhap(nd.getTenDangNhap())
                .hoTen(nd.getHoTen())
                .email(nd.getEmail())
                .soDienThoai(nd.getSoDienThoai())
                .maVaiTro(nd.getVaiTro())
                .trangThai(nd.getTrangThai())
                .ngayTao(nd.getNgayTao())
                .build();
    }

}
