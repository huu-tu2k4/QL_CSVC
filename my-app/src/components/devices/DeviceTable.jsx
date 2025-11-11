// src/components/devices/DevicesTable.jsx
import { useEffect, useState } from 'react';
import { getAllThietBi } from '../../services/DeviceService';

const statusColors = {
  HOAT_DONG: 'bg-success-subtle text-success',
  HONG: 'bg-danger-subtle text-danger',
  DANG_SU_DUNG: 'bg-info-subtle text-info',
  BAO_TRI: 'bg-warning-subtle text-warning',
};

export default function DevicesTable() {
  const [devices, setDevices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [keyword, setKeyword] = useState('');
  const [trangThai, setTrangThai] = useState('');

  const fetchDevices = async () => {
    setLoading(true);
    try {
      const data = await getAllThietBi(keyword, trangThai || null, page, 10);
      setDevices(data.content);
      setTotalPages(data.totalPages);
    } catch (error) {
      console.error('Lỗi tải thiết bị:', error);
      alert('Không thể tải danh sách thiết bị');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchDevices();
  }, [page, keyword, trangThai]);

  return (
    <div>
      {/* Bộ lọc */}
      <div className="row mb-3 g-3">
        <div className="col-md-6">
          <input
            type="text"
            className="form-control"
            placeholder="Tìm kiếm thiết bị..."
            value={keyword}
            onChange={(e) => {
              setKeyword(e.target.value);
              setPage(0);
            }}
          />
        </div>
        <div className="col-md-4">
          <select
            className="form-select"
            value={trangThai}
            onChange={(e) => {
              setTrangThai(e.target.value);
              setPage(0);
            }}
          >
            <option value="">Tất cả trạng thái</option>
            <option value="DANG_SU_DUNG">Đang sử dụng</option>
            <option value="HONG">Hỏng</option>
            <option value="BAO_TRI">Bảo trì</option>
          </select>
        </div>
      </div>

      {/* Bảng */}
      <div className="table-responsive">
        <table className="table table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th>Mã TB</th>
              <th>Tên thiết bị</th>
              <th>Loại</th>
              <th>Phòng</th>
              <th>Trạng thái</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="7" className="text-center">Đang tải...</td></tr>
            ) : devices.length === 0 ? (
              <tr><td colSpan="7" className="text-center text-muted">Không có dữ liệu</td></tr>
            ) : (
              devices.map((device) => (
                <tr key={device.maThietBi}>
                  <td className="fw-semibold">{device.maThietBi}</td>
                  <td>{device.tenThietBi}</td>

                  {/* SỬA TẠI ĐÂY – DÙNG TÊN TRƯỜNG ĐÚNG */}
                  <td>{device.tenLoaiThietBi || '-'}</td>
                  <td>{device.tenPhongHoc || '-'}</td>

                  <td>
                    <span className={`badge ${statusColors[device.trangThai] || 'bg-secondary-subtle'}`}>
                      {device.trangThai?.replace('_', ' ') || '—'}
                    </span>
                  </td>
                  <td>
                    <button className="btn btn-sm btn-outline-secondary" title="Xem">
                      <i className="bi bi-eye"></i>
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {/* Phân trang */}
      {totalPages > 1 && (
        <nav className="d-flex justify-content-center mt-3">
          <ul className="pagination">
            <li className={`page-item ${page === 0 ? 'disabled' : ''}`}>
              <button className="page-link" onClick={() => setPage(page - 1)}>Trước</button>
            </li>
            {[...Array(totalPages)].map((_, i) => (
              <li key={i} className={`page-item ${i === page ? 'active' : ''}`}>
                <button className="page-link" onClick={() => setPage(i)}>{i + 1}</button>
              </li>
            ))}
            <li className={`page-item ${page === totalPages - 1 ? 'disabled' : ''}`}>
              <button className="page-link" onClick={() => setPage(page + 1)}>Sau</button>
            </li>
          </ul>
        </nav>
      )}
    </div>
  );
}