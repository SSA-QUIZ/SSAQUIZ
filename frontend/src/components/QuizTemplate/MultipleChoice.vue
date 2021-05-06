<template>
  <div id="multiple-choice">
    <div class="choice-row">
      <QuizButton :isAnswer="isAnswer" :answer="answer[0]" :height="height" :font="font" color="#ffdc46" icon="fas fa-cat" class="choice" />
      <QuizButton :isAnswer="isAnswer" :answer="answer[1]" :height="height" :font="font" color="#ff85b1" icon="fas fa-leaf" class="choice" />
    </div>
    <div class="choice-row">
      <QuizButton :isAnswer="isAnswer" :answer="answer[2]" :height="height" :font="font" color="#7cb1ff" icon="fa fa-car" class="choice" />
      <QuizButton :isAnswer="isAnswer" :answer="answer[3]" :height="height" :font="font" color="#aaed81" icon="fas fa-pills" class="choice" />
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
    'isAnswer',
    'choice',
    'height',
    'font'
  ],
  data: function () {
    return {
      answer: ["", "", "", ""]
    }
  },
  created: function () {
    if (this.isAnswer === "true") this.answer = this.choice;
  }
}
</script>
<style>

#multiple-choice {
	display: flex;
	flex-direction: column;
  margin-top: 10px;
  padding: 2.5%;
}
.choice-row {
	display: flex;
	width: 100%;
}
.choice {
	height: 100%;
  width: 48%;
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
    align-items: center;
	}
	.choice {
    height: 100px;
    margin: 5px;
    width: 95%;
	}
	.quiz-button {
    height: 100px !important;
    font-size: 2.5rem !important;
    margin: 5px !important;
	}
}
</style>