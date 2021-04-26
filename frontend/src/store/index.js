import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    googleLoginToken: '',
  },
  mutations: {
    SET_GOOGLE_LOGIN_TOKEN: function (state, value) {
      state.googleLoginToken = value;
    }
  },
  actions: {
    setGoogleLoginToken: function ({ commit }, value) {
      commit('SET_GOOGLE_LOGIN_TOKEN', value);
    }
  },
  modules: {
  }
})
