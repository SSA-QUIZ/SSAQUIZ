import axios from 'axios';

const UserStore = {
    namespaced: true,
    state: {
      quizList: [],
      selectQuiz: {},
      newQuizId: '',
    },
    getters: {},
    mutations: {
      SET_QUIZLIST: function (state, value) {
        state.quizList = value;
      },
      ADD_QUIZ: function (state, value) {
        state.quizList.splice(0, 0, value);
        state.newQuizId = value.id;
      },
      SELECT_QUIZ: function (state, value) {
        state.selectQuiz = value;
      },
      DELETE_QUIZ: function (state, value) {
        let index = state.quizList.findIndex(function(item) {return item.id === value});
        state.quizList.splice(index, 1);
      },
    },
    actions: {
      setQuizList: function ({ commit }, value) {
        axios.get("http://k4a304.p.ssafy.io/api-quiz/workbook-all/" + value)
        .then(res => {
          commit('SET_QUIZLIST', res.data.object);
        })
        .catch(err => { console.log(err); })
      },
      addQuiz: async function ({ commit }, value) {
        await axios.post("http://k4a304.p.ssafy.io/api-quiz/workbook", value)
          .then(res => {
            commit('ADD_QUIZ', res.data.object);
          })
          .catch(err => console.log(err))
      },
      selectQuiz: function ({ commit }, value) {
        axios.get(`http://k4a304.p.ssafy.io/api-quiz/workbook/${value}`)
          .then(res => {
            console.log(res);
            commit('SELECT_QUIZ', res.object);
          })
          .catch(err => console.log(err))
      },
      removeQuiz: function ({ commit }, value) {
        axios.delete(`http://k4a304.p.ssafy.io/api-quiz/workbook/${value[0]}/${value[1]}`)
          .then(res => {
            commit('DELETE_QUIZ', value[0]);
            return res
          })
          .catch(err => console.log(err))
      },

    }
  }
  
  export default UserStore;