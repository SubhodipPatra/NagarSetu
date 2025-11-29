import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../utils/axiosConfig";
import "../../components/styles/AuthForm.css";

export default function RegisterOfficer() {
  const [officer, setOfficer] = useState({
    name: "",
    email: "",
    password: "",
    department: "",
    location: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setOfficer({ ...officer, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/officers/register", officer);
      alert("Officer registration submitted for approval");
      navigate("/login");
    } catch (err) {
      alert("Registration failed");
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Register as Officer</h2>
      <form onSubmit={handleSubmit} className="auth-form">
        <input name="name" placeholder="Name" onChange={handleChange} required />
        <input name="email" placeholder="Email" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
        <input name="department" placeholder="Department" onChange={handleChange} required />
        <input name="location" placeholder="Pincode" onChange={handleChange} required />
        <button type="submit">Register</button>
      </form>
    </div>
  );
}
