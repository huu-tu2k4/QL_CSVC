// src/components/devices/DeviceTable.jsx
const devices = [
  {
    id: 'TB001',
    name: 'Máy tính Dell Optiplex 3070',
    model: 'Dell Optiplex 3070',
    type: 'Thiết bị CNTT',
    serial: 'SN12345',
    unit: 'Khoa CNTT',
    location: 'Tòa A - P101',
    room: 'Phòng B202',
    status: 'Đang sử dụng',
    statusColor: 'secondary',
  },
  {
    id: 'TB002',
    name: 'Máy chiếu Epson EB-2250U',
    model: 'Epson EB-2250U',
    type: 'Thiết bị CNTT',
    serial: 'EP789456',
    unit: 'Khoa CNTT',
    location: 'Tòa B - P205',
    room: 'Phòng A101',
    status: 'Sẵn sàng',
    statusColor: 'primary',
  },
  {
    id: 'TB003',
    name: 'Bàn giảng viên gỗ',
    model: 'Hòa Phát Standard',
    type: 'Thiết bị nội thất',
    serial: 'NT001',
    unit: 'Khoa Toán',
    location: 'Tòa A - P101',
    room: 'Phòng A101',
    status: 'Đang sử dụng',
    statusColor: 'secondary',
  },
  {
    id: 'TB004',
    name: 'Máy in HP LaserJet Pro',
    model: 'HP LaserJet Pro M404dn',
    type: 'Thiết bị CNTT',
    serial: 'HP445566',
    unit: 'Khoa CNTT',
    location: 'Tòa V - P303',
    room: 'Phòng V303',
    status: 'Bảo trì',
    statusColor: 'warning',
  },
];

export default function DeviceTable() {
  return (
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
          {devices.map((d) => (
            <tr key={d.id}>
              <td className="fw-semibold">{d.id}</td>
              <td>
                <div>
                  <p className="mb-0 fw-medium">{d.name}</p>
                  <p className="mb-0 text-muted small">{d.model}</p>
                </div>
              </td>
              <td>{d.type}</td>
              <td className="text-muted small">{d.serial}</td>
              <td>{d.unit}</td>
              <td>
                <div>
                  <p className="mb-0">{d.location}</p>
                  <p className="mb-0 text-muted small">{d.room}</p>
                </div>
              </td>
              <td>
                <span className={`badge bg-${d.statusColor}-subtle text-${d.statusColor}`}>
                  {d.status}
                </span>
              </td>
              <td>
                <div className="d-flex gap-1">
                  <button className="btn btn-sm btn-outline-secondary" title="Xem">
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
          ))}
        </tbody>
      </table>
    </div>
  );
}