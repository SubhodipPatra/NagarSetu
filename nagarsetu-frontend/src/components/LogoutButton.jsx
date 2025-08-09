import { useNavigate } from "react-router-dom";
import { logout } from "../utils/auth";

export default function LogoutButton() {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return <button onClick={handleLogout}>Logout</button>;
}
