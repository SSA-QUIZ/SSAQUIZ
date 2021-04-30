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
    },
    SET_SLIDE_QUESTION: function (state, value) {
      state.quizData.slideList[value[0]].question = value[1];
    },
    ADD_SLIDE: function (state, value) {
      state.quizData.slideList.push(value);
    },
    SET_MULTIPLE_CHOICE: function (state, value) {
      state.quizData.slideList[value[0]].answerList = value[1];
    },
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
    },
    setSlideQuestion: function ({ commit }, value) {
      commit('SET_SLIDE_QUESTION', value);
    },
    addSlide: function ({ commit }, value) {
      commit('ADD_SLIDE', value);
    },
    setMultipleChoice: function ({ commit }, value) {
      commit('SET_MULTIPLE_CHOICE', value)
    },
  }
};

export default CreateQuizStore;