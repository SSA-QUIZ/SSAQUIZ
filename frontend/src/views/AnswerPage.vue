<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar :index="quizIndex+1" :all="quizData['slideList'].length" />
    <Quiz :title="question" image="@/assets/images/Default.png" id="quiz-div" />
    <QuizButton color="#7cb1ff" icon="fa fa-car" :answer="answer" height="20vh" id="answer-div" />
    <!-- <ShortAnswerResult title="우리 팀 팀장의 이름은?"/> -->
    <NextStepButton @click.native="setIsInterim(true)" dark="true"/>
    <div style="height: 3%;"></div>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import QuizButton from '@/components/common/QuizButton.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AnswerPage',
  components: {
    Header,
    ProgressBar,
    Quiz,
    QuizButton,
    // ShortAnswerResult,
    NextStepButton,
  },
  watch: {
    isEnd: function (newVal) {
      if (newVal === true) {
        this.$router.push({name: "ResultPage"});
      }
    },
    isInterim: function (newVal) {
      if (newVal === true) {
        this.setQuizIndex();
        if (this.quizData.slideList.length === this.quizIndex) {
          this.sendEndMessage();
        } else {
          this.$router.push({name: "InterimScorePage"});
        }
        
      }
    },
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["isInterim", "quizIndex", "quizData", "isEnd"]),
    question: function () {
      return this.quizData["slideList"][this.quizIndex]["question"];
    },
    answer: function () {
      return this.quizData["slideList"][this.quizIndex]["answerList"][this.quizData["slideList"][this.quizIndex]["answer"]];
    },
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["setIsInterim", "setQuizIndex", "sendEndMessage"])
  },
}
</script>

<style>
#answer-page-container {
  background-color: #F2F2F2;
  height: 100%;
  display: flex;
  flex-flow: column;
  align-items: center;
  margin-bottom: -10%;
}
#quiz-div {
  height: 60%;
}
#answer-div {
  width: 95%;
  height: auto;
}
</style>