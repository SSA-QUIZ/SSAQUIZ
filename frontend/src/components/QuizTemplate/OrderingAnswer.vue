<template>
  <div>
    <OrderingFrame class="solving-quiz-page-content__ordering" :solvingString="solvingString" />
    <Ordering mode="solving" class="solving-quiz-page-content__ordering" @click-answer="setAnswer" />
    <div class="solving-quiz-page__button">
      <button class="solving-quiz-page__ordering__button" style="background-color: #c3356a;" @click="resetAnswer">초기화</button>
      <button class="solving-quiz-page__ordering__button" @click="clickButton([answer, username, quizIndex])">제출</button>
    </div>
  </div>
</template>

<script>
import Ordering from '@/components/QuizTemplate/Ordering.vue';
import OrderingFrame from '@/components/QuizTemplate/OrderingFrame.vue';
import { mapState } from 'vuex';

export default {
  name: "OrderingAnswer",
  components: {
    Ordering,
    OrderingFrame
  },
  data: function () {
    return {
      index: 0,
      answerArray: [0, 0, 0, 0],
      solvingString: "0000",
    }
  },
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("PlayQuizStore", ["username", "quizIndex"]),
    answer: function () {
      return this.answerArray.join('');
    }
  },
  methods: {
    resetAnswer: function () {
      this.index = 0;
      this.answerArray = [0, 0, 0, 0];
      this.solvingString = "0000";
    },
    setAnswer: function (val) {
      if (this.answerArray[val-1] === 0 && this.index < 4) {
        let changedAnswer = this.solvingString.substr(0, this.index) + val + this.solvingString.substr(this.index+1);
        this.solvingString = changedAnswer;
        this.answerArray[val-1] = ++this.index;
      }
    },
    clickButton: function (data) {
      if (this.isStudent) {
        this.$emit('click-button', data);
      }
    },
  }

}
</script>

<style>

</style>