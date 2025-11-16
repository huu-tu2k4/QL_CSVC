import axios from 'axios';

const noAuthEndpoints = ['/auth/login', '/auth/refresh', '/auth/logout'];

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 10000,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor: thêm Token vào header
axiosInstance.interceptors.request.use(
  (config) => {
    const url = config.url?.split('?')[0];
    if (url && !noAuthEndpoints.includes(url)) {
      const token = localStorage.getItem('token'); // đổi tên cho rõ
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor: xử lý 401 → gọi /auth/refresh (không body)
axiosInstance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    const url = originalRequest.url?.split('?')[0];

    console.log("❌ API ERROR:", error.response?.status, url);

    if (
      error.response?.status === 401 &&
      !originalRequest._retry &&
      url &&
      !noAuthEndpoints.includes(url)
    ) {
      originalRequest._retry = true;

      try {
        console.log("🔄 BẮT ĐẦU REFRESH TOKEN...");

        const refreshResponse = await axiosInstance.post('/auth/refresh');

        console.log("✅ REFRESH RESPONSE:", refreshResponse.data);

        const newToken = refreshResponse.data.result.token;
        console.log("🆕 NEW ACCESS TOKEN:", newToken);

        localStorage.setItem('token', newToken);

        originalRequest.headers.Authorization = `Bearer ${newToken}`;

        console.log("🔁 GỬI LẠI REQUEST CŨ:", originalRequest.url);
        return axiosInstance(originalRequest);

      } catch (refreshError) {
        console.error("🔥 REFRESH FAILED:", refreshError);

        localStorage.removeItem('token');
        window.location.href = '/login';

        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);


export default axiosInstance;