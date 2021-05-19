<template>
  <button :style="style" class="quiz-button">
    <template v-if="mode == 'True'">
      <p :style="fontStyle" class="quiz-button__tf">O</p>
    </template>
    <template v-else-if="mode == 'False'">
      <p :style="fontStyle" class="quiz-button__tf">X</p>
    </template>
  </button>
</template>

<script>
export default {
  name: 'TrueFalseButton',
  props: [
    'width',
    'height',
    'font',
    'icon',
    'margin',
    'answer',
    'mode',
    'index',
    'fontSize'
  ],
  data: function () {
    return {
      style: '',
      fontStyle: '',
    }
  },
  created: function () {
    this.setStyle();
    this.setFontStyle();
  },
  methods: {
    setStyle: function () {
      if (this.mode === "True") {
        this.style += "background-color: #7CB1FF;"
      } else {
        this.style += "background-color: #ff85b1;"
      }
      if (this.color != undefined) this.style += "background-color: " + this.color + ";\n";
      if (this.width != undefined) this.style += "width: " + this.width + ";\n";
      if (this.height != undefined) this.style += "height: " + this.height + "%;\n";
      if (this.font != undefined) this.style += "font-size: " + this.font + ";\n";
      if (this.margin != undefined) this.style += "margin: " + this.margin + ";\n";
    },
    setFontStyle: function () {
      if (this.fontSize != undefined) this.fontStyle += "font-size: " + this.fontSize + ";\n";
    },
  }
}
</script>

<style scoped>
.quiz-button {
	display: flex;
	justify-content: center;
	align-items: center;
  width: 50%;
  margin: 5px;
	border-radius: 15px;
	font-family: 'Jua';
	font-size: 60px;
  padding: 0 auto;
}
.quiz-button__tf {
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 700;
}
</style>