// src/services/userService.js
import axiosInstance from '../api/axiosInstance';

export const getMyInfo = async () => {
  const res = await axiosInstance.get('/api/nguoi_dung/myInfo');
  return res.data.result;
};

export const getAllUsers = async (page = 0, size = 10, search = '') => {
  const params = { page, size };
  if (search?.trim()) params.search = search.trim();

  const response = await axiosInstance.get('/api/nguoi_dung', { params });
  const result = response.data.result;

  return {
    data: result.content || [],
    totalPages: result.totalPages || 1,
    totalElements: result.totalElements || 0,
    currentPage: result.number || 0,
  };
};

export const deleteUser = async (maNguoiDung) => {
  return axiosInstance.delete(`/api/nguoi_dung/${maNguoiDung}`);
};

export const toggleStatus = async (maNguoiDung, currentStatus) => {
  const isActive = currentStatus === true || currentStatus === 'HOAT_DONG';
  return axiosInstance.patch(`/api/nguoi_dung/${maNguoiDung}/trang-thai`, {
    trangThai: !isActive,
  });
};

export const getUserById = async (maNguoiDung) => {
  const res = await axiosInstance.get(`/api/nguoi_dung/${maNguoiDung}`);
  return res.data.result;
};

export const createUser = async (userData) => {
  return axiosInstance.post('/api/nguoi_dung', userData);
};

export const updateUser = async (maNguoiDung, userData) => {
  return axiosInstance.put(`/api/nguoi_dung/${maNguoiDung}`, userData);
};

const userService = {
  getMyInfo,
  getAllUsers,
  deleteUser,
  toggleStatus,
  getUserById,
  createUser,
  updateUser,
};

export default userService;