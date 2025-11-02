// src/pages/MaintenancePage.jsx
import CreateTicketButton from '../components/maintenance/CreateTicketButton';
import SearchFilter from '../components/maintenance/SearchFilter';
import MaintenanceTable from '../components/maintenance/MaintenanceTable';
import { useState } from 'react';

export default function MaintenancePage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [filterStatus, setFilterStatus] = useState('Tất cả trạng thái');

  return (
    <div className="p-4">
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý bảo trì</h2>
          <p className="text-muted mb-0">Danh sách phiếu bảo trì thiết bị</p>
        </div>
        <CreateTicketButton />
      </div>

      <div className="card border-0 shadow-sm">
        <div className="card-body">
          <SearchFilter onSearch={setSearchTerm} onFilter={setFilterStatus} />
          <MaintenanceTable />
        </div>
      </div>
    </div>
  );
}