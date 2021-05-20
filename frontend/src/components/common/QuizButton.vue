<template>
  <button :style="style" class="quiz-button">
    <template>
      <div v-if="answer" class="quiz-button-div">
        <span style="text-align: left;"><i :class="icon"></i></span>
        <span class="answer">{{ answer }}</span>
        <span></span>
      </div>
      <div v-else class="quiz-button-div">
        <span></span>
        <span><i :class="icon"></i></span>
        <span></span>
      </div>
    </template>
  </button>
</template>

<script>
export default {
  name: 'QuizButton',
  props: [
    'color',
    'width',
    'height',
    'font',
    'icon',
    'margin',
    'answer',
    'mode',
    'index'
  ],
  data: function () {
    return {
      style: '',
    }
  },
  created: function () {
    this.setStyle();
  },
  methods: {
    setStyle: function () {
      if (this.color != undefined) this.style += "background-color: " + this.color + ";\n";
      if (this.width != undefined) this.style += "width: " + this.width + ";\n";
      if (this.height != undefined) this.style += "height: " + this.height + ";\n";
      if (this.font != undefined) this.style += "font-size: " + this.font + ";\n";
      if (this.margin != undefined) this.style += "margin: " + this.margin + ";\n";
      
      if (14 < this.answer.length && this.answer.length <= 25) {
        this.style += "font-size: " + "1.5rem" + ";\n";
      } else if (25 < this.answer.length) {
        this.style += "font-size: " + "1rem" + ";\n";
      }
    }
  }
}
</script>

<style scoped>
.quiz-button {
	display: flex;
	justify-content: space-between;
	align-items: center;
  width: 100%;
	height: 100%;
	border-radius: 15px;
	font-family: 'Jua';
	font-size: 2rem;
  padding: 0 auto;
}
.quiz-button-div {
	display: flex;
	justify-content: space-between;
	align-items: center;
  width: 100%;
}

.quiz-button-div .answer {
  width: 100%;
}

.quiz-button-ordering, .quiz-button-ordering-frame {
	justify-content: center;
  width: 100%;
}
.quiz-button-ordering-frame {
	color: gray;
  font-size: 2.5rem;
}
i {
  margin: 30px;
  font-size: 3rem;
}
span {
  width: 33%;
}
</style>