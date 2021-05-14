<template>
  <div>
    <OrderingFrame class="solving-quiz-page-content__ordering" :orderingIndex="orderingIndexArray" />
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
      answer: "0000",
      selected: [false, false, false, false],
      orderingIndexArray: [0, 0, 0, 0],
    }
  },
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("PlayQuizStore", ["username", "quizIndex"]),
  },
  methods: {
    resetAnswer: function () {
      this.index = 0;
      this.answer = "0000";
      this.selected = [false, false, false, false];
      this.orderingIndexArray = [0, 0, 0, 0];
    },
    setAnswer: function (val) {
      if (this.index == 4 || this.selected[val-1]) return;
      let changedAnswer = this.answer.substr(0, this.index) + val + this.answer.substr(this.index+1);
      this.answer = changedAnswer;
      this.selected[val-1] = true;
      this.orderingIndexArray[this.index++] = val;
      console.log(this.answer, this.selected, this.orderingIndexArray)
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