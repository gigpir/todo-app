import { createStore } from "vuex";

export default createStore({
  state: {
    refreshToken: localStorage.getItem("refreshToken"),
    token: localStorage.getItem("token"),
  },
  mutations: {
    saveToken(state, token) {
      localStorage.setItem("token", token);
      state.token = token;
    },
    saveRefreshToken(state, refreshToken) {
      localStorage.setItem("refreshToken", refreshToken);
      state.refreshToken = refreshToken;
    },
    clearToken(state) {
      localStorage.removeItem("token");
      state.token = "";
    },
    clearRefreshToken(state) {
      localStorage.removeItem("refreshToken");
      state.refreshToken = "";
    },
  },
  actions: {},
  modules: {},
});
