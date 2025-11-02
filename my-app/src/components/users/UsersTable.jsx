// src/components/users/UsersTable.jsx
const users = [
  {
    username: 'admin',
    fullName: 'Nguyễn Văn Quản trị',
    email: 'admin@utc.edu.vn',
    phone: '0987654321',
    role: 'Quản trị hệ thống',
    unit: '-',
    status: 'Hoạt động',
  },
  {
    username: 'qttb1',
    fullName: 'Trần Văn Thiết bị',
    email: 'qttb@utc.edu.vn',
    phone: '0912345678',
    role: 'Phòng Quản trị thiết bị',
    unit: 'Khoa CNTT',
    status: 'Hoạt động',
  },
  {
    username: 'gv_cntt',
    fullName: 'Lê Thị Giảng viên',
    email: 'gv.cntt@utc.edu.vn',
    phone: '0923456789',
    role: 'Nhân viên',
    unit: 'Khoa CNTT',
    status: 'Hoạt động',
  },
  {
    username: 'truong_khoa_toan',
    fullName: 'Phạm Văn Hành chính',
    email: 'tk.toan@utc.edu.vn',
    phone: '0934567890',
    role: 'Trưởng khoa',
    unit: 'Khoa Toán',
    status: 'Hoạt động',
  },
];

const getRoleBadgeClass = (role) => {
  if (role.includes('Quản trị')) return 'bg-secondary-subtle text-secondary';
  if (role.includes('Trưởng')) return 'bg-primary-subtle text-primary';
  if (role.includes('Phòng')) return 'bg-info-subtle text-info';
  return 'bg-light text-dark';
};

const getStatusBadgeClass = (status) => {
  return status === 'Hoạt động'
    ? 'bg-success-subtle text-success'
    : 'bg-danger-subtle text-danger';
};

export default function UsersTable() {
  return (
    <div className="table-responsive">
      <table className="table table-hover align-middle">
        <thead className="table-light">
          <tr>
            <th>Tên đăng nhập</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Số điện thoại</th>
            <th>Vai trò</th>
            <th>Đơn vị</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.username}>
              <td className="fw-semibold">{user.username}</td>
              <td>{user.fullName}</td>
              <td>{user.email}</td>
              <td>{user.phone}</td>
              <td>
                <span className={`badge ${getRoleBadgeClass(user.role)}`}>
                  {user.role}
                </span>
              </td>
              <td>{user.unit}</td>
              <td>
                <span className={`badge ${getStatusBadgeClass(user.status)}`}>
                  {user.status}
                </span>
              </td>
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