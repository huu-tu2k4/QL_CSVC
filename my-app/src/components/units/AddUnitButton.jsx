// src/components/units/AddUnitButton.jsx
import { useState } from "react";
import UnitEditModal from "./UnitEditModal";
import unitService from "../../services/unitService";

export default function AddUnitButton({ onAdd }) {
  const [showModal, setShowModal] = useState(false);

  const handleSave = async (payload) => {
    try {
      await unitService.createUnit(payload);
      setShowModal(false);
      if (onAdd) onAdd(); // refresh table
    } catch (error) {
      console.error(error);
      alert("Thêm đơn vị thất bại!");
    }
  };

  return (
    <>
      <button className="btn btn-primary d-flex align-items-center gap-2" onClick={() => setShowModal(true)}>
        <i className="bi bi-plus-lg"></i>
        Thêm đơn vị
      </button>

      {showModal && (
        <UnitEditModal
          show={showModal}
          onClose={() => setShowModal(false)}
          onSave={handleSave}
          unit={{ maDonVi: "", maCode: "", tenDonVi: "", moTa: "" }}
        />
      )}
    </>
  );
}
