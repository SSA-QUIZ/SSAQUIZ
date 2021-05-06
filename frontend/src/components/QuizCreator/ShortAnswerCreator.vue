<template>
  <div id="short-answer-creator">
    <div id="short-answer-creator__question-box">
      <input type="text" 
        placeholder="문제를 입력해주세요."
        :value="question" 
        id="short-answer-creator__input-question"
      >
      <span v-if="image == ''"><i class="fas fa-camera camera-img"></i></span>
      <span v-else id="image__span"><img :src="image" width="150px;" /></span>
    </div>
    <div id="short-answer-creator__answer-box">
      <span>정답</span>
      <TextBox message="" @change-input="setAnswer"/>
    </div>
  </div>
</template>

<script>
import TextBox from '@/components/Form/TextBox.vue';
export default {
  name: "ShortAnswerCreator",
  data: function () {
    return {
      answer: '',
      question: '',
      image:''
    }
  },
  components: {
    TextBox
  },
  methods: {
    setAnswer: function (data) {
      this.answer = data
    }
  }
}
</script>

<style>
#short-answer-creator {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#short-answer-creator__question-box {
  width: 100%;
  height: 70%;
  border-radius: 30px;
  background-color: #c4c4c4;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#short-answer-creator__input-question {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  height: 40px;
  width: 100%;
  margin-bottom: 40px;
  text-align: center;
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

#short-answer-creator__answer-box {
  width: 100%;
  height: 30%;
  display: flex;
  align-items: center;
  justify-content: center;
}

#short-answer-creator__answer-box span {
  font-family: 'Nanum Pen Script', cursive;
  font-size: 72px;
  margin-right: 10%;
}
</style>