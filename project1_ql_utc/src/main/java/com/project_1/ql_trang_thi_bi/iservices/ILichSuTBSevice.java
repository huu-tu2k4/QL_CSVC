package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.LichSuThietBiDTO;
import com.project_1.ql_trang_thi_bi.models.LichSuThietBi;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ILichSuTBSevice {
    LichSuThietBi create(LichSuThietBiDTO lichSuThietBiDTO);
    List<LichSuThietBi> getAll();
    LichSuThietBi getById(String maLS) throws DataAccessException;

}
