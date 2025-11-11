// src/components/maintenance/MaintenanceTable.jsx
export default function MaintenanceTable({ tickets }) {
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