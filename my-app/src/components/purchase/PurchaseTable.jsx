// src/components/purchase/PurchaseTable.jsx
import { useEffect, useState } from 'react';
import { getAllPurchases, getPurchaseById } from '../../services/PurchaseService';
import PurchaseDetailModal from './PurchaseDetailModal';

export default function PurchaseTable() {
  const [purchases, setPurchases] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const fetchPurchases = async () => {
    setLoading(true);
    try {
      const data = await getAllPurchases();
      setPurchases(data);
    } catch (error) {
      alert('Không thể tải dữ liệu');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPurchases();
  }, []);

  const handleViewDetail = async (maPhieu) => {
    try {
      const detail = await getPurchaseById(maPhieu);
      setSelected(detail);
      setShowModal(true);
    } catch (error) {
      alert('Lỗi tải chi tiết');
    }
  };

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('vi-VN');
  };

  return (
    <>
      {/* BẢNG */}
      <div className="table-responsive">
        <table className="table table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th>Số phiếu</th>
              <th>Nhà cung cấp</th>
              <th>Người tạo</th>
              <th>Ngày tạo</th>
              <th>Tổng tiền</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="6" className="text-center py-4">Đang tải...</td></tr>
            ) : purchases.length === 0 ? (
              <tr><td colSpan="6" className="text-center text-muted py-4">Chưa có phiếu</td></tr>
            ) : (
              purchases.map((p) => (
                <tr key={p.maPhieu}>
                  <td className="fw-semibold">{p.soPhieu || p.maPhieu}</td>
                  <td>{p.nhaCungCap || <span className="text-muted">—</span>}</td>
                  <td>{p.nguoiTao || <span className="text-muted">—</span>}</td>
                  <td className="text-muted small">{formatDate(p.ngayTao)}</td>
                  <td className="text-nowrap fw-medium">
                    {formatCurrency(p.tongTien)}
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() => handleViewDetail(p.maPhieu)}
                    >
                      <i className="bi bi-file-text"></i>
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {/* MODAL RIÊNG – DÙNG PORTAL */}
      <PurchaseDetailModal
        show={showModal}
        onClose={() => setShowModal(false)}
        phieu={selected}
      />
    </>
  );
}