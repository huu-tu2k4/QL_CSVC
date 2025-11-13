// src/pages/LiquidationPage.jsx
import CreateLiquidationButton from '../components/liquidation/CreateLiquidationButton';
import LiquidationTable from '../components/liquidation/LiquidationTable';

export default function LiquidationPage() {
  return (
    <div className="p-4">
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý thanh lý</h2>
          <p className="text-muted mb-0">Danh sách phiếu thanh lý thiết bị</p>
        </div>
        <CreateLiquidationButton />
      </div>

      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <LiquidationTable />
        </div>
      </div>
    </div>
  );
}