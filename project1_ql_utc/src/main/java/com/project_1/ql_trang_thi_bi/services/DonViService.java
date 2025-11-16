package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.DonViDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IDonViService;
import com.project_1.ql_trang_thi_bi.models.DonVi;
import com.project_1.ql_trang_thi_bi.repositorys.DonViRepository;
import com.project_1.ql_trang_thi_bi.repositorys.ThietBiRepository;
import com.project_1.ql_trang_thi_bi.response.DonViResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonViService implements IDonViService {

    private final DonViRepository donViRepository;
    private final ThietBiRepository thietBiRepository;

    @Override
    public List<DonViResponse> getAll() {
        return donViRepository.findAll()
                .stream()
                .map(dv -> {
                    int deviceCount = thietBiRepository.countByDonVi(dv.getMaDonVi());
                    DonViResponse res = DonViResponse.fromDonVi(dv);
                    res.setSoLuongTB(deviceCount);
                    return res;
                })
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public DonViResponse getById(String maDonVi) {
        DonVi dv = donViRepository.findById(maDonVi)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị"));
        int deviceCount = thietBiRepository.countByDonVi(dv.getMaDonVi());
        DonViResponse res = DonViResponse.fromDonVi(dv);
        res.setSoLuongTB(deviceCount);
        return res;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public DonViResponse create(DonViDTO dto) {
        DonVi dv = DonVi.builder()
                .maDonVi(dto.getMaDonVi())
                .maCode(dto.getMaCode())
                .tenDonVi(dto.getTenDonVi())
                .moTa(dto.getMoTa())
                .build();
        DonVi saved = donViRepository.save(dv);
        int deviceCount = thietBiRepository.countByDonVi(saved.getMaDonVi());
        DonViResponse res = DonViResponse.fromDonVi(saved);
        res.setSoLuongTB(deviceCount);
        return res;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public DonViResponse update(String maDonVi, DonViDTO dto) {
        DonVi dv = donViRepository.findById(maDonVi)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị"));
        dv.setTenDonVi(dto.getTenDonVi());
        dv.setMoTa(dto.getMoTa());
        DonVi updated = donViRepository.save(dv);
        int deviceCount = thietBiRepository.countByDonVi(updated.getMaDonVi());
        DonViResponse res = DonViResponse.fromDonVi(updated);
        res.setSoLuongTB(deviceCount);
        return res;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String maDonVi) {
        DonVi dv = donViRepository.findById(maDonVi)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị"));
        donViRepository.delete(dv);
    }
}
