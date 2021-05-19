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
      <button style="margin-right: 2%;" @click="$emit('edit-quiz')">
        <i class="material-icons">edit</i>
      </button>
      <button @click="$emit('delete-quiz')">
        <i class="material-icons">delete</i>
      </button>
    </div>
  </div>

</template>

<script scoped>
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

<style scoped>
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

/* 아이콘 */

i.material-icons {
    font-size: 2rem;
		color: white;
		position: relative;
		border-radius: 50%;
		padding: 5px;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
		transition: color 0.2s ease, background-color 0.2s ease, transform 0.2s ease;
}

i.material-icons:after {
		content: "";
		width: 100%;
		height: 100%;
		border: solid 2px;
		transform: scale(0.8);
		position: absolute;
		top: -1px;
		left: 0px;
		border-radius: 50%;
		transition: all 0.2s ease;
}

i.material-icons:hover:after {
		transform: scale(1);
}

i.material-icons:nth-of-type(1) {
		background-color: #f3d795;
}

i.material-icons:nth-of-type(1):hover {
		color: #f3d795;
}

i.material-icons:nth-of-type(1):after {
		border-color: #f3d795;
}

i.material-icons:hover {
		background-color: transparent;
		cursor: pointer;
		box-shadow: none;
}

@media (max-width:601px) {
	i.material-icons {
		padding:10px;
		margin:5px;
		font-size:1.8rem;
	}
}

</style>