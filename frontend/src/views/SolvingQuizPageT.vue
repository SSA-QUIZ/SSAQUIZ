<template>
  <div id="solving-quiz-page-t-container">
    <button @click="disconnect_ws">테스트</button>
    <Header @time-zero="sendFinMessage(quizIndex)" mode="playQuiz" :time="time" />
    <ProgressBar :index="quizIndex+1" :all="quizData['slideList'].length" />
    <div id="solving-quiz-page-t-content">
      <div class="solving-quiz-t-div solving-quiz-t-div__side">
      </div>
      <div class="solving-quiz-t-div solving-quiz-t-div__center">
        <template v-if="category==='4지선다'" >
          <Quiz id="solving-quiz-t-quiz" :title="question" :image="imagePath" />
          <MultipleChoice id="solving-quiz-t-choice" :choice="choices" height="15vh" font="3.5vw" />
        </template>
        <ShortAnswerT v-else-if="category==='단답형'" :title="question" :image="imagePath" />
      </div>
      <div class="solving-quiz-t-div solving-quiz-t-div__side">
        <NextStepButton @click.native="sendFinMessage(quizIndex)" dark="true" />
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
import ShortAnswerT from '@/components/QuizTemplate/ShortAnswerT.vue';

export default {
  name: 'SolvingQuizPageT',
  components: {
    Header,
    ProgressBar,
    Quiz,
    NextStepButton,
    MultipleChoice,
    ShortAnswerT,
  },
  data: function () {
    return {
      timeLimit: [10, 15, 20],
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
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex", "isFin", "category"]),
    choices: function () {
      return this.quizData["slideList"][this.quizIndex]["answerList"];
    },
    question: function () {
      return this.quizData["slideList"][this.quizIndex]["question"];
    },
    imagePath: function () {
      return this.quizData["slideList"][this.quizIndex]["imagePath"];
    },
    time: function () {
      return this.timeLimit[this.quizData["slideList"][this.quizIndex]["time"]];
    },
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["disconnect_ws", "sendFinMessage"])
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