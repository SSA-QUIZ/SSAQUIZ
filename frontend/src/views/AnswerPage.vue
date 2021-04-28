<template>
  <div id="answer-page-container">
    <Header mode="off" />
    <ProgressBar index="2" all="12" />
    <Quiz title="이 동물은 무엇일까요?" image="@/assets/images/Default.png" />
    <QuizButton color="#7cb1ff" icon="fa fa-car" answer="고래" id="answer-div" />
    <div style="height: 3%;"></div>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import QuizButton from '@/components/common/QuizButton.vue';
export default {
  name: 'AnswerPage',
  components: {
    Header,
    ProgressBar,
    Quiz,
    QuizButton
  }
}
</script>

<style>
#answer-page-container {
  background-color: #F2F2F2;
  height: 100%;
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
}
#quiz-div {
  height: 50%;
}
#answer-div {
  width: 95%;
  height: auto;
}
</style>