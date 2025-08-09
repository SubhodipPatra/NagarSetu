import { useEffect, useState } from "react";
import api from "../utils/axiosConfig";
import "./styles/OfficerApprovalTable.css"; // ✅ Import CSS

export default function OfficerApprovalTable() {
  const [officers, setOfficers] = useState([]);

  useEffect(() => {
    fetchPending();
  }, []);

  const fetchPending = async () => {
    const res = await api.get("/admin/officers/pending");
    setOfficers(res.data);
  };

  const approve = async (id) => {
    await api.put(`/admin/officers/approve/${id}`);
    fetchPending();
  };

  const reject = async (id) => {
    await api.put(`/admin/officers/reject/${id}`);
    fetchPending();
  };

  return officers.length === 0 ? (
    <p>No pending officers.</p>
  ) : (
    <table className="approval-table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Department</th>
          <th>Email</th>
          <th>Location</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {officers.map((off) => (
          <tr key={off.id}>
            <td>{off.name}</td>
            <td>{off.department}</td>
            <td>{off.email}</td>
            <td>{off.location}</td>
            <td>
              <button className="approve-button" onClick={() => approve(off.id)}>
                Approve
              </button>
              <button className="reject-button" onClick={() => reject(off.id)}>
                Reject
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
