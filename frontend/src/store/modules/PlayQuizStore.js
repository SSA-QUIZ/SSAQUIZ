import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

var ws;
var pin;

const PlayQuizStore = {
  namespaced: true,
  state: {
    stompClient: ws,
    PIN: pin,
    students: [],
    isStart: false,
    category: '',
    totalNum: 0,
    quizIndex: 0,
    username: '',
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
    SET_STUDENTS: function (state, value) {
      state.students = value;
    },
    SET_ISSTART: function (state, value) {
      state.isStart = value;
    },
    SET_TOTALNUM: function (state, value) {
      state.totalNum = value;
    },
    SET_CATEGORY: function (state, value) {
      state.category = value;
    },
    SET_USERNAME: function (state, value) {
      state.username = value;
    },
    SEND_ANSWER: function (state, value) {
      state.stompClient = value;
    },
  },
  actions: {
    sendAnswer: function ({ commit }, value) {
      let sendAnswerMessage = {
        sender: value[1],
        content: value[0],
        type: "SUBMIT",
        quizNum: value[2],
      };
      ws.send(`/quiz/room/sendAnswer/${pin}`, {}, JSON. stringify(sendAnswerMessage));
      commit('SEND_ANSWER', ws)
    },
    setUsername: function ({ commit }, value) {
      commit('SET_USERNAME', value);
    },
    defaultIsStart: function ({ commit }) {
      commit('SET_ISSTART', false);
    },
    setPINWS: function ({ commit }, value) {
      pin = value[0];
      ws = Stomp.over(new SockJS("http://k4a304.p.ssafy.io/api-play/connect"));
      ws.connect({}, () => {
        const subscribeMessage = {
          sender: value[1],
          type: 'JOIN'
        }
        ws.send(`/quiz/room/enterUser/${value[0]}`, {}, JSON.stringify(subscribeMessage));
        ws.subscribe(`/pin/${value[0]}`, (msg) => {
          console.log(msg.body)
          let type = JSON.parse(msg.body).type
          if (type === "START") {
            commit('SET_ISSTART', true);
          } else if (type === "USERLIST") {
            commit('SET_STUDENTS', JSON.parse(msg.body).content);
          } else if (type === "TOTALNUM") {
            commit('SET_TOTALNUM', JSON.parse(msg.body).content);
          } else if (type === "CATEGORY") {
            commit('SET_CATEGORY', JSON.parse(msg.body).content);
          }
        })
        ws.subscribe(`/pin/${value[0]}/nickname/${value[1]}`, (msg) => {
          console.log(msg.body);
        })
        commit('SUBSCRIBE_QUIZ_ROOM', ws);
        return 1
      })
    },
  }
};

export default PlayQuizStore;