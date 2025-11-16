import axiosInstance from '../api/axiosInstance';

// Key lưu accessToken
const TOKEN_KEY = 'token';

// ==================== LOGIN ====================
export const login = async (userName, password) => {
  try {
    const response = await axiosInstance.post('/auth/login', { userName, password });
    console.log('Login response:', response.data);

    const token = response.data.result?.token;
    if (!token) {
      throw new Error('Token không tồn tại trong response');
    }

    localStorage.setItem(TOKEN_KEY, token);
    return response.data;
  } catch (err) {
    console.error('Login failed:', err);
    throw err;
  }
};

// ==================== LOGOUT ====================
export const logout = async () => {
  try {
    // GỌI /auth/logout → KHÔNG CẦN BODY
    // Backend sẽ xóa cookie refresh_token
    await axiosInstance.post('/auth/logout');
  } catch (err) {
    console.warn('Server logout failed (có thể do mạng):', err);
  } finally {
    // Luôn xóa localStorage và redirect
    localStorage.removeItem(TOKEN_KEY);
    window.location.href = '/login';
  }
};

// ==================== KIỂM TRA ĐĂNG NHẬP ====================
export const isAuthenticated = () => {
  return !!localStorage.getItem(TOKEN_KEY);
};

// ==================== LẤY TOKEN (nếu cần dùng ở nơi khác) ====================
export const getToken = () => {
  return localStorage.getItem(TOKEN_KEY);
};