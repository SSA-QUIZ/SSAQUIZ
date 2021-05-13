import axios from 'axios';

const CreateQuizStore = {
  namespaced: true,
  state: {
    quizData: {},
    selectedSlideIndex: 0,
    answerStyle: [
      {
        "icon": "fas fa-cat",
        "color": "#ffdc46"
      },
      {
        "icon": "fas fa-leaf",
        "color": "#ff85b1"
      },
      {
        "icon": "fa fa-car",
        "color": "#7cb1ff"
      },
      {
        "icon": "fas fa-pills",
        "color": "#aaed81"
      }
    ]
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
    }
  },
  actions: {
    getQuizData: function ({ commit }, value) {
      axios.get("https://k4a304.p.ssafy.io/api-quiz/workbook/" + value)
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
    removeSlide: function ({ commit }, value) {
      commit('DELETE_SLIDE', value);
      // await axios.delete(`https://k4a304.p.ssafy.io/api-quiz/workbook/${value[0]}/${value[1]}/${value[2]}`)
      //   .then(res => {
      //     console.log(res);
      //     commit('DELETE_SLIDE', value[2]);
      //     return res
      //   })
      //   .catch(err => {
      //     console.log(err);
      //     commit('DELETE_SLIDE', value[2]);
      //   })
    },
    setSelectedSlideIndex: function ({ commit }, value) {
      commit('SET_SELECTED_SLIDE_INDEX', value);
    }
  }
};

export default CreateQuizStore;