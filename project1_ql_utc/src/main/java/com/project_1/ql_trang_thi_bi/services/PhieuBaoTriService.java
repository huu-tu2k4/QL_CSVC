package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.PhieuBaoTriDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IPhieuBaoTriService;
import com.project_1.ql_trang_thi_bi.models.DonVi;
import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.models.PhieuBaoTri;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import com.project_1.ql_trang_thi_bi.repositorys.BaoTriRepository;
import com.project_1.ql_trang_thi_bi.repositorys.DonViRepository;
import com.project_1.ql_trang_thi_bi.repositorys.NguoiDungRepository;
import com.project_1.ql_trang_thi_bi.repositorys.ThietBiRepository;
import com.project_1.ql_trang_thi_bi.response.BaoTriResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhieuBaoTriService implements IPhieuBaoTriService {

    private final BaoTriRepository baoTriRepository;
    private final ThietBiRepository thietBiRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final DonViRepository donViRepository;

    @Override
    public List<BaoTriResponse> getAll() {
        return baoTriRepository.findAll()
                .stream()
                .map(BaoTriResponse::fromPhieuBaoTri)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('BAO_TRI')")
    public BaoTriResponse getBaoTriByID(String maBT) throws DataNotFoundException {
        PhieuBaoTri phieu = baoTriRepository.findById(maBT)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy phiếu bảo trì"));
        return BaoTriResponse.fromPhieuBaoTri(phieu);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('BAO_TRI')")
    public BaoTriResponse createBaoTri(PhieuBaoTriDTO dto) throws DataNotFoundException {
        ThietBi thietBi = thietBiRepository.findById(dto.getMaThietBi())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy thiết bị có mã: " + dto.getMaThietBi()));

        DonVi donVi = donViRepository.findById(dto.getMaDonViDeXuat())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị đề xuất"));

        NguoiDung nguoiPhuTrach = nguoiDungRepository.findById(dto.getNguoiPhuTrach())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người phụ trách"));

        PhieuBaoTri phieu = PhieuBaoTri.builder()
                .maPhieuBT(dto.getMaPhieuBT())
                .soPhieuBt(dto.getSoPhieuBt())
                .thietBi(thietBi)
                .donViDeXuat(donVi)
                .ngayDeXuat(dto.getNgayDeXuat())
                .moTaSuCo(dto.getMoTaSuCo())
                .nguoiPhuTrach(nguoiPhuTrach)
                .ngayHoanTat(dto.getNgayHoanTat())
                .mucUuTien(dto.getMucUuTien())
                .trangThai(dto.getTrangThai())
                .chiPhi(dto.getChiPhi())
                .ghiChu(dto.getGhiChu())
                .build();

        PhieuBaoTri saved = baoTriRepository.save(phieu);
        return BaoTriResponse.fromPhieuBaoTri(saved);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('BAO_TRI')")
    public BaoTriResponse updateBaoTri(String maBT, PhieuBaoTriDTO dto) throws DataNotFoundException {
        PhieuBaoTri phieu = baoTriRepository.findById(maBT)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy phiếu bảo trì"));

        if (dto.getMaThietBi() != null) {
            ThietBi thietBi = thietBiRepository.findById(dto.getMaThietBi())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy thiết bị có mã: " + dto.getMaThietBi()));
            phieu.setThietBi(thietBi);
        }

        if (dto.getMaDonViDeXuat() != null) {
            DonVi donVi = donViRepository.findById(dto.getMaDonViDeXuat())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị đề xuất"));
            phieu.setDonViDeXuat(donVi);
        }

        if (dto.getNguoiPhuTrach() != null) {
            NguoiDung nguoiPhuTrach = nguoiDungRepository.findById(dto.getNguoiPhuTrach())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người phụ trách"));
            phieu.setNguoiPhuTrach(nguoiPhuTrach);
        }

        phieu.setSoPhieuBt(dto.getSoPhieuBt() != null ? dto.getSoPhieuBt() : phieu.getSoPhieuBt());
        phieu.setNgayDeXuat(dto.getNgayDeXuat() != null ? dto.getNgayDeXuat() : phieu.getNgayDeXuat());
        phieu.setMoTaSuCo(dto.getMoTaSuCo() != null ? dto.getMoTaSuCo() : phieu.getMoTaSuCo());
        phieu.setNgayHoanTat(dto.getNgayHoanTat() != null ? dto.getNgayHoanTat() : phieu.getNgayHoanTat());
        phieu.setMucUuTien(dto.getMucUuTien() != null ? dto.getMucUuTien() : phieu.getMucUuTien());
        phieu.setTrangThai(dto.getTrangThai() != null ? dto.getTrangThai() : phieu.getTrangThai());
        phieu.setChiPhi(dto.getChiPhi() != null ? dto.getChiPhi() : phieu.getChiPhi());
        phieu.setGhiChu(dto.getGhiChu() != null ? dto.getGhiChu() : phieu.getGhiChu());

        PhieuBaoTri updated = baoTriRepository.save(phieu);
        return BaoTriResponse.fromPhieuBaoTri(updated);
    }
}
