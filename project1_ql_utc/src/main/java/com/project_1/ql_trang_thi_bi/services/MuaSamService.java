package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IMuaSamService;
import com.project_1.ql_trang_thi_bi.models.*;
import com.project_1.ql_trang_thi_bi.repositorys.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MuaSamService implements IMuaSamService {
    private final PhieuMuaSamRepository phieuMuaSamRepository;
    private final ChiTietMuaSamRepository chiTietMuaSamRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final KhoRepository khoRepository;
    private final LoaiThietBiRepository loaiThietBiRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PhieuMuaSam create(PhieuMuaSam phieuMuaSam) throws DataNotFoundException {

        if (phieuMuaSam.getNgayTao() == null) {
            phieuMuaSam.setNgayTao(LocalDateTime.now());
        }

        // Validate nhà cung cấp
        if (phieuMuaSam.getNhaCungCap() != null && phieuMuaSam.getNhaCungCap().getMaNCC() != null) {
            NhaCungCap ncc = nhaCungCapRepository.findById(phieuMuaSam.getNhaCungCap().getMaNCC())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhà cung cấp"));
            phieuMuaSam.setNhaCungCap(ncc);
        }

        // Validate người tạo
        if (phieuMuaSam.getNguoiTao() != null && phieuMuaSam.getNguoiTao().getMaNguoiDung() != null) {
            NguoiDung nguoiTao = nguoiDungRepository.findById(phieuMuaSam.getNguoiTao().getMaNguoiDung())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người tạo"));
            phieuMuaSam.setNguoiTao(nguoiTao);
        }

        // Lưu phiếu để có khóa chính
        PhieuMuaSam savedPhieu = phieuMuaSamRepository.save(phieuMuaSam);

        BigDecimal tongTien = BigDecimal.ZERO;

        if (phieuMuaSam.getChiTietMuaSamList() != null) {
            for (ChiTietMuaSam chiTiet : phieuMuaSam.getChiTietMuaSamList()) {
                // Gắn phiếu cho chi tiết
                chiTiet.setPhieuMuaSam(savedPhieu);

                // Validate loại thiết bị
                if (chiTiet.getLoaiThietBi() != null && chiTiet.getLoaiThietBi().getMaLoai() != null) {
                    LoaiThietBi loai = loaiThietBiRepository.findById(chiTiet.getLoaiThietBi().getMaLoai())
                            .orElseThrow(() -> new DataNotFoundException("Không tìm thấy loại thiết bị"));
                    chiTiet.setLoaiThietBi(loai);
                }

                // Validate kho nhập
                if (chiTiet.getKhoNhap() != null && chiTiet.getKhoNhap().getMaKho() != null) {
                    Kho kho = khoRepository.findById(chiTiet.getKhoNhap().getMaKho())
                            .orElseThrow(() -> new DataNotFoundException("Không tìm thấy kho nhập"));
                    chiTiet.setKhoNhap(kho);
                }

                // ✅ Tính thành tiền cho chi tiết
                BigDecimal donGia = chiTiet.getDonGia() != null ? chiTiet.getDonGia() : BigDecimal.ZERO;
                BigDecimal soLuong = chiTiet.getSoLuong() != null ? BigDecimal.valueOf(chiTiet.getSoLuong()) : BigDecimal.ZERO;
                BigDecimal thanhTien = donGia.multiply(soLuong);

                // ✅ Cộng dồn tổng tiền
                tongTien = tongTien.add(thanhTien);

                // Lưu chi tiết
                chiTietMuaSamRepository.save(chiTiet);
            }
        }

        // ✅ Cập nhật tổng tiền phiếu
        savedPhieu.setTongTien(tongTien);
        return phieuMuaSamRepository.save(savedPhieu);
    }

    @Override
    public List<PhieuMuaSam> getAll() {
        return phieuMuaSamRepository.findAll();
    }

    @Override
    public PhieuMuaSam getByID(String maMua) throws DataNotFoundException {
        return phieuMuaSamRepository.findById(maMua)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy"));
    }
}
