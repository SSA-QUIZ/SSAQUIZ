<template>
  <button class="quiz-button" :style="style">
    <span v-if="icon"><i :class="icon"></i></span>
    <span>{{ answer }}</span>
    <span v-if="icon"></span>
  </button>
</template>

<script>
export default {
  name: 'QuizButton',
  props: [
    'color',
    'width',
    'height',
    'icon',
    'margin',
    'answer'
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
      if (this.margin != undefined) this.style += "margin: " + this.margin + ";\n";
      if (this.answer != undefined) this.style += "justify-content: space-between;\n";
    }
  }
}
</script>

<style scoped>
.quiz-button {
	display: flex;
	flex-grow: 1;
	justify-content: center;
	align-items: center;
	height: 30vh;
	margin: 0.3%;
	border-radius: 15px;
	font-family: Jua;
	font-size: 6rem;
  padding: 0 auto;
}

i {
  margin: 30px;
}
</style>