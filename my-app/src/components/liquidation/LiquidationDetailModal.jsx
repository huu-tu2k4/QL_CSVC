// src/components/liquidation/LiquidationDetailModal.jsx
import { createPortal } from 'react-dom';

const formatCurrency = (amount) => {
  return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
};

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString('vi-VN');
};

export default function LiquidationDetailModal({ show, onClose, phieu }) {
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
              Chi tiết phiếu thanh lý: {phieu.soPhieuTL}
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
                <strong>Số phiếu:</strong> <code>{phieu.soPhieuTL}</code>
              </div>
              <div className="col-md-6">
                <strong>Mã phiếu:</strong> <span className="fw-medium">{phieu.maPhieuTL}</span>
              </div>
              <div className="col-md-6">
                <strong>Thiết bị:</strong> {phieu.thietBi}
              </div>
              <div className="col-md-6">
                <strong>Lý do thanh lý:</strong>{' '}
                <span className="text-danger small">{phieu.lyDo}</span>
              </div>
              <div className="col-md-6">
                <strong>Ngày thanh lý:</strong> {formatDate(phieu.ngayThanhLy)}
              </div>
              <div className="col-md-6">
                <strong>Người duyệt:</strong> {phieu.nguoiDuyet}
              </div>
              <div className="col-md-6">
                <strong>Ngày duyệt:</strong> {formatDate(phieu.ngayDuyet)}
              </div>
              <div className="col-md-6">
                <strong>Giá trị gốc:</strong>{' '}
                <span className="text-decoration-line-through text-muted">
                  {formatCurrency(phieu.giaTriGoc)}
                </span>
              </div>
              <div className="col-md-6">
                <strong>Giá trị thanh lý:</strong>{' '}
                <span className="text-success fw-bold fs-5">
                  {formatCurrency(phieu.giaTriThanhLy)}
                </span>
              </div>
              {phieu.ghiChu && (
                <div className="col-12">
                  <strong>Ghi chú:</strong> {phieu.ghiChu}
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