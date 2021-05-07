<template>
  <div class="quiz-slide">
    <p class="slide-number">{{ number }}</p>
    <div class="quiz-img" :class="{ 'quiz-img__selected' : isSelected}">
    </div>
  </div>
</template>

<script>
export default {
  name: "QuizSlide",
  props: [
    'number',
    'slide',
    'isSelected'
  ],
}
</script>

<style>
.quiz-slide {
  display: flex;
  width: 90%;
	height: 0;
	overflow: hidden;
	padding-bottom: 56.26%;
}
.quiz-slide p {
  margin-left: 10px;
}
.quiz-img {
  background-color: #c4c4c4;
  position: relative;
	width: 100%;
	height: 0;
	overflow: hidden;
	padding-bottom: 56.26%;
  cursor: pointer;
}
.quiz-img__selected {
	border: 3px solid #E7527E;
  border-radius: 5px;
}
.slide-number {
  width: 38px;
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
}
</style>