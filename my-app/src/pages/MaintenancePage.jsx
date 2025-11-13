// src/pages/MaintenancePage.jsx
import CreateMaintenanceButton from '../components/maintenance/CreateMaintenanceButton';
import MaintenanceTable from '../components/maintenance/MaintenanceTable';

export default function MaintenancePage() {
  return (
    <div className="p-4">
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý bảo trì</h2>
          <p className="text-muted mb-0">Danh sách phiếu bảo trì thiết bị</p>
        </div>
        <CreateMaintenanceButton />
      </div>

      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <MaintenanceTable />
        </div>
      </div>
    </div>
  );
}