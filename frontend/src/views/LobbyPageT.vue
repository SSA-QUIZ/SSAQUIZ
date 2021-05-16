<template>
  <div id="lobby-page--teacher">
    <button @click="disconnect_ws">테스트</button>
    <div id="lobby-page--teacher__header">
      <img class="ssaquiz-logo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ">
    </div>
    <div id="lobby-page--teacher__body">
      <div id="lobby-page--teacher__info">
        <button class="PIN-button">{{ PIN }}</button>
        <img class="QR-code" src="@/assets/images/QRcode.png" alt="QRcode">
      </div>
      <div id="lobby-page--teacher__nickname">
        <template
          v-for="(student, index) in students"
        ><NicknameButton :key="index" :student=student :index=index /></template>
      </div>
      <NextStepButton @click.native="clickStartButton"/>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import NicknameButton from '@/components/common/NicknameButton.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
import axios from 'axios';

export default {
  name: "LobbyPageT",
  components: {
    NicknameButton,
    NextStepButton
  },
  data: function () {
    return {
      PIN: this.$route.params.PIN,
      quizId: this.$route.params.quizId,
    }
  },
  created: function () {
    this.defaultIsStart();
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["students", "isStart"])
  },
  watch: {
    isStart: function (val) {
      if (val === true) {
        this.$router.push({name: "LoadingPage"})
      }
    }
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["disconnect_ws","sendStartMessage", "defaultIsStart", "startQuiz", "setQuizData", "sendTotalNum"]),
    clickStartButton: function () {
      axios.get(`https://k4a304.p.ssafy.io/api-quiz/workbook/${this.quizId}`)
        .then(res => {
          this.setQuizData(res.data.object);
          let answerList = [];
          let scoreFactorList = [];
          let originalScoreFactorList = [1, 1.5, 2];
          res.data.object.slideList.forEach(slide => answerList.push(slide.answer));
          res.data.object.slideList.forEach(slide => scoreFactorList.push(originalScoreFactorList[slide.scoreFactor]));
          this.sendStartMessage([answerList, scoreFactorList]);
          this.sendTotalNum(res.data.object.slideList.length);
          this.startQuiz();
        })
        .catch(err => console.log(err))
    },
  },
}
</script>

<style>
#lobby-page--teacher {
  height: 100%;
  background-color: #CFE1F6;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#lobby-page--teacher__header {
  margin: 150px 0 50px 0;
  max-width: 100vw;
  width: 95%;
  display: flex;
  justify-content: center;
}

.ssaquiz-logo {
  max-width: 100%;
	justify-content: center;
	margin: 0 0 15px 0;
}

#lobby-page--teacher__body {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-left: 13%;
}

#lobby-page--teacher__info {
  margin-right: 30px;
  width: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.PIN-button {
  font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
  font-size: 40px;
	color: #FFFFFF;
  max-width: 100%;
	width: 250px;
	height: 60px;
	background-color: #545DE3;
	border-radius: 20px;
}

.QR-code {
  width: 85%;
  margin-top: 10%;
}

#lobby-page--teacher__nickname {
  width: 600px;
  height: 400px;
  border-width: 10px 0 10px 10px;
  border-style: solid;
  border-color: #FFFFFF;
  border-radius: 20px;
  background-color: #FFFFFF;
  overflow: auto;
  display: table-cell;
  text-align: center;
}

@media(max-width: 1500px) {
  #lobby-page--teacher__nickname {
    width: 350px;
    height: 350px;
  }
  #lobby-page--teacher__info {
    width: 300px;
  }
}
</style>