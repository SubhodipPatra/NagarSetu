import LogoutButton from "../../components/LogoutButton";
import OfficerApprovalTable from "../../components/OfficerApprovalTable";
import ComplaintTableAdmin from "../../components/ComplaintTableAdmin";
import ComplaintCountByDepartment from "../../components/ComplaintCountByDepartment";
import "./AdminDashboard.css";

export default function AdminDashboard() {
  return (
    <div className="admin-dashboard">
      <h2>Welcome, Admin!</h2>
      <hr />
      <ComplaintCountByDepartment />
      <hr />
      <h3>Pending Officer Approvals</h3>
      <OfficerApprovalTable />
      <hr />
      <h3>All Complaints</h3>
      <ComplaintTableAdmin />
    </div>
  );
}
