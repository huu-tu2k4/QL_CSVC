// src/components/maintenance/MaintenanceTable.jsx
const tickets = [
  {
    id: 'BT001',
    device: 'Máy tính Dell Optiplex 3070',
    deviceCode: 'TB001',
    requester: 'Trần Văn Thiết bị',
    unit: 'Khoa CNTT',
    date: '09:00:00 25/10/2024',
    priority: 'Cao',
    priorityColor: 'warning',
    status: 'Đang xử lý',
    statusColor: 'info',
    cost: '-',
  },
  {
    id: 'BT002',
    device: 'Máy chiếu Epson EB-2250U',
    deviceCode: 'TB002',
    requester: 'Lê Thị Giảng viên',
    unit: 'Khoa CNTT',
    date: '14:30:00 26/10/2024',
    priority: 'Khẩn cấp',
    priorityColor: 'danger',
    status: 'Mới',
    statusColor: 'secondary',
    cost: '-',
  },
  {
    id: 'BT003',
    device: 'Máy in HP LaserJet Pro',
    deviceCode: 'TB004',
    requester: 'Nguyễn Văn Quản trị',
    unit: 'Khoa CNTT',
    date: '10:00:00 24/10/2024',
    priority: 'Bình thường',
    priorityColor: 'primary',
    status: 'Hoàn tất',
    statusColor: 'success',
    cost: '500.000 đ',
  },
  {
    id: 'BT004',
    device: 'Điều hòa Daikin 2HP',
    deviceCode: 'TB005',
    requester: 'Phạm Văn Hành chính',
    unit: 'Khoa Toán',
    date: '08:00:00 20/10/2024',
    priority: 'Cao',
    priorityColor: 'warning',
    status: 'Hoàn tất',
    statusColor: 'success',
    cost: '1.200.000 đ',
  },
];

export default function MaintenanceTable() {
  return (
    <div className="table-responsive">
      <table className="table table-hover align-middle">
        <thead className="table-light">
          <tr>
            <th>Số phiếu</th>
            <th>Thiết bị</th>
            <th>Người đề xuất</th>
            <th>Ngày đề xuất</th>
            <th>Mức ưu tiên</th>
            <th>Trạng thái</th>
            <th>Chi phí</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {tickets.map((t) => (
            <tr key={t.id}>
              <td className="fw-semibold">{t.id}</td>
              <td>
                <div>
                  <p className="mb-0 fw-medium">{t.device}</p>
                  <p className="mb-0 text-muted small">{t.deviceCode}</p>
                </div>
              </td>
              <td>
                <div>
                  <p className="mb-0">{t.requester}</p>
                  <p className="mb-0 text-muted small">{t.unit}</p>
                </div>
              </td>
              <td className="text-muted small">{t.date}</td>
              <td>
                <span className={`badge bg-${t.priorityColor}-subtle text-${t.priorityColor}`}>
                  {t.priority}
                </span>
              </td>
              <td>
                <span className={`badge bg-${t.statusColor}-subtle text-${t.statusColor}`}>
                  {t.status}
                </span>
              </td>
              <td className="text-end">{t.cost}</td>
              <td>
                <button className="btn btn-sm btn-outline-secondary" title="Xem chi tiết">
                  <i className="bi bi-eye"></i>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}