// src/components/users/AddUserButton.jsx
import React, { useState } from 'react';
import UserAddModal from './UserAddModal';
import userService from '../../services/userService';

export default function AddUserButton({ onUserAdded }) {
  const [modalOpen, setModalOpen] = useState(false);

  const handleSave = async (payload) => {
    await userService.createUser(payload);
    onUserAdded?.(); // callback để refresh bảng
  };

  return (
    <>
      <button
        className="btn btn-primary d-flex align-items-center gap-2"
        onClick={() => setModalOpen(true)}
      >
        <i className="bi bi-plus-lg"></i>
        Thêm người dùng
      </button>

      {modalOpen && (
        <UserAddModal
          show={modalOpen}
          onClose={() => setModalOpen(false)}
          onSave={handleSave}
        />
      )}
    </>
  );
}
