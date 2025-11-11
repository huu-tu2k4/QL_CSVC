// src/components/rooms/RoomsTable.jsx
import { useEffect, useState } from 'react';
import { getAllPhong, getPhongById, deletePhong } from '../../services/RoomService';
import RoomDetailModal from './RoomDetailModal';

const getTypeLabel = (loai) => {
  switch (loai) {
    case 'PHONG_HOC': return 'Phòng học';
    case 'PHONG_MAY': return 'Phòng máy';
    case 'VAN_PHONG': return 'Văn phòng';
    case 'PHONG_THI_NGHIEM': return 'Phòng thí nghiệm';
    default: return loai;
  }
};

const getTypeBadge = (loai) => {
  switch (loai) {
    case 'PHONG_HOC': return 'bg-info-subtle text-info';
    case 'PHONG_MAY': return 'bg-purple-subtle text-purple';
    case 'VAN_PHONG': return 'bg-warning-subtle text-warning';
    case 'PHONG_THI_NGHIEM': return 'bg-success-subtle text-success';
    default: return 'bg-secondary-subtle text-secondary';
  }
};

export default function RoomsTable() {
  const [rooms, setRooms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const fetchRooms = async () => {
    setLoading(true);
    try {
      const data = await getAllPhong();
      setRooms(data);
    } catch (error) {
      console.error('Lỗi tải phòng:', error);
      alert('Không thể tải danh sách phòng');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRooms();
  }, []);

  const handleViewDetail = async (maPhong) => {
    try {
      const detail = await getPhongById(maPhong);
      setSelected(detail);
      setShowModal(true);
    } catch (error) {
      alert('Không thể tải chi tiết phòng');
    }
  };

  const handleDelete = async (maPhong) => {
    if (!window.confirm(`Xóa phòng ${maPhong}?`)) return;
    try {
      await deletePhong(maPhong);
      setRooms(rooms.filter(r => r.maPhong !== maPhong));
      alert('Đã xóa phòng thành công!');
    } catch (error) {
      alert('Lỗi xóa phòng: ' + error.response?.data || error.message);
    }
  };

  return (
    <>
      <div className="table-responsive">
        <table className="table table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th>Mã phòng</th>
              <th>Tên phòng</th>
              <th>Loại phòng</th>
              <th>Vị trí</th>
              <th>Sức chứa</th>
              <th>Đơn vị</th>
              <th>Số thiết bị</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr><td colSpan="8" className="text-center py-4">Đang tải...</td></tr>
            ) : rooms.length === 0 ? (
              <tr><td colSpan="8" className="text-center text-muted py-4">Chưa có phòng</td></tr>
            ) : (
              rooms.map((room) => (
                <tr key={room.maPhong}>
                  <td className="fw-semibold">{room.maCode}</td>
                  <td>{room.tenPhong}</td>
                  <td>
                    <span className={`badge ${getTypeBadge(room.loaiPhong)}`}>
                      {getTypeLabel(room.loaiPhong)}
                    </span>
                  </td>
                  <td>
                    <div>
                      <div>{room.toaNha}</div>
                      <div className="text-muted small">Tầng {room.tang}</div>
                    </div>
                  </td>
                  <td>{room.sucChua} người</td>
                  <td>{room.maDonVi}</td>
                  <td>{room.thietBis?.length || 0}</td>
                  <td>
                    <div className="d-flex gap-1">
                      <button
                        className="btn btn-sm btn-outline-secondary"
                        onClick={() => handleViewDetail(room.maPhong)}
                        title="Xem chi tiết"
                      >
                        <i className="bi bi-eye"></i>
                      </button>
                      <button
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => handleDelete(room.maPhong)}
                        title="Xóa"
                      >
                        <i className="bi bi-trash"></i>
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      <RoomDetailModal
        show={showModal}
        onClose={() => setShowModal(false)}
        phong={selected}
      />
    </>
  );
}