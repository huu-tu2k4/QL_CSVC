package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.VaiTroDTO;
import com.project_1.ql_trang_thi_bi.exceptions.AppException;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.exceptions.ErrorCode;
import com.project_1.ql_trang_thi_bi.models.Permission;
import com.project_1.ql_trang_thi_bi.models.VaiTro;
import com.project_1.ql_trang_thi_bi.iservices.IVaiTroService;
import com.project_1.ql_trang_thi_bi.repositorys.PermissionRepository;
import com.project_1.ql_trang_thi_bi.repositorys.VaiTroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaiTroService implements IVaiTroService {

    private final VaiTroRepository vaiTroRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public List<VaiTroDTO> getAllRoles() {
        return vaiTroRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VaiTroDTO getRoleByMaVaiTro(String maVaiTro) {
        VaiTro role = vaiTroRepository.findByMaVaiTro(maVaiTro)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        return toDTO(role);
    }

    @Override
    public VaiTroDTO createRole(VaiTroDTO dto) {
        if(vaiTroRepository.existsById(dto.getMaVaiTro())) {
            throw new  AppException(ErrorCode.ROLE_EXISTED);
        }
        Set<Permission> permissions = new HashSet<>();
        if (dto.getPermissionIds() != null) {
            for (Long pid : dto.getPermissionIds()) {
                Permission p = permissionRepository.findById(pid)
                        .orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
                permissions.add(p);
            }
        }

        VaiTro role = VaiTro.builder()
                .maVaiTro(dto.getMaVaiTro())
                .tenVaiTro(dto.getTenVaiTro())
                .moTa(dto.getMoTa())
                .permissions(permissions)
                .build();
        System.out.println("MA VAI TRO TRƯỚC SAVE: " + role.getMaVaiTro());
        role = vaiTroRepository.save(role);
        return toDTO(role);
    }

    @Override
    public VaiTroDTO updateRole(String maVaiTro, VaiTroDTO dto) {
        VaiTro role = vaiTroRepository.findByMaVaiTro(maVaiTro)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

        // Không set lại maVaiTro vì là PK
        role.setTenVaiTro(dto.getTenVaiTro());
        role.setMoTa(dto.getMoTa());

        Set<Permission> permissions = new HashSet<>();
        if (dto.getPermissionIds() != null) {
            for (Long pid : dto.getPermissionIds()) {
                Permission p = permissionRepository.findById(pid)
                        .orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
                permissions.add(p);
            }
        }
        role.setPermissions(permissions);

        role = vaiTroRepository.save(role);
        return toDTO(role);
    }

    @Override
    public void deleteRole(String maVaiTro) {
        vaiTroRepository.deleteById(maVaiTro);
    }

    private VaiTroDTO toDTO(VaiTro role) {
        Set<Long> permissionIds = role.getPermissions().stream()
                .map(Permission::getId)
                .collect(Collectors.toSet());
        return VaiTroDTO.builder()
                .maVaiTro(role.getMaVaiTro())
                .tenVaiTro(role.getTenVaiTro())
                .moTa(role.getMoTa())
                .permissionIds(permissionIds)
                .build();
    }
}
