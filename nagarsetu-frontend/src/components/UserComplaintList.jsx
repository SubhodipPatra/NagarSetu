import { useEffect, useState } from "react";
import api from "../utils/axiosConfig";
import "./styles/UserComplaintList.css"; // ✅ import the stylesheet

export default function UserComplaintList() {
  const [complaints, setComplaints] = useState([]);

  useEffect(() => {
    fetchComplaints();
  }, []);

  const fetchComplaints = async () => {
    try {
      const res = await api.get("/complaints/my");
      setComplaints(res.data);
    } catch (err) {
      console.error("Failed to fetch complaints");
    }
  };

  return (
    <div className="user-complaint-list">
      {complaints.length === 0 ? (
        <p>No complaints yet.</p>
      ) : (
        complaints.map((c) => (
          <div className="complaint-item" key={c.id}>
            <b>{c.type}</b>
            <p>{c.description}</p>
            <p className="complaint-status">Status: {c.status}</p>
            {c.remarks && (
              <div className="complaint-remarks">Remarks: {c.remarks}</div>
            )}
          </div>
        ))
      )}
    </div>
  );
}
