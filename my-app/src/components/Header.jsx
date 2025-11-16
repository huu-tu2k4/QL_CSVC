import { logout } from "../services/authService";
import { useEffect, useState } from "react";
import { getMyInfo } from "../services/userService";

export default function Header({ toggleSidebar, isSidebarOpen }) {
  const today = new Date().toLocaleDateString("vi-VN", {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
  });

  const [currentUser, setCurrentUser] = useState({
    fullName: "",
    roleName: "",
    avatar: "",
  });

  // ===============================
  // Lấy info khi load Header
  // ===============================
  useEffect(() => {
    const fetchInfo = async () => {
      try {
        const res = await getMyInfo(); // gọi API /api/nguoi_dung/myInfo
        console.log("User info từ Header:", res);

        setCurrentUser({
          fullName: res.hoTen || "Người dùng",
          roleName: res.maVaiTro?.tenVaiTro || "Chưa có vai trò",
          avatar: res.avatarUrl || "", // nếu backend có avatar
        });
      } catch (err) {
        console.error("Lỗi lấy thông tin user ở Header:", err);
      }
    };

    fetchInfo();
  }, []);

  const handleLogout = () => {
    logout();
  };

  return (
    <header className="bg-white shadow-sm border-bottom px-4 py-3 d-flex justify-content-between align-items-center">
      <button onClick={toggleSidebar} className="btn btn-outline-secondary btn-sm">
        <i className={`bi ${isSidebarOpen ? "bi-x-lg" : "bi-list"}`}></i>
      </button>

      <span className="text-muted">{today}</span>

      <div className="d-flex align-items-center gap-3">
        <div
          className="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center"
          style={{ width: "36px", height: "36px" }}
        >
          {currentUser.avatar ? (
            <img
              src={currentUser.avatar}
              alt="Avatar"
              className="rounded-circle w-100 h-100"
              style={{ objectFit: "cover" }}
            />
          ) : (
            (currentUser.fullName?.charAt(0) || "U").toUpperCase()
          )}
        </div>

        <div className="d-none d-lg-block">
          <div className="fw-semibold small">{currentUser.fullName}</div>
          <div className="text-muted small">({currentUser.roleName})</div>
        </div>

        <button
          onClick={handleLogout}
          className="btn btn-outline-danger btn-sm d-flex align-items-center gap-1"
        >
          <i className="bi bi-box-arrow-right"></i>
          <span className="d-none d-md-inline">Đăng xuất</span>
        </button>
      </div>
    </header>
  );
}
