<template>
  <div id="solving-quiz-page-t-container">
    <Header @time-zero="sendFinMessage(quizIndex)" mode="playQuiz" :time="time" />
    <ProgressBar :index="quizIndex+1" :all="quizData['slideList'].length" />
    <div id="solving-quiz-page-t-content">
      <template v-if="category==='4지선다'" >
        <Quiz id="solving-quiz-t-quiz" :title="question" :image="imagePath !== '' ? imagePath : 'default'" />
        <MultipleChoice id="solving-quiz-t-choice" :choice="choices" />
      </template>
      <template v-else-if="category==='단답형'" >
        <Quiz id="solving-quiz-t-quiz-short" :title="question" :image="imagePath !== '' ? imagePath : 'default'" />
        <div class="text-box-input" >
          <span>정답을 입력해주세요.</span>
        </div>
      </template>
      <template v-if="category==='순서맞추기' || category==='순서맞히기'" >
        <Quiz id="solving-quiz-t-quiz" :title="question" :image="imagePath !== '' ? imagePath : 'default'" />
        <Ordering id="solving-quiz-t-ordering" />
      </template>
      <template v-else-if="category==='TF'" >
        <Quiz id="solving-quiz-t-quiz" :title="question" :image="imagePath !== '' ? imagePath : 'default'" />
        <TFChoice id="solving-quiz-t-tf" />
      </template>
      <NextStepButton @click.native="sendFinMessage(quizIndex)" dark="true" />
      <Spinner v-if="scoreFactor !== 0"/>
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
import Ordering from '@/components/QuizTemplate/Ordering.vue';
import TFChoice from '@/components/QuizTemplate/TFChoice.vue';
import Spinner from '@/components/common/Spinner.vue';


export default {
  name: 'SolvingQuizPageT',
  components: {
    Header,
    ProgressBar,
    Quiz,
    NextStepButton,
    MultipleChoice,
    Ordering,
    TFChoice,
    Spinner
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
    scoreFactor: function () {
      return this.quizData["slideList"][this.quizIndex]["scoreFactor"];
    },
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["sendFinMessage"])
  },
}
</script>

<style scoped>
#solving-quiz-page-t-container {
  background-color: #F2F2F2;
  height: 100vh;
  padding: 0 10% 1% 10%;
  display: flex;
  flex-flow: column;
  align-items: center;
}
#solving-quiz-page-t-content {
  display: flex;
  flex-flow: column;
  width: 100%;
  height: 75%;
  justify-content:center;
  align-items: center; 
}
#solving-quiz-t-quiz-short {
  width: 100%;
  height: 80%;
  display: flex;
  flex-flow: column;
}
#solving-quiz-t-quiz {
  width: 100%;
  height: 50%;
  display: flex;
  flex-flow: column;
}
#solving-quiz-t-choice {
  width: 100%;
  height: 50%;
  display: flex;
  flex-direction: column;
}
#solving-quiz-t-ordering {
  display: flex;
  width: 100%;
  height: 50%;
}
#solving-quiz-t-tf {
  width: 100%;
  height: 50%;
  display: flex;
}
.text-box-input {
  font-family: 'Jua';
  font-size: 30px;
	width: 100%;
	height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
	background-color: #FFFFFF;
	border: 3px solid #FFFFFF;
	border-radius: 5px;
	text-align: center;
  outline: none;
	border: 3px solid #545DE3;
}
</style>