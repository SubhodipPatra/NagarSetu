import { useState } from "react";
import api from "../utils/axiosConfig";
import "./styles/ComplaintCountByDepartment.css"; // ✅ CSS import

export default function ComplaintCountByDepartment() {
  const [dept, setDept] = useState("");
  const [count, setCount] = useState(null);

  const handleFetch = async () => {
    const res = await api.get("/admin/complaints/count", {
      params: { department: dept },
    });
    setCount(res.data.total);
  };

  return (
    <div className="complaint-count-container">
      <h3>Complaint Count by Department</h3>
      <input
        placeholder="Enter Department (e.g., BDO)"
        value={dept}
        onChange={(e) => setDept(e.target.value)}
      />
      <button onClick={handleFetch}>Get Count</button>
      {count !== null && (
        <p>
          Total complaints for <b>{dept}</b>: {count}
        </p>
      )}
    </div>
  );
}
