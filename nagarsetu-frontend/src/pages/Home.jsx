import { Link } from "react-router-dom";
import Login from "./auth/Login";
import "../components/styles/Home.css";

export default function Home() {
  return (
    <div className="home-container">
      <h1>NagarSetu</h1>
      <p><i><b>Connecting Citizens with Local Governance</b></i></p>

      <div className="home-buttons">
        <div className="login-button-wrapper">
          <Login />
        </div>
        <Link to="/register/user">
          <button className="home-button">Register as User</button>
        </Link>
        <Link to="/register/officer">
          <button className="home-button">Register as Officer</button>
        </Link>
      </div>
    </div>
  );
}
