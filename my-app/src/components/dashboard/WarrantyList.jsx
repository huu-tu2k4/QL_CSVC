// src/components/dashboard/WarrantyList.jsx
export default function WarrantyList() {
  const items = [
    { name: 'Máy tính Dell Optiplex 3070', expiry: '1/9/2026', days: '675' },
    { name: 'Máy in HP LaserJet Pro', expiry: '15/12/2025', days: '414' },
    { name: 'Máy chiếu Epson EB-2250U', expiry: '20/11/2025', days: '389' },
  ];

  return (
    <div className="card border-0 shadow-sm">
      <div className="card-body">
        <div className="d-flex align-items-center gap-2 mb-3">
          <i className="bi bi-box-seam text-purple fs-4"></i>
          <h3 className="h5 mb-0">Bảo hành sắp hết hạn</h3>
        </div>
        <div className="vstack gap-3">
          {items.map((it, i) => (
            <div key={i}>
              <p className="mb-1 fw-medium">{it.name}</p>
              <div className="d-flex justify-content-between small text-muted">
                <span>Hết hạn: {it.expiry}</span>
                <span className="badge bg-success-subtle text-success">Còn {it.days} ngày</span>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}