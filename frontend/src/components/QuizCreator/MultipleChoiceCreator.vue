<template>
  <div id="multiple-choice-creator">
    <div id="multiple-choice-creator__question-box">
      <input type="text" placeholder="문제를 입력해주세요." :value="question" @input="changeQuestion" id="multiple-choice-creator__input-question">
      <i class="fas fa-camera camera-img"></i>
    </div>
    <div>
      <div class="choice-row">
        <CreatorQuizButton @change-choice="getChoice" index="1" height="225px" margin="0 5px 0 0" color="#ffdc46" icon="fas fa-cat" class="choice" />
        <CreatorQuizButton @change-choice="getChoice" index="2" height="225px" margin="0 0 0 5px" color="#ff85b1" icon="fas fa-leaf" class="choice" />
      </div>
      <div class="choice-row">
        <CreatorQuizButton @change-choice="getChoice" index="3" height="225px" margin="0 5px 0 0" color="#7cb1ff" icon="fa fa-car" class="choice" />
        <CreatorQuizButton @change-choice="getChoice" index="4" height="225px" margin="0 0 0 5px" color="#aaed81" icon="fas fa-pills" class="choice" />
      </div>

    </div>
  </div>
</template>

<script>
import CreatorQuizButton from '@/components/QuizCreator/CreatorQuizButton.vue';

export default {
  name: "MultipleChoiceCreator",
  components: {
    CreatorQuizButton
  },
  data: function () {
    return {
      question: "",
      choices: [
        "", "", "", ""
      ],
    }
  },
  methods: {
    getChoice: function (idx, data) {
      this.choices[idx-1] = data;
      this.$emit("change-choices", this.choices, 'multipleChoice')
   },
    changeQuestion(e) {
      this.question = e.target.value;
      this.$emit("change-question", this.question)
    }
  },
}
</script>

<style>
#multiple-choice-creator {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#multiple-choice-creator__question-box {
  width: 100%;
  height: 300px;
  border-radius: 30px;
  background-color: #c4c4c4;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#multiple-choice-creator__input-question {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  height: 40px;
  width: 100%;
  margin-bottom: 40px;
  text-align: center;
}

#multiple-choice-creator__input-question:focus, #multiple-choice-creator__input-question:active {
	outline: none;
}

.camera-img {
  text-align: center;
  font-size: 100px;
}

.choice-row {
	display: flex;
	width: 100%;
	margin: 1% 0% 0% 0%;
}

</style>