// src/components/history/TimelineItem.jsx
const eventTypes = {
  'Mua sắm': { icon: 'bi-cart4', color: 'primary' },
  'Cấp phát': { icon: 'bi-box-seam', color: 'success' },
  'Yêu cầu bảo trì': { icon: 'bi-wrench', color: 'warning' },
  'Kiểm kê': { icon: 'bi-clipboard-check', color: 'info' },
  'Sửa chữa': { icon: 'bi-gear', color: 'secondary' },
};

export default function TimelineItem({ event }) {
  const type = eventTypes[event.title] || { icon: 'bi-circle', color: 'secondary' };

  return (
    <div className="d-flex position-relative mb-5">
      {/* Dot + Line */}
      <div className="d-flex flex-column align-items-center me-4">
        <div
          className={`rounded-circle d-flex align-items-center justify-content-center text-white bg-${type.color}`}
          style={{ width: '40px', height: '40px' }}
        >
          <i className={`bi ${type.icon} fs-5`}></i>
        </div>
        <div className="flex-grow-1 border-start border-2 border-secondary mt-2" style={{ height: '100%' }}></div>
      </div>

      {/* Content */}
      <div className="flex-grow-1">
        <div className="d-flex justify-content-between align-items-start mb-2">
          <div>
            <h3 className="h5 fw-bold mb-1">{event.title}</h3>
            <p className="text-muted small mb-0">
              <i className="bi bi-clock me-1"></i>
              {event.date}
            </p>
          </div>
          <span className="badge bg-primary text-white">{event.code}</span>
        </div>

        <p className="text-muted mb-3">{event.description}</p>

        {event.details && (
          <div className="row g-3 small">
            {event.details.map((d, i) => (
              <div className="col-md-6" key={i}>
                <div className="d-flex justify-content-between">
                  <span className="text-muted">{d.label}:</span>
                  <span className="fw-medium ms-2">{d.value}</span>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}