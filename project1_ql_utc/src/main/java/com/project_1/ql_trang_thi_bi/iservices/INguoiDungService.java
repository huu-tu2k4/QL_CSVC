package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.NguoiDungDTO;
import com.project_1.ql_trang_thi_bi.response.NguoiDungResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INguoiDungService {

    NguoiDungResponse createNguoiDung(NguoiDungDTO dto);

    NguoiDungResponse updateNguoiDung(String maNguoiDung, NguoiDungDTO dto);

    void deleteNguoiDung(String maNguoiDung);

    NguoiDungResponse getNguoiDungById(String maNguoiDung);

    Page<NguoiDungResponse> getAllNguoiDung(Pageable pageable);

    NguoiDungResponse getMyInfo();
}
