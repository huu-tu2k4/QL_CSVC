// src/components/history/EventFilter.jsx
import { useState } from 'react';

export default function EventFilter({ onFilter }) {
  const [selected, setSelected] = useState('Tất cả sự kiện');

  const handleChange = (e) => {
    const value = e.target.value;
    setSelected(value);
    onFilter(value);
  };

  return (
    <div className="d-flex align-items-center gap-3 mb-4">
      <span className="text-muted small">Lọc theo loại sự kiện:</span>
      <select
        className="form-select"
        style={{ width: '200px' }}
        value={selected}
        onChange={handleChange}
      >
        {['Tất cả sự kiện', 'Mua sắm', 'Cấp phát', 'Bảo trì', 'Kiểm kê', 'Sửa chữa'].map((opt) => (
          <option key={opt} value={opt}>{opt}</option>
        ))}
      </select>
    </div>
  );
}