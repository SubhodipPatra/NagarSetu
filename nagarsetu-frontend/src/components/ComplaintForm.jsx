import { useState } from "react";
import api from "../utils/axiosConfig";
import "./styles/ComplaintForm.css"; // ✅ CSS import

export default function ComplaintForm() {
  const [form, setForm] = useState({
    description: "",
    userPincode: "",
    type: "DEVELOPMENT",
  });
  const [success, setSuccess] = useState("");

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/complaints/submit", form);
      setSuccess("Complaint submitted successfully!");
    } catch (err) {
      setSuccess("Failed to submit complaint");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="complaint-form">
     <textarea
  name="description"
  placeholder="Description"
  onChange={handleChange}
  required
  rows={5} // Number of visible lines
  style={{ width: "100%" }} // Optional: to match input width
/>

      <input
        name="userPincode"
        placeholder="Your Pincode"
        onChange={handleChange}
        required
      />
      <select name="type" onChange={handleChange} required>
        <option value="DEVELOPMENT">Development</option>
        <option value="ISSUE">Issue</option>
        <option value="ELECTRICITY">Electricity</option>
        <option value="WATER">Water</option>
      </select>
      <button type="submit">Submit</button>
      {success && <p>{success}</p>}
    </form>
  );
}
