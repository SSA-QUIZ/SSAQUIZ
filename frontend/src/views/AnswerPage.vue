<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar index="2" all="12" />
    <Quiz title="이 동물은 무엇일까요?" image="@/assets/images/Default.png" />
    <QuizButton color="#7cb1ff" icon="fa fa-car" answer="고래" id="answer-div" />
    <!-- <ShortAnswerResult title="우리 팀 팀장의 이름은?"/> -->
    <NextStepButton dark="true"/>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import QuizButton from '@/components/common/QuizButton.vue';
// import ShortAnswerResult from '@/components/QuizTemplate/ShortAnswerResult.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';

export default {
  name: 'AnswerPage',
  components: {
    Header,
    ProgressBar,
    Quiz,
    QuizButton,
    // ShortAnswerResult,
    NextStepButton,
  }
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
  height: 50%;
}
#answer-div {
  width: 95%;
  height: auto;
}
</style>