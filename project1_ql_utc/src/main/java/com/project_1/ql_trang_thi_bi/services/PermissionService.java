package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.PermissionDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.models.Permission;
import com.project_1.ql_trang_thi_bi.iservices.IPermissionService;
import com.project_1.ql_trang_thi_bi.repositorys.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {

    private final PermissionRepository repository;

    @Override
    public List<PermissionDTO> getAllPermissions() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDTO getPermissionById(Long id) {
        Permission permission = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Permission not found"));
        return toDTO(permission);
    }

    @Override
    public PermissionDTO createPermission(PermissionDTO dto) {
        Permission permission = Permission.builder()
                .code(dto.getCode())
                .moTa(dto.getMoTa())
                .build();
        return toDTO(repository.save(permission));
    }

    @Override
    public PermissionDTO updatePermission(Long id, PermissionDTO dto) {
        Permission permission = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Permission not found"));

        permission.setCode(dto.getCode());
        permission.setMoTa(dto.getMoTa());

        return toDTO(repository.save(permission));
    }

    @Override
    public void deletePermission(Long id) {
        repository.deleteById(id);
    }

    private PermissionDTO toDTO(Permission permission) {
        return PermissionDTO.builder()
                .code(permission.getCode())
                .moTa(permission.getMoTa())
                .build();
    }
}
