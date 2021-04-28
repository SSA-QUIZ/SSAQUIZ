<template>
  <div id="container">
    <Header mode="off" />
    <div id="content">
      <div id="profile">
        <span id="profile__image"><i class="fas fa-user-circle"></i></span>
        <div id="profile__info">
          <div style="font-size: 5vh; font-weight: 900; font-family: Jua;">
            {{ nickname }}
            <button style="font-size: 3vh; color: gray;"><i class="fas fa-pen"></i></button>
          </div>
          <div><u>{{ email }}</u></div>
        </div>
      </div>
      <div id="quiz-set-list">
        <div id="quiz-set-list__title">
          <span style="font-size: 4vh; font-family: Jua;">{{ nickname }}님의 퀴즈</span>
          <button style="font-size: 5vh; color: #4F37DE;"><i class="fa fa-plus-circle"></i></button>
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

  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import QuizSet from '@/components/QuizSet.vue';
export default {
  name: 'UserPage',
  components: {
    Header,
    QuizSet
  },
  data: function () {
    return {
      nickname: '현나최',
      email: 'ssafy@ssaquiz.com'
    }
  }
}
</script>

<style scoped>
#container {
  background-color: #CFE1F6;
  height: 100%;
}
#content {
  display: flex;
  height: 87.5%;
}
#profile {
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