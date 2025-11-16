package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.dtos.PhongHocDTO;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IPhongHocService;
import com.project_1.ql_trang_thi_bi.models.DonVi;
import com.project_1.ql_trang_thi_bi.models.PhongHoc;
import com.project_1.ql_trang_thi_bi.models.ThietBi;
import com.project_1.ql_trang_thi_bi.repositorys.DonViRepository;
import com.project_1.ql_trang_thi_bi.repositorys.PhongHocRepository;
import com.project_1.ql_trang_thi_bi.repositorys.ThietBiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhongHocService implements IPhongHocService {

    private final PhongHocRepository phongHocRepository;
    private final DonViRepository donViRepository;
    private final ThietBiRepository thietBiRepository;


    @Override
    public List<PhongHoc> getAllPhong() {
        return phongHocRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PhongHoc createPhong(PhongHocDTO phongDTO) {

        DonVi donVi = donViRepository.findById(phongDTO.getMaDonVi())
                .orElseThrow(()-> new DataNotFoundException("Không thấy phòng thuộc đơn vị nào"));

        PhongHoc phongHoc = PhongHoc.builder()
                .maPhong(phongDTO.getMaPhong())
                .maCode(phongDTO.getMaCode())
                .tenPhong(phongDTO.getTenPhong())
                .loaiPhong(phongDTO.getLoaiPhong())
                .sucChua(phongDTO.getSucChua())
                .tang(phongDTO.getTang())
                .toaNha(phongDTO.getToaNha())
                .donVi(donVi)
                .ghiChu(phongDTO.getGhiChu())


                .build();

        if(phongDTO.getThietBis() != null && !phongDTO.getThietBis().isEmpty()){
            List<ThietBi> thietBiList = phongDTO.getThietBis().stream()
                    .map(thietBiDTO -> {
                        ThietBi thietBi = thietBiRepository.findById(thietBiDTO.getMaThietBi())
                                .orElseThrow(()-> new DataNotFoundException("Khong tim thay thiet bi"+ thietBiDTO.getMaThietBi()));
                        thietBi.setPhongHoc(phongHoc);
                        return thietBi;
                    })
                    .collect(Collectors.toList());
            phongHoc.setThietBis(thietBiList);
        }
        return phongHocRepository.save(phongHoc);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PhongHoc getPhongById(String maPhong) throws DataNotFoundException {
        return phongHocRepository.findById(maPhong)
                .orElseThrow(()-> new DataNotFoundException("Không tìm thấy"));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PhongHoc updatePhong(String maPhong, PhongHocDTO phongDTO) throws DataNotFoundException {
        PhongHoc  existingPhong = phongHocRepository.findById(maPhong)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy"));
        DonVi existingDonVi = donViRepository.findById(phongDTO.getMaDonVi())
                .orElseThrow(()-> new DataNotFoundException("Không tìm thấy đơn vị"));

        existingPhong.setTenPhong(phongDTO.getTenPhong());
        existingPhong.setLoaiPhong(phongDTO.getLoaiPhong());
        existingPhong.setSucChua(phongDTO.getSucChua());
        existingPhong.setSucChua(phongDTO.getSucChua());
        existingPhong.setTang(phongDTO.getTang());
        existingPhong.setToaNha(phongDTO.getToaNha());
        existingPhong.setDonVi(existingDonVi);
        existingPhong.setGhiChu(phongDTO.getGhiChu());


        if (phongDTO.getThietBis() != null) {
            if (existingPhong.getThietBis() != null) {
                existingPhong.getThietBis().forEach(tb -> tb.setPhongHoc(null));
            }


            List<ThietBi> newThietBi = phongDTO.getThietBis().stream()
                    .map(thietBiDTO -> {
                        ThietBi thietBi = thietBiRepository.findById(thietBiDTO.getMaThietBi())
                                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy TB có mã:" + thietBiDTO.getMaThietBi()));
                        thietBi.setPhongHoc(existingPhong);
                        return thietBi;
                    })
                    .collect(Collectors.toList());
            existingPhong.setThietBis(newThietBi);

        }


        return phongHocRepository.save(existingPhong);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePhong(String maPhong) throws DataNotFoundException {
        PhongHoc phong = phongHocRepository.findById(maPhong)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy phòng có mã: " + maPhong));

        if(phong.getThietBis() != null){
            phong.getThietBis().forEach(thietBi -> thietBi.setPhongHoc(null));

        }
        phongHocRepository.delete(phong);

    }
}
