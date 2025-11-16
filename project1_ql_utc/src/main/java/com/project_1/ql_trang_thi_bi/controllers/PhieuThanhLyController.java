package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.PhieuThanhLyDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IThanhLyService;
import com.project_1.ql_trang_thi_bi.models.PhieuThanhLy;
import com.project_1.ql_trang_thi_bi.response.ThanhLyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/thanh_ly")
@RequiredArgsConstructor
public class PhieuThanhLyController {

    private final IThanhLyService thanhLyService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PhieuThanhLyDTO phieuThanhLyDTO){
        try {
            PhieuThanhLy phieuThanhLy = thanhLyService.create(phieuThanhLyDTO);
            return ResponseEntity.ok(ThanhLyResponse.fromThanhLy(phieuThanhLy));
        }catch (DataNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ThanhLyResponse>> getAll(){
        List<ThanhLyResponse> responses = thanhLyService.getAll()
                .stream()
                .map(ThanhLyResponse::fromThanhLy)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{maTL}")
    public ResponseEntity<?> getById(@PathVariable("maTL") String maTL){
        try {
            PhieuThanhLy phieuThanhLy = thanhLyService.getByID(maTL);
            return ResponseEntity.ok(ThanhLyResponse.fromThanhLy(phieuThanhLy));
        }catch (DataNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
