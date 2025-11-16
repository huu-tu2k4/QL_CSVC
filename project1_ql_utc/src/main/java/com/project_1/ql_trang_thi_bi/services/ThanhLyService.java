package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.PhieuBaoTriDTO;
import com.project_1.ql_trang_thi_bi.dtos.PhieuThanhLyDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IThanhLyService;
import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.PhieuThanhLy;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import com.project_1.ql_trang_thi_bi.repositorys.NguoiDungRepository;
import com.project_1.ql_trang_thi_bi.repositorys.PhieuThanhLyRepository;
import com.project_1.ql_trang_thi_bi.repositorys.ThietBiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThanhLyService implements IThanhLyService {

    private final PhieuThanhLyRepository phieuThanhLyRepository;
    private final ThietBiRepository thietBiRepository;
    private final NguoiDungRepository nguoiDungRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('THANH_LY')")
    public PhieuThanhLy create(PhieuThanhLyDTO phieuThanhLyDTO) {


        ThietBi thietBi = thietBiRepository.findById(phieuThanhLyDTO.getMaThietBi())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy thiết bị"));

        NguoiDung nguoiDuyet = nguoiDungRepository.findById(phieuThanhLyDTO.getNguoiDuyet())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người duyệt"));


        PhieuThanhLy phieuThanhLy = PhieuThanhLy.builder()
                .maPhieuTL(phieuThanhLyDTO.getMaPhieuTL())
                .soPhieuTl(phieuThanhLyDTO.getSoPhieuTl())
                .thietBi(thietBi)
                .lyDo(phieuThanhLyDTO.getLyDo())
                .ngayThanhLy(phieuThanhLyDTO.getNgayThanhLy() == null ? LocalDateTime.now() : phieuThanhLyDTO.getNgayThanhLy())
                .nguoiDuyet(nguoiDuyet)
                .ngayDuyet(phieuThanhLyDTO.getNgayDuyet())
                .giaTriGoc(phieuThanhLyDTO.getGiaTriGoc())
                .giaTriThanhLy(phieuThanhLyDTO.getGiaTriThanhLy())
                .ghiChu(phieuThanhLyDTO.getGhiChu())
                .build();
        return phieuThanhLyRepository.save(phieuThanhLy);
    }

    @Override
    public List<PhieuThanhLy> getAll() {
        return phieuThanhLyRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('THANH_LY')")
    public PhieuThanhLy getByID(String maTL) throws DataNotFoundException {
        return phieuThanhLyRepository.findById(maTL)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy"));
    }
}
