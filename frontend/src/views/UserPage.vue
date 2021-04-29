<template>
  <div id="user-page-container">
    <Header mode="off" />
    <div id="user-page-content">
      <div id="profile">
        <span id="profile__image"><i class="fas fa-user-circle"></i></span>
        <div id="profile__info">
          <div style="font-size: 5vh; font-weight: 900; font-family: Jua;">
            {{ nickname }}
            <button style="font-size: 3vh; color: gray;" @click="moveToUserInfo"><i class="fas fa-pen"></i></button>
          </div>
          <div><u>{{ email }}</u></div>
        </div>
      </div>
      <div id="quiz-set-list">
        <div id="quiz-set-list__title">
          <span style="font-size: 4vh; font-family: Jua;">{{ nickname }}님의 퀴즈</span>
          <button @click="openDialog = true" style="font-size: 5vh; color: #4F37DE;"><i class="fa fa-plus-circle"></i></button>
        </div>
        <div id="quiz-set-list__list">
          <QuizSet quizTitle="퀴즈1" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈2" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈3" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈4" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈4" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈4" class="quiz-set"/>
        </div>
      </div>
    </div>
    <Dialog
      emoticon="🤓"
      content="퀴즈의 이름을 작성해주세요." 
      @close="openDialog = false" 
      @accept="createQuiz(); openDialog = false;"
      @change-input-value="changeQuizTitle"
      v-if="openDialog"
    />
  </div>
</template>

<script>
import { mapActions } from 'vuex';

import Header from '@/components/common/Header.vue';
import QuizSet from '@/components/QuizSet.vue';
import Dialog from '@/components/Popup/Dialog';

export default {
  name: 'UserPage',
  components: {
    Header,
    QuizSet,
    Dialog
  },
  data: function () {
    return {
      nickname: localStorage.getItem('nickname'),
      email: localStorage.getItem('email'),
      openDialog: false,
      quizTitle: '',
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", [
      "setQuizData"
    ]),
    moveToUserInfo: function () {
      this.$router.push({ name: "UserInfo" });
    },
    changeQuizTitle: function (data) {
      this.quizTitle = data
    },
    createQuiz: function () {
      const params = {"userId": parseInt(localStorage.getItem('id')), "workbookTitle": this.quizTitle}
      this.setQuizData(params)
        .then(() => {
          this.$router.push({ name: "CreatorPage" })
        })
        .catch(err => console.log(err))
    }
  }
}
</script>

<style scoped>
#user-page-container {
  background-color: #CFE1F6;
  height: 100%;
}

#user-page-content {
  display: flex;
  height: 87.5%;
}

#user-page-content > #profile {
  display: flex;
  width: 40%;
  height: 60%;
  align-items: center;
}

#profile__image {
  font-size: 18vh;
  color: #4F37DE;
  margin-left: 7%;
}

#quiz-set-list {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  width:60%;
  height: 100%;
}

#profile__info {
  margin-left: 5%;
  font-size: 2vh;
}

#quiz-set-list__list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 90%;
  height: 70%;
  border-radius: 15px;
  background-color: white;
  overflow-x: no-display;
  overflow-y: scroll;
}

#quiz-set-list__title {
  width: 90%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1%;
}

.quiz-set {
  width: 37%;
  height: 35%;
  margin: 5%;
}

::-webkit-scrollbar { width: 30px; }

::-webkit-scrollbar-track { 
  background-color: #F2F2F2;
  border-radius: 0px 15px 15px 0px; 
}

::-webkit-scrollbar-thumb { 
  background: #c4c4c4; 
  border-radius: 15px;
  background-clip: padding-box;
  border : 8px solid transparent;
}

</style>