package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IKiemKeService;
import com.project_1.ql_trang_thi_bi.models.*;
import com.project_1.ql_trang_thi_bi.repositorys.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KiemKeService implements IKiemKeService {

    private final PhieuKiemKeRepository phieuKiemKeRepository;
    private final ChiTietKiemKeRepository chiTietKiemKeRepository;
    private final DonViRepository donViRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ThietBiRepository thietBiRepository;

    @Override
    public List<PhieuKiemKe> getAll() {
        return phieuKiemKeRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('KIEM_KE')")
    public PhieuKiemKe getById(String maKK) throws DataNotFoundException {
        return phieuKiemKeRepository.findById(maKK)
                .orElseThrow(()-> new DataNotFoundException("Khong tìm thấy"));
    }

    @Override

    public List<ChiTietKiemKe> getAllChiTiet() {
        return chiTietKiemKeRepository.findAll();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('KIEM_KE')")
    public PhieuKiemKe create(PhieuKiemKe phieuKiemKe) throws DataNotFoundException {

        // Gán mã phiếu nếu chưa có
        if (phieuKiemKe.getMaPhieuKK() == null || phieuKiemKe.getMaPhieuKK().isEmpty()) {
            phieuKiemKe.setMaPhieuKK("KK_" + UUID.randomUUID().toString().substring(0, 8));
        }

        // Ngày kiểm kê mặc định
        if (phieuKiemKe.getNgayKiemKe() == null) {
            phieuKiemKe.setNgayKiemKe(LocalDateTime.now());
        }

        DonVi donVi = donViRepository.findById(phieuKiemKe.getDonVi().getMaDonVi())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị có mã: " + phieuKiemKe.getDonVi().getMaDonVi()));
        phieuKiemKe.setDonVi(donVi);

        // Người kiểm kê
        NguoiDung nguoiDung = nguoiDungRepository.findById(phieuKiemKe.getNguoiKiemKe().getMaNguoiDung())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người kiểm kê có mã: " + phieuKiemKe.getNguoiKiemKe().getMaNguoiDung()));
        phieuKiemKe.setNguoiKiemKe(nguoiDung);

        // Danh sách chi tiết
        if (phieuKiemKe.getChiTietKiemKeList() != null) {
            for (ChiTietKiemKe ct : phieuKiemKe.getChiTietKiemKeList()) {
                ct.setPhieuKiemKe(phieuKiemKe);

                // Kiểm tra thiết bị
                ThietBi thietBi = thietBiRepository.findById(ct.getThietBi().getMaThietBi())
                        .orElseThrow(() -> new DataNotFoundException("Không tìm thấy thiết bị có mã: " + ct.getThietBi().getMaThietBi()));
                ct.setThietBi(thietBi);
            }
        }

        return phieuKiemKeRepository.save(phieuKiemKe);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('KIEM_KE')")
    public PhieuKiemKe update(String maKK, PhieuKiemKe phieuKiemKe) throws DataNotFoundException {

        PhieuKiemKe existingKK = phieuKiemKeRepository.findById(maKK)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy phiếu kiểm kê có mã: " + maKK));

        // Cập nhật các trường cơ bản
        if (phieuKiemKe.getSoPhieuKk() != null) {
            existingKK.setSoPhieuKk(phieuKiemKe.getSoPhieuKk());
        }
        if (phieuKiemKe.getGhiChu() != null) {
            existingKK.setGhiChu(phieuKiemKe.getGhiChu());
        }
        if (phieuKiemKe.getNgayKiemKe() != null) {
            existingKK.setNgayKiemKe(phieuKiemKe.getNgayKiemKe());
        }

        // Cập nhật đơn vị
        if (phieuKiemKe.getDonVi() != null && phieuKiemKe.getDonVi().getMaDonVi() != null) {
            DonVi donVi = donViRepository.findById(phieuKiemKe.getDonVi().getMaDonVi())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị có mã: " + phieuKiemKe.getDonVi().getMaDonVi()));
            existingKK.setDonVi(donVi);
        }

        // Cập nhật người kiểm kê
        if (phieuKiemKe.getNguoiKiemKe() != null && phieuKiemKe.getNguoiKiemKe().getMaNguoiDung() != null) {
            NguoiDung nguoiDung = nguoiDungRepository.findById(phieuKiemKe.getNguoiKiemKe().getMaNguoiDung())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người kiểm kê có mã: " + phieuKiemKe.getNguoiKiemKe().getMaNguoiDung()));
            existingKK.setNguoiKiemKe(nguoiDung);
        }

        // ✅ Cập nhật danh sách chi tiết kiểm kê
        if (phieuKiemKe.getChiTietKiemKeList() != null && !phieuKiemKe.getChiTietKiemKeList().isEmpty()) {
            // Xóa toàn bộ chi tiết cũ trong CSDL
            chiTietKiemKeRepository.deleteAll(existingKK.getChiTietKiemKeList());

            // Xóa khỏi entity để tránh lỗi trùng khóa
            existingKK.getChiTietKiemKeList().clear();

            // Thêm lại các chi tiết mới
            for (ChiTietKiemKe ctMoi : phieuKiemKe.getChiTietKiemKeList()) {
                // Kiểm tra thiết bị
                ThietBi thietBi = thietBiRepository.findById(ctMoi.getThietBi().getMaThietBi())
                        .orElseThrow(() -> new DataNotFoundException("Không tìm thấy thiết bị có mã: " + ctMoi.getThietBi().getMaThietBi()));

                ctMoi.setThietBi(thietBi);
                ctMoi.setPhieuKiemKe(existingKK); // gắn liên kết hai chiều

                chiTietKiemKeRepository.save(ctMoi);
                existingKK.getChiTietKiemKeList().add(ctMoi);
            }
        }

        // ✅ Lưu toàn bộ lại và trả về
        return phieuKiemKeRepository.save(existingKK);
    }

}
