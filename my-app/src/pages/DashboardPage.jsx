// src/pages/DashboardPage.jsx
import StatsGrid from '../components/dashboard/StatsGrid';
import MaintenanceList from '../components/dashboard/MaintenanceList';
import WarrantyList from '../components/dashboard/WarrantyList';
import QuickStats from '../components/dashboard/QuickStats';

export default function DashboardPage() {
  return (
    <div className="p-4">
      <div className="mb-5">
        <h2 className="h3 fw-bold">Tổng quan hệ thống</h2>
        <p className="text-muted">Quản lý cơ sở vật chất trường đại học</p>
      </div>
      <StatsGrid />
      <div className="row g-4 mb-5">
        <div className="col-lg-8"><MaintenanceList /></div>
        <div className="col-lg-4"><WarrantyList /></div>
      </div>
      <QuickStats />
    </div>
  );
}