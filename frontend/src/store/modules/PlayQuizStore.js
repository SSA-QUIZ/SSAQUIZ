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
    }
  },
  actions: {
    defaultIsStart: function ({ commit }) {
      commit('SET_ISSTART', false);
    },
    setPINWS: function ({ commit }, value) {
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
          }
        })
        commit('SUBSCRIBE_QUIZ_ROOM', ws);
        return 1
      })
    },
  }
};

export default PlayQuizStore;