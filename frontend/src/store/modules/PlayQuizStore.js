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
    score: 0,
    plusScore: 0,
    isFin: false,
    isSolved: false,
    isCorrect: false,
    isNext: false,
    isEnd: false,
    isConnected: false,
    teacherDisconnected: false,
    isValidNickname: 0,
    resultData2: [],
    answerData: {}
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
    ADD_QUIZINDEX: function (state) {
      state.quizIndex++;
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
    SET_ISFIN: function (state, value) {
      state.isFin = value;
    },
    SET_ISSOLVED: function (state, value) {
      state.isSolved = value;
    },
    SET_ISCORRECT: function (state, value) {
      state.isCorrect = value;
    },
    SET_ISNEXT: function (state, value) {
      state.isNext = value;
    },
    SET_ISEND: function (state, value) {
      state.isEnd = value;
    },
    SET_RESULTDATA: function (state, value) {
      state.resultData2 = value;
    },
    SET_SCORE: function (state, value) {
      state.score = value;
    },
    SET_PLUSSCORE: function (state, value) {
      state.plusScore = value;
    },
    SET_ISVALIDNICKNAME: function (state, value) {
      state.isValidNickname = value;
    },
    SET_ANSWER_DATA: function (state, value) {
      state.answerData = value;
    },
    DISCONNECT_WS: function (state, value) {
      state.stompClient = value;
    },
    SET_ISCONNECTED: function (state, value) {
      state.isConnected = value;
    },
    SET_TEACHER_DISCONNECTED: function (state, value) {
      state.teacherDisconnected = value;
    },
  },
  actions: {
    setIsValidNickname: function ({ commit }, value) {
      commit("SET_ISVALIDNICKNAME", value);
    },
    setIsFin: function ({ commit }, value) {
      commit("SET_ISFIN", value);
    },
    setIsSolved: function ({ commit }, value) {
      commit("SET_ISSOLVED", value);
    },
    setIsCorrect: function ({ commit }, value) {
      commit("SET_ISCORRECT", value);
    },
    setIsNext: function ({ commit }, value) {
      commit("SET_ISNEXT", value);
    },
    sendAnswer: function ({ commit }, value) {
      let sendAnswerMessage = {
        sender: value[1],
        content: value[0],
        type: "SUBMIT",
        quizNum: value[2],
      };
      ws.send(`/quiz/room/sendAnswer/${pin}`, {}, JSON. stringify(sendAnswerMessage));
      commit('SEND_ANSWER', ws);
      commit('SET_ISSOLVED', true);
    },
    setUsername: function ({ commit }, value) {
      commit('SET_USERNAME', value);
    },
    defaultIsStart: function ({ commit }) {
      commit('SET_ISSTART', false);
    },
    setPINWS: function ({ commit }, value) {
      pin = value[0];
      commit('SET_PINWS', pin);
      ws = Stomp.over(new SockJS("https://k4a304.p.ssafy.io/api-play/connect"));
      ws.connect({}, () => {
        const subscribeMessage = {
          sender: value[1],
          type: 'JOIN'
        }
        ws.send(`/quiz/room/enterUser/${value[0]}`, {}, JSON.stringify(subscribeMessage));
        ws.subscribe(`/pin/${value[0]}`, (msg) => {
          let type = JSON.parse(msg.body).type;
          let content = JSON.parse(msg.body).content;
          if (type === "START") {
            commit('SET_ISSTART', true);
          } else if (type === "USERLIST") {
            commit('SET_STUDENTS', content);
          } else if (type === "TOTALNUM") {
            commit('SET_TOTALNUM', content);
          } else if (type === "CATEGORY") {
            commit('SET_CATEGORY', content);
          } else if (type === "FINISH") {
            commit('SET_ISFIN', true);
          } else if (type === "NEXT") {
            commit('ADD_QUIZINDEX');
            commit('SET_ISNEXT', true);
          } else if (type === "END") {
            commit('SET_RESULTDATA', content);
            commit('SET_ISEND', true);
          } else if (type === "LEAVE" || content === "teacher disconnected") {
            commit('SET_TEACHER_DISCONNECTED', true);
          } else if (type === "JOIN") {
            if (content === "join fail (over length)") {
              commit('SET_ISVALIDNICKNAME', 1);
            } else if (content === "join fail (overlap)") {
              commit('SET_ISVALIDNICKNAME', 2);
            } else if (content === "join fail (null)" || content === "join fail (space character)") {
              commit('SET_ISVALIDNICKNAME', 3);
            } else {
              commit('SET_ISVALIDNICKNAME', 4);
            }
          }
        })
        ws.subscribe(`/pin/${value[0]}/nickname/${value[1]}`, (msg) => {
          let isCorrectAnswer = JSON.parse(msg.body).content.answer;
          let newScore = JSON.parse(msg.body).content.CurrentScore;
          let plusScore = JSON.parse(msg.body).content.plusScore;
          commit('SET_ISCORRECT', isCorrectAnswer);
          commit('SET_SCORE', newScore);
          commit('SET_PLUSSCORE', plusScore);
        })
        commit('SUBSCRIBE_QUIZ_ROOM', ws);
        return 1
      })
    },
    setAnswerData: function ({ commit }, value) {
      commit('SET_ANSWER_DATA', value);
    },
    disconnectWS: function ({ commit }) {
      ws.disconnect();
      commit('DISCONNECT_WS', ws);
    }
  }
};

export default PlayQuizStore;