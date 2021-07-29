// Import Modules
import store from "@/store/index.js";

// Custom modules
import auth from "@/middlewares/auth.js";
//import axios from "axios";
import authLogout from "@/middlewares/auth-logout";

const authService = {
  login: (username, password) => {
    const url = "/login";

    return auth
      .post(url, {
        username: username,
        password: password,
      })
      .then((res) => {
        // We should receive the following object
        // {
        //   token: <token>,
        //   refreshToken: <refreshToken>
        // }
        // Store the info
        store.commit("saveToken", res.token);
        store.commit("saveRefreshToken", res.refreshToken);

        // Return the res for the next handler
        return res;
      });
  },
  register: (name, surname, username, password) => {
    const url = "/register";

    return auth
      .post(url, {
        name: name,
        surname: surname,
        username: username,
        password: password,
      })
      .then((res) => {
        // We should receive the following object
        // {
        //   token: <token>,
        //   refreshToken: <refreshToken>
        // }
        // Store the info
        store.commit("saveToken", res.token);
        store.commit("saveRefreshToken", res.refreshToken);

        // Return the res for the next handler
        return res;
      });
  },
  refreshToken: () => {
    const url = "/refreshToken";
    const refreshToken = store.state.refreshToken;

    return auth
      .post(url, {
        refreshToken: refreshToken,
      })
      .then((res) => {
        // We should receive the following object
        // {
        //   token: <token>
        // }
        // Store the info
        store.commit("saveToken", res.token);

        // Return the res for the next handler
        return res;
      })
      .catch((error) => {
        // refreshToken is not valid
        store.commit("clearToken");
        store.commit("clearRefreshToken");

        return Promise.reject(error);
      });
  },
  logout: () => {
    //const url = auth.defaults.baseURL + "/logout";
    //const token = store.state.token;
    const refreshToken = store.state.refreshToken;

    authLogout.post("", {
      refreshToken: refreshToken,
    });
  },
};

export default authService;
