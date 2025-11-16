package com.project_1.ql_trang_thi_bi.response;

import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThietBiResponse {


    private String maThietBi;


    private String tenThietBi;

    private String tenLoaiThietBi;

    private String tenPhongHoc;

    private String viTri;

    private String tinhTrang;


    private TrangThaiThietBi trangThai;


    private String ghiChu;


    public  static ThietBiResponse fromThietBi(ThietBi thietBi){
        return ThietBiResponse.builder()
                .maThietBi(thietBi.getMaThietBi())
                .tenThietBi(thietBi.getTenThietBi())
                .tenLoaiThietBi(thietBi.getLoaiThietBi() != null ? thietBi.getLoaiThietBi().getTenLoai() : null)
                .tenPhongHoc(thietBi.getPhongHoc() != null ? thietBi.getPhongHoc().getTenPhong(): null)
                .viTri(thietBi.getViTri())
                .tinhTrang(thietBi.getTinhTrang())
                .trangThai(thietBi.getTrangThai())
                .ghiChu(thietBi.getGhiChu())
                .build();
    }
}
