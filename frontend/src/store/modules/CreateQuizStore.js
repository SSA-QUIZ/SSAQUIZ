import axios from 'axios';

const CreateQuizStore = {
  namespaced: true,
  state: {
    quizData: {},
    selectedSlideIndex: 0,
    preQuizData: {},
  },
  mutations: {
    SET_QUIZ_DATA: function (state, value) {
      state.quizData = value;
    },
    SET_PRE_QUIZ_DATA: function (state, value) {
      state.preQuizData = JSON.parse(JSON.stringify(value));
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
      else if (value[0] === "time")
        state.quizData.slideList[value[1]].time = value[2];
      else
        state.quizData.slideList[value[1]].type = value[2];
    },
    DELETE_SLIDE: function (state, value) {
      state.quizData.slideList.splice(value, 1);
    },
    SET_SELECTED_SLIDE_INDEX: function (state, value) {
      state.selectedSlideIndex = value;
    },
    SET_ORDERINGANSWER: function (state, value) {
      let target = state.quizData["slideList"][state.selectedSlideIndex]["answer"];
      target = target.substr(0, value[0]-1) + value[1].toString() + target.substr(value[0]);
      state.quizData["slideList"][state.selectedSlideIndex]["answer"] = target;      
    },
    RESET_ORDERINGANSWER: function (state) {
      state.quizData["slideList"][state.selectedSlideIndex]["answer"] = "0000";
    },
  },
  actions: {
    getQuizData: function ({ commit }, value) {
      axios.get("https://k4a304.p.ssafy.io/api-quiz/workbook/" + value)
        .then(res => {
          commit('SET_QUIZ_DATA', res.data.object);
          commit('SET_PRE_QUIZ_DATA', res.data.object);
        })
        .catch(err => console.log(err))
    },
    setPreQuizData: function ({ commit }, value) {
      commit('SET_PRE_QUIZ_DATA', value);
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
    removeSlide: function ({ commit }, value) {
      commit('DELETE_SLIDE', value);
    },
    setSelectedSlideIndex: function ({ commit }, value) {
      commit('SET_SELECTED_SLIDE_INDEX', value);
    },
    setOrderingAnswer: function ({ commit }, value) {
      commit('SET_ORDERINGANSWER', value);
    },
    resetOrderingAnswer: function ({ commit }) {
      commit('RESET_ORDERINGANSWER');
    }
  }
};

export default CreateQuizStore;