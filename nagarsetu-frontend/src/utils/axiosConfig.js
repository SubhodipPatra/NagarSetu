import axios from "axios";

// Define public endpoints that don't need a token
const publicEndpoints = ["/auth/login", "/users/register", "/officers/register"];

// Create Axios instance with backend base URL
const api = axios.create({
  baseURL: "http://localhost:8084", // 🔁 Your backend URL
  withCredentials: false, // JWT is in headers, not cookies
});

// Request Interceptor: Attach token if not public
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");

    // Check if the current request URL matches a public endpoint
    const isPublic = publicEndpoints.some((url) => config.url.includes(url));

    // Attach token if it's not a public request
    if (!isPublic && token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response Interceptor: Handle auth errors globally
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && [401, 403].includes(error.response.status)) {
      console.warn("🚫 Unauthorized or Forbidden:", error.response);
    }
    return Promise.reject(error);
  }
);

export default api;
