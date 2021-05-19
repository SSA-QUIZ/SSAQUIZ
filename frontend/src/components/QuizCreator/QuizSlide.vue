<template>
  <div class="quiz-slide">
    <p class="slide-number">{{ number }}</p>
    <div v-if="slide.imagePath" class="quiz-img" :style="{ 'background-image' : 'url(' + slide.imagePath + ')', 'background-size' : 'cover' }" :class="{ 'quiz-img__selected' : isSelected}">
      <span v-if ="isSelected" @click="$emit('delete-slide')">
        <i class="fas fa-times"></i>
      </span>
    </div>
    <div v-else class="quiz-img" :style="{ 'background-image' : `url(${defaultImage})`, 'background-size' : 'cover' }" :class="{ 'quiz-img__selected' : isSelected}">
      <span v-if ="isSelected" @click="$emit('delete-slide')">
        <i class="fas fa-times"></i>
      </span>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import defaultImage from '@/assets/images/DefaultSlideImg.png'

export default {
  name: "QuizSlide",
  props: ['number',
    'slide',
    'isSelected'
  ],
  data: function () {
    return {
      defaultImage: defaultImage,
    }
  },
  computed: {
    ...mapState("CreateQuizStore", ['quizData', 'selectedSlideIndex']),
  },
}
</script>

<style scoped>
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
  position: relative;
  width: 100%;
  height: 0;
  overflow: hidden;
  padding-bottom: 56.26%;
  cursor: pointer;
}

.quiz-img__selected {
  border: 3px solid #e7527e;
  border-radius: 10px;
}

.quiz-img img {
  width: 75%;
}

.quiz-img__selected {
	border: 3px solid #E7527E;
  border-radius: 5px;
}

.slide-number {
  width: 38px;
  font-family: 'Jua';
  font-size: 38px;
}

span {
  position: absolute;
  right: 5%;
  font-size: 30px;
}
</style>