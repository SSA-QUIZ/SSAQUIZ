<template>
  <button class="creator-quiz-button" :style="style">
    <span><i :class="icon"></i></span>
    <span v-if="quiz==='TF'" class="creator-quiz-button-tf__input">{{ msg }}</span>
    <input v-else :value="choice" @input="changeChoice" type="text" :placeholder=placeholder class="creator-quiz-button__input">
    <!-- 라디오 버튼 -->
    <div class="radio-box">
      <input type="radio"
        class="option-input radio" 
        name="answer"
        @click="checkAnswer"
      />
    </div>
  </button>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'CreatorQuizButton',
  props: [
    'color',
    'width',
    'height',
    'icon',
    'margin', 
    'index',
    'quiz',
    'msg'
  ],
  data: function () {
    return {
      style: '',
      placeholder: '',
      inputChoice: '',
    }
  },
  computed: {
    ...mapState("CreateQuizStore", ['quizData', 'selectedSlideIndex']),
    choice: function () {
      return this.quizData["slideList"][this.selectedSlideIndex]["answerList"][this.index];
    },
  },
  watch: {
    selectedSlideIndex: function () {
      let answerIndex = this.quizData["slideList"][this.selectedSlideIndex]["answer"];
      if (this.index === parseInt(answerIndex)) {
        document.getElementsByClassName("option-input")[this.index].checked = true;
      } else {
        document.getElementsByClassName("option-input")[this.index].checked = false;
      }
    }
  },
  mounted: function () {
    this.$nextTick(function () {
      if (this.quizData["slideList"][this.selectedSlideIndex]["answer"] === "") {
        return;
      }
      else if (this.quizData["slideList"][this.selectedSlideIndex]["answer"] == this.index) {
        document.getElementsByClassName("option-input")[parseInt(this.index)].checked = true;
      }
    })
  },
  created: function () {
    this.setStyle();
    this.placeholder = '선택지 ' + (this.index + 1);
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setAnswer", "setMultipleChoice"]),
    checkAnswer: function () {
      this.setAnswer([this.selectedSlideIndex, (this.index).toString()]);
    },
    getAnswer: function () {
      
    },
    changeChoice: function (e) {
      this.inputChoice = e.target.value;
      let val = [this.selectedSlideIndex, this.index, this.inputChoice];
      this.setMultipleChoice(val);
    },
    setStyle: function () {
      if (this.color != undefined) this.style += "background-color: " + this.color + ";\n";
      if (this.width != undefined) this.style += "width: " + this.width + ";\n";
      if (this.height != undefined) this.style += "height: " + this.height + ";\n";
      if (this.margin != undefined) this.style += "margin: " + this.margin + ";\n";
      if (this.answer != undefined) this.style += "justify-content: space-between;\n";
    }
  }
}
</script>

<style scoped>
.creator-quiz-button {
	display: flex;
	flex-grow: 1;
	justify-content: center;
	align-items: center;
	height: 30vh;
	margin: 0.3%;
	border-radius: 15px;
	font-family: 'Jua';
	font-size: 6rem;
  padding: 0 auto;
}

.creator-quiz-button__input {
	font-family: 'Jua';
  font-size: 50px;
  height: 50px;
  width: 100%;
  text-align: center;
  margin-right: 30px;
}

.creator-quiz-button-tf__input {
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 700;
  font-size: 100px;
  height: 40%;
  width: 100%;
  text-align: center;
  margin-right: 30px;
}

.creator-quiz-button__input:focus, .creator-quiz-button__input:active {
	outline: none;
}

i {
  margin: 30px;
}

/* 라디오 버튼 css */
.radio-box {
  margin-right: 5%;
  margin-bottom: 5%;
}

.radio-box .option-input {
  -webkit-appearance: none;
  -moz-appearance: none;
  -ms-appearance: none;
  -o-appearance: none;
  appearance: none;
  position: relative;
  top: 13.33333px;
  right: 0;
  bottom: 0;
  left: 0;
  height: 50px;
  width: 50px;
  transition: all 0.15s ease-out 0s;
  background: #cbd1d8;
  border: none;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  margin-right: 0.5rem;
  outline: none;
  position: relative;
}

.radio-box .option-input:hover {
  background: #9faab7;
}

.radio-box .option-input:checked {
  background: #eb5b41;
}

.radio-box .option-input:checked::before {
  height: 50px;
  width: 50px;
  position: absolute;
  content: '✔';
  display: inline-block;
  font-size: 29px;
  text-align: center;
  line-height: 55px;
}

.radio-box .option-input:checked::after {
  -webkit-animation: click-wave 0.65s;
  -moz-animation: click-wave 0.65s;
  animation: click-wave 0.65s;
  background: #ee7964;
  content: '';
  display: block;
  position: relative;
  z-index: 100;
}

.radio-box .option-input.radio {
  border-radius: 50%;
}

.radio-box .option-input.radio::after {
  border-radius: 50%;
}

@keyframes click-wave {
  0% {
    height: 40px;
    width: 40px;
    opacity: 0.35;
    position: relative;
  }
  100% {
    height: 200px;
    width: 200px;
    margin-left: -80px;
    margin-top: -80px;
    opacity: 0;
  }
}
</style>