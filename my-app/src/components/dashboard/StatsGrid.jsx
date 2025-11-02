// src/components/dashboard/StatsGrid.jsx
export default function StatsGrid() {
  const stats = [
    { label: 'Tổng thiết bị', value: '1,234', icon: 'bi-display', color: 'blue' },
    { label: 'Đang sử dụng', value: '892', icon: 'bi-check-circle-fill', color: 'success' },
    { label: 'Bảo trì', value: '45', icon: 'bi-tools', color: 'warning' },
    { label: 'Hỏng hóc', value: '12', icon: 'bi-exclamation-triangle-fill', color: 'danger' },
  ];

  return (
    <div className="row g-4 mb-5">
      {stats.map((s, i) => (
        <div className="col-md-3" key={i}>
          <div className="card h-100 border-0 shadow-sm">
            <div className="card-body d-flex justify-content-between align-items-center">
              <div>
                <p className="text-muted small mb-1">{s.label}</p>
                <h3 className="mb-0 fw-bold">{s.value}</h3>
              </div>
              <i className={`bi ${s.icon} fs-1 text-${s.color}`}></i>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}