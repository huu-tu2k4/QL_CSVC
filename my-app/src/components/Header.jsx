// src/components/Header.jsx
export default function Header({ toggleSidebar, isSidebarOpen }) {
  const today = new Date().toLocaleDateString('vi-VN', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });

  return (
    <header className="bg-white shadow-sm border-bottom px-4 py-3 d-flex justify-content-between align-items-center">
      <button
        onClick={toggleSidebar}
        className="btn btn-outline-secondary btn-sm"
        aria-label={isSidebarOpen ? 'Ẩn sidebar' : 'Hiện sidebar'}
      >
        <i className={`bi ${isSidebarOpen ? 'bi-x-lg' : 'bi-list'}`}></i>
      </button>
      <span className="text-muted">{today}</span>
    </header>
  );
}