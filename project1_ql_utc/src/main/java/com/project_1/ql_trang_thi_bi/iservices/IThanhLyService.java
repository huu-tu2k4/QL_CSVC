package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.PhieuBaoTriDTO;
import com.project_1.ql_trang_thi_bi.dtos.PhieuThanhLyDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.PhieuThanhLy;

import java.util.List;

public interface IThanhLyService {
    PhieuThanhLy create(PhieuThanhLyDTO phieuThanhLyDTO);
    List<PhieuThanhLy> getAll();
    PhieuThanhLy getByID(String maTL) throws DataNotFoundException;

}
