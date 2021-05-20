<template>
  <header id="ssaquiz-header">
    <img id="header-logo" src="@/assets/images/SSAQUIZ.png" @click="goWelcomePage">
    <div class="header-content" v-if="mode == 'userInfo'">
      <div class="header-content-text">{{ nickname }}</div>
      <div id="header-content-score">{{ score }}</div>
    </div>
    <div class="header-content" v-else-if="mode == 'playQuiz'">
      <div class="header-content-text">
        <div>현재까지</div>
        <div><span style="color: red;">{{ solvedNum }}명 </span>응답</div>
      </div>
      <div id="header-content-second"><span>{{ second }}</span></div>
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
        this.$emit('time-zero');
        clearInterval(this.timer);
      } else {
        this.second--;
      }
    },
  }
}
</script>

<style scoped>
#ssaquiz-header {
  width: 100%;
	height: 15%;
	display: flex;
	justify-content: space-between;
	align-items: center;
  z-index: 1;
}

#header-logo {
  height: 80%;
  width: auto;
  max-width: 30%;
  object-fit: contain;
  z-index: 1;
  cursor: pointer;
}

img:hover {
  transition: all 0.25s linear;
  transform: scale(1.05);
}

.header-content {
  width: 70%;
  height: 50%;
	display: flex;
	justify-content:flex-end;
	align-items: center;
}
.header-content-text {
  width: calc(100% - 13vh);
  text-align: end;
	font-family: "Jua";
	font-size: 3.5vh;
	font-weight: 900;
	margin-right: 3%;
}
#header-content-score {
  width: 13vh;
  height: 100%;
	font-size: 3.5vh;
	font-weight: bold;
	color: rgb(129, 129, 129);
	background-color: #e2e2e2;
	border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

#header-content-second {
  width: 90px;
  height: 90px;
	font-size: 45px;
	color: white;
	background-color: #4f37de;
	border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

@media (max-width: 700px) {
  .header-content-text {
    font-size: 2.5vh;
  }
  #header-content-second {
    width: 70px;
    height: 70px;
    font-size: 30px;
  }
}
</style>