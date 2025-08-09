import { jwtDecode } from "jwt-decode"; // ✅ correct named import


export const saveToken = (token) => {
  localStorage.setItem("token", token);
};

export const getToken = () => localStorage.getItem("token");

export const getRoleFromToken = () => {
  const token = getToken();
  if (!token) return null;
  try {
    const decoded = jwtDecode(token);
    return decoded?.roles?.[0]; // Example: ROLE_USER
  } catch (e) {
    return null;
  }
};

export const logout = () => {
  localStorage.removeItem("token");
};
