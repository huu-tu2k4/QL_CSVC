// src/components/users/UserEditModal.jsx
import React, { useState, useEffect } from 'react';
import { createPortal } from 'react-dom';

export default function UserEditModal({ show, onClose, user, onSave }) {
  const [formData, setFormData] = useState({
    hoTen: '',
    email: '',
    soDienThoai: '',
    maVaiTro: '',
    trangThai: true,
  });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (user) {
      setFormData({
        hoTen: user.hoTen || '',
        email: user.email || '',
        soDienThoai: user.soDienThoai || '',
        maVaiTro: user.maVaiTro?.maVaiTro || '', // chỉ lấy ID string
        trangThai: user.trangThai === true,
      });
    }
  }, [user]);

  if (!show || !user) return null;

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }));
  };

  const handleSubmit = async () => {
    setLoading(true);
    try {
      // Payload đúng backend chấp nhận
      const payload = {
  ho_ten: formData.hoTen?.trim() || "",
  email: formData.email?.trim() || null,
  so_dien_thoai: formData.soDienThoai?.trim() || null,
  ma_vai_tro: formData.maVaiTro || null,
  trang_thai: formData.trangThai,
};


      console.log('Payload gửi backend:', payload);

      await onSave(user.maNguoiDung, payload);
     
      onClose();
    } catch (err) {
      console.error('Lỗi cập nhật:', err.response?.data || err);
      alert('Cập nhật thất bại!');
    } finally {
      setLoading(false);
    }
  };

  const roleOptions = [
    { value: 'ADMIN', label: 'Quản trị viên' },
    { value: 'BAO_TRI', label: 'Nhân viên bảo trì' },
    { value: 'KIEM_KE', label: 'Nhân viên kiểm kê' },
    { value: 'THANH_LY', label: 'Nhân viên thanh lý' },
  ];

  const modalContent = (
    <div className="modal show d-block" style={{ background: 'rgba(0,0,0,0.6)' }} tabIndex="-1">
      <div className="modal-dialog modal-lg modal-dialog-centered">
        <div className="modal-content shadow-lg">
          <div className="modal-header bg-primary text-white">
            <h5 className="modal-title">
              Chỉnh sửa người dùng: <strong>{user.tenDangNhap}</strong>
            </h5>
            <button type="button" className="btn-close btn-close-white" onClick={onClose} disabled={loading} />
          </div>

          <div className="modal-body">
            <div className="row g-3">
              <div className="col-md-6">
                <label className="form-label fw-semibold">Họ tên</label>
                <input type="text" className="form-control" name="hoTen" value={formData.hoTen} onChange={handleChange} required />
              </div>
              <div className="col-md-6">
                <label className="form-label fw-semibold">Email</label>
                <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} />
              </div>
              <div className="col-md-6">
                <label className="form-label fw-semibold">Số điện thoại</label>
                <input type="text" className="form-control" name="soDienThoai" value={formData.soDienThoai} onChange={handleChange} placeholder="VD: 0123456789" />
              </div>
              <div className="col-md-6">
                <label className="form-label fw-semibold">Vai trò</label>
                <select className="form-select" name="maVaiTro" value={formData.maVaiTro} onChange={handleChange}>
                  <option value="">Chưa phân quyền</option>
                  {roleOptions.map(r => (
                    <option key={r.value} value={r.value}>{r.label}</option>
                  ))}
                </select>
              </div>
              <div className="col-md-6 d-flex align-items-center">
                <div className="form-check form-switch">
                  <input type="checkbox" className="form-check-input" id="trangThai" name="trangThai" checked={formData.trangThai} onChange={handleChange} />
                  <label className="form-check-label fw-semibold" htmlFor="trangThai">
                    {formData.trangThai ? 'Hoạt động' : 'Bị khóa'}
                  </label>
                </div>
              </div>
            </div>
          </div>

          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose} disabled={loading}>Hủy</button>
            <button className="btn btn-primary" onClick={handleSubmit} disabled={loading}>
              {loading ? (
                <>
                  <span className="spinner-border spinner-border-sm me-2"></span>
                  Đang lưu...
                </>
              ) : 'Lưu thay đổi'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );

  return createPortal(modalContent, document.body);
}
