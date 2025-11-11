// src/components/inventory/InventoryTable.jsx
import { useEffect, useState } from 'react';
import { getAllKiemKe, getKiemKeById } from '../../services/InventoryService';
import InventoryDetailModal from './InventoryDetailModal';

export default function InventoryTable() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    try {
      const result = await getAllKiemKe();
      setData(result);
    } catch (error) {
      console.error('Lỗi tải kiểm kê:', error);
      alert('Không thể tải dữ liệu');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleViewDetail = async (maKK) => {
    try {
      const detail = await getKiemKeById(maKK);
      setSelected(detail);
      setShowModal(true);
    } catch (error) {
      alert('Không thể tải chi tiết');
    }
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('vi-VN');
  };

  return (
    <>
      <div className="table-responsive">
        <table className="table table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th>Số phiếu</th>
              <th>Đơn vị</th>
              <th>Người kiểm kê</th>
              <th>Ngày kiểm kê</th>
              <th>Tổng TB</th>
              <th>Tốt</th>
              <th>Thiếu</th>
              <th>Hỏng</th>
              <th>Chuyển vị trí</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="10" className="text-center py-4">Đang tải...</td></tr>
            ) : data.length === 0 ? (
              <tr><td colSpan="10" className="text-center text-muted py-4">Chưa có phiếu kiểm kê</td></tr>
            ) : (
              data.map((item) => (
                <tr key={item.maKK}>
                  <td className="fw-semibold">{item.soPhieu || item.maKK}</td>
                  <td>{item.tenDonVi}</td>
                  <td>{item.nguoiKiemKe}</td>
                  <td className="text-muted small">{formatDate(item.ngayKiemKe)}</td>
                  <td>{item.tongTB}</td>
                  <td>
                    <span className="badge bg-success-subtle text-success">
                      {item.soTot}
                    </span>
                  </td>
                  <td>
                    <span className="badge bg-danger-subtle text-danger">
                      {item.soThieu}
                    </span>
                  </td>
                  <td>
                    <span className="badge bg-warning-subtle text-warning">
                      {item.soHong}
                    </span>
                  </td>
                  <td>
                    <span className="badge bg-info-subtle text-info">
                      {item.soChuyenViTri}
                    </span>
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() => handleViewDetail(item.maKK)}
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

      {/* Modal dùng Portal → Không bị cắt */}
      <InventoryDetailModal
        show={showModal}
        onClose={() => setShowModal(false)}
        phieu={selected}
      />
    </>
  );
}