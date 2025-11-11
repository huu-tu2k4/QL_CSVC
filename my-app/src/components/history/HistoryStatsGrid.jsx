// src/components/history/HistoryStatsGrid.jsx
export default function HistoryStatsGrid({ stats }) {
  return (
    <div className="row g-3 mb-4">
      {stats.map((s, i) => (
        <div className="col-md-3" key={i}>
          <div className="card border-0 shadow-sm h-100">
            <div className="card-body text-center py-3">
              <i className={`bi ${s.icon} text-primary fs-3 mb-2`}></i>
              <h3 className="mb-0 fw-bold">{s.value}</h3>
              <p className="text-muted small mb-0">{s.label}</p>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}