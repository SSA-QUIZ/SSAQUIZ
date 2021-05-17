<template>
  <div id="await">
    <BubbleBG/>
    <!-- 고래 배경 -->
    <div class="wheel">
      <img class="inner-wheel" src="@/assets/images/pinkWhale.png" alt="SSAQUIZ">
    </div>
    <!-- 메인 -->
    <div class="container">
      <div class="row">
        <img src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ">
      </div>
      <div class="row">
        <div class="loading">
          <div class="loading__letter"><img src="@/assets/images/잠.png"></div>
          <div class="loading__letter"><img src="@/assets/images/시.png"></div>
          <div class="loading__letter"><img src="@/assets/images/만.png"></div>
          <div class="loading__letter"><img src="@/assets/images/기.png"></div>
          <div class="loading__letter"><img src="@/assets/images/다.png"></div>
          <div class="loading__letter"><img src="@/assets/images/려.png"></div>
          <div class="loading__letter"><img src="@/assets/images/주.png"></div>
          <div class="loading__letter"><img src="@/assets/images/세.png"></div>
          <div class="loading__letter"><img src="@/assets/images/요.png"></div>
          <div class="loading__letter"><img src="@/assets/images/shrimp2.png"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import BubbleBG from '@/components/effects/BubbleBG.vue';
export default {
  name: "AwaitPage",
  components: {
    BubbleBG
  },
  watch: {
    isFin: function (newVal) {
      if (newVal === true) {
        if (this.isCorrect === true) {
          this.$router.push({ name: "CorrectAnswer" });
        } else if (this.isCorrect === false) {
          this.$router.push({ name: "WrongAnswer" });
        }
      }
    },
  },
  computed: {
    ...mapState("PlayQuizStore", ["isFin", "isCorrect"])
  },
}
</script>

<style scoped>

#await {
  background-color: #cfe1f6;
  height: 100%;
  position: relative;
  overflow:hidden;
}

#await .container {
  width: 100%
}

#await .row {
  width: 100%;
  display: flex;
  justify-content: center;
}

#await .row > h1,
#await .row > img {
  z-index: 2;
}

#await .container .row > img {
  height: 80px;
  margin-top: 2%;
  margin-bottom: 7%;
}

#await .container h1 {
  color: black;
  font-size: 65px;
  font-family: Jua;
  margin-top: 150px;
}

#await .container span {
  color: black;
  font-size: 200px;
  font-family: Jua;
  margin-bottom: 7%;
}

/* 로딩텍스트 */

.loading {
  display: flex;
  flex-direction: row;
  margin-top: 200px;
}

.loading__letter {
  animation-name: bounce;
  animation-duration: 2s;
  animation-iteration-count: infinite;
}


.loading__letter > img {
  width: 80px;
  height: 80px;
}


.loading__letter:nth-child(2) {
  animation-delay: .1s;	
}
.loading__letter:nth-child(3) {
  animation-delay: .2s;
}
.loading__letter:nth-child(4) {
  animation-delay: .3s;	
}
.loading__letter:nth-child(5) {
  animation-delay: .4s;
}
.loading__letter:nth-child(6) {
  animation-delay: .5s;	
}
.loading__letter:nth-child(7) {
  animation-delay: .6s;
}
.loading__letter:nth-child(8) {
  animation-delay: .8s;
}
.loading__letter:nth-child(9) {
  animation-delay: 1s;
}
.loading__letter:nth-child(10) {
  animation-delay: 1.2s;
}

@keyframes bounce {
  0% {
    transform: translateY(0px)
  }
  40% {
    transform: translateY(-40px);
  }
  80%,
  100% {
    transform: translateY(0px);
  }
}

/* 고래 rotation 배경*/
.wheel-con{
  background-color: #f2f2f2;
  height: 100%;
  position: relative;
  overflow:hidden;
}
.wheel{
  position: absolute;
  left:50%;
  display: block;
  width: 100px;
  height: 100px;
  transform-origin: center 500%;
  animation: spin 3s linear infinite;
}

.inner-wheel{
  top: -150px;
  left: -50px;
  width: 300px;
  height: 300px;

  transform-origin: center center;
  transform: scaleY(-1);
}

@keyframes spin {
  50% { transform: rotate(180deg); }
  100% { transform: rotate(360deg); }
}

/* 모바일 화면 */
@media (max-width: 700px) {
  .loading {
    display: flex;
    flex-direction: row;
    margin-top: 70%;
  }
  .loading__letter > img {
    width: 30px;
    height: 30px;
  }
  .wheel{
    position: absolute;
    left:45%;
    top: 20%;
    display: block;
    width: 40px;
    height: 40px;
    transform-origin: center 480%;
    animation: spin 2.5s linear infinite;
  }
  .inner-wheel{
    top: -70px;
    left: -30px;
    width: 150px;
    height: 150px;

    transform-origin: center center;
    transform: scaleY(-1);
  }
}
</style>