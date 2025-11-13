// src/components/maintenance/MaintenanceDetailModal.jsx
import { createPortal } from 'react-dom';

const formatCurrency = (amount) => {
  return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
};

const formatDate = (dateString) => {
  if (!dateString) return <em className="text-muted">Chưa hoàn tất</em>;
  return new Date(dateString).toLocaleDateString('vi-VN');
};

const getPriorityBadge = (muc) => {
  switch (muc) {
    case 'CAO': return 'bg-danger-subtle text-danger';
    case 'TRUNG_BINH': return 'bg-warning-subtle text-warning';
    case 'BINH_THUONG': return 'bg-info-subtle text-info';
    default: return 'bg-secondary-subtle text-secondary';
  }
};

const getStatusBadge = (trangThai) => {
  switch (trangThai) {
    case 'DANG_XU_LY': return 'bg-warning-subtle text-warning';
    case 'HOAN_TAT': return 'bg-success-subtle text-success';
    case 'HUY': return 'bg-danger-subtle text-danger';
    default: return 'bg-secondary-subtle text-secondary';
  }
};

export default function MaintenanceDetailModal({ show, onClose, phieu }) {
  if (!show || !phieu) return null;

  const modalContent = (
    <div
      className="modal show d-block"
      style={{ background: 'rgba(0,0,0,0.5)', zIndex: 9999 }}
      tabIndex="-1"
    >
      <div className="modal-dialog modal-lg modal-dialog-scrollable">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">
              Chi tiết phiếu bảo trì: {phieu.soPhieuBt}
            </h5>
            <button
              type="button"
              className="btn-close"
              onClick={onClose}
              aria-label="Close"
            ></button>
          </div>

          <div className="modal-body" style={{ maxHeight: '70vh', overflowY: 'auto' }}>
            <div className="row g-3 mb-4">
              <div className="col-md-6">
                <strong>Số phiếu:</strong> <code>{phieu.soPhieuBt}</code>
              </div>
              <div className="col-md-6">
                <strong>Mã phiếu:</strong> <span className="fw-medium">{phieu.maPhieuBT}</span>
              </div>
              <div className="col-md-6">
                <strong>Thiết bị:</strong> {phieu.tenThietBi}
              </div>
              <div className="col-md-6">
                <strong>Mã TB:</strong> <code>{phieu.maThietBi}</code>
              </div>
              <div className="col-md-6">
                <strong>Người đề xuất:</strong> {phieu.nguoiDeXuat}
              </div>
              <div className="col-md-6">
                <strong>Đơn vị đề xuất:</strong> {phieu.tenDonViDeXuat}
              </div>
              <div className="col-md-6">
                <strong>Ngày đề xuất:</strong> {formatDate(phieu.ngayDeXuat)}
              </div>
              <div className="col-md-6">
                <strong>Mức ưu tiên:</strong>{' '}
                <span className={`badge ${getPriorityBadge(phieu.mucUuTien)}`}>
                  {phieu.mucUuTien?.replace('_', ' ')}
                </span>
              </div>
              <div className="col-12">
                <strong>Mô tả sự cố:</strong>
                <div className="p-2 bg-light rounded small mt-1">{phieu.moTaSuCo}</div>
              </div>
              <div className="col-md-6">
                <strong>Người phụ trách:</strong> {phieu.nguoiPhuTrach || <em className="text-muted">Chưa phân công</em>}
              </div>
              <div className="col-md-6">
                <strong>Ngày hoàn tất:</strong> {formatDate(phieu.ngayHoanTat)}
              </div>
              <div className="col-md-6">
                <strong>Trạng thái:</strong>{' '}
                <span className={`badge ${getStatusBadge(phieu.trangThai)}`}>
                  {phieu.trangThai?.replace('_', ' ')}
                </span>
              </div>
              <div className="col-md-6">
                <strong>Chi phí:</strong>{' '}
                <span className="text-success fw-bold">
                  {phieu.chiPhi ? formatCurrency(phieu.chiPhi) : <em className="text-muted">Chưa xác định</em>}
                </span>
              </div>
              {phieu.ghiChu && (
                <div className="col-12">
                  <strong>Ghi chú:</strong>
                  <div className="p-2 bg-light rounded small mt-1">{phieu.ghiChu}</div>
                </div>
              )}
            </div>
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