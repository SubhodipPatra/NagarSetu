import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../utils/axiosConfig";
import { saveToken, getRoleFromToken } from "../../utils/auth";
import "../../components/styles/Login.css"; 

export default function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/login", form);
      saveToken(res.data.token);
      const role = getRoleFromToken();

      if (role === "ROLE_USER") navigate("/user/dashboard");
      else if (role === "ROLE_OFFICER") navigate("/officer/dashboard");
      else if (role === "ROLE_ADMIN") navigate("/admin/dashboard");
      else setError("Unknown role");
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className="auth-form">
        <input
          name="email"
          type="email"
          placeholder="Email"
          onChange={handleChange}
          autoComplete="email"
          required
        />
        <input
          name="password"
          type="password"
          placeholder="Password"
          onChange={handleChange}
          autoComplete="current-password"
          required
        />
        <button type="submit">Login</button>
        {error && <p className="error-msg">{error}</p>}
      </form>
    </div>
  );
}
