<template>
  <div id="loading-page-container">
    <div id="loading-page-timer" class="loding-page__animation">{{ second }}</div>
    <div id="loading-page-start" class="loding-page__animation" style="display: none;">start!</div>
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color    
    />
  </div>
</template>

<script>
import Alert from '@/components/Popup/Alert.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: 'LoadingPage',
  components: {
    Alert,
  },
  data: function () {
    return {
      second: 3,
      timer: setInterval(() => this.countDown(), 1000),
      flag: false,
      alertMessage: '',
      color: '',
    }
  },
  watch: {
    teacherDisconnected: function (newVal) {
      if (newVal === true) {
        clearInterval(this.timer);
        this.alertMessage = "퀴즈가 종료되었습니다. 잠시 후 메인페이지로 이동합니다.";
        this.color = "red";
        this.flag = !this.flag;
        setTimeout (() =>   {
          this.disconnectWS(); 
          this.$router.push({name: "WelcomePage"});
        }, 2500);
      }
    },
  },
  beforeRouteLeave(to, from, next) {
    clearInterval(this.timer);
    next();
  },
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex"]),
    ...mapState("PlayQuizStore", ["teacherDisconnected"]),
  },
  created: function () {
    if (this.isStudent === false) {
      this.sendCategory(this.quizData.slideList[this.quizIndex].category);
    }
  },
  methods: {
    ...mapActions("PlayQuizStore", ["disconnectWS"]),
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
  }
  30% {
    -webkit-transform: scale3d(1.25, 0.75, 1);
  }
  40% {
    -webkit-transform: scale3d(0.75, 1.25, 1);
  }
  50% {
    -webkit-transform: scale3d(1.15, 0.85, 1);
  }
  65% {
    -webkit-transform: scale3d(0.95, 1.05, 1);
  }
  75% {
    -webkit-transform: scale3d(1.05, 0.95, 1);
  }
  100% {
    -webkit-transform: scale3d(1, 1, 1);
  }
}
.loding-page__animation {
	-webkit-animation: jello-horizontal 1s steps(2, end) infinite both;  
  font-size : 15rem;
  font-family : Jua;
  font-weight: bold;
  color: white;
}

@media (max-width: 700px) {
  .loding-page__animation {
    font-size: 6rem;
  }
}

</style>