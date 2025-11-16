package com.project_1.ql_trang_thi_bi.iservices;


import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.ChiTietKiemKe;
import com.project_1.ql_trang_thi_bi.models.PhieuKiemKe;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IKiemKeService {

    List<PhieuKiemKe> getAll();
    PhieuKiemKe getById(String maKK) throws DataAccessException;
    List<ChiTietKiemKe> getAllChiTiet();
    PhieuKiemKe create(PhieuKiemKe phieuKiemKe) throws DataNotFoundException;
    PhieuKiemKe update(String maKK, PhieuKiemKe phieuKiemKe) throws  DataNotFoundException;


}
