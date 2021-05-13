<template>
  <div class="dialog-container">
    <div id="dialog-container">
      <div id="dialog-title">
        <span>퀴즈 추가</span>
        <span @click="$emit('close')"><i class="fas fa-times"></i></span>
      </div>
      <div id="dialog-content">
        <div class="type-row">
          <QuizButton style="font-size: 40px;" margin="5px" answer="4지선다" width="48%" height="150px" color="#ffdc46"
            @click.native="addMultipleChoice" />
          <QuizButton style="font-size: 40px;" margin="5px" answer="단답형" width="48%" height="150px" color="#ff85b1"
            @click.native="addShortAnswer" />
        </div>
        <div class="type-row">
          <QuizButton style="font-size: 40px;" margin="5px" answer="순서맞히기" width="48%" height="150px" color="#7cb1ff"
            @click.native="addOrder" />
          <QuizButton style="font-size: 40px;" margin="5px" answer="TF" width="48%" height="150px" color="#aaed81" 
            @click.native="addVote" />
        </div>
      </div>
    </div>
    <div class="overlay" @click="$emit('close')"></div>
  </div>
</template>

<script>
import QuizButton from '@/components/common/QuizButton.vue';
import { mapActions } from 'vuex';


export default {
  name: 'QuizTypeDialog',
  components: {
    QuizButton
  },
  props: [
  ],
  data: function () {
    return {
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["addSlide"]),
    addMultipleChoice: function () {
      let defaultValue = {
        "category": "4지선다",
        "question": "",
        "imagePath": "",
        "answer": "",
        "answerList": ["", "", "", ""],
        "time": 0,
        "scoreFactor": 0,
        "type": 0
      };
      this.addSlide(defaultValue);
      this.$emit('close');
    },
    addShortAnswer: function () {
      let defaultValue = {
        "category": "단답형",
        "question": "",
        "imagePath": "",
        "answer": "",
        "answerList": ["", "", "", ""],
        "time": 0,
        "scoreFactor": 0,
        "type": 0
      };
      this.addSlide(defaultValue);
      this.$emit('close');
    },
    addOrder: function () {
      let defaultValue = {
        "category": "순서맞히기",
        "question": "",
        "imagePath": "",
        "answer": "",
        "answerList": ["", "", "", ""],
        "time": 0,
        "scoreFactor": 0,
        "type": 0
      };
      this.addSlide(defaultValue);
      this.$emit('close');
    },
    addVote: function () {
      let defaultValue = {
        "category": "TF",
        "question": "",
        "imagePath": "",
        "answer": "",
        "answerList": ["True", "False"],
        "time": 0,
        "scoreFactor": 0,
        "type": 0
      };
      this.addSlide(defaultValue);
      this.$emit('close');
    },

  }
}
</script>

<style>
.overlay, .dialog-container {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.overlay {
  opacity: 0.5;
  background-color: black;
}

.type-row {
  display: flex;
}

#dialog-container {
  width: 60%;
  height: auto;
  z-index: 10;
  opacity: 1;
  position: absolute;
  background-color: white;
  font-family: Jua;
  border-radius: 15px;
}
#dialog-title {
  display: flex;
  justify-content: space-between;
  font-size: 50px;
  padding: 2% 5% 0% 5%;
}
#dialog-content {
  font-size: 1.8rem;
  text-align: center;
  padding: 3%;
}
.input-box {
  border: 1px solid black;
  font-family: Jua;
}
</style>