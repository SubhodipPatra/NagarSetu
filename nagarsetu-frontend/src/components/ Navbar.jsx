import { Link, useNavigate } from "react-router-dom";
import { getRoleFromToken, logout } from "../utils/auth";
import logo from "../assets/logo.jpg"; // ✅ Add your logo here
import "./styles/Navbar.css"; // Optional

export default function Navbar() {
  const role = getRoleFromToken();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <Link to="/">
          <img src={logo} alt="NagarSetu Logo" className="navbar-logo" />
        </Link>
        {role === "ROLE_USER" && <Link to="/user/dashboard">Dashboard</Link>}
        {role === "ROLE_OFFICER" && <Link to="/officer/dashboard">Dashboard</Link>}
        {role === "ROLE_ADMIN" && <Link to="/admin/dashboard">Dashboard</Link>}
      </div>
      <div className="navbar-right">
        {role && <button onClick={handleLogout}>Logout</button>}
      </div>
    </nav>
  );
}
