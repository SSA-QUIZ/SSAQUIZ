<template>
  <div class="quiz-slide">
    <p class="slide-number">{{ number }}</p>
    <div class="quiz-img">

    </div>
  </div>
</template>

<script>
export default {
  name: "QuizSlide",
  components: {

  },
  props: ['number', 'slide'],
  data: function () {
    return {

    }
  },
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

.slide-number {
  width: 38px;
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
}
</style>