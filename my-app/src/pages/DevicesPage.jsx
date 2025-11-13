// src/pages/EquipmentPage.jsx
import AddEquipmentButton from '../components/devices/AddDeviceButton';
import EquipmentTable from '../components/devices/DeviceTable';

export default function EquipmentPage() {
  return (
    <div className="p-4">
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý thiết bị</h2>
          <p className="text-muted mb-0">Danh sách thiết bị và trạng thái</p>
        </div>
        <AddEquipmentButton />
      </div>

      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <EquipmentTable />
        </div>
      </div>
    </div>
  );
}