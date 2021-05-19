<template>
  <div id="solving-quiz-page">
    <Header mode="userInfo" :nickname="username" :score="score" />
    <ProgressBar :index="quizIndex+1" :all="totalNum" />
    <div id="solving-quiz-page-content">
      <MultipleChoice @click-button="sendAnswer" :choice="choice" v-if="category == '4지선다' || category == 'vote'" />
      <ShortAnswer v-else-if="category==='단답형'" @click-button="sendAnswer" />
      <TFChoice v-else-if="category==='TF'" @click-button="sendAnswer" />
      <OrderingAnswer v-else-if="category==='순서맞히기'" @click-button="sendAnswer" />
    </div>
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color    
    />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';
import ShortAnswer from '@/components/QuizTemplate/ShortAnswer.vue';
import OrderingAnswer from '@/components/QuizTemplate/OrderingAnswer.vue';
import TFChoice from '@/components/QuizTemplate/TFChoice.vue';
import Alert from '@/components/Popup/Alert.vue';

export default {
  name: 'SolvingQuizPage',
  components: {
    Header,
    ProgressBar,
    MultipleChoice,
    ShortAnswer,
    OrderingAnswer,
    TFChoice,
    Alert
  },
  data: function () {
    return {
      choice: ["", "", "", ""],
      flag: false,
      alertMessage: '',
      color: '',
    }
  },
  methods: {
    ...mapActions("PlayQuizStore", ["sendAnswer", "disconnectWS"]),
  },
  watch: {
    isFin: function (newVal) {
      if (newVal === true) {
        this.$router.push({ name: "WrongAnswer" })
      }
    },
    isSolved: function (newVal) {
      if (newVal === true) {
        this.$router.push({ name: "AwaitPage" })
      }
    },
    teacherDisconnected: function (newVal) {
      if (newVal === true) {
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
  computed: {
    ...mapState("PlayQuizStore", ["category", "totalNum", "quizIndex", "username", "score", "isFin", "isSolved", "teacherDisconnected"])
  },
}
</script>

<style scoped>
#solving-quiz-page {
  background-color: #f2f2f2;
  height: 100vh;
  padding: 0 10% 1% 10%;
  position: relative;
}

@media (max-width: 480px) {
  #solving-quiz-page {
    padding: 0 3% 1% 3%;
  }
}

#solving-quiz-page-content {
  display: flex;
  flex-flow: column;
  justify-content: center;
	width: 100%;
  height: 75%;
}
</style>