import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../utils/axiosConfig";
import "../../components/styles/AuthForm.css";


export default function RegisterUser() {
  const [user, setUser] = useState({ name: "", email: "", pincode: "", password: "" });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/users/register", user);
      alert("User registered successfully");
      navigate("/login");
    } catch (err) {
      alert("Registration failed");
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Register as User</h2>
      <form onSubmit={handleSubmit} className="auth-form">
        <input name="name" placeholder="Name" onChange={handleChange} required />
        <input name="email" placeholder="Email" onChange={handleChange} required />
        <input name="pincode" placeholder="Pincode" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
        <button type="submit">Register</button>
      </form>
    </div>
  );
}
