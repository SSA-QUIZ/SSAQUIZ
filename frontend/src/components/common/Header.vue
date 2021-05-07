<template>
  <header>
    <div style="width: 30%;">
      <img width="100%" src="@/assets/images/SSAQUIZ.png" @click="goWelcomePage" style="cursor: pointer;">
    </div>
    <div class="header-content" v-if="mode == 'userInfo'">
      <div class="header-content-text">{{ nickname }}</div>
      <div id="header-content-score">{{ score }}</div>
    </div>
    <div class="header-content" v-else-if="mode == 'playQuiz'">
      <div class="header-content-text">
        <div>현재까지</div>
        <div><span style="color: red;">{{ solvedNum }}명 </span>응답</div>
      </div>
      <div id="header-content-second">{{ second }}</div>
    </div>
    <div class="header-content" v-else></div>
  </header>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'Header',
  props: [
    'mode',
    'nickname',
    'score',
    'time'
  ],
  data: function () {
    return {
      solved: 0,
      second: this.time,
      timer: setInterval(() => this.countDown(), 1000)
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["solvedNum"])
  },
  methods: {
    goWelcomePage: function () {
      this.$router.push({ name: "WelcomePage" });
    },
    countDown: function () {
      if (this.second == 0) {
        this.$emit('time-zero')
      }
      this.second--;
    },
  }
}
</script>

<style scoped>
header {
  width: 100%;
	height: 15%;
	display: flex;
	justify-content: space-between;
	align-items: center;
  margin-bottom: -2%;
	padding: 0vh 3vh 0vh 3vh;
}
.header-content {
  width: 70%;
	display: flex;
	justify-content:flex-end;
	align-items: center;
}
.header-content-text {
  width: 40%;
  text-align: end;
	font-family: "Jua";
	font-size: 3.5vh;
	font-weight: 900;
	margin-right: 3%;
}
#header-content-score {
	font-size: 3.5vh;
	font-weight: 900;
	color: rgb(255, 255, 255);
	background-color: rgb(170, 170, 170);
	border-radius: 15px;
  margin-right: 3%;
	padding: 1vh 3vh 1vh 3vh;
}

#header-content-second {
  width: 90px;
  height: 90px;
	font-size: 45px;
	color: white;
	background-color: #4f37de;
	border-radius: 50%;
  margin-right: 3%;
  display: flex;
  justify-content: center;
  align-items: center;
}

@media (max-width: 700px) {
  .header-content-text {
    font-size: 2.5vh;
    margin-right: 2%;
  }
  #header-content-second {
    width: 70px;
    height: 70px;
    font-size: 30px;
  }
}
</style>