package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.VaiTroDTO;

import java.util.List;

public interface IVaiTroService {

    List<VaiTroDTO> getAllRoles();

    // dùng maVaiTro làm khóa chính
    VaiTroDTO getRoleByMaVaiTro(String maVaiTro);

    VaiTroDTO createRole(VaiTroDTO dto);

    VaiTroDTO updateRole(String maVaiTro, VaiTroDTO dto);

    void deleteRole(String maVaiTro);
}
