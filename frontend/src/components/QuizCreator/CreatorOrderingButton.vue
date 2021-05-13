<template>
  <button :id="buttonId" class="creator-ordering-button" :style="style">
    <template>
      <div class="creator-ordering-button">
        <p class="creator-ordering-button__icon"><i :class="answerStyle[index-1].icon"></i></p>
        <input :value="choice" @input="changeChoice" type="text" :placeholder=placeholder class="creator-ordering-button__input">
        <p @click="clickButton" class="creator-ordering-button__index">{{ answerText === '0' ? '' : answerText }}</p>
      </div>
    </template>
  </button>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: "OrderingButton",
  props: [
    'mode',
    'index',
    'answer',
    'buttonId',
    'currentIdx'

  ],
  data: function () {
    return {
      placeholder: '',
      inputChoice: '',
      style: '',
      clicked: false,
      typed: false,
    }
  },
  computed: {
    ...mapState("CreateQuizStore", ["quizData", "selectedSlideIndex", "answerStyle"]),
    choice: function () {
      this.typed;
      return this.quizData["slideList"][this.selectedSlideIndex]["answerList"][this.index-1];
    },
    answerText: function () {
      this.clicked;
      return this.quizData["slideList"][this.selectedSlideIndex]["answer"].charAt(this.index-1);
    },
  },
  created: function () {
    this.placeholder = '선택지' + this.index;
    this.style = 'background-color: ' + this.answerStyle[this.index-1].color;
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setAnswer", "setMultipleChoice"]),
    changeChoice: function (e) {
      this.typed = !this.typed;
      this.inputChoice = e.target.value;
      let val = [this.selectedSlideIndex, this.index-1, this.inputChoice];
      this.setMultipleChoice(val);
    },
    clickButton: function () {
      if (this.currentIdx <= 4 && this.answerText === '0') {
        this.clicked = !this.clicked;
        this.$emit('click-button');
      }
    },
  }
}
</script>

<style scoped>
.creator-ordering-button {
	display: flex;
  flex-flow: column;
	justify-content: center;
	align-items: center;
  width: 100%;
	height: 100%;
	margin: 0.3%;
	border-radius: 15px;
	font-family: Jua;
	font-size: 3rem;
  padding: 0 auto;
}
.creator-ordering-button__input {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 50px;
  height: 50px;
  max-height: 100px;
  width: 100%;
  text-align: center;
}
.creator-ordering-button__icon {
  font-size: 5rem;
}
.creator-ordering-button__index {
  width:60px;
  height:60px;
  margin-top: 30px;
  margin-bottom:-20px;
  color: #d0a9e1;
  background-color: #72448d;
  border-radius: 50%;
}
</style>