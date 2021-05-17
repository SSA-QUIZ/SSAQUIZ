<template>
  <div id="short-answer-result">
    <div class="row">
      <span class="question">{{ title }}</span>
    </div>
    <div class="row">
      <span class="answer">{{ answer }}</span>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "ShortAnswerResult",
  props: [
    "title"
  ],
  computed: {
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex"]),
    answer: function () {
      return this.quizData["slideList"][this.quizIndex]["answer"];
    },
  },
}
</script>

<style>
#short-answer-result {
  width: 100%;
  height: 55%;
}

#short-answer-result .row {
  display: flex;
  width: 100%;
  justify-content: center;
}

#short-answer-result .row .question {
  font-family: Jua;
  font-size: 3rem;
  margin-top: 2vw;
}

#short-answer-result .row .answer {
  font-family: 'Noto Sans KR';
  font-size: 5rem;
  margin-top: 5vw;
}
</style>