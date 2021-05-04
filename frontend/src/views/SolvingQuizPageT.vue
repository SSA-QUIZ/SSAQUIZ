<template>
  <div id="solving-quiz-page-t-container">
    <Header mode="playQuiz" time="20" />
    <ProgressBar index="1" all="12" />
    <Quiz id="solving-quiz-t-quiz" title="이 동물은 무엇일까요?" image="" />
    <MultipleChoice id="solving-quiz-t-choice" :choice="choice" height="18vh" />
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import Quiz from '@/components/common/Quiz.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';

export default {
  name: 'SolvingQuizPageT',
  components: {
    Header,
    ProgressBar,
    Quiz,
    MultipleChoice,
  },
  data: function () {
    return {
      choice: ['문어', '복어', '오징어', '대게'],
    }
  }
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
#solving-quiz-t-quiz {
  height: 30%;
  margin: 10px;
}
#solving-quiz-t-choice {
  width: 80%;
  height: auto;
  margin: 10px;
}
</style>