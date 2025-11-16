package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.ThietBiDTO;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import com.project_1.ql_trang_thi_bi.response.ThietBiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IThietBiService {
        ThietBi createThietBi(ThietBiDTO thietBiDTO);
        ThietBi updateThietBi(String maThietBi, ThietBiDTO thietBiDTO) throws DataNotFoundException;
        void deleteThietBi(String maThietBi) throws DataNotFoundException;
        com.project_1.ql_trang_thi_bi.response.ThietBiResponse getThietBiById(String maThietBi) throws DataNotFoundException;
        Page<ThietBiResponse> getAllThietBi(String keyword, TrangThaiThietBi trangThaiThietBi, Pageable pageable);

}
