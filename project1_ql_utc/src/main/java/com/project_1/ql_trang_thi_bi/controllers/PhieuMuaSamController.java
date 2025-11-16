package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IMuaSamService;
import com.project_1.ql_trang_thi_bi.models.PhieuMuaSam;
import com.project_1.ql_trang_thi_bi.response.PhieuMuaSamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mua_moi")
@RequiredArgsConstructor
public class PhieuMuaSamController {
    private final IMuaSamService muaSamService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PhieuMuaSam phieuMuaSam){
        try {
            PhieuMuaSam phieuMuaSam1 = muaSamService.create(phieuMuaSam);
            return ResponseEntity.ok(PhieuMuaSamResponse.fromMuaSam(phieuMuaSam1));
        } catch (DataNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("")
    public ResponseEntity<List<PhieuMuaSamResponse>> getAll(){
        List<PhieuMuaSamResponse> responses = muaSamService.getAll()
                .stream()
                .map(PhieuMuaSamResponse::fromMuaSam)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{maMua}")
    public ResponseEntity<?> getById(@PathVariable String maMua){
        try {
            PhieuMuaSam phieuMuaSam = muaSamService.getByID(maMua);
            return ResponseEntity.ok(PhieuMuaSamResponse.fromMuaSam(phieuMuaSam));
        }catch (DataNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
