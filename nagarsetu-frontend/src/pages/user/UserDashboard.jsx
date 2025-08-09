import { useEffect, useState } from "react";
import api from "../../utils/axiosConfig";
import ComplaintForm from "../../components/ComplaintForm";
import UserComplaintList from "../../components/UserComplaintList";
import LogoutButton from "../../components/LogoutButton";
import "./UserDashboard.css"; // CSS import

export default function UserDashboard() {
  const [userName, setUserName] = useState("");

  useEffect(() => {
    fetchUserInfo();
  }, []);

  const fetchUserInfo = async () => {
    try {
      const res = await api.get("/users/me"); // ✅ This endpoint must exist
      setUserName(res.data.name); // Ensure backend returns { name: "User Name" }
    } catch (err) {
      console.error("Failed to fetch user info");
    }
  };

  return (
    <div className="user-dashboard">
      <h2>Welcome, {userName || "User"}!</h2>
      <h3>Submit a Complaint</h3>
      <ComplaintForm />
      <hr />
      <h3>Your Complaints</h3>
      <UserComplaintList />
    </div>
  );
}
