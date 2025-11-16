package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.ApiResponse;
import com.project_1.ql_trang_thi_bi.dtos.LichSuThietBiDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.ILichSuTBSevice;
import com.project_1.ql_trang_thi_bi.models.LichSuThietBi;
import com.project_1.ql_trang_thi_bi.models.PhieuBaoTri;
import com.project_1.ql_trang_thi_bi.response.LichSuTBResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lich_su")
@RequiredArgsConstructor
public class LichSuThietBiController {

    private final ILichSuTBSevice lichSuTBSevice;

    @PostMapping("")
    public ApiResponse<LichSuTBResponse> create(@Valid @RequestBody LichSuThietBiDTO lichSuThietBiDTO){
        LichSuThietBi lichSuThietBi = lichSuTBSevice.create(lichSuThietBiDTO);
        return ApiResponse.<LichSuTBResponse>builder()
                .result(LichSuTBResponse.fromLichSu(lichSuThietBi))
                .build();
    }


    @GetMapping("")
    public ApiResponse<List<LichSuTBResponse>> getAll(){
        List<LichSuTBResponse> responses = lichSuTBSevice.getAll()
                .stream()
                .map(LichSuTBResponse::fromLichSu)
                .toList();
        return ApiResponse.<List<LichSuTBResponse>>builder()
                .result(responses)
                .build();
    }


    @GetMapping("/{maLS}")
    public ApiResponse<LichSuTBResponse> getById(@PathVariable String maLS) {
        try {
            LichSuThietBi lichSuThietBi = lichSuTBSevice.getById(maLS);
            return ApiResponse.<LichSuTBResponse>builder()
                    .result(LichSuTBResponse.fromLichSu(lichSuThietBi))
                    .build();
        }catch (DataNotFoundException e){
            throw e; // GlobalExceptionHandler sẽ handle
        }
    }

}
