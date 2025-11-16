package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.DonViDTO;
import com.project_1.ql_trang_thi_bi.models.DonVi;
import com.project_1.ql_trang_thi_bi.response.DonViResponse;

import java.util.List;

public interface IDonViService {


    DonViResponse create(DonViDTO dto);


    DonViResponse update(String maDonVi, DonViDTO dto);


    void delete(String maDonVi);


    DonViResponse getById(String maDonVi);


    List<DonViResponse> getAll();
}
