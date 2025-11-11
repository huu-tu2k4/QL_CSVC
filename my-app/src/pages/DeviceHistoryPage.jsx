// src/pages/DeviceHistoryPage.jsx
import { useEffect, useState } from 'react';
import { getAllLichSu } from '../services/HistoryDeviceService';
import HistoryStatsGrid from '../components/history/HistoryStatsGrid';
import EventFilter from '../components/history/EventFilter';
import TimelineItem from '../components/history/TimelineItem';
import ExportReportButton from '../components/history/ExportReportButton';

export default function DeviceHistoryPage() {
  const [rawData, setRawData] = useState([]);
  const [filteredEvents, setFilteredEvents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const data = await getAllLichSu();
        console.log('API Response:', data); // Debug
        setRawData(data);
        setFilteredEvents(mapToTimelineFormat(data));
      } catch (error) {
        console.error('Lỗi:', error);
        alert('Không thể tải dữ liệu');
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  // CHUẨN HÓA DỮ LIỆU API → FORMAT TIMELINE
  const mapToTimelineFormat = (apiData) => {
    return apiData.map(item => {
      // 1. Chuẩn hóa loại sự kiện
      const typeMap = {
        MUA_SAM: 'Mua sắm',
        CAP_PHAT: 'Cấp phát',
        BAO_TRI: 'Yêu cầu bảo trì',
        KIEM_KE: 'Kiểm kê',
        SUA_CHUA: 'Sửa chữa',
      };

      // 2. Format ngày đúng (ISO string → Date → toLocaleString)
      const formatDate = (isoString) => {
        const date = new Date(isoString);
        return isNaN(date.getTime()) ? 'Không xác định' : date.toLocaleString('vi-VN');
      };

      // 3. Tạo details theo đúng label
      const details = [
        { label: 'Thiết bị', value: item.thietBi },
        { label: 'Người thực hiện', value: item.nguoiThucHien || '—' },
      ];

      // Thêm thông tin theo loại sự kiện
      if (item.loaiSuKien === 'CAP_PHAT') {
        details.push({ label: 'Từ đơn vị', value: item.tuDonVi });
        details.push({ label: 'Đến đơn vị', value: item.denDonVi });
      } else if (item.loaiSuKien === 'MUA_SAM') {
        details.push({ label: 'Chi phí', value: `${item.chiPhi?.toLocaleString('vi-VN')} ₫` });
        details.push({ label: 'Kho nhập', value: item.tuKho });
      }

      return {
        title: typeMap[item.loaiSuKien] || item.loaiSuKien,
        code: item.maLichSu,
        date: formatDate(item.ngaySuKien),
        description: item.moTa || 'Không có mô tả',
        details: details.filter(d => d.value && d.value !== '—'),
      };
    });
  };

  // XỬ LÝ LỌC
  const handleFilter = (selectedText) => {
    if (selectedText === 'Tất cả sự kiện') {
      setFilteredEvents(mapToTimelineFormat(rawData));
      return;
    }

    const typeMapReverse = {
      'Mua sắm': 'MUA_SAM',
      'Cấp phát': 'CAP_PHAT',
      'Yêu cầu bảo trì': 'BAO_TRI',
      'Kiểm kê': 'KIEM_KE',
      'Sửa chữa': 'SUA_CHUA',
    };

    const filtered = rawData.filter(item => item.loaiSuKien === typeMapReverse[selectedText]);
    setFilteredEvents(mapToTimelineFormat(filtered));
  };

  // TÍNH STATS ĐỘNG
  const stats = {
    total: filteredEvents.length,
    muaSam: filteredEvents.filter(e => e.title === 'Mua sắm').length,
    baoTri: filteredEvents.filter(e => e.title === 'Yêu cầu bảo trì' || e.title === 'Sửa chữa').length,
    kiemKe: filteredEvents.filter(e => e.title === 'Kiểm kê').length,
  };

  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h2 className="h4 fw-bold mb-1">Lịch sử thiết bị</h2>
          <p className="text-muted mb-0">Theo dõi toàn bộ hoạt động thiết bị</p>
        </div>
        <ExportReportButton data={filteredEvents} />
      </div>

      {/* Stats */}
      <div className="mb-4">
        <HistoryStatsGrid
          stats={[
            { value: stats.total, label: 'Tổng sự kiện', icon: 'bi-journal-text' },
            { value: stats.muaSam, label: 'Mua sắm', icon: 'bi-cart4' },
            { value: stats.baoTri, label: 'Bảo trì/Sửa chữa', icon: 'bi-tools' },
            { value: stats.kiemKe, label: 'Kiểm kê', icon: 'bi-clipboard-check' },
          ]}
        />
      </div>

      {/* Filter */}
      <div className="card border-0 shadow-sm mb-4">
        <div className="card-body">
          <EventFilter onFilter={handleFilter} />
        </div>
      </div>

      {/* Timeline */}
      <div className="card border-0 shadow-sm">
        <div className="card-body">
          {loading ? (
            <p className="text-center text-muted">Đang tải...</p>
          ) : filteredEvents.length === 0 ? (
            <p className="text-center text-muted">Không có dữ liệu</p>
          ) : (
            filteredEvents.map((event, i) => (
              <TimelineItem
                key={event.code}
                event={event}
                isLast={i === filteredEvents.length - 1}
              />
            ))
          )}
        </div>
      </div>
    </div>
  );
}