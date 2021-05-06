<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar index="2" all="12" />
    <Quiz title="이 동물은 무엇일까요?" image="@/assets/images/Default.png" id="quiz-div" />
    <QuizButton color="#7cb1ff" icon="fa fa-car" isAnswer="true" answer="고래" height="20vh" id="answer-div" />
    <NextStepButton dark="true" />
    <div style="height: 3%;"></div>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import QuizButton from '@/components/common/QuizButton.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
export default {
  name: 'AnswerPage',
  components: {
    Header,
    ProgressBar,
    Quiz,
    QuizButton,
    NextStepButton
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
  height: 60%;
}
#answer-div {
  width: 95%;
  height: auto;
}
</style>