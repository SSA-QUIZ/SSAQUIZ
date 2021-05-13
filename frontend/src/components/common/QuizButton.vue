<template>
  <button :style="style" class="quiz-button">
    <template v-if="mode == 'ordering'">
      <div class="quiz-button-ordering">
        <p style="font-size: 5rem;"><i :class="icon"></i></p>
        <p>{{ answer }}</p>
      </div>
    </template>
    <template v-else-if="mode == 'orderingFrame'">
      <div class="quiz-button-ordering-frame">
        <p style="font-size: 5rem;">{{ index }}</p>
        <p>{{ answer }}</p>
      </div>
    </template>
    <template v-else>
      <div v-if="answer" class="quiz-button-div">
        <span style="text-align: left;"><i :class="icon"></i></span>
        <span>{{ answer }}</span>
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
	height: 30vh;
	margin: 0.3%;
	border-radius: 15px;
	font-family: Jua;
	font-size: 60px;
  padding: 0 auto;
}
.quiz-button-div {
	display: flex;
	justify-content: space-between;
	align-items: center;
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
}
span {
  width: 33%;
}
</style>