<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar :index="quizIndex+1" :all="quizData['slideList'].length" />
    <div id="answer-page-content">
      <template v-if="category==='4지선다'" >
        <Quiz :title="question" :image="imagePath !== '' ? imagePath : 'default'"/>
        <div id="button-container">
          <QuizButton :color="answerStyle[index].color" :icon="answerStyle[index].icon" :answer="answer" height="50%" width="100%"/>
        </div>
      </template>
      <template v-else-if="category==='단답형'">
        <Quiz :title="question" :image="imagePath !== '' ? imagePath : 'default'"/>
        <div class="text-box-input" >
          <span>{{quizData["slideList"][this.quizIndex]["answer"]}}</span>
        </div>
      </template>
      <template v-else-if="category==='순서맞추기'">
        <Quiz :title="question" :image="imagePath !== '' ? imagePath : 'default'" />
        <Ordering id="answer-ordering" mode="answer" />
      </template>
      <template v-else-if="category==='TF'">
        <Quiz :title="question" :image="imagePath !== '' ? imagePath : 'default'" />
        <div id="button-container">
          <TrueFalseButton :mode="index === 0 ? 'True' : 'False'" style="height: 100%; width: 100%;" />
        </div>
      </template>
      <NextStepButton @click.native="setIsInterim(true)" dark="true"/>
    </div>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import QuizButton from '@/components/common/QuizButton.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
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
    },
    imagePath: function () {
      return this.quizData["slideList"][this.quizIndex]["imagePath"];
    },
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["setIsInterim", "setQuizIndex", "sendEndMessage"])
  },
}
</script>

<style scoped>
#answer-page-container {
  background-color: #F2F2F2;
  height: 100vh;
  max-height: 100vh;
  padding: 0 10% 1% 10%;
  display: flex;
  flex-flow: column;
  align-items: center;
}
#answer-page-content {
  display: flex;
  flex-flow: column;
  width: 100%;
  height: 75%;
  max-height: 75%;
  align-items: center;
  justify-content: center;
}
#button-container {
  height: 50%;
  width: 50%;
  display: flex;
  align-items: center;
}
#answer-ordering {
  display: flex;
  width: 100%;
  height: 50%;
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