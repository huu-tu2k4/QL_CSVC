// src/components/purchase/PurchaseDetailModal.jsx
import { createPortal } from 'react-dom';

export default function PurchaseDetailModal({ show, onClose, phieu }) {
  if (!show || !phieu) return null;

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
  };

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
              Chi tiết phiếu {phieu.soPhieu || phieu.maPhieu}
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
                <strong>Nhà cung cấp:</strong>{' '}
                {phieu.nhaCungCap || <em className="text-muted">Chưa có</em>}
              </div>
              <div className="col-md-6">
                <strong>Người tạo:</strong>{' '}
                {phieu.nguoiTao || <em className="text-muted">Chưa có</em>}
              </div>
              <div className="col-md-6">
                <strong>Ngày tạo:</strong> {formatDate(phieu.ngayTao)}
              </div>
              <div className="col-md-6">
                <strong>Tổng tiền:</strong>{' '}
                <span className="text-danger fw-bold fs-5">
                  {formatCurrency(phieu.tongTien)}
                </span>
              </div>
              {phieu.ghiChu && (
                <div className="col-12">
                  <strong>Ghi chú:</strong> {phieu.ghiChu}
                </div>
              )}
            </div>

            {phieu.chiTietList && phieu.chiTietList.length > 0 && (
              <>
                <hr className="my-4" />
                <h6 className="mb-3">Danh sách thiết bị</h6>
                <div className="table-responsive">
                  <table className="table table-sm table-bordered align-middle">
                    <thead className="table-light">
                      <tr>
                        <th>Mã TB</th>
                        <th>Tên</th>
                        <th>SL</th>
                        <th>Đơn giá</th>
                        <th>Thành tiền</th>
                        <th>BH</th>
                        <th>Ghi chú</th>
                      </tr>
                    </thead>
                    <tbody>
                      {phieu.chiTietList.map((item) => (
                        <tr key={item.maChiTiet}>
                          <td className="fw-medium">{item.maTb}</td>
                          <td>{item.tenThietBi}</td>
                          <td>{item.soLuong}</td>
                          <td>{formatCurrency(item.donGia)}</td>
                          <td>{formatCurrency(item.thanhTien)}</td>
                          <td>{item.thoiGianBaoHanh} tháng</td>
                          <td className="text-muted small">{item.ghiChu || '—'}</td>
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

  // RENDER RA BODY → THOÁT KHỎI MỌI CSS CHA
  return createPortal(modalContent, document.body);
}