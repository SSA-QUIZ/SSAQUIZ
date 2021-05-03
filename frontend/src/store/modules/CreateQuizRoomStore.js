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
    }
  },
  actions: {
    setPINWS: async function ({ commit }) {
      await axios.get("http://k4a304.p.ssafy.io/api-play/pin")
        .then(res => {
          pin = res.data.object;
          commit('SET_PINWS', pin);
          ws = Stomp.over(new SockJS("http://k4a304.p.ssafy.io/api-play/connect"));
          ws.connect({}, () => {
            // const subscribeMessage = {
            //   sender: value,
            //   type: 'JOIN'
            // }
            // ws.send(`/quiz/room.enterUser/${pin}`, {}, JSON.stringify(subscribeMessage));
            ws.subscribe(`/pin/${pin}`, (msg) => {
              commit('ADD_STUDENTS', JSON.parse(msg.body).sender);
            })
            commit('SUBSCRIBE_QUIZ_ROOM', ws);
          })
          commit('SET_STOMP_CLIENT', ws);
          return 1
        })
        .catch(err => console.log(err))
    },
  }
};

export default CreateQuizRoomStore;