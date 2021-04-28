<template>
  <button class="creator-quiz-button" :style="style">
    <span><i :class="icon"></i></span>
    <input :value="choice" @input="changeChoice" type="text" :placeholder=placeholder class="creator-quiz-button__input">
  </button>
</template>

<script>
export default {
  name: 'CreatorQuizButton',
  props: [
    'color',
    'width',
    'height',
    'icon',
    'margin',
    'answer',
    'index'
  ],
  data: function () {
    return {
      style: '',
      choice: '',
      placeholder: '',
    }
  },
  created: function () {
    this.setStyle();
    this.placeholder = '선택지 ' + this.index;
  },
  methods: {
    changeChoice: function (e) {
      this.choice = e.target.value;
      this.$emit('change-choice', this.index, this.choice);
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
	font-family: Jua;
	font-size: 6rem;
  padding: 0 auto;
}

.creator-quiz-button__input {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 50px;
  height: 50px;
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
</style>