import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    googleLogin: false,
  },
  mutations: {
    GOOGLE_LOGIN: function (state, value) {
      state.googleLogin = value;
    }
  },
  actions: {
    googleLogin: function ({ commit }, value) {
      commit('GOOGLE_LOGIN',value);
    }
  },
  modules: {}
})
