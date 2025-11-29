import { useEffect, useState } from "react";
import api from "../../utils/axiosConfig";
import LogoutButton from "../../components/LogoutButton";
import OfficerComplaintCard from "../../components/OfficerComplaintCard";
import "./OfficerDashboard.css"; 

export default function OfficerDashboard() {
  const [complaints, setComplaints] = useState([]);
  const [officerName, setOfficerName] = useState("");

  useEffect(() => {
    fetchComplaints();
    fetchOfficerInfo();
  }, []);

  const fetchComplaints = async () => {
    try {
      const res = await api.get("/officer/complaints");
      setComplaints(res.data);
    } catch (err) {
      console.error("Error fetching officer complaints");
    }
  };

  const fetchOfficerInfo = async () => {
    try {
      const res = await api.get("/officers/me"); 
      setOfficerName(res.data.name); 
    } catch (err) {
      console.error("Failed to fetch officer info");
    }
  };

  return (
    <div className="officer-dashboard">
      <h2>Welcome, Officer {officerName}!</h2>
      
      <h3>Your Assigned Complaints</h3>

      {complaints.length === 0 ? (
        <p className="no-complaints">No assigned complaints.</p>
      ) : (
        <div className="complaints-container">
          {complaints.map((c) => (
            <OfficerComplaintCard
              key={c.id}
              complaint={c}
              refresh={fetchComplaints}
            />
          ))}
        </div>
      )}
    </div>
  );
}
