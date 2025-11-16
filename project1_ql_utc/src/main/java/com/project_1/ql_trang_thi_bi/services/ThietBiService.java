package com.project_1.ql_trang_thi_bi.services;


import com.project_1.ql_trang_thi_bi.dtos.ThietBiDTO;
import com.project_1.ql_trang_thi_bi.enums.TrangThaiThietBi;
import com.project_1.ql_trang_thi_bi.exceptions.DataNotFoundException;
import com.project_1.ql_trang_thi_bi.iservices.IThietBiService;
import com.project_1.ql_trang_thi_bi.models.*;
import com.project_1.ql_trang_thi_bi.repositorys.*;
import com.project_1.ql_trang_thi_bi.response.ThietBiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ThietBiService implements IThietBiService {

    private final ThietBiRepository thietBiRepository;
    private final LoaiThietBiRepository loaiThietBiRepository;
    private final PhongHocRepository phongHocRepository;
    private final KhoRepository khoRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final DonViRepository donViRepository;
    private final NguoiDungRepository nguoiDungRepository;


    @Override
    public ThietBi createThietBi(ThietBiDTO thietBiDTO) {

        LoaiThietBi loaiThietBi = loaiThietBiRepository.findById(thietBiDTO.getMaLoai())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy loại thiết bị"));
        PhongHoc phongHoc = phongHocRepository.findById(thietBiDTO.getMaPhong())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy phòng học"));
        Kho kho = khoRepository.findById(thietBiDTO.getMaKho())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy kho"));
        NhaCungCap nhaCungCap = nhaCungCapRepository.findById(thietBiDTO.getMaNcc())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhà cung cấp"));
        DonVi donVi = donViRepository.findById(thietBiDTO.getMaDonViSoHuu())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn vị sở hữu"));
        NguoiDung nguoiDung = nguoiDungRepository.findById(thietBiDTO.getMaNguoiSuDung())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người sử dụng"));

        ThietBi thietBi = ThietBi.builder()
                .maThietBi(thietBiDTO.getMaThietBi())
                .tenThietBi(thietBiDTO.getTenThietBi())
                .loaiThietBi(loaiThietBi)
                .phongHoc(phongHoc)
                .kho(kho)
                .nhaCungCap(nhaCungCap)
                .donViSoHuu(donVi)
                .nguoiSuDung(nguoiDung)
                .soSerial(thietBiDTO.getSoSerial())
                .model(thietBiDTO.getModel())
                .hangSx(thietBiDTO.getHangSx())
                .ngayMua(thietBiDTO.getNgayMua())
                .giaMua(thietBiDTO.getGiaMua())
                .baoHanhDen(thietBiDTO.getBaoHanhDen())
                .viTri(thietBiDTO.getViTri())
                .trangThai(thietBiDTO.getTrangThai())
                .tinhTrang(thietBiDTO.getTinhTrang())
                .ghiChu(thietBiDTO.getGhiChu())
                .ngayTao(thietBiDTO.getNgayTao())
                .ngayCapNhat(thietBiDTO.getNgayCapNhat())
                .build();

        return thietBiRepository.save(thietBi);
    }

    @Override
    public ThietBi updateThietBi(String maThietBi, ThietBiDTO thietBiDTO) throws DataNotFoundException {
        return null;
    }

    @Override
    public void deleteThietBi(String maThietBi) throws DataNotFoundException {

    }

    @Override
    public ThietBiResponse getThietBiById(String maThietBi) throws DataNotFoundException {
        ThietBi thietBi = thietBiRepository.findById(maThietBi)
                .orElseThrow(()-> new DataNotFoundException("Không tìm thấy thiết bị có mã: "+ maThietBi));
        return ThietBiResponse.fromThietBi(thietBi);
    }

    @Override
    public Page<ThietBiResponse> getAllThietBi(String keyword, TrangThaiThietBi trangThaiThietBi, Pageable pageable) {

        Page<ThietBi> page = thietBiRepository.findAllWithFilter(keyword, trangThaiThietBi, pageable);

        return page.map(ThietBiResponse::fromThietBi);
    }

    private ThietBiDTO convertToDTO(ThietBi thietBi) {
            return ThietBiDTO.builder()

                    .build();
    }
}
