package com.project_1.ql_trang_thi_bi.iservices;

import com.project_1.ql_trang_thi_bi.dtos.PermissionDTO;

import java.util.List;

public interface IPermissionService {
    List<PermissionDTO> getAllPermissions();
    PermissionDTO getPermissionById(Long id);
    PermissionDTO createPermission(PermissionDTO dto);
    PermissionDTO updatePermission(Long id, PermissionDTO dto);
    void deletePermission(Long id);
}
