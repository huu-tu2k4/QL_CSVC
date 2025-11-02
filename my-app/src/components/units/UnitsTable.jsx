// src/components/units/UnitsTable.jsx
const units = [
  {
    code: 'CNTT',
    name: 'Khoa Công nghệ thông tin',
    description: 'Khoa CNTT',
    devices: 456,
    staff: 25,
  },
  {
    code: 'TOAN',
    name: 'Khoa Toán',
    description: 'Khoa Toán',
    devices: 312,
    staff: 18,
  },
  {
    code: 'QTTB',
    name: 'Phòng Quản trị thiết bị',
    description: 'Phòng quản lý tài sản và thiết bị',
    devices: 0,
    staff: 8,
  },
];

export default function UnitsTable() {
  return (
    <div className="table-responsive">
      <table className="table table-hover align-middle">
        <thead className="table-light">
          <tr>
            <th>Mã code</th>
            <th>Tên đơn vị</th>
            <th>Mô tả</th>
            <th>Số thiết bị</th>
            <th>Số nhân viên</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {units.map((unit) => (
            <tr key={unit.code}>
              <td className="fw-semibold">{unit.code}</td>
              <td>{unit.name}</td>
              <td className="text-muted small">{unit.description}</td>
              <td>{unit.devices}</td>
              <td>{unit.staff}</td>
              <td>
                <div className="d-flex gap-1">
                  <button
                    className="btn btn-sm btn-outline-secondary"
                    title="Sửa"
                  >
                    <i className="bi bi-pencil-square"></i>
                  </button>
                  <button
                    className="btn btn-sm btn-outline-danger"
                    title="Xóa"
                  >
                    <i className="bi bi-trash"></i>
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