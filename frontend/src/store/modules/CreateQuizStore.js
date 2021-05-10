import axios from 'axios';

const CreateQuizStore = {
  namespaced: true,
  state: {
    quizData: {},
  },
  getters: {
    getData(state) {
      return state.quizData;
    }
  },
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
      state.quizData.slideList[value[0]].answerList[value[1]] = value[2];
    },
    SET_ANSWER: function (state, value) {
      state.quizData.slideList[value[0]].answer = value[1];
    },
    SET_IMAGE_DATA: function (state, value) {
      state.quizData.slideList[value[0]].imagePath = value[1];
    },
    SET_OPTIONS: function (state, value) {
      if (value[0] === "scoreFactor")
        state.quizData.slideList[value[1]].scoreFactor = value[2];
      else if (value[0] === "timeLimit")
        state.quizData.slideList[value[1]].time = value[2];
      else
        state.quizData.slideList[value[1]].type = value[2];
    }
  },
  actions: {
    getQuizData: async function ({ commit }, value) {
      await axios.get("https://k4a304.p.ssafy.io/api-quiz/workbook/" + value)
        .then(res => {
          commit('SET_QUIZ_DATA', res.data.object);
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
    setAnswer: function ({ commit }, value) {
      commit('SET_ANSWER', value)
    },
    resetQuizData: function ({ commit }) {
      commit('SET_QUIZ_DATA', [])
    },
    setImageData: function ({ commit }, value) {
      commit('SET_IMAGE_DATA', value)
    },
    setOptions: function ({ commit }, value) {
      commit('SET_OPTIONS', value)
    },
  }
};

export default CreateQuizStore;