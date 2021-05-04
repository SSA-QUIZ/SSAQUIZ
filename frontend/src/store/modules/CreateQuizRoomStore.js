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
  },
  actions: {
    defaultIsStart: function ({ commit }) {
      commit('SET_ISSTART', false);
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
                commit('SET_ISSTART', true)
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