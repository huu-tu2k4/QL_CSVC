package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.PhongHocDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.PhongHoc;

import java.util.List;

public interface IPhongHocService {


    List<PhongHoc> getAllPhong();
    PhongHoc createPhong(PhongHocDTO phongDTO);
    PhongHoc getPhongById(String maPhong) throws DataNotFoundException;
    PhongHoc updatePhong(String maPhong, PhongHocDTO phongDTO) throws DataNotFoundException;
    void deletePhong(String maPhong) throws DataNotFoundException;
}
