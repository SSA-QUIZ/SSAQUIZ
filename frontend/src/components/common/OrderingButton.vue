<template>
  <button :id="buttonId" class="ordering-button" :style="style">
    <template v-if="mode == 'ordering'">
      <div class="ordering-button">
        <p class="ordering-button__icon"><i :class="answerStyle[index-1].icon"></i></p>
        <p class="ordering-button__input">{{ choice }}</p>
      </div>
    </template>
    <template v-else-if="mode == 'orderingFrame'">
      <div class="ordering-button__frame">
        <p style="font-size: 5rem;">{{ index }}</p>
        <p>{{ answer }}</p>
      </div>
    </template>
  </button>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: "OrderingButton",
  props: [
    'mode',
    'index',
    'answer',
    'buttonId'
  ],
  data: function () {
    return {
      inputChoice: '',
      style: ''
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex", "answerStyle"]),
    choice: function () {
      return this.quizData["slideList"][this.quizIndex]["answerList"][this.index-1];
    },
  },
  created: function () {
    this.style = 'background-color: ' + this.answerStyle[this.index-1].color;
  },
}
</script>

<style scoped>
.ordering-button {
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
.ordering-button, .ordering-button__frame {
	justify-content: center;
  width: 100%;
}
.ordering-button__frame {
	color: gray;
  font-size: 2.5rem;
}
.ordering-button__input {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 50px;
  height: 50px;
  max-height: 100px;
  width: 100%;
  text-align: center;
}
.ordering-button__icon {
  font-size: 5rem;
}
</style>