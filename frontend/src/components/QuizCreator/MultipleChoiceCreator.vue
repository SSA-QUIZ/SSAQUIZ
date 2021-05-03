<template>
  <div id="multiple-choice-creator">
    <div id="multiple-choice-creator__question-box">
      <input type="text" 
        placeholder="문제를 입력해주세요." 
        :value="question" 
        @input="changeQuestion" 
        id="multiple-choice-creator__input-question"
        >
      <span v-if="image == ''"><i class="fas fa-camera camera-img"></i></span>
      <span v-else id="image__span"><img :src="image" width="150px;" /></span>
    </div>
    <div>
      <div class="choice-row">
        <CreatorQuizButton :choice=choices[0] @change-choice="getChoice" index="1" height="225px" margin="0 5px 0 0" color="#ffdc46" icon="fas fa-cat" class="choice" />
        <CreatorQuizButton :choice=choices[1] @change-choice="getChoice" index="2" height="225px" margin="0 0 0 5px" color="#ff85b1" icon="fas fa-leaf" class="choice" />
      </div>
      <div class="choice-row">
        <CreatorQuizButton :choice=choices[2] @change-choice="getChoice" index="3" height="225px" margin="0 5px 0 0" color="#7cb1ff" icon="fa fa-car" class="choice" />
        <CreatorQuizButton :choice=choices[3] @change-choice="getChoice" index="4" height="225px" margin="0 0 0 5px" color="#aaed81" icon="fas fa-pills" class="choice" />
      </div>

    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import CreatorQuizButton from '@/components/QuizCreator/CreatorQuizButton.vue';

export default {
  name: "MultipleChoiceCreator",
  props: [
    "slideIndex"
  ],
  components: {
    CreatorQuizButton
  },
  data: function () {
    return {
      question: "",
      choices: [
        "", "", "", ""
      ],
      image: ""
    }
  },
  created: function () {
    let data = this.quizData.slideList[this.slideIndex];
    this.choices = data.answerList;
    this.question = data.question;
    this.image = data.imagePath;
  },
  computed: {
    ...mapState("CreateQuizStore", ['quizData']),
  },
  watch: {
    slideIndex: function (newVal) {
      let data = this.quizData.slideList[newVal];
      this.question = data.question;
      this.choices = data.answerList;
      this.image = data.imagePath;
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setSlideQuestion", "setMultipleChoice"]),
    getChoice: function (idx, data) {
      this.choices[idx-1] = data;
      let val = [this.slideIndex, this.choices];
      this.setMultipleChoice(val);
   },
    changeQuestion(e) {
      this.question = e.target.value;
      let val = [this.slideIndex, this.question];
      this.setSlideQuestion(val);
    },

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
  align-items: center;
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

#image__span {
  display: flex;
  justify-content: center;
  align-items: center;
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