// Import Modules
import axios from "axios";

// Define configuration parameters
const baseURL = "/auth";

// Api
const auth = axios.create({
  baseURL: baseURL,
});

// Set configuration
auth.defaults.headers.post["Content-Type"] = "application/json";
auth.defaults.headers.put["Content-Type"] = "application/json";

auth.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
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

export default auth;
