<template>
  <button class="quiz-button" :style="style"><i :class="icon"></i></button>
</template>

<script>
export default {
  name: 'QuizButton',
  props: [
    'color',
    'width',
    'height',
    'icon',
    'margin'
  ],
  data: function () {
    return {
      style: ''
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
      if (this.margin != undefined) this.style += "margin: " + this.margin + ";\n";
    }
  }
}
</script>

<style>
.quiz-button {
	display: flex;
	flex-grow: 1;
	justify-content: center;
	align-items: center;
	height: 30vh;
	margin: 10px;
	border-radius: 15px;
	font-family: Jua;
	font-size: 6rem;
}
</style>