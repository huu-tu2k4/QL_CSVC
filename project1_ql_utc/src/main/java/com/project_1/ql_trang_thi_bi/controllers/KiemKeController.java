package com.project_1.ql_trang_thi_bi.controllers;

import com.project_1.ql_trang_thi_bi.dtos.PhieuBaoTriDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IKiemKeService;
import com.project_1.ql_trang_thi_bi.models.PhieuKiemKe;
import com.project_1.ql_trang_thi_bi.response.ChiTietKiemKeResponse;
import com.project_1.ql_trang_thi_bi.response.KiemKeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kiem_ke")
@RequiredArgsConstructor
public class KiemKeController {

    private final IKiemKeService kiemKeService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody PhieuKiemKe phieuKiemKe){
        PhieuKiemKe savephieuKiemKe = kiemKeService.create(phieuKiemKe);
        return ResponseEntity.ok(KiemKeResponse.fromKiemKe(savephieuKiemKe));
    }

    @GetMapping("")
    public ResponseEntity<List<KiemKeResponse>> getAll(){
        List<KiemKeResponse> responses = kiemKeService.getAll()
                .stream()
                .map(KiemKeResponse::fromKiemKe)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chi_tiet")
    public ResponseEntity<List<ChiTietKiemKeResponse>> getAllChiTiet(){
        List<ChiTietKiemKeResponse> responses = kiemKeService.getAllChiTiet()
                .stream()
                .map(ChiTietKiemKeResponse::fromChiTietKK)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{maKK}")
    public  ResponseEntity<?> getKKById(@PathVariable String maKK){
        try {
            PhieuKiemKe phieuKiemKe = kiemKeService.getById(maKK);
            return ResponseEntity.ok(KiemKeResponse.fromKiemKe(phieuKiemKe));
        } catch (DataNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{maKK}")
    public ResponseEntity<?> update(@PathVariable String maKK, @RequestBody PhieuKiemKe phieuKiemKe)

       throws DataNotFoundException{
        PhieuKiemKe update = kiemKeService.update(maKK, phieuKiemKe);
        return ResponseEntity.ok(update);


    }


}
