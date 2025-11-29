import { useEffect, useState } from "react";
import api from "../utils/axiosConfig";
import "./styles/ComplaintTableAdmin.css"; // ✅ Import CSS

export default function ComplaintTableAdmin() {
  const [complaints, setComplaints] = useState([]);

  useEffect(() => {
    fetchComplaints();
  }, []);

  const fetchComplaints = async () => {
    try {
      const res = await api.get("/admin/complaints");
      setComplaints(res.data);
    } catch (err) {
      console.error("Failed to fetch complaints");
    }
  };

  const deleteComplaint = async (id) => {
    if (!window.confirm("Are you sure you want to delete this complaint?")) return;

    try {
      await api.delete(`/complaints/${id}`);
      alert("✅ Complaint deleted");
      fetchComplaints();
    } catch (err) {
      alert("❌ Failed to delete complaint");
    }
  };

  return (
    <table className="complaint-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Description</th>
          <th>Type</th>
          <th>Status</th>
          <th>User Email</th>
          <th>Officer</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {complaints.map((c) => (
          <tr key={c.id}>
            <td>{c.id}</td>
            <td>{c.description}</td>
            <td>{c.type}</td>
            <td>{c.status}</td>
            <td>{c.userEmail}</td>
            <td>{c.officer?.name || "Not assigned"}</td>
            <td>
              <button className="delete-button" onClick={() => deleteComplaint(c.id)}>
                Delete
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
