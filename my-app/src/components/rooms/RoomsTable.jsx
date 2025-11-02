// src/components/rooms/RoomsTable.jsx
const rooms = [
  {
    code: 'A101',
    name: 'Phòng học A101',
    type: 'Phòng học',
    building: 'Tòa A',
    floor: 'Tầng 1',
    capacity: '60 người',
    unit: 'Khoa CNTT',
    devices: 62,
  },
  {
    code: 'B202',
    name: 'Phòng máy B202',
    type: 'Phòng máy',
    building: 'Tòa B',
    floor: 'Tầng 2',
    capacity: '45 người',
    unit: 'Khoa CNTT',
    devices: 46,
  },
  {
    code: 'V303',
    name: 'Phòng làm việc V303',
    type: 'Văn phòng',
    building: 'Tòa V',
    floor: 'Tầng 3',
    capacity: '10 người',
    unit: 'Khoa Toán',
    devices: 15,
  },
  {
    code: 'C105',
    name: 'Phòng thí nghiệm C105',
    type: 'Phòng thí nghiệm',
    building: 'Tòa C',
    floor: 'Tầng 1',
    capacity: '30 người',
    unit: 'Khoa CNTT',
    devices: 35,
  },
];

const getBadgeClass = (type) => {
  switch (type) {
    case 'Phòng học': return 'bg-info-subtle text-info';
    case 'Phòng máy': return 'bg-purple-subtle text-purple';
    case 'Văn phòng': return 'bg-warning-subtle text-warning';
    case 'Phòng thí nghiệm': return 'bg-success-subtle text-success';
    default: return 'bg-secondary-subtle text-secondary';
  }
};

export default function RoomsTable() {
  return (
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
          {rooms.map((room) => (
            <tr key={room.code}>
              <td className="fw-semibold">{room.code}</td>
              <td>{room.name}</td>
              <td>
                <span className={`badge ${getBadgeClass(room.type)}`}>
                  {room.type}
                </span>
              </td>
              <td>
                <div>
                  <div>{room.building}</div>
                  <div className="text-muted small">{room.floor}</div>
                </div>
              </td>
              <td>{room.capacity}</td>
              <td>{room.unit}</td>
              <td>{room.devices}</td>
              <td>
                <div className="d-flex gap-1">
                  <button
                    className="btn btn-sm btn-outline-secondary"
                    title="Xem chi tiết"
                  >
                    <i className="bi bi-eye"></i>
                  </button>
                  <button
                    className="btn btn-sm btn-outline-secondary"
                    title="Sửa"
                  >
                    <i className="bi bi-pencil-square"></i>
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}