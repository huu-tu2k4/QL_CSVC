// src/components/equipment/EquipmentDetailModal.jsx
import { createPortal } from 'react-dom';

export default function EquipmentDetailModal({ show, onClose, thietBi }) {
  if (!show || !thietBi) return null;

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
  };

  const getStatusBadge = (trangThai) => {
    const map = {
      DANG_SU_DUNG: 'bg-info-subtle text-info',
      SAN_SANG: 'bg-success-subtle text-success',
      BAO_TRI: 'bg-warning-subtle text-warning',
      HONG: 'bg-danger-subtle text-danger',
    };
    return map[trangThai] || 'bg-secondary-subtle text-secondary';
  };

  const modalContent = (
    <div className="modal show d-block" style={{ background: 'rgba(0,0,0,0.5)', zIndex: 9999 }} tabIndex="-1">
      <div className="modal-dialog modal-lg modal-dialog-scrollable">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">Chi tiết thiết bị: {thietBi.tenThietBi}</h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>
          <div className="modal-body">
            <div className="row g-3">
              <div className="col-md-6">
                <strong>Mã TB:</strong> <code>{thietBi.maThietBi}</code>
              </div>
              <div className="col-md-6">
                <strong>Serial:</strong> <span className="text-muted">{thietBi.serialNumber}</span>
              </div>
              <div className="col-md-6">
                <strong>Tên thiết bị:</strong> {thietBi.tenThietBi}
              </div>
              <div className="col-md-6">
                <strong>Loại:</strong> {thietBi.tenLoaiThietBi}
              </div>
              <div className="col-md-6">
                <strong>Đơn vị:</strong> {thietBi.tenDonVi}
              </div>
              <div className="col-md-6">
                <strong>Trạng thái:</strong>{' '}
                <span className={`badge ${getStatusBadge(thietBi.trangThai)}`}>
                  {thietBi.trangThai?.replace('_', ' ')}
                </span>
              </div>
              <div className="col-md-6">
                <strong>Vị trí:</strong> {thietBi.toaNha} - {thietBi.tenPhongHoc}
              </div>
              {/* <div className="col-md-6">
                <strong>Giá trị:</strong> {formatCurrency(thietBi.giaTri)}
              </div> */}
              {thietBi.ghiChu && (
                <div className="col-12">
                  <strong>Ghi chú:</strong> {thietBi.ghiChu}
                </div>
              )}
            </div>
          </div>
          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>Đóng</button>
          </div>
        </div>
      </div>
    </div>
  );

  return createPortal(modalContent, document.body);
}