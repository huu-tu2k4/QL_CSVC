// src/pages/InventoryPage.jsx
import CreateInventoryButton from '../components/inventory/CreateInventoryButton';
import InventoryTable from '../components/inventory/InventoryTable';

export default function InventoryPage() {
  return (
    <div className="p-4">
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý kiểm kê</h2>
          <p className="text-muted mb-0">Danh sách phiếu kiểm kê thiết bị</p>
        </div>
        <CreateInventoryButton />
      </div>

      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <InventoryTable />
        </div>
      </div>
    </div>
  );
}