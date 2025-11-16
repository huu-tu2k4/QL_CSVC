package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.DonViDTO;
import com.project_1.ql_trang_thi_bi.iservices.IDonViService;
import com.project_1.ql_trang_thi_bi.response.DonViResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/don_vi")
@RequiredArgsConstructor
public class DonViController {

    private final IDonViService donViService;

    @GetMapping
    public List<DonViResponse> getAll() {
        return donViService.getAll();
    }

    @GetMapping("/{maDonVi}")
    public DonViResponse getById(@PathVariable String maDonVi) {
        return donViService.getById(maDonVi);
    }

    @PostMapping
    public DonViResponse create(@RequestBody DonViDTO dto) {
        return donViService.create(dto);
    }

    @PutMapping("/{maDonVi}")
    public DonViResponse update(@PathVariable String maDonVi, @RequestBody DonViDTO dto) {
        return donViService.update(maDonVi, dto);
    }

    @DeleteMapping("/{maDonVi}")
    public void delete(@PathVariable String maDonVi) {
        donViService.delete(maDonVi);
    }
}
