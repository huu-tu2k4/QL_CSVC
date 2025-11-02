// src/components/dashboard/QuickStats.jsx
export default function QuickStats() {
  const sections = [
    { title: 'Thiết bị theo loại', data: [['Thiết bị CNTT', 856], ['Thiết bị nội thất', 234], ['Thiết bị khác', 144]] },
    { title: 'Thiết bị theo đơn vị', data: [['Khoa CNTT', 456], ['Khoa Toán', 312], ['Khác', 466]] },
    { title: 'Thống kê phòng', data: [['Phòng học', 45], ['Phòng máy', 12], ['Văn phòng', 28]] },
  ];

  return (
    <div className="row g-4">
      {sections.map((sec, i) => (
        <div className="col-md-4" key={i}>
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <h3 className="h5 mb-3">{sec.title}</h3>
              <div className="vstack gap-2">
                {sec.data.map(([label, val], j) => (
                  <div key={j} className="d-flex justify-content-between">
                    <span className="text-muted">{label}</span>
                    <span className="fw-semibold">{val}</span>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}