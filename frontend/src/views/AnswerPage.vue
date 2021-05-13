<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar :index="quizIndex+1" :all="quizData['slideList'].length" />
    <div id="answer-page-content">
      <template v-if="category==='4지선다'" >
        <Quiz :title="question" image="@/assets/images/Default.png" />
        <QuizButton :color="answerStyle[index].color" :icon="answerStyle[index].icon" :answer="answer" height="20vh" />
      </template>
      <ShortAnswerResult v-else-if="category==='단답형'" :title="question"/>
      <template v-else-if="category==='순서맞히기'">
        <Quiz title="하이" image="@/assets/images/Default.png" style="height: 70%" />
        <Ordering mode="answer" style="height: 60%;" />
      </template>
      <template v-else-if="category==='TF'">
        <Quiz :title="question" image="@/assets/images/Default.png" />
        <TrueFalseButton height="20vh" :mode="answer==='0' ? 'True' : 'False'" />
      </template>
    </div>
    <NextStepButton @click.native="setIsInterim(true)" dark="true"/>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import QuizButton from '@/components/common/QuizButton.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
import ShortAnswerResult from '@/components/QuizTemplate/ShortAnswerResult.vue';
import Ordering from '@/components/QuizTemplate/Ordering.vue';
import TrueFalseButton from '@/components/QuizCreator/TrueFalseButton.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'AnswerPage',
  components: {
    Header,
    ProgressBar,
    Quiz,
    QuizButton,
    ShortAnswerResult,
    Ordering,
    NextStepButton,
    TrueFalseButton,
  },
  data: function () {
    return {
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
    ...mapState("CreateQuizStore", ["answerStyle"]),
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
}
/* .quiz-div {
  height: 50%;
}
.answer-div {
  width: 95%;
  height: auto;
} */
.answer-page-content__ordering {
  height: 30%;
}
</style>