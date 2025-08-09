import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/auth/Login";
import RegisterUser from "./pages/auth/RegisterUser";
import RegisterOfficer from "./pages/auth/RegisterOfficer";
import UserDashboard from "./pages/user/UserDashboard";
import OfficerDashboard from "./pages/officer/OfficerDashboard";
import AdminDashboard from "./pages/admin/AdminDashboard";
import { getRoleFromToken } from "./utils/auth";
import Navbar from "./components/ Navbar";
function App() {
  const role = getRoleFromToken();

  return (
    <BrowserRouter>
    <Navbar/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register/user" element={<RegisterUser />} />
        <Route path="/register/officer" element={<RegisterOfficer />} />
        <Route path="/user/dashboard" element={role === "ROLE_USER" ? <UserDashboard /> : <Navigate to="/" />} />
        <Route path="/officer/dashboard" element={role === "ROLE_OFFICER" ? <OfficerDashboard /> : <Navigate to="/" />} />
        <Route path="/admin/dashboard" element={role === "ROLE_ADMIN" ? <AdminDashboard /> : <Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
