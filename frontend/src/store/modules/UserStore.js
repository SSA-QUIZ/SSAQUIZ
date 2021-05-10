import axios from 'axios';

const UserStore = {
    namespaced: true,
    state: {
      quizList: [],
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
      DELETE_QUIZ: function (state, value) {
        let index = state.quizList.findIndex(function(item) {return item.id === value});
        state.quizList.splice(index, 1);
      }
    },
    actions: {
      setQuizList: function ({ commit }, value) {
        axios.get("https://k4a304.p.ssafy.io/api-quiz/workbook-all/" + value)
        .then(res => {
          commit('SET_QUIZLIST', res.data.object);
        })
        .catch(err => { console.log(err); })
      },
      addQuiz: async function ({ commit }, value) {
        await axios.post("https://k4a304.p.ssafy.io/api-quiz/workbook", value)
          .then(res => {
            commit('ADD_QUIZ', res.data.object);
            return res.data.object.id;
          })
          .catch(err => console.log(err))
      },
      removeQuiz: function ({ commit }, value) {
        axios.delete(`https://k4a304.p.ssafy.io/api-quiz/workbook/${value[0]}/${value[1]}`)
          .then(res => {
            commit('DELETE_QUIZ', value[0]);
            return res
          })
          .catch(err => console.log(err))
      }
    }
  }
  
  export default UserStore;