// src/components/maintenance/SearchFilter.jsx
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
    const value = e.target.value;
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
          placeholder="Tìm kiếm theo số phiếu, mã TB..."
          value={search}
          onChange={handleSearch}
        />
      </div>

      <select
        className="form-select"
        style={{ width: '200px' }}
        value={status}
        onChange={handleFilter}
      >
        {['Tất cả trạng thái', 'Mới', 'Đang xử lý', 'Hoàn tất'].map((s) => (
          <option key={s} value={s}>{s}</option>
        ))}
      </select>
    </div>
  );
}