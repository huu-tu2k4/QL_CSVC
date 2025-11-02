// src/components/inventory/InventoryTable.jsx
const inventoryData = [
  {
    id: 'KK202410',
    unit: 'Khoa CNTT',
    checker: 'Nguyễn Văn Quản trị',
    date: '15/10/2024',
    total: 456,
    good: 420,
    missing: 2,
    broken: 4,
    moved: 30,
  },
  {
    id: 'KK202409',
    unit: 'Khoa Toán',
    checker: 'Trần Văn Thiết bị',
    date: '20/9/2024',
    total: 312,
    good: 305,
    missing: 1,
    broken: 2,
    moved: 4,
  },
];

export default function InventoryTable() {
  return (
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
          {inventoryData.map((item) => (
            <tr key={item.id}>
              <td className="fw-semibold">{item.id}</td>
              <td>{item.unit}</td>
              <td>{item.checker}</td>
              <td>{item.date}</td>
              <td>{item.total}</td>
              <td>
                <span className="badge bg-success-subtle text-success">
                  {item.good}
                </span>
              </td>
              <td>
                <span className="badge bg-danger-subtle text-danger">
                  {item.missing}
                </span>
              </td>
              <td>
                <span className="badge bg-warning-subtle text-warning">
                  {item.broken}
                </span>
              </td>
              <td>
                <span className="badge bg-info-subtle text-info">
                  {item.moved}
                </span>
              </td>
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