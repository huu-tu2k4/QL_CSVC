// src/components/inventory/InventoryDetailModal.jsx
import { createPortal } from 'react-dom';

export default function InventoryDetailModal({ show, onClose, phieu }) {
  if (!show || !phieu) return null;

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('vi-VN');
  };

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
              Chi tiết phiếu {phieu.soPhieu || phieu.maKK}
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
                <strong>Đơn vị:</strong> {phieu.tenDonVi}
              </div>
              <div className="col-md-6">
                <strong>Người kiểm kê:</strong> {phieu.nguoiKiemKe}
              </div>
              <div className="col-md-6">
                <strong>Ngày kiểm kê:</strong> {formatDate(phieu.ngayKiemKe)}
              </div>
              <div className="col-md-6">
                <strong>Tổng thiết bị:</strong>{' '}
                <span className="fw-bold">{phieu.tongTB}</span>
              </div>
            </div>

            <hr className="my-4" />

            <div className="row text-center">
              <div className="col">
                <div className="badge bg-success-subtle text-success fs-5 p-3 rounded">
                  {phieu.soTot} Tốt
                </div>
              </div>
              <div className="col">
                <div className="badge bg-danger-subtle text-danger fs-5 p-3 rounded">
                  {phieu.soThieu} Thiếu
                </div>
              </div>
              <div className="col">
                <div className="badge bg-warning-subtle text-warning fs-5 p-3 rounded">
                  {phieu.soHong} Hỏng
                </div>
              </div>
              <div className="col">
                <div className="badge bg-info-subtle text-info fs-5 p-3 rounded">
                  {phieu.soChuyenViTri} Chuyển vị trí
                </div>
              </div>
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