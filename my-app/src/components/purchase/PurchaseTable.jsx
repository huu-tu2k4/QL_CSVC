// src/components/purchase/PurchaseTable.jsx
const purchases = [
  {
    id: 'MS20240901',
    supplier: 'Công ty Thiết bị ABC',
    creator: 'Nguyễn Văn Quản trị',
    date: '1/9/2024',
    total: '18.000.000 VNĐ',
  },
  {
    id: 'MS20240315',
    supplier: 'Công ty Thiết bị ABC',
    creator: 'Trần Văn Thiết bị',
    date: '15/3/2024',
    total: '75.000.000 VNĐ',
  },
  {
    id: 'MS20240820',
    supplier: 'Công ty Nội thất Hòa Phát',
    creator: 'Nguyễn Văn Quản trị',
    date: '20/8/2024',
    total: '70.000.000 VNĐ',
  },
];

export default function PurchaseTable() {
  return (
    <div className="table-responsive">
      <table className="table table-hover align-middle">
        <thead className="table-light">
          <tr>
            <th>Số phiếu</th>
            <th>Nhà cung cấp</th>
            <th>Người tạo</th>
            <th>Ngày tạo</th>
            <th>Tổng tiền</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {purchases.map((p) => (
            <tr key={p.id}>
              <td className="fw-semibold">{p.id}</td>
              <td>{p.supplier}</td>
              <td>{p.creator}</td>
              <td className="text-muted small">{p.date}</td>
              <td className="text-end text-nowrap fw-medium">{p.total}</td>
              <td>
                <button className="btn btn-sm btn-outline-secondary" title="Xem chi tiết">
                  <i className="bi bi-file-text"></i>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}