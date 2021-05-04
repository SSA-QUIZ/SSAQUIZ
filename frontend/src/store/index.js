import Vue from 'vue';
import Vuex from 'vuex';

// import store module
import CreateQuizStore from './modules/CreateQuizStore';
import CreateQuizRoomStore from './modules/CreateQuizRoomStore';
import PlayQuizStore from './modules/PlayQuizStore';
import CommonStore from './modules/CommonStore';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    CreateQuizStore,
    CreateQuizRoomStore,
    PlayQuizStore,
    CommonStore,
  }
})
