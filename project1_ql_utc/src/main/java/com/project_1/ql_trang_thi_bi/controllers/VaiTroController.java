package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.VaiTroDTO;
import com.project_1.ql_trang_thi_bi.iservices.IVaiTroService;
import com.project_1.ql_trang_thi_bi.dtos.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class VaiTroController {

    private final IVaiTroService service;

    @GetMapping
    public ApiResponse<List<VaiTroDTO>> getAllRoles() {
        return ApiResponse.<List<VaiTroDTO>>builder()
                .result(service.getAllRoles())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<VaiTroDTO> getRoleById(@PathVariable String id) {
        return ApiResponse.<VaiTroDTO>builder()
                .result(service.getRoleByMaVaiTro(id))
                .build();
    }

    @PostMapping
    public ApiResponse<VaiTroDTO> createRole(@RequestBody VaiTroDTO dto) {
        return ApiResponse.<VaiTroDTO>builder()
                .result(service.createRole(dto))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<VaiTroDTO> updateRole(@PathVariable String id, @RequestBody VaiTroDTO dto) {
        return ApiResponse.<VaiTroDTO>builder()
                .result(service.updateRole(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRole(@PathVariable String id) {
        service.deleteRole(id);
        return ApiResponse.<Void>builder()
                .result(null)
                .build();
    }
}
