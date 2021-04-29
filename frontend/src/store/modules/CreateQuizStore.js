import axios from 'axios';

const CreateQuizStore = {
  namespaced: true,
  state: {
    quizData: {},
  },
  getters: {},
  mutations: {
    SET_QUIZ_DATA: function (state, value) {
      state.quizData = value;
    }
  },
  actions: {
    setQuizData: function ({ commit }, value) {
      console.log(value)
      axios.post("http://k4a304.p.ssafy.io/api-quiz/workbook", value)
        .then(res => {
          commit('SET_QUIZ_DATA', res.data.object);
          return res.data.object.id
        })
        .catch(err => console.log(err))
    }
  }
};

export default CreateQuizStore;