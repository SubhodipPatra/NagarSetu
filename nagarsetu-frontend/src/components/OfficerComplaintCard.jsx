import { useState } from "react";
import api from "../utils/axiosConfig";
import "./styles/OfficerComplaintCard.css"; // ✅ Import CSS

export default function OfficerComplaintCard({ complaint, refresh }) {
  const [status, setStatus] = useState(complaint.status);
  const [remarks, setRemarks] = useState(complaint.remarks || "");

  const updateStatus = async () => {
    try {
      await api.put(`/officer/complaints/update/${complaint.id}`, null, {
        params: { status, remarks },
      });
      alert("Complaint updated");
      refresh();
    } catch (err) {
      alert("Failed to update complaint");
    }
  };

  return (
    <div className="complaint-card">
      <h4>{complaint.type}</h4>
      <p><b>Description:</b> {complaint.description}</p>
      <p><b>Status:</b> {complaint.status}</p>
      <p><b>User Pincode:</b> {complaint.userPincode}</p>
      <p><b>User Email:</b> {complaint.userEmail}</p>

      <select value={status} onChange={(e) => setStatus(e.target.value)}>
        <option value="PENDING">PENDING</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="RESOLVED">RESOLVED</option>
        <option value="REJECTED">REJECTED</option>
      </select>

      <input
        placeholder="Remarks (optional)"
        value={remarks}
        onChange={(e) => setRemarks(e.target.value)}
      />

      <button onClick={updateStatus}>Update</button>
    </div>
  );
}
