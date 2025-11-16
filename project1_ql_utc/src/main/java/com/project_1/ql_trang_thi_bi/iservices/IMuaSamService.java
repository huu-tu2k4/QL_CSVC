package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.PhieuMuaSam;
import com.project_1.ql_trang_thi_bi.response.PhieuMuaSamResponse;

import java.util.List;

public interface IMuaSamService {
    PhieuMuaSam create(PhieuMuaSam phieuMuaSam) throws DataNotFoundException;
    List<PhieuMuaSam> getAll();
    PhieuMuaSam getByID(String maMua) throws DataNotFoundException;
}
