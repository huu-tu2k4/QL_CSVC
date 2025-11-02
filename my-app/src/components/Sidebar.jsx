// src/components/Sidebar.jsx
import { NavLink } from 'react-router-dom';

export default function Sidebar({ isOpen }) {
  const menuItems = [
    { to: '/', icon: 'bi-speedometer2', label: 'Tổng quan', exact: true },
    { to: '/devices', icon: 'bi-display', label: 'Thiết bị' },
    { to: '/history', icon: 'bi-clock-history', label: 'Lịch sử TB' },
    { to: '/maintenance', icon: 'bi-tools', label: 'Bảo trì' },
    { to: '/purchase', icon: 'bi-cart3', label: 'Mua sắm' },
    { to: '/inventory', icon: 'bi-clipboard-check', label: 'Kiểm kê' },
    { to: '/units', icon: 'bi-building', label: 'Đơn vị' },
    { to: '/rooms', icon: 'bi-door-open', label: 'Phòng' },
    { to: '/users', icon: 'bi-people', label: 'Người dùng' },
  ];

  return (
    <aside
      className={`bg-dark text-white d-flex flex-column transition-all ${
        isOpen ? 'sidebar-open' : 'sidebar-closed'
      }`}
      style={{ width: isOpen ? '280px' : '70px', flexShrink: 0, overflow: 'hidden' }}
    >
      {/* Logo */}
      <div className="p-3 border-bottom border-secondary d-flex align-items-center gap-3">
        {isOpen ? (
          <>
            <div className="bg-primary rounded-circle d-flex align-items-center justify-content-center text-white fw-bold" style={{ width: '40px', height: '40px', fontSize: '1.2rem' }}>
              CS
            </div>
            <div>
              <h1 className="h6 fw-bold text-white mb-0">Quản lý CSVC</h1>
              <p className="small text-light opacity-75 mb-0">Trường Đại học</p>
            </div>
          </>
        ) : (
          <div className="bg-primary rounded-circle d-flex align-items-center justify-content-center text-white fw-bold mx-auto" style={{ width: '40px', height: '40px', fontSize: '1.2rem' }}>
            CS
          </div>
        )}
      </div>

      {/* Menu */}
      <nav className="flex-grow-1 p-2">
        <ul className="nav flex-column">
          {menuItems.map((item) => (
            <li className="nav-item mb-1" key={item.to}>
              <NavLink
                to={item.to}
                end={item.exact}
                className={({ isActive }) =>
                  `btn w-100 text-start d-flex align-items-center gap-2 px-2 py-2 rounded ${
                    isActive ? 'btn-primary text-white' : 'btn-dark text-light'
                  }`
                }
                title={!isOpen ? item.label : ''}
              >
                <i className={`bi ${item.icon} fs-5`}></i>
                {isOpen && <span className="small">{item.label}</span>}
              </NavLink>
            </li>
          ))}
        </ul>
      </nav>

      {/* User */}
      {isOpen && (
        <div className="p-3 border-top border-secondary">
          <div className="d-flex align-items-center gap-2">
            <div className="bg-secondary rounded-circle d-flex align-items-center justify-content-center" style={{ width: '36px', height: '36px' }}>
              <i className="bi bi-person text-white"></i>
            </div>
            <div className="text-truncate">
              <p className="mb-0 small fw-medium text-white text-truncate">Admin</p>
              <p className="mb-0 text-xs text-light opacity-75 text-truncate">admin@utc.edu.vn</p>
            </div>
          </div>
        </div>
      )}
    </aside>
  );
}