// src/components/users/UsersTable.jsx
import React, { useState, useEffect } from 'react';
import userService from '../../services/userService';
import UserEditModal from './UserEditModal';

export default function UsersTable() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedUser, setSelectedUser] = useState(null);
  const [editModalOpen, setEditModalOpen] = useState(false);

  const pageSize = 1000; // lấy nhiều dữ liệu để search frontend

  const fetchUsers = async () => {
    setLoading(true);
    try {
      const res = await userService.getAllUsers(0, pageSize);
      setUsers(res.data);
    } catch (err) {
      console.error('Lỗi tải danh sách người dùng:', err);
      alert('Lỗi tải dữ liệu: ' + (err.message || 'Kiểm tra console'));
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const filteredUsers = users.filter(u =>
    u.tenDangNhap.toLowerCase().includes(searchTerm.toLowerCase()) ||
    (u.email || '').toLowerCase().includes(searchTerm.toLowerCase())
  );

  const getRoleBadge = (vaiTro) => {
    if (!vaiTro || !vaiTro.tenVaiTro) return 'bg-secondary';
    const role = vaiTro.tenVaiTro.toLowerCase();
    if (role.includes('admin') || role.includes('quản trị')) return 'bg-danger text-white';
    if (role.includes('bảo trì')) return 'bg-warning text-dark';
    if (role.includes('kiểm kê')) return 'bg-primary text-white';
    return 'bg-success text-white';
  };

  const handleDelete = async (user) => {
    if (!window.confirm(`Bạn có chắc muốn xóa người dùng ${user.tenDangNhap}?`)) return;
    try {
      await userService.deleteUser(user.maNguoiDung);
      alert('Xóa thành công!');
      fetchUsers();
    } catch (err) {
      console.error(err);
      alert('Xóa thất bại!');
    }
  };

  const handleEditSave = async (maNguoiDung, updatedUser) => {
  try {
    // Gọi API update backend
    await userService.updateUser(maNguoiDung, updatedUser);

    // Fetch lại danh sách từ backend để FE render đúng
    await fetchUsers();

    setEditModalOpen(false);
  } catch (err) {
    console.error('Lỗi cập nhật:', err.response?.data || err);
    alert('Cập nhật thất bại! ');
  }
};




  if (loading) return (
    <div className="text-center py-5">
      <div className="spinner-border text-primary"></div>
    </div>
  );

  return (
    <div className="card shadow-sm border-0">
      <div className="card-header bg-primary text-white">
        <h4 className="mb-0">
          <i className="bi bi-people-fill me-2"></i>
          Quản lý người dùng ({filteredUsers.length})
        </h4>
      </div>
      <div className="card-body">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Tìm kiếm tên, email..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            onKeyDown={e => { if (e.key === 'Enter') setSearchTerm(e.target.value) }}
          />
          <button className="btn btn-primary" onClick={() => setSearchTerm(searchTerm)}>
            <i className="bi bi-search"></i>
          </button>
        </div>

        <div className="table-responsive">
          <table className="table table-hover align-middle">
            <thead className="table-light">
              <tr>
                <th>Tên đăng nhập</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Vai trò</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              {filteredUsers.map((user) => (
                <tr key={user.maNguoiDung}>
                  <td className="fw-bold">{user.tenDangNhap}</td>
                  <td>{user.hoTen}</td>
                  <td>{user.email || <span className="text-muted">Chưa có</span>}</td>
                  <td>
                    <span className={`badge rounded-pill ${getRoleBadge(user.maVaiTro)}`}>
                      {user.maVaiTro?.tenVaiTro || 'Chưa phân quyền'}
                    </span>
                  </td>
                  <td>
                    <span className={`badge ${user.trangThai ? 'bg-success' : 'bg-secondary'} text-white`}>
                      {user.trangThai ? 'Hoạt động' : 'Bị khóa'}
                    </span>
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-primary me-1"
                      onClick={() => { setSelectedUser(user); setEditModalOpen(true); }}
                      title="Sửa"
                    >
                      <i className="bi bi-pencil"></i>
                    </button>
                    <button
                      className="btn btn-sm btn-outline-danger"
                      onClick={() => handleDelete(user)}
                      title="Xóa"
                    >
                      <i className="bi bi-trash"></i>
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {editModalOpen && selectedUser && (
          <UserEditModal
            show={editModalOpen}
            user={selectedUser}
            onClose={() => setEditModalOpen(false)}
            onSave={handleEditSave}
          />
        )}
      </div>
    </div>
  );
}
