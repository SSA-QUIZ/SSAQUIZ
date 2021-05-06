import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

var ws;
var pin;

const CreateQuizRoomStore = {
  namespaced: true,
  state: {
    stompClient: ws,
    PIN: pin,
    students: [],
    isStart: false,
    quizData: Object,
    quizIndex: 0,
    solvedNum: 0,
  },
  getters: {},
  mutations: {
    SET_PINWS: function (state, value) {
      state.PIN = value;
    },
    SET_STOMP_CLIENT: function (state, value) {
      state.stompClient = value;
    },
    SUBSCRIBE_QUIZ_ROOM: function (state, value) {
      state.stompClient = value;
    },
    ADD_STUDENTS: function (state, value) {
      state.students.push({nickname: value, color: '#FFE059'});
      let UserListMessage = {
        type: "USERLIST",
        content: state.students,
      };
      ws.send(`/quiz/room/sendUserList/${pin}`, {}, JSON.stringify(UserListMessage))
    },
    SEND_ANSWERLIST: function (state, value) {
      state.stompClient = value;
    },
    SET_ISSTART: function (state, value) {
      state.isStart = value;
    },
    SET_QUIZDATA: function (state, value) {
      state.quizData = value;
    },
    SET_QUIZINDEX: function (state) {
      state.quizIndex++;
    },
    SEND_CATEGORY: function (state, value) {
      state.stompClient = value;
    },
    SEND_TOTALNUM: function (state, value) {
      state.stompClient = value;
    },
    ADD_SOLVEDNUM: function (state) {
      state.solvedNum++;
    },
  },
  actions: {
    sendTotalNum: function ({ commit }, value) {
      let sendTotalNumMessage = {
        type: "TOTALNUM",
        content: value
      };
      ws.send(`/quiz/room/sendTotalNum/${pin}`, {}, JSON.stringify(sendTotalNumMessage));
      commit('SEND_TOTALNUM', ws);
    },
    sendCategory: function ({ commit }, value) {
      let sendCategoryMessage = {
        type: "CATEGORY",
        content: value
      };
      ws.send(`/quiz/room/sendCategory/${pin}`, {}, JSON.stringify(sendCategoryMessage));
      commit('SEND_CATEGORY', ws);
    },
    setQuizIndex: function ({ commit }) {
      commit('SET_QUIZINDEX');
    },
    setQuizData: function ({ commit }, value) {
      commit('SET_QUIZDATA', value);
    },
    defaultIsStart: function ({ commit }) {
      commit('SET_ISSTART', false);
    },
    startQuiz: function ({ commit }) {
      commit('SET_ISSTART', true);
    },
    setPINWS: async function ({ commit }) {
      await axios.get("http://k4a304.p.ssafy.io/api-play/pin")
        .then(res => {
          pin = res.data.object;
          commit('SET_PINWS', pin);
          ws = Stomp.over(new SockJS("http://k4a304.p.ssafy.io/api-play/connect"));
          ws.connect({}, () => {
            ws.subscribe(`/pin/${pin}`, (msg) => {
              console.log(msg.body)
              let type = JSON.parse(msg.body).type
              if (type === "JOIN") {
                commit('ADD_STUDENTS', JSON.parse(msg.body).sender);
              } else if (type === "START") {
                commit('SET_ISSTART', true);
              } else if (type === "SUBMIT") {
                commit('ADD_SOLVEDNUM');
              }
            })
            commit('SUBSCRIBE_QUIZ_ROOM', ws);
          })
          commit('SET_STOMP_CLIENT', ws);
        })
        .catch(err => console.log(err))
    },
    sendAnswerList: function ({ commit }, value) {
      const sendAnswerListMessage = {
        type: 'START',
        content: value,
      };
      ws.send(`/quiz/room/startQuiz/${pin}`, {}, JSON.stringify(sendAnswerListMessage))
      commit('SEND_ANSWERLIST', ws);
    },
  }
};

export default CreateQuizRoomStore;