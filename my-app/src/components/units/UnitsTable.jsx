// src/components/units/UnitsTable.jsx
import { useState } from "react";
import UnitEditModal from "./UnitEditModal";
import unitService from "../../services/unitService";

export default function UnitsTable({ units, onRefresh }) {
  const [editUnit, setEditUnit] = useState(null);

  const handleDelete = async (maDonVi) => {
    if (!window.confirm("Bạn có chắc muốn xóa đơn vị này?")) return;
    try {
      await unitService.deleteUnit(maDonVi);
      if (onRefresh) onRefresh();
    } catch (error) {
      console.error(error);
      alert("Xóa thất bại!");
    }
  };

  const handleSaveEdit = async (payload) => {
    try {
      await unitService.updateUnit(payload.ma_don_vi, payload);
      setEditUnit(null);
      if (onRefresh) onRefresh();
    } catch (error) {
      console.error(error);
      alert("Cập nhật thất bại!");
    }
  };

  return (
    <div className="table-responsive">
      <table className="table table-hover align-middle">
        <thead className="table-light">
          <tr>
            <th>Mã code</th>
            <th>Tên đơn vị</th>
            <th>Mô tả</th>
            <th>Số thiết bị</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {units.map(unit => (
            <tr key={unit.maDonVi}>
              <td className="fw-semibold">{unit.maCode}</td>
              <td>{unit.tenDonVi}</td>
              <td className="text-muted small">{unit.moTa}</td>
              <td>{unit.soLuongTB}</td>
              <td>
                <div className="d-flex gap-1">
                  <button className="btn btn-sm btn-outline-secondary" onClick={() => setEditUnit(unit)}>
                    <i className="bi bi-pencil-square"></i>
                  </button>
                  <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(unit.maDonVi)}>
                    <i className="bi bi-trash"></i>
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {editUnit && (
        <UnitEditModal
          show={!!editUnit}
          unit={editUnit}
          onClose={() => setEditUnit(null)}
          onSave={handleSaveEdit}
        />
      )}
    </div>
  );
}
