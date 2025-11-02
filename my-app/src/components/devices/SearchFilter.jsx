// src/components/devices/SearchFilter.jsx
import { useState } from 'react';

export default function SearchFilter({ onSearch, onFilter }) {
  const [search, setSearch] = useState('');
  const [status, setStatus] = useState('Tất cả trạng thái');

  const handleSearch = (e) => {
    const value = e.target.value;
    setSearch(value);
    onSearch(value);
  };

  const handleFilter = (e) => {
    const value = e.target.textContent;
    setStatus(value);
    onFilter(value);
  };

  return (
    <div className="d-flex gap-3 mb-4 flex-wrap">
      <div className="position-relative flex-grow-1" style={{ maxWidth: '400px' }}>
        <i className="bi bi-search position-absolute top-50 start-3 translate-middle-y text-muted"></i>
        <input
          type="text"
          className="form-control ps-5"
          placeholder="Tìm kiếm theo mã TB, tên, serial..."
          value={search}
          onChange={handleSearch}
        />
      </div>

      <div className="dropdown">
        <button
          className="btn btn-outline-secondary dropdown-toggle d-flex align-items-center justify-content-between"
          type="button"
          data-bs-toggle="dropdown"
          style={{ width: '200px' }}
        >
          {status}
        </button>
        <ul className="dropdown-menu">
          {['Tất cả trạng thái', 'Đang sử dụng', 'Sẵn sàng', 'Bảo trì', 'Hỏng hóc'].map((s) => (
            <li key={s}>
              <button className="dropdown-item" onClick={() => { setStatus(s); onFilter(s); }}>
                {s}
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}