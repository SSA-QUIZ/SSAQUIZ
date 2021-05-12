<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar :index="quizIndex+1" :all="quizData['slideList'].length" />
    <template v-if="category==='4지선다'" >
      <Quiz :title="question" image="@/assets/images/Default.png" id="quiz-div" />
      <QuizButton :color="answerStyle[index].color" :icon="answerStyle[index].icon" :answer="answer" height="20vh" id="answer-div" />
    </template>
    <ShortAnswerResult v-else-if="category==='단답형'" :title="question"/>
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
import ShortAnswerResult from '@/components/QuizTemplate/ShortAnswerResult'
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AnswerPage',
  components: {
    Header,
    ProgressBar,
    Quiz,
    QuizButton,
    ShortAnswerResult,
    NextStepButton,
  },
  data: function () {
    return {
      answerStyle: [
        {
          "icon": "fas fa-cat",
          "color": "#ffdc46"
        },
        {
          "icon": "fas fa-leaf",
          "color": "#ff85b1"
        },
        {
          "icon": "fa fa-car",
          "color": "#7cb1ff"
        },
        {
          "icon": "fas fa-pills",
          "color": "#aaed81"
        }
      ]
    }
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
    ...mapState("CreateQuizRoomStore", ["isInterim", "quizIndex", "quizData", "isEnd", "category"]),
    question: function () {
      return this.quizData["slideList"][this.quizIndex]["question"];
    },
    answer: function () {
      return this.quizData["slideList"][this.quizIndex]["answerList"][this.quizData["slideList"][this.quizIndex]["answer"]];
    },
    index: function () {
      return parseInt(this.quizData["slideList"][this.quizIndex]["answer"]);
    }
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