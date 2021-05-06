const CommonStore = {
  namespaced: true,
  state: {
    isStudent: true,
  },
  getters: {},
  mutations: {
    SET_ISSTUDENT: function (state, value) {
      state.isStudent = value;
    }
  },
  actions: {
    setIsStudent: function ({ commit }, value) {
      commit('SET_ISSTUDENT', value);
    }
  }
}

export default CommonStore;