import { useState, useEffect } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { isAuthenticated, logout } from './services/authService';
import Sidebar from './components/Sidebar';
import Header from './components/Header';
import DashboardPage from './pages/DashboardPage';
import DevicesPage from './pages/DevicesPage';
import EquipmentHistoryPage from './pages/DeviceHistoryPage';
import MaintenancePage from './pages/MaintenancePage';
import PurchasePage from './pages/PurchasePage';
import InventoryPage from './pages/InventoryPage';
import LiquidationPage from './pages/LiquidationPage';
import UnitsPage from './pages/UnitsPage';
import RoomsPage from './pages/RoomsPage';
import UsersPage from './pages/UsersPage';
import LoginPage from './pages/LoginPage';
import './App.css';


// =========================
// ProtectedRoute
// =========================
function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token');
  if (!token) return <Navigate to="/login" replace />;

  return children;
}


// =========================
// Dashboard Layout
// =========================
function DashboardLayout() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  const handleLogout = () => {
    // ❗ FIX: KHÔNG navigate nữa vì hàm logout đã tự redirect
    logout();
  };

  return (
    <div id="container" className="d-flex h-100 w-100">
      <div className="tailwind w-100 h-100">
        <div id="node-0_6_container" className="d-flex w-100 h-100">
          <Sidebar isOpen={isSidebarOpen} />

          <main className="flex-grow-1 bg-light d-flex flex-column" style={{ minWidth: 0 }}>
            <Header
              toggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)}
              onLogout={handleLogout}
            />

            <div className="flex-grow-1 overflow-auto p-3">
              <Routes>
                <Route path="/" element={<DashboardPage />} />
                <Route path="/devices" element={<DevicesPage />} />
                <Route path="/history" element={<EquipmentHistoryPage />} />
                <Route path="/maintenance" element={<MaintenancePage />} />
                <Route path="/purchase" element={<PurchasePage />} />
                <Route path="/inventory" element={<InventoryPage />} />
                <Route path="/liquidation" element={<LiquidationPage />} />
                <Route path="/units" element={<UnitsPage />} />
                <Route path="/rooms" element={<RoomsPage />} />
                <Route path="/users" element={<UsersPage />} />
              </Routes>
            </div>
          </main>
        </div>
      </div>
    </div>
  );
}


// =========================
// App
// =========================
export default function App() {
  const [isAuth, setIsAuth] = useState(isAuthenticated());

  useEffect(() => {
    const updateAuth = () => setIsAuth(isAuthenticated());
    window.addEventListener('storage', updateAuth);
    return () => window.removeEventListener('storage', updateAuth);
  }, []);

  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />

      <Route
        path="/*"
        element={
          <ProtectedRoute>
            <DashboardLayout />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}
