import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    googleLoginToken: '',
    userInfo: [],
  },
  mutations: {
    SET_GOOGLE_LOGIN_TOKEN: function (state, value) {
      state.googleLoginToken = value;
    },
    SET_USER_INFO: function (state, value) {
      state.userInfo = value;
    }
  },
  actions: {
    setGoogleLoginToken: function ({ commit }, value) {
      commit('SET_GOOGLE_LOGIN_TOKEN', value);
    },
    setUserInfo: function ({ commit }, value) {
      commit('SET_USER_INFO', value);
    }
  },
  modules: {
  }
})
