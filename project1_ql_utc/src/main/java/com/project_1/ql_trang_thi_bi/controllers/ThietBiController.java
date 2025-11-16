package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.ThietBiDTO;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IThietBiService;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import com.project_1.ql_trang_thi_bi.response.ThietBiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thiet_bi")
@RequiredArgsConstructor
public class ThietBiController {
    private final IThietBiService thietBiService;


    @PostMapping("")
    public ResponseEntity<?> creteThietBi(@Valid @RequestBody ThietBiDTO thietBiDTO){
        ThietBi newThietBi = thietBiService.createThietBi(thietBiDTO);
        return ResponseEntity.ok(newThietBi);
    }

    @GetMapping("")
    public  ResponseEntity<Page<ThietBiResponse>> getAll(
            @Valid
            @RequestBody
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false)TrangThaiThietBi trangThaiThietBi,
            Pageable pageable
            ) {
        Page<ThietBiResponse> thietBi = thietBiService.getAllThietBi(keyword,trangThaiThietBi,pageable);
        return ResponseEntity.ok(thietBi);
    }

    @GetMapping("/{maThietBi}")
    public ResponseEntity<ThietBiResponse> getThietBiById(@PathVariable String maThietBi) throws DataNotFoundException {
        ThietBiResponse thietBi = thietBiService.getThietBiById(maThietBi);
        return ResponseEntity.ok(thietBi);
    }

}
