// src/components/equipment/EquipmentTable.jsx
import { useEffect, useState } from 'react';
import { getAllThietBi, getThietBiById } from '../../services/DeviceService';
import EquipmentDetailModal from './DeviceDetailModal';

const getStatusBadge = (trangThai) => {
  const map = {
    DANG_SU_DUNG: 'bg-info-subtle text-info',
    SAN_SANG: 'bg-success-subtle text-success',
    BAO_TRI: 'bg-warning-subtle text-warning',
    HONG: 'bg-danger-subtle text-danger',
  };
  return map[trangThai] || 'bg-secondary-subtle text-secondary';
};

export default function EquipmentTable() {
  const [data, setData] = useState({ content: [], totalPages: 0 });
  const [loading, setLoading] = useState(true);
  const [keyword, setKeyword] = useState('');
  const [trangThai, setTrangThai] = useState('');
  const [page, setPage] = useState(0);
  const [selected, setSelected] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    try {
      const result = await getAllThietBi(keyword, trangThai || null, page, 10);
      setData(result);
    } catch (error) {
      alert('Không thể tải dữ liệu');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [keyword, trangThai, page]);

  const handleViewDetail = async (maThietBi) => {
    try {
      const detail = await getThietBiById(maThietBi);
      setSelected(detail);
      setShowModal(true);
    } catch (error) {
      alert('Không thể tải chi tiết');
    }
  };

  return (
    <>
      {/* Tìm kiếm + Lọc */}
      <div className="d-flex gap-3 mb-4 flex-wrap">
        <div className="position-relative flex-fill" style={{ maxWidth: '400px' }}>
          <i className="bi bi-search position-absolute top-50 start-3 translate-middle-y text-muted"></i>
          <input
            type="text"
            className="form-control ps-5"
            placeholder="Tìm kiếm theo mã TB, tên, serial..."
            value={keyword}
            onChange={(e) => {
              setKeyword(e.target.value);
              setPage(0);
            }}
          />
        </div>
        <select
          className="form-select w-auto"
          value={trangThai}
          onChange={(e) => {
            setTrangThai(e.target.value);
            setPage(0);
          }}
        >
          <option value="">Tất cả trạng thái</option>
          <option value="DANG_SU_DUNG">Đang sử dụng</option>
          <option value="SAN_SANG">Sẵn sàng</option>
          <option value="BAO_TRI">Bảo trì</option>
          <option value="HONG">Hỏng</option>
        </select>
      </div>

      {/* Bảng */}
      <div className="table-responsive">
        <table className="table table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th>Mã TB</th>
              <th>Tên thiết bị</th>
              <th>Loại</th>
              <th>Serial</th>
              <th>Đơn vị</th>
              <th>Vị trí</th>
              <th>Trạng thái</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="8" className="text-center py-4">Đang tải...</td></tr>
            ) : data.content.length === 0 ? (
              <tr><td colSpan="8" className="text-center text-muted py-4">Không có thiết bị</td></tr>
            ) : (
              data.content.map((item) => (
                <tr key={item.maThietBi}>
                  <td className="fw-semibold">{item.maThietBi}</td>
                  <td>
                    <div>
                      <p className="mb-0">{item.tenThietBi}</p>
                      <p className="text-xs text-muted mb-0">{item.tenLoaiThietBi}</p>
                    </div>
                  </td>
                  <td>{item.tenLoaiThietBi}</td>
                  <td className="text-muted small">{item.serialNumber}</td>
                  <td>{item.tenDonVi}</td>
                  <td>
                    <div>
                      <p className="mb-0 small">{item.toaNha}</p>
                      <p className="text-xs text-muted mb-0">{item.tenPhongHoc}</p>
                    </div>
                  </td>
                  <td>
                    <span className={`badge ${getStatusBadge(item.trangThai)}`}>
                      {item.trangThai?.replace('_', ' ')}
                    </span>
                  </td>
                  <td>
                    <div className="d-flex gap-1">
                      <button
                        className="btn btn-sm btn-outline-secondary"
                        onClick={() => handleViewDetail(item.maThietBi)}
                        title="Xem chi tiết"
                      >
                        <i className="bi bi-eye"></i>
                      </button>
                      <button className="btn btn-sm btn-outline-secondary" title="Sửa">
                        <i className="bi bi-pencil-square"></i>
                      </button>
                      <button className="btn btn-sm btn-outline-secondary" title="Lịch sử">
                        <i className="bi bi-clock-history"></i>
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {/* Phân trang */}
      {data.totalPages > 1 && (
        <nav className="d-flex justify-content-center mt-3">
          <ul className="pagination">
            <li className={`page-item ${page === 0 ? 'disabled' : ''}`}>
              <button className="page-link" onClick={() => setPage(page - 1)}>«</button>
            </li>
            {[...Array(data.totalPages)].map((_, i) => (
              <li key={i} className={`page-item ${i === page ? 'active' : ''}`}>
                <button className="page-link" onClick={() => setPage(i)}>{i + 1}</button>
              </li>
            ))}
            <li className={`page-item ${page === data.totalPages - 1 ? 'disabled' : ''}`}>
              <button className="page-link" onClick={() => setPage(page + 1)}>»</button>
            </li>
          </ul>
        </nav>
      )}

      {/* Modal */}
      <EquipmentDetailModal show={showModal} onClose={() => setShowModal(false)} thietBi={selected} />
    </>
  );
}