// src/components/liquidation/LiquidationTable.jsx
import { useEffect, useState } from 'react';
import { getAllThanhLy, getThanhLyById } from '../../services/LiquidationService';
import LiquidationDetailModal from './LiquidationDetailModal';

const formatCurrency = (amount) => {
  return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
};

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString('vi-VN');
};

export default function LiquidationTable() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    try {
      const result = await getAllThanhLy();
      setData(result);
    } catch (error) {
      console.error('Lỗi tải thanh lý:', error);
      alert('Không thể tải dữ liệu');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleViewDetail = async (maTL) => {
    try {
      const detail = await getThanhLyById(maTL);
      setSelected(detail);
      setShowModal(true);
    } catch (error) {
      alert('Không thể tải chi tiết');
    }
  };

  return (
    <>
      <div className="table-responsive">
        <table className="table table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th>Số phiếu</th>
              <th>Thiết bị</th>
              <th>Lý do</th>
              <th>Ngày thanh lý</th>
              <th>Giá trị gốc</th>
              <th>Giá trị TL</th>
              <th>Người duyệt</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="8" className="text-center py-4">Đang tải...</td></tr>
            ) : data.length === 0 ? (
              <tr><td colSpan="8" className="text-center text-muted py-4">Chưa có phiếu thanh lý</td></tr>
            ) : (
              data.map((item) => (
                <tr key={item.maPhieuTL}>
                  <td className="fw-semibold">{item.soPhieuTL}</td>
                  <td>{item.thietBi}</td>
                  <td className="small text-danger">{item.lyDo}</td>
                  <td className="text-muted small">{formatDate(item.ngayThanhLy)}</td>
                  <td className="text-decoration-line-through text-muted small">
                    {formatCurrency(item.giaTriGoc)}
                  </td>
                  <td className="text-success fw-medium">
                    {formatCurrency(item.giaTriThanhLy)}
                  </td>
                  <td>{item.nguoiDuyet}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() => handleViewDetail(item.maPhieuTL)}
                      title="Xem chi tiết"
                    >
                      <i className="bi bi-eye"></i>
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      <LiquidationDetailModal
        show={showModal}
        onClose={() => setShowModal(false)}
        phieu={selected}
      />
    </>
  );
}