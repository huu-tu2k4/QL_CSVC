package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.ApiResponse;
import com.project_1.ql_trang_thi_bi.dtos.NguoiDungDTO;
import com.project_1.ql_trang_thi_bi.response.NguoiDungResponse;
import com.project_1.ql_trang_thi_bi.iservices.INguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nguoi_dung")
@RequiredArgsConstructor
public class NguoiDungController {

    private final INguoiDungService nguoiDungService;

    @PostMapping

    public ApiResponse<NguoiDungResponse> createNguoiDung(@RequestBody NguoiDungDTO dto) {

        return ApiResponse.<NguoiDungResponse>builder()
                .result(nguoiDungService.createNguoiDung(dto))
                .build();
    }


    @PutMapping("/{maNguoiDung}")
    public ApiResponse<NguoiDungResponse> updateNguoiDung(@PathVariable String maNguoiDung, @RequestBody NguoiDungDTO dto) {
        return ApiResponse.<NguoiDungResponse>builder()
                .result(nguoiDungService.updateNguoiDung(maNguoiDung, dto))
                .build();
    }

    @DeleteMapping("/{maNguoiDung}")
    public ApiResponse<Void> deleteNguoiDung(@PathVariable String maNguoiDung) {
        nguoiDungService.deleteNguoiDung(maNguoiDung);
        return ApiResponse.<Void>builder()
                .build();
    }


    @GetMapping("/{maNguoiDung}")
    public ApiResponse<NguoiDungResponse> getNguoiDungById(@PathVariable String maNguoiDung) {
        return ApiResponse.<NguoiDungResponse>builder()
                .result(nguoiDungService.getNguoiDungById(maNguoiDung))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<NguoiDungResponse>> getAllNguoiDung(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ApiResponse.<Page<NguoiDungResponse>>builder()
                .result(nguoiDungService.getAllNguoiDung(pageable))
                .build();
    }
    @GetMapping("/myInfo")
    ApiResponse<NguoiDungResponse> getMyInfo() {
        return ApiResponse.<NguoiDungResponse>builder()
                .result(nguoiDungService.getMyInfo())
                .build();
    }
}
