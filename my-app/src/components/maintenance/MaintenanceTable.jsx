// src/components/maintenance/MaintenanceTable.jsx
import { useEffect, useState } from 'react';
import { getAllBaoTri, getBaoTriById } from '../../services/MaintenanceService';
import MaintenanceDetailModal from './MaintenanceDetailModal';

const formatCurrency = (amount) => {
  return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
};

const formatDate = (dateString) => {
  return dateString ? new Date(dateString).toLocaleDateString('vi-VN') : '';
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

export default function MaintenanceTable() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    try {
      const result = await getAllBaoTri();
      setData(result);
    } catch (error) {
      alert('Không thể tải dữ liệu');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleViewDetail = async (maBT) => {
    try {
      const detail = await getBaoTriById(maBT);
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
              <th>Mã TB</th>
              <th>Sự cố</th>
              <th>Ưu tiên</th>
              <th>Trạng thái</th>
              <th>Chi phí</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="8" className="text-center py-4">Đang tải...</td></tr>
            ) : data.length === 0 ? (
              <tr><td colSpan="8" className="text-center text-muted py-4">Chưa có phiếu bảo trì</td></tr>
            ) : (
              data.map((item) => (
                <tr key={item.maPhieuBT}>
                  <td className="fw-semibold">{item.soPhieuBt}</td>
                  <td>{item.tenThietBi}</td>
                  <td><code>{item.maThietBi}</code></td>
                  <td className="small text-danger">{item.moTaSuCo}</td>
                  <td>
                    <span className={`badge ${getPriorityBadge(item.mucUuTien)}`}>
                      {item.mucUuTien?.replace('_', ' ')}
                    </span>
                  </td>
                  <td>
                    <span className={`badge ${getStatusBadge(item.trangThai)}`}>
                      {item.trangThai?.replace('_', ' ')}
                    </span>
                  </td>
                  <td className="text-success fw-medium">
                    {item.chiPhi ? formatCurrency(item.chiPhi) : '—'}
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() => handleViewDetail(item.maPhieuBT)}
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

      <MaintenanceDetailModal
        show={showModal}
        onClose={() => setShowModal(false)}
        phieu={selected}
      />
    </>
  );
}