<template>
  <div id="result">
    <div class="bg"></div>
    <img class="whale" src="@/assets/images/WelcomPage-icon.png"/>
    <div class="text">1등은 누구일까요?!</div>
    <img class="logo" src="@/assets/images/resultLogo.png"/>
    <div @click="moveToUserPage" class="router-image">
      <img src="@/assets/images/shrimp.png"/>
    </div>
    <Podium/>
    <Winner/>
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color    
    />
  </div>
</template>

<script>
import Podium from "@/components/ResultPageTemplate/Podium.vue";
import Winner from "@/components/ResultPageTemplate/Winner.vue";
import Alert from '@/components/Popup/Alert.vue';
import { mapActions, mapState } from 'vuex';
export default {
  name: "ResultPage",
  components: {
    Podium,
    Winner,
    Alert
  },
  data: function () {
    return {
      flag: false,
      alertMessage: '',
      color: '',
    }
  },
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("PlayQuizStore", ["teacherDisconnected"]),
  },
  watch: {
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
  methods: {
    ...mapActions("PlayQuizStore", ["disconnectWS"]),
    ...mapActions("CreateQuizRoomStore", ["disconnectTeacherWS", "sendExitTeacherMessage"]),
    moveToUserPage: function () {
      if (this.isStudent === true) {
        this.disconnectWS();
      } else if (this.isStudent === false) {
        this.sendExitTeacherMessage();
        // this.disconnectTeacherWS();
      }
      this.$router.push({ name: "WelcomePage" });
    }
  }
}
</script>

<style scoped>
/* 결과 페이지 css */

#result {
  display: flex;
  justify-content: center;
  height: 100%;
}

/* 두구두구 효과 */

#result .bg {
  background: #cfe1f6;
  width: 50vw;
  height: 50vw;
  border-radius: 50%;
  top: 50%;
  margin-top: -25%;
  margin-left: -25%;
  left: 50%;
  position: absolute;
  animation: empty both 3s linear;
  
}

#result .whale {
  position: absolute;
  top: 30%;
  width: 300px;
  height: 250px;
  overflow: hidden;
  animation: shake .16s linear 20, moveTop both .5s ease-in-out 3.5s, goOut forwards .2s ease 5s;
}

#result .whale:before {
  content: '';
  position: absolute;
  width: 100%;
  transform: scale( 1.2 );
  height: 100%;
  animation: fill both 2s linear;
}

#result .whale:after {
  content: 'A';
  color: #fff;
  position: absolute;
  right: -2px;
  bottom: -12px;
}

#result .text {
  color: #cfe1f6;
  text-shadow: 0 1px 0 #fff;
  font-size: 3rem;
  font-family: Jua;
  color: #545de3;
  position: absolute;
  top: 50%;
  animation: fave .7s both ease-in-out 3.5s, goOut forwards .2s ease 5s;
}

/* 결과 페이지 result 로고 */

#result .logo {
  position: absolute;
  width: 330px;
  height: 90px;
  animation: comeIn both .2s ease 5.5s;
}

/* 유저 정보 페이지로 이동 */

#result .router-image {
  position: absolute;
  left: 3%;
  top: 2%;
  width: 10%;
  height: 15%;
  z-index: 5;
  cursor: pointer;

  animation: shaking infinite, router-effect .3s ease both 8.5s;
	animation-duration: 1.8s;
	animation-timing-function: linear;
}

#result .router-image:hover {
  animation-play-state: paused;
}

#result .router-image img {
  display: block;
  height: 80%;
}


/* 애니메이션 효과 */

@keyframes empty {
  0% {
    transform: scale( 3 );
  }
  100% {
    transform: scale( 0 );
  }
}

@keyframes shake {
  0%, 100% {
    transform: rotate( 0 );
  }
  10%, 50%, 90% {
    transform: rotate( 4deg );
  }
  30%, 70% {
    transform: rotate( -4deg );
  }
}

@keyframes moveTop {
  100% {
    top: 13%;
  }
}

@keyframes fave {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  } 
}

@keyframes fill {
  0% {
    bottom: -100%;
  }
  100% {
    bottom: 0%;
  }
}

@keyframes goOut {
  100% {
    opacity: 0;
    top: 45%;
  }
}

@keyframes comeIn {
  0% {
   opacity: 0;
   top: 0%;
  }
  100% {
    opacity: 1;
    top: 2%;
  }
}

/* 유저 페이지로 이동하는 아이콘의 이벤트 */

@keyframes router-effect {
  0% {
   opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

@keyframes shaking {
	3%, 98% { transform: translate(0px); }
	16% { transform: translate(10px); }
	33% { transform: translate(-10px); }
	50% { transform: translate(6px); }
	67% { transform: translate(-6px); }
	83% { transform: translate(3px); }
}

</style>