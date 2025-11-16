package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.LichSuThietBiDTO;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiLichSu;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.ILichSuTBSevice;
import com.project_1.ql_trang_thi_bi.models.DonVi;
import com.project_1.ql_trang_thi_bi.models.LichSuThietBi;
import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import com.project_1.ql_trang_thi_bi.repositorys.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LichSuTBService implements ILichSuTBSevice {

    private final LichSuTBRepository lichSuTBRepository;
    private final ThietBiRepository thietBiRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final DonViRepository donViRepository;
    private final KhoRepository khoRepository;



    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public LichSuThietBi create(LichSuThietBiDTO dto) {

        ThietBi thietBi = thietBiRepository.findById(dto.getMaThietBi())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy thiết bị"));

        // Người thực hiện (bắt buộc)
        NguoiDung nguoiThucHien = nguoiDungRepository.findById(dto.getNguoiThucHien())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người thực hiện"));

        // Các quan hệ nullable (không bắt buộc)
        DonVi tuDonVi = dto.getTuDonVi() != null ? donViRepository.findById(dto.getTuDonVi()).orElse(null) : null;
        DonVi denDonVi = dto.getDenDonVi() != null ? donViRepository.findById(dto.getDenDonVi()).orElse(null) : null;

//        NguoiDung tuNguoiDung = dto.getTuNguoiDung() != null ? nguoiDungRepository.findById(dto.getTuNguoiDung()).orElse(null) : null;
//        NguoiDung denNguoiDung = dto.getDenNguoiDung() != null ? nguoiDungRepository.findById(dto.getDenNguoiDung()).orElse(null) : null;

        com.project_1.ql_trang_thi_bi.models.Kho tuKho = dto.getTuKho() != null ? khoRepository.findById(dto.getTuKho()).orElse(null) : null;
        com.project_1.ql_trang_thi_bi.models.Kho denKho = dto.getDenKho() != null ? khoRepository.findById(dto.getDenKho()).orElse(null) : null;

        // Build entity
        LichSuThietBi lichSu = LichSuThietBi.builder()
                .maLichSu(dto.getMaLichSu())
                .thietBi(thietBi)
                .loaiSuKien(dto.getLoaiSuKien())
                .ngaySuKien(dto.getNgaySuKien())
                .nguoiThucHien(nguoiThucHien)
                .tuDonVi(tuDonVi)
                .denDonVi(denDonVi)
                .tuKho(tuKho)
                .denKho(denKho)
                .soPhieu(dto.getSoPhieu())
                .chiPhi(dto.getChiPhi())
                .moTa(dto.getMoTa())
                .build();

        return lichSuTBRepository.save(lichSu);
    }

    @Override
    public List<LichSuThietBi> getAll() {
        return lichSuTBRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public LichSuThietBi getById(String maLS) throws DataAccessException {

        return lichSuTBRepository.findById(maLS)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy"));
    }

}
