// src/pages/MaintenancePage.jsx
import { useEffect, useState } from 'react';
import { getAllBaoTri } from '../services/MaintenanceService';
import MaintenanceTable from '../components/maintenance/MaintenanceTable';
import CreateTicketButton from '../components/maintenance/CreateTicketButton';
import SearchFilter from '../components/maintenance/SearchFilter';

export default function MaintenancePage() {
  const [tickets, setTickets] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const data = await getAllBaoTri();
        console.log('API Bảo trì:', data); // Debug
        const mapped = data.map(mapApiToTicket);
        setTickets(mapped);
        setFiltered(mapped);
      } catch (error) {
        console.error('Lỗi:', error);
        alert('Không thể tải danh sách bảo trì');
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  // CHUẨN HÓA DỮ LIỆU API → FORMAT MaintenanceTable CẦN
  const mapApiToTicket = (item) => {
    // 1. Format ngày đúng (ISO string → Date → toLocaleString)
    const formatDate = (isoString) => {
      if (!isoString) return '—';
      const date = new Date(isoString);
      return isNaN(date.getTime()) ? 'Invalid Date' : date.toLocaleString('vi-VN');
    };

    // 2. Map mức ưu tiên
    const priorityMap = {
      CAO: { label: 'Cao', color: 'warning' },
      KHAN_CAP: { label: 'Khẩn cấp', color: 'danger' },
      BINH_THUONG: { label: 'Bình thường', color: 'primary' },
    };

    // 3. Map trạng thái
    const statusMap = {
      MOI: { label: 'Mới', color: 'secondary' },
      DANG_XU_LY: { label: 'Đang xử lý', color: 'info' },
      HOAN_TAT: { label: 'Hoàn tất', color: 'success' },
    };

    return {
      id: item.soPhieuBt || item.maPhieuBT,
      device: item.tenThietBi,
      deviceCode: item.maThietBi,
      requester: item.nguoiDeXuat || '—',
      unit: item.tenDonViDeXuat || '—',
      date: formatDate(item.ngayDeXuat),
      priority: priorityMap[item.mucUuTien]?.label || '—',
      priorityColor: priorityMap[item.mucUuTien]?.color || 'secondary',
      status: statusMap[item.trangThai]?.label || '—',
      statusColor: statusMap[item.trangThai]?.color || 'secondary',
      cost: item.chiPhi ? `${Number(item.chiPhi).toLocaleString('vi-VN')} đ` : '-',
    };
  };

  // Tìm kiếm
  const handleSearch = (term) => {
    const lower = term.toLowerCase();
    const result = tickets.filter(t =>
      t.id.toLowerCase().includes(lower) ||
      t.deviceCode.toLowerCase().includes(lower) ||
      t.device.toLowerCase().includes(lower)
    );
    setFiltered(result);
  };

  // Lọc trạng thái
  const handleFilter = (statusText) => {
    if (statusText === 'Tất cả trạng thái') {
      setFiltered(tickets);
    } else {
      setFiltered(tickets.filter(t => t.status === statusText));
    }
  };

  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h2 className="h4 fw-bold mb-1">Bảo trì thiết bị</h2>
          <p className="text-muted mb-0">Quản lý phiếu yêu cầu bảo trì</p>
        </div>
        <CreateTicketButton />
      </div>

      {/* Filter */}
      <div className="card border-0 shadow-sm mb-4">
        <div className="card-body">
          <SearchFilter onSearch={handleSearch} onFilter={handleFilter} />
        </div>
      </div>

      {/* Table */}
      <div className="card border-0 shadow-sm">
        <div className="card-body">
          {loading ? (
            <p className="text-center text-muted">Đang tải...</p>
          ) : filtered.length === 0 ? (
            <p className="text-center text-muted">Không có dữ liệu</p>
          ) : (
            <MaintenanceTable tickets={filtered} />
          )}
        </div>
      </div>
    </div>
  );
}