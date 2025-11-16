// src/pages/UsersPage.jsx
import AddUserButton from '../components/users/AddUserButton';
import UsersTable from '../components/users/UsersTable';

export default function UsersPage() {
  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          
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