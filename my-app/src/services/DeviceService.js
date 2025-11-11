// src/services/thietBiService.js
import axios from '../api/axios';

const THIET_BI_API = '/api/thiet_bi';

export const getAllThietBi = async (keyword = '', trangThai = null, page = 0, size = 10) => {
  const params = { keyword, page, size };
  if (trangThai) params.trangThaiThietBi = trangThai;

  const response = await axios.get(THIET_BI_API, { params });
  return response.data; // Page<ThietBiResponse>
};

export const createThietBi = async (data) => {
  const response = await axios.post(THIET_BI_API, data);
  return response.data;
};

export const getThietBiById = async (maThietBi) => {
  const response = await axios.get(`${THIET_BI_API}/${maThietBi}`);
  return response.data;
};