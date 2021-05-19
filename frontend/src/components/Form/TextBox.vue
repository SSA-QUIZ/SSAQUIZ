<template>
  <div id="text-box">
    <input class="text-box-input" :placeholder=message :value="data" @input="onInput">
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: "TextBox",
  props: [
    "message",
  ],
  data: function () {
    return {
      inputAnswer: '',
    }
  },
  methods: {
    onInput: function (e) {
      this.inputAnswer = e.target.value;
			this.$emit('change-input',this.inputAnswer);
		}
  },
  computed: {
    ...mapState("CreateQuizStore", ["quizData", "selectedSlideIndex"]),
    ...mapState("CommonStore", ["isStudent"]),
    data: function () {
      if (this.isStudent === false) {
        return this.quizData["slideList"][this.selectedSlideIndex]["answer"];
      } else {
        return '';
      }
    },
  },
}
</script>

<style scoped>
#text-box .text-box-input {
  font-family: 'Jua';
  font-size: 30px;
	width: 100%;
	height: 150px;
  text-align: center;
	background-color: #FFFFFF;
	border: 3px solid #FFFFFF;
	border-radius: 5px;
	text-align: center;
}

#text-box .text-box-input:focus, #text-box .text-box-input:active {
	outline: none;
	border: 3px solid #545DE3;
}

@media (max-width: 700px) {
  #text-box .text-box-input {
    font-size: 20px;
  }
}
</style>