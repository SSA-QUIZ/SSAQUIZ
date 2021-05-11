<template>
  <div>
    <button id="quiz-set" @click="$emit('start-quiz')">
      <img v-if="quizImage" :src="quizImage">
      <img v-else src="@/assets/images/Default.png">
      <div class="image-hover-container">
        <div class="image-hover-text">
          <span class="text">{{ quizTitle }}</span>
        </div>
      </div>
    </button>
    <div class="quiz-set__buttons">
      <button style="margin-right: 2%;" @click="$emit('edit-quiz')"><i class="far fa-edit"></i></button>
      <button><i class="far fa-trash-alt" @click="$emit('delete-quiz')"></i></button>
    </div>
  </div>

</template>

<script>
export default {
  name: 'QuizSet',
  props: [
    'quizTitle',
    'quizImage'
  ],
  methods: {
  }
}
</script>

<style>
#quiz-set {
  width: 90%;
  height: 90%;
  border-radius: 15px;
  margin: 1%;
  position: relative;
  display: inline-block;
  transition: all 0.2s linear;
}

 #quiz-set img {
  display: block;
  width: 60%;
  margin-left: 20%;
}

.quiz-set__buttons {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 90%;
  font-size: 3vh;
}

.quiz-set__buttons button:hover {
  transition: all 0.25s linear;
  transform: scale(1.1);
}

/* hover css */
.image-hover-container 
{
  position: absolute;
  top: 0;
  width: 100%;  
  height: 100%;
  margin: 0 auto;
  opacity: 0;
  cursor: default;
  transition: opacity 0.2s linear;
}
.image-hover-container:hover 
{
  opacity: 0.8;
  cursor: pointer;
}

.image-hover-text
{
  position: relative;
  height: 100%;
  text-align: center;
  background: #fff;
  margin: 0 auto;
  padding: 30% 0px;
  overflow: hidden;
  font-size: 30px;
  font-family: Jua;
  word-wrap: break-word;
}

</style>