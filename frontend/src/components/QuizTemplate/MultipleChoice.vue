<template>
  <div id="multiple-choice">
    <div class="choice-row">
      <QuizButton color="#ffdc46" icon="fas fa-cat" class="choice" />
      <QuizButton color="#ff85b1" icon="fas fa-leaf" class="choice" />
    </div>
    <div class="choice-row">
      <QuizButton color="#7cb1ff" icon="fa fa-car" class="choice" />
      <QuizButton color="#aaed81" icon="fas fa-pills" class="choice" />
    </div>
  </div>
</template>

<script>
import QuizButton from '@/components/common/QuizButton.vue';
export default {
  name: 'MultipleChoice',
  components: {
    QuizButton
  },
  props: [
    'choice'
  ]
}
</script>
<style>

#multiple-choice {
	display: flex;
	flex-direction: column;
  margin-top: 1.5vh;
	padding: 3vh;
}
.choice-row {
	display: flex;
	width: 100%;
	margin: 1% 0% 1% 0%;
}
.choice {
	display: flex;
	flex-grow: 1;
	justify-content: center;
	align-items: center;
	height: 30vh;
	border-radius: 15px;
	font-family: Jua;
	font-size: 6rem;
}

@media (max-width: 700px) {
	#multiple-choice {
    display: flex;
    flex-direction: column;
    margin-top: 0;
	}
	.choice-row {
    display: flex;
    flex-direction: column;
	}
	.choice {
    height: 100px;
    font-size: 2rem;
    margin: 5px;
	}
	.quiz-button {
    height: 100px !important;
    font-size: 2.5rem !important;
    margin: 5px !important;
	}
}
</style>