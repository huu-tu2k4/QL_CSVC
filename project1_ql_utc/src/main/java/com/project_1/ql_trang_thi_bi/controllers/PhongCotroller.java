package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.PhongHocDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IPhongHocService;
import com.project_1.ql_trang_thi_bi.models.PhongHoc;
import com.project_1.ql_trang_thi_bi.response.PhongHocResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/phong") // Thêm thiết bị xong thêm phòng

@RequiredArgsConstructor
public class PhongCotroller {
    private final IPhongHocService phongHocService;

    @GetMapping("")
    public ResponseEntity<List<PhongHocResponse>> getAllPhong(){
        List<PhongHocResponse> responses = phongHocService.getAllPhong().stream()
                .map(PhongHocResponse::fromPhong)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("")
    public ResponseEntity<?> createPhong(@RequestBody PhongHocDTO phongHocDTO){
        try {
            PhongHoc phongHoc = phongHocService.createPhong(phongHocDTO);
            return ResponseEntity.ok(PhongHocResponse.fromPhong(phongHoc));
        }catch (DataNotFoundException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/ma_phong")
    public ResponseEntity<?> getPhongById(@PathVariable("maPhong") String maPhong) {
        try {
            PhongHoc phong = phongHocService.getPhongById(maPhong);
            return ResponseEntity.ok(PhongHocResponse.fromPhong(phong));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{maPhong}")
    public ResponseEntity<?> updatePhong(@PathVariable("maPhong") String maPhong, @RequestBody PhongHocDTO phongDTO) {
        try {
            PhongHoc phong = phongHocService.updatePhong(maPhong, phongDTO);
            return ResponseEntity.ok(PhongHocResponse.fromPhong(phong));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{maPhong}")
    public ResponseEntity<?> deletePhong(@PathVariable("maPhong") String maPhong) {
        try {
            phongHocService.deletePhong(maPhong);
            return ResponseEntity.ok("Đã xóa phòng có mã: " + maPhong);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
