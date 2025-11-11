// src/components/rooms/RoomDetailModal.jsx
import { createPortal } from 'react-dom';

const getTypeLabel = (loai) => {
  switch (loai) {
    case 'PHONG_HOC': return 'Phòng học';
    case 'PHONG_MAY': return 'Phòng máy';
    case 'VAN_PHONG': return 'Văn phòng';
    case 'PHONG_THI_NGHIEM': return 'Phòng thí nghiệm';
    default: return loai;
  }
};

const getTypeBadge = (loai) => {
  switch (loai) {
    case 'PHONG_HOC': return 'bg-info-subtle text-info';
    case 'PHONG_MAY': return 'bg-purple-subtle text-purple';
    case 'VAN_PHONG': return 'bg-warning-subtle text-warning';
    case 'PHONG_THI_NGHIEM': return 'bg-success-subtle text-success';
    default: return 'bg-secondary-subtle text-secondary';
  }
};

export default function RoomDetailModal({ show, onClose, phong }) {
  if (!show || !phong) return null;

  const modalContent = (
    <div
      className="modal show d-block"
      style={{ background: 'rgba(0,0,0,0.5)', zIndex: 9999 }}
      tabIndex="-1"
    >
      <div className="modal-dialog modal-xl modal-dialog-scrollable">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">
              Chi tiết phòng: {phong.tenPhong} ({phong.maCode})
            </h5>
            <button
              type="button"
              className="btn-close"
              onClick={onClose}
              aria-label="Close"
            ></button>
          </div>

          <div className="modal-body" style={{ maxHeight: '75vh', overflowY: 'auto' }}>
            <div className="row g-3 mb-4">
              <div className="col-md-6">
                <strong>Mã phòng:</strong> <code>{phong.maPhong}</code>
              </div>
              <div className="col-md-6">
                <strong>Mã code:</strong> <span className="fw-semibold">{phong.maCode}</span>
              </div>
              <div className="col-md-6">
                <strong>Tên phòng:</strong> {phong.tenPhong}
              </div>
              <div className="col-md-6">
                <strong>Loại phòng:</strong>{' '}
                <span className={`badge ${getTypeBadge(phong.loaiPhong)}`}>
                  {getTypeLabel(phong.loaiPhong)}
                </span>
              </div>
              <div className="col-md-6">
                <strong>Vị trí:</strong> {phong.toaNha} - Tầng {phong.tang}
              </div>
              <div className="col-md-6">
                <strong>Sức chứa:</strong> {phong.sucChua} người
              </div>
              <div className="col-md-6">
                <strong>Đơn vị:</strong> {phong.maDonVi}
              </div>
              <div className="col-md-6">
                <strong>Ghi chú:</strong> {phong.ghiChu || <em className="text-muted">Không có</em>}
              </div>
            </div>

            {phong.thietBis && phong.thietBis.length > 0 && (
              <>
                <hr className="my-4" />
                <h6 className="mb-3">
                  Danh sách thiết bị ({phong.thietBis.length} thiết bị)
                </h6>
                <div className="table-responsive">
                  <table className="table table-sm table-bordered align-middle">
                    <thead className="table-light">
                      <tr>
                        <th>Mã TB</th>
                        <th>Tên thiết bị</th>
                        <th>Loại</th>
                        <th>Vị trí</th>
                        <th>Tình trạng</th>
                        <th>Trạng thái</th>
                        <th>Ghi chú</th>
                      </tr>
                    </thead>
                    <tbody>
                      {phong.thietBis.map((tb) => (
                        <tr key={tb.maThietBi}>
                          <td className="fw-medium">{tb.maThietBi}</td>
                          <td>{tb.tenThietBi}</td>
                          <td>{tb.tenLoaiThietBi}</td>
                          <td className="small">{tb.viTri}</td>
                          <td>
                            <span className={`badge ${
                              tb.tinhTrang === 'Tốt' ? 'bg-success-subtle text-success' :
                              tb.tinhTrang === 'Hỏng' ? 'bg-danger-subtle text-danger' :
                              'bg-warning-subtle text-warning'
                            }`}>
                              {tb.tinhTrang}
                            </span>
                          </td>
                          <td>
                            <span className={`badge ${
                              tb.trangThai === 'DANG_SU_DUNG' ? 'bg-info-subtle text-info' :
                              tb.trangThai === 'HOAT_DONG' ? 'bg-success-subtle text-success' :
                              'bg-secondary-subtle text-secondary'
                            }`}>
                              {tb.trangThai?.replace('_', ' ')}
                            </span>
                          </td>
                          <td className="text-muted small">{tb.ghiChu || '—'}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </>
            )}
          </div>

          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>
              Đóng
            </button>
          </div>
        </div>
      </div>
    </div>
  );

  return createPortal(modalContent, document.body);
}