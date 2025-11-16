// src/components/units/UnitEditModal.jsx
import React, { useState, useEffect } from "react";
import { createPortal } from "react-dom";

export default function UnitEditModal({ show, onClose, unit, onSave }) {
  const [formData, setFormData] = useState({
    maDonVi: "",
    maCode: "",
    tenDonVi: "",
    moTa: "",
  });

  useEffect(() => {
    if (unit) {
      setFormData({
        maDonVi: unit.maDonVi || "",
        maCode: unit.maCode || "",
        tenDonVi: unit.tenDonVi || "",
        moTa: unit.moTa || "",
      });
    }
  }, [unit]);

  if (!show) return null;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    const payload = {
      ma_don_vi: formData.maDonVi.trim(),
      ma_code: formData.maCode.trim(),
      ten_don_vi: formData.tenDonVi.trim(),
      mo_ta: formData.moTa.trim() || null,
    };
    await onSave(payload);
    onClose();
  };

  const modalContent = (
    <div className="modal show d-block" style={{ background: "rgba(0,0,0,0.5)" }}>
      <div className="modal-dialog modal-dialog-centered">
        <div className="modal-content">
          <div className="modal-header bg-primary text-white">
            <h5 className="modal-title">{unit.maDonVi ? "Chỉnh sửa đơn vị" : "Thêm đơn vị"}</h5>
            <button className="btn-close btn-close-white" onClick={onClose}></button>
          </div>
          <div className="modal-body">
            <div className="mb-3">
              <label className="form-label">Mã đơn vị</label>
              <input type="text" className="form-control" name="maDonVi" value={formData.maDonVi} onChange={handleChange} />
            </div>
            <div className="mb-3">
              <label className="form-label">Mã code</label>
              <input type="text" className="form-control" name="maCode" value={formData.maCode} onChange={handleChange} />
            </div>
            <div className="mb-3">
              <label className="form-label">Tên đơn vị</label>
              <input type="text" className="form-control" name="tenDonVi" value={formData.tenDonVi} onChange={handleChange} />
            </div>
            <div className="mb-3">
              <label className="form-label">Mô tả</label>
              <textarea className="form-control" name="moTa" value={formData.moTa} onChange={handleChange}></textarea>
            </div>
          </div>
          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>Hủy</button>
            <button className="btn btn-primary" onClick={handleSubmit}>
              {unit.maDonVi ? "Lưu thay đổi" : "Thêm"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );

  return createPortal(modalContent, document.body);
}
