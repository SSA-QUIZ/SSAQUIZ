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
    isFin: false,
    isInterim: false,
    isNext: false,
    isEnd: false,
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
    NEXT_QUIZ: function (state, value) {
      state.stompClient = value;
    },
    SET_ISFIN: function (state, value) {
      state.isFin = value;
    },
    SET_ISINTERIM: function (state, value) {
      state.isInterim = value;
    },
    SET_ISNEXT: function (state, value) {
      state.isNext = value;
    },
    SET_ISEND: function (state, value) {
      state.isEnd = value;
    },
  },
  actions: {
    sendEndMessage: function ({ commit }) {
      let sendEndMessage = {
        type: "END"
      };
      ws.send(`/quiz/room/endQuiz/${pin}`, {}, JSON.stringify(sendEndMessage));
      commit("SET_STOMP_CLIENT", ws);
    },
    sendFinMessage: function ({ commit }) {
      let sendFinMessage = {
        type: "FINISH"
      };
      ws.send(`/quiz/room/finishQuiz/${pin}`, {}, JSON.stringify(sendFinMessage));
      commit("SET_STOMP_CLIENT", ws);
    },
    setIsInterim: function ({ commit }, value) {
      commit("SET_ISINTERIM", value);
    },
    setIsNext: function ({ commit }, value) {
      commit("SET_ISNEXT", value);
    },
    setIsFin: function ({ commit }, value) {
      commit("SET_ISFIN", value);
    },
    nextQuiz: function ({ commit }) {
      let nextQuizMessage = {
        type: "NEXT"
      };
      ws.send(`/quiz/room/nextQuiz/${pin}`, {}, JSON.stringify(nextQuizMessage));
      commit("SET_STOMP_CLIENT", ws);
    },
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
              let type = JSON.parse(msg.body).type
              if (type === "JOIN") {
                commit('ADD_STUDENTS', JSON.parse(msg.body).sender);
              } else if (type === "START") {
                commit('SET_ISSTART', true);
              } else if (type === "SUBMIT") {
                commit('ADD_SOLVEDNUM');
              } else if (type === "NEXT") {
                commit('SET_ISNEXT', true);
              } else if (type === "FINISH") {
                commit('SET_ISFIN', true);
              } else if (type === "END") {
                commit("SET_ISEND", true);
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