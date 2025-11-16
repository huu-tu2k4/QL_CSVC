package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.PhieuBaoTriDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.PhieuBaoTri;
import com.project_1.ql_trang_thi_bi.response.BaoTriResponse;

import java.util.List;

public interface IPhieuBaoTriService {
    List<BaoTriResponse> getAll();
    BaoTriResponse createBaoTri(PhieuBaoTriDTO phieuBaoTriDTO) throws DataNotFoundException;
    BaoTriResponse updateBaoTri(String maBT, PhieuBaoTriDTO phieuBaoTriDTO) throws  DataNotFoundException;
    BaoTriResponse getBaoTriByID(String maBT) throws DataNotFoundException;
}
