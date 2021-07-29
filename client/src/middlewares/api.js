// Import Modules
import axios from "axios";
import store from "@/store/index.js";

// Custom modules
import authService from "@/service/auth-service.js";

// Define configuration parameters
const baseURL = "/api/v1";

// Api
const api = axios.create({
  baseURL: baseURL,
});

// Set configuration
api.defaults.headers.post["Content-Type"] = "application/json";
api.defaults.headers.put["Content-Type"] = "application/json";

// Interceptor for the protected endpoints which need to have the authorization header
api.interceptors.request.use(
  (config) => {
    const token = store.state.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      return config;
    } else {
      return config;
    }
  },
  (error) => {
    if (error.response) {
      // The request was made and the server responded with a status code that falls out of the range of 2xx
      return Promise.reject(error.response.data);
    } else if (error.request) {
      // The request was made but no response was received
      return Promise.reject({
        status: 503,
        message: "Service Unavailable",
      });
    } else {
      // Something happened in setting up the request that triggered an Error
      return Promise.reject({
        status: -1,
        message: error.message,
      });
    }
  }
);

api.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    const originalRequest = error.config;

    // Try to refresh the token
    if (error.response.status === 401 && !originalRequest._retry) {
      if (store.state.refreshToken) {
        // Refresh token
        originalRequest._retry = true;
        return authService.refreshToken().then(() => {
          return api(originalRequest);
        });
      } else {
        // Token is expired so => we can safely delete them
        store.commit("clearToken");
      }
    }

    // Handle errors
    if (error.response) {
      // The request was made and the server responded with a status code that falls out of the range of 2xx
      return Promise.reject(error.response.data);
    } else if (error.request) {
      // The request was made but no response was received
      return Promise.reject({
        status: 503,
        message: "Service Unavailable",
      });
    } else {
      // Something happened in setting up the request that triggered an Error
      return Promise.reject({
        status: -1,
        message: error.message,
      });
    }
  }
);

// Export module
export default api;
