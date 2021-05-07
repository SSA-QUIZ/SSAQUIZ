<template>
  <div id="solving-quiz-page-t-container">
    <Header @time-zero="sendFinMessage" mode="playQuiz" :time="quizData['slideList'][quizIndex]['time']" />
    <ProgressBar index="1" all="12" />
    <div id="solving-quiz-page-t-content">
      <div class="solving-quiz-t-div solving-quiz-t-div__side">
      </div>
      <div class="solving-quiz-t-div solving-quiz-t-div__center">
        <Quiz id="solving-quiz-t-quiz" title="이 동물은 무엇일까요?" image="" />
        <MultipleChoice id="solving-quiz-t-choice" :choice="choice" height="15vh" font="3.5vw" />
        <!-- <ShortAnswerT title="우리 팀 팀장의 이름은?" image="" /> -->
      </div>
      <div class="solving-quiz-t-div solving-quiz-t-div__side">
        <NextStepButton @click.native="sendFinMessage" dark="true" />
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';
import { mapActions, mapState } from 'vuex';
// import ShortAnswerT from '@/components/QuizTemplate/ShortAnswerT.vue';

export default {
  name: 'SolvingQuizPageT',
  components: {
    Header,
    ProgressBar,
    Quiz,
    NextStepButton,
    MultipleChoice,
    // ShortAnswerT,
  },
  data: function () {
    return {
      choice: ['문어', '복어', '오징어', '대게'],
    }
  },
  watch: {
    isFin: function (newVal) {
      if (newVal === true) {
        this.$router.push({name: "AnswerPage"});
      }
    },
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex", "isFin"])
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["sendFinMessage"])
  },
}
</script>

<style scoped>
#solving-quiz-page-t-container {
  background-color: #F2F2F2;
  height: 100%;
  display: flex;
  flex-flow: column;
  align-items: center;
  margin-bottom: -10%;
}
#solving-quiz-page-t-content {
  display: flex;
  width:100%;
  height: 75%;
  justify-content:center;
  align-items: center; 
}
#solving-quiz-t-quiz {
  height: 50%;
  margin: 10px;
}
#solving-quiz-t-choice {
  width: 70%;
  height: 60%;
  margin: 5px;
}
.solving-quiz-t-div {
  height: 100%;
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
}
.solving-quiz-t-div__side {
  width: 10%;
}
.solving-quiz-t-div__center {
  width: 80%;
}
</style>