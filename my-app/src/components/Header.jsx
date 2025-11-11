// src/components/Header.jsx
import { useState } from 'react';

// import { useAuth } from '../contexts/AuthContext';

// const { user, logout } = useAuth();

// const currentUser = {
//   name: user?.fullName || 'Người dùng',
//   role: user?.role || 'Nhân viên',
//   avatar: user?.avatar,
// };

export default function Header({ toggleSidebar, isSidebarOpen }) {
  const today = new Date().toLocaleDateString('vi-VN', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });

  // Giả lập thông tin người dùng (sau này lấy từ context/auth)
  const currentUser = {
    name: 'Nguyễn Văn Quản trị',
    role: 'Quản trị hệ thống',
    avatar: '/avatar-placeholder.png', // hoặc dùng icon
  };

  const handleLogout = () => {
    // Xử lý đăng xuất (xóa token, chuyển hướng login, etc.)
    alert('Đã đăng xuất!');
    // Ví dụ: window.location.href = '/login';
  };

  return (
    <header className="bg-white shadow-sm border-bottom px-4 py-3 d-flex justify-content-between align-items-center">
      {/* Nút toggle sidebar */}
      <button
        onClick={toggleSidebar}
        className="btn btn-outline-secondary btn-sm"
        aria-label={isSidebarOpen ? 'Ẩn sidebar' : 'Hiện sidebar'}
      >
        <i className={`bi ${isSidebarOpen ? 'bi-x-lg' : 'bi-list'}`}></i>
      </button>

      {/* Ngày hiện tại */}
      <span className="text-muted d-none d-md-block">{today}</span>

      {/* Thông tin người dùng + Đăng xuất */}
      <div className="d-flex align-items-center gap-3">
        <div className="d-flex align-items-center gap-2">
          {/* Avatar */}
          <div
            className="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center"
            style={{ width: '36px', height: '36px', fontSize: '0.9rem' }}
          >
            {currentUser.avatar ? (
              <img
                src={currentUser.avatar}
                alt="Avatar"
                className="rounded-circle w-100 h-100"
                style={{ objectFit: 'cover' }}
              />
            ) : (
              currentUser.name.charAt(0).toUpperCase()
            )}
          </div>

          {/* Tên + Chức vụ */}
          <div className="d-none d-lg-block">
            <div className="fw-semibold small">{currentUser.name}</div>
            <div className="text-muted small">({currentUser.role})</div>
          </div>
        </div>

        {/* Nút Đăng xuất */}
        <button
          onClick={handleLogout}
          className="btn btn-outline-danger btn-sm d-flex align-items-center gap-1"
          title="Đăng xuất"
        >
          <i className="bi bi-box-arrow-right"></i>
          <span className="d-none d-md-inline">Đăng xuất</span>
        </button>
      </div>
    </header>
  );
}