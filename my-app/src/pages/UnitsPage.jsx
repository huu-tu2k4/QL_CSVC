// src/pages/UnitsPage.jsx
import AddUnitButton from '../components/units/AddUnitButton';
import UnitsTable from '../components/units/UnitsTable';

export default function UnitsPage() {
  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý đơn vị</h2>
          <p className="text-muted mb-0">Danh sách khoa, phòng ban</p>
        </div>
        <AddUnitButton />
      </div>

      {/* Table Card */}
      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <UnitsTable />
        </div>
      </div>
    </div>
  );
}