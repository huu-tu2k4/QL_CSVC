// src/pages/PurchasePage.jsx
import CreatePurchaseButton from '../components/purchase/CreatePurchaseButton';
import PurchaseTable from '../components/purchase/PurchaseTable';

export default function PurchasePage() {
  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý mua sắm</h2>
          <p className="text-muted mb-0">Danh sách phiếu mua sắm thiết bị</p>
        </div>
        <CreatePurchaseButton />
      </div>

      {/* Table Card */}
      <div className="card border-0 shadow-sm">
        <div className="card-body">
          <PurchaseTable />
        </div>
      </div>
    </div>
  );
}