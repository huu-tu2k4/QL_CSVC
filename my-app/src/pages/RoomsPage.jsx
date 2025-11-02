// src/pages/RoomsPage.jsx
import AddRoomButton from '../components/rooms/AddRoomButton';
import RoomsTable from '../components/rooms/RoomsTable';

export default function RoomsPage() {
  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý phòng</h2>
          <p className="text-muted mb-0">Danh sách phòng học, phòng làm việc</p>
        </div>
        <AddRoomButton />
      </div>

      {/* Table Card */}
      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <RoomsTable />
        </div>
      </div>
    </div>
  );
}