// src/pages/EquipmentHistoryPage.jsx
import { useState } from 'react';
import HistoryStatsGrid from '../components/history/HistoryStatsGrid';
import EventFilter from '../components/history/EventFilter';
import TimelineItem from '../components/history/TimelineItem';
import ExportReportButton from '../components/history/ExportReportButton';

const rawEvents = [
  {
    title: 'Mua sắm',
    date: 'lúc 10:00 1 tháng 9, 2024',
    code: 'MS20240901',
    description: 'Mua sắm thiết bị máy tính cho phòng máy B202',
    details: [
      { label: 'Thực hiện bởi', value: 'Nguyễn Văn Quản trị' },
      { label: 'Đến đơn vị', value: 'Khoa CNTT' },
      { label: 'Đến người dùng', value: 'Trần Văn Thiết bị' },
      { label: 'Chi phí', value: '18.000.000 VNĐ' },
    ],
  },
  {
    title: 'Cấp phát',
    date: 'lúc 14:00 2 tháng 9, 2024',
    code: 'CP20240902',
    description: 'Cấp phát thiết bị cho giảng viên Trần Văn Thiết bị',
    details: [
      { label: 'Thực hiện bởi', value: 'Nguyễn Văn Quản trị' },
      { label: 'Đến đơn vị', value: 'Khoa CNTT' },
      { label: 'Đến người dùng', value: 'Trần Văn Thiết bị' },
    ],
  },
  {
    title: 'Yêu cầu bảo trì',
    date: 'lúc 09:00 25 tháng 10, 2024',
    code: 'BT001',
    description: 'Yêu cầu bảo trì: Máy chạy chậm, cần nâng cấp RAM',
    details: [
      { label: 'Thực hiện bởi', value: 'Trần Văn Thiết bị' },
      { label: 'Từ đơn vị', value: 'Khoa CNTT' },
      { label: 'Từ người dùng', value: 'Trần Văn Thiết bị' },
    ],
  },
  {
    title: 'Kiểm kê',
    date: 'lúc 08:00 15 tháng 10, 2024',
    code: 'KK202410',
    description: 'Kiểm kê định kỳ tháng 10/2024 - Tình trạng: Tốt',
    details: [
      { label: 'Thực hiện bởi', value: 'Nguyễn Văn Quản trị' },
      { label: 'Đến đơn vị', value: 'Khoa CNTT' },
    ],
  },
  {
    title: 'Mua sắm',
    date: 'lúc 14:30 15 tháng 3, 2024',
    code: 'MS20240315',
    description: 'Mua sắm máy chiếu Epson cho phòng học A101',
    details: [
      { label: 'Thực hiện bởi', value: 'Nguyễn Văn Quản trị' },
      { label: 'Đến đơn vị', value: 'Khoa CNTT' },
      { label: 'Chi phí', value: '25.000.000 VNĐ' },
    ],
  },
  {
    title: 'Sửa chữa',
    date: 'lúc 10:00 24 tháng 10, 2024',
    code: 'BT003',
    description: 'Sửa chữa máy in: vệ sinh và thay roller',
    details: [
      { label: 'Thực hiện bởi', value: 'Nguyễn Văn Quản trị' },
      { label: 'Từ đơn vị', value: 'Khoa CNTT' },
      { label: 'Chi phí', value: '500.000 VNĐ' },
    ],
  },
];

export default function EquipmentHistoryPage() {
  const [filter, setFilter] = useState('Tất cả sự kiện');
  const filteredEvents = filter === 'Tất cả sự kiện'
    ? rawEvents
    : rawEvents.filter(e => e.title === filter);

  return (
    <div className="p-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Lịch sử thiết bị</h2>
          <p className="text-muted mb-0">Theo dõi vòng đời và các thay đổi của thiết bị</p>
        </div>
        <ExportReportButton />
      </div>

      {/* Stats */}
      <HistoryStatsGrid />

      {/* Timeline Card */}
      <div className="card border-0 shadow-sm">
        <div className="card-body">
          <EventFilter onFilter={setFilter} />
          <div className="position-relative">
            {filteredEvents.map((event, i) => (
              <TimelineItem key={i} event={event} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}