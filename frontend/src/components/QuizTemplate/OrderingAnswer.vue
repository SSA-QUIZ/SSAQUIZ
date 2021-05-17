<template>
  <div id="ordering-answer-container">
    <OrderingFrame class="ordering-answer-content" :solvingString="solvingString" />
    <Ordering mode="solving" class="ordering-answer-content" :setColor="setColor" @click-answer="setAnswer" />
    <div class="ordering-answer__button">
      <button class="ordering-answer__ordering__button" style="background-color: #c3356a;" @click="resetAnswer">초기화</button>
      <button class="ordering-answer__ordering__button" @click="clickButton([answer, username, quizIndex])">제출</button>
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
      setColor: false,
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
      this.setColor = !this.setColor;
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
#ordering-answer-container {
  width: 100%;
  height: 40%;
}

.ordering-answer__ordering__button {
  width: 120px;
  height: 65px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 35px;
  font-family: Jua;
  color: white;
  background-color: #454995;
  border-radius: 10px;
  margin-right: 0.3%;
}
.ordering-answer__button {
  display: flex;
  justify-content: flex-end;
  margin: 1.5% 0 0 0;
}

@media(max-width: 700px) {
  .ordering-answer__ordering__button {
    width: 300px;
    height: 60px;
    font-size: 25px;
    margin: 3% 0 0 2%;
  }
  .ordering-answer__button {
    justify-content: center;
  }
}
</style>