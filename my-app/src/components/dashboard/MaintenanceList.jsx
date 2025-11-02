// src/components/dashboard/MaintenanceList.jsx
export default function MaintenanceList() {
  const items = [
    { id: '#BT001', title: 'Máy tính Dell Optiplex 3070', desc: 'Máy chạy chậm, cần nâng cấp RAM', date: '25/10/2024', priority: 'Cao', status: 'Đang xử lý', pColor: 'warning', sColor: 'primary' },
    { id: '#BT002', title: 'Máy chiếu Epson EB-2250U', desc: 'Đèn chiếu mờ', date: '26/10/2024', priority: 'Khẩn cấp', status: 'Mới', pColor: 'danger', sColor: 'secondary' },
    { id: '#BT003', title: 'Điều hòa Daikin 2HP', desc: 'Không làm lạnh', date: '24/10/2024', priority: 'Bình thường', status: 'Hoàn tất', pColor: 'info', sColor: 'success' },
  ];

  return (
    <div className="card border-0 shadow-sm">
      <div className="card-body">
        <div className="d-flex align-items-center gap-2 mb-3">
          <i className="bi bi-tools text-warning fs-4"></i>
          <h3 className="h5 mb-0">Phiếu bảo trì gần đây</h3>
        </div>
        <div className="vstack gap-3">
          {items.map((it, i) => (
            <div key={i} className="border-start border-3 border-secondary ps-3">
              <div className="d-flex justify-content-between align-items-start mb-1">
                <div>
                  <span className="text-muted small">{it.id}</span>
                  <h6 className="mb-0">{it.title}</h6>
                </div>
                <div className="d-flex gap-1">
                  <span className={`badge bg-badges bg-${it.pColor}-subtle text-${it.pColor}`}>{it.priority}</span>
                  <span className={`badge bg-${it.sColor}-subtle text-${it.sColor}`}>{it.status}</span>
                </div>
              </div>
              <p className="text-muted small mb-1">{it.desc}</p>
              <p className="text-muted small"><i className="bi bi-clock me-1"></i>{it.date}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}