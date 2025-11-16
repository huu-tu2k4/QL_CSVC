package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.PhieuBaoTriDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IPhieuBaoTriService;
import com.project_1.ql_trang_thi_bi.response.BaoTriResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bao_tri")
@RequiredArgsConstructor
public class PhieuBaoTriController {

    private final IPhieuBaoTriService phieuBaoTriService;

    @PostMapping
    public ResponseEntity<?> createBaoTri(@RequestBody PhieuBaoTriDTO dto){
        try {
            BaoTriResponse response = phieuBaoTriService.createBaoTri(dto);
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{maBT}")
    public ResponseEntity<?> updateBaoTri(@PathVariable String maBT, @RequestBody PhieuBaoTriDTO dto){
        try {
            BaoTriResponse response = phieuBaoTriService.updateBaoTri(maBT, dto);
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BaoTriResponse>> getAll() {
        List<BaoTriResponse> responses = phieuBaoTriService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{maBT}")
    public ResponseEntity<?> getBaoTriById(@PathVariable String maBT){
        try {
            BaoTriResponse response = phieuBaoTriService.getBaoTriByID(maBT);
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
