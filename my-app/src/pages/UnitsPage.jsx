// src/pages/UnitsPage.jsx
import { useState, useEffect } from "react";
import AddUnitButton from "../components/units/AddUnitButton";
import UnitsTable from "../components/units/UnitsTable";
import unitService from "../services/unitService";

export default function UnitsPage() {
  const [units, setUnits] = useState([]);

  const fetchUnits = async () => {
    try {
      const data = await unitService.getAllUnits();
      setUnits(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchUnits();
  }, []);

  return (
    <div className="p-4">
      <div className="d-flex justify-content-between align-items-start mb-4 flex-wrap gap-3">
        <div>
          <h2 className="h4 fw-bold mb-1">Quản lý đơn vị</h2>
          <p className="text-muted mb-0">Danh sách khoa, phòng ban</p>
        </div>
        <AddUnitButton onAdd={fetchUnits} />
      </div>

      <div className="card border-0 shadow-sm">
        <div className="card-body p-0">
          <UnitsTable units={units} onRefresh={fetchUnits} />
        </div>
      </div>
    </div>
  );
}
