<template>
  <div id="loading-page-container">
    <div id="loading-page-timer" class="loding-page__animation">{{ second }}</div>
    <div id="loading-page-start" class="loding-page__animation" style="display: none;">start!</div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'LoadingPage',
  data: function () {
    return {
      second: 3,
    }
  },
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex"]),
  },
  created: function () {
    if (this.isStudent === false) {
      console.log(this.quizData.slideList[this.quizIndex])
      this.sendCategory(this.quizData.slideList[this.quizIndex].category);
    }
    this.second = 3;
    this.changeSecond();
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["sendCategory"]),
    countDown: function () {
      if (this.second == 1) {
        document.getElementById('loading-page-timer').style.display = "none";
        document.getElementById('loading-page-start').style.display = "block";
      }
      else if (this.second == 0) {
        document.getElementById('loading-page-start').style.display = "none";
        // 여기에서 퀴즈 진행 페이지로 넘어가면 됩니다!
        if (this.isStudent === true) {
          this.$router.push({ name: "SolvingQuizPage" })
        } else {
          this.$router.push({ name: "SolvingQuizPageT" })
        }
      }
      this.second--;
    },
    changeSecond: function () {
      setInterval(() => this.countDown(), 1000);
    }
  }
}
</script>

<style scoped>
#loading-page-container {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #4f37de;
  height: 100%;
}

@keyframes jello-horizontal {
  0% {
    -webkit-transform: scale3d(1, 1, 1);
            transform: scale3d(1, 1, 1);
  }
  30% {
    -webkit-transform: scale3d(1.25, 0.75, 1);
            transform: scale3d(1.25, 0.75, 1);
  }
  40% {
    -webkit-transform: scale3d(0.75, 1.25, 1);
            transform: scale3d(0.75, 1.25, 1);
  }
  50% {
    -webkit-transform: scale3d(1.15, 0.85, 1);
            transform: scale3d(1.15, 0.85, 1);
  }
  65% {
    -webkit-transform: scale3d(0.95, 1.05, 1);
            transform: scale3d(0.95, 1.05, 1);
  }
  75% {
    -webkit-transform: scale3d(1.05, 0.95, 1);
            transform: scale3d(1.05, 0.95, 1);
  }
  100% {
    -webkit-transform: scale3d(1, 1, 1);
            transform: scale3d(1, 1, 1);
  }
}
.loding-page__animation {
	-webkit-animation: jello-horizontal 1s steps(2, end) infinite both;  
  font-size : 15rem;
  font-family : Jua;
  font-weight: bold;
  color: white;
}

</style>