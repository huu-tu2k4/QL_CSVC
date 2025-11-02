// src/pages/UsersPage.jsx
import AddUserButton from '../components/users/AddUserButton';
import UsersTable from '../components/users/UsersTable';

export default function UsersPage() {
  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý người dùng</h2>
          <p className="text-muted mb-0">Danh sách người dùng hệ thống</p>
        </div>
        <AddUserButton />
      </div>

      {/* Table Card */}
      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <UsersTable />
        </div>
      </div>
    </div>
  );
}