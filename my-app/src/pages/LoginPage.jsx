import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../services/authService';

function LoginPage() {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      await login(userName, password); // login() đã lưu accessToken vào localStorage
      navigate('/', { replace: true }); // chuyển về trang chủ
    } catch (err) {
      console.error('Login error:', err);
      setError(err.response?.data?.message || err.message || 'Đăng nhập thất bại. Vui lòng thử lại.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center position-relative overflow-hidden bg-light">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-5 col-lg-4">
            <div className="card shadow-lg border-0 rounded-4">
              <div className="card-body p-5">
                <h4 className="fw-bold text-primary text-center mb-4">QLCSVC ĐH</h4>
                
                <form onSubmit={handleSubmit} noValidate>
                  <div className="mb-3">
                    <label htmlFor="userName" className="form-label fw-medium">
                      Tên đăng nhập
                    </label>
                    <input
                      id="userName"
                      type="text"
                      className={`form-control form-control-lg ${error ? 'is-invalid' : ''}`}
                      value={userName}
                      onChange={(e) => setUserName(e.target.value.trim())}
                      required
                      disabled={loading}
                      autoComplete="username"
                      placeholder="Nhập tên đăng nhập"
                    />
                  </div>

                  <div className="mb-3">
                    <label htmlFor="password" className="form-label fw-medium">
                      Mật khẩu
                    </label>
                    <input
                      id="password"
                      type="password"
                      className={`form-control form-control-lg ${error ? 'is-invalid' : ''}`}
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                      disabled={loading}
                      autoComplete="current-password"
                      placeholder="Nhập mật khẩu"
                    />
                  </div>

                  {error && (
                    <div className="alert alert-danger py-2 px-3 small" role="alert">
                      {error}
                    </div>
                  )}

                  <button
                    type="submit"
                    className="btn btn-primary w-100 btn-lg fw-semibold"
                    disabled={loading || !userName || !password}
                  >
                    {loading ? (
                      <>
                        <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                        Đang đăng nhập...
                      </>
                    ) : (
                      'Đăng nhập'
                    )}
                  </button>
                </form>

                <div className="text-center mt-4">
                  <small className="text-muted">
                    © 2025 QLCSVC ĐH. All rights reserved.
                  </small>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;