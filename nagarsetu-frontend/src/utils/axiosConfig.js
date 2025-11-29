import axios from "axios";


const publicEndpoints = ["/auth/login", "/users/register", "/officers/register"];


const api = axios.create({
  baseURL: "http://localhost:8084", 
  withCredentials: false, 
});


api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");

    const isPublic = publicEndpoints.some((url) => config.url.includes(url));

    if (!isPublic && token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);


api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && [401, 403].includes(error.response.status)) {
      console.warn(" Unauthorized or Forbidden:", error.response);
    }
    return Promise.reject(error);
  }
);

export default api;
