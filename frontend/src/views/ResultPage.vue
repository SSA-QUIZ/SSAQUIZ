<template>
  <div id="result">
    <div class="bg"></div>
    <img class="anetwork" src="@/assets/images/WelcomPage-icon.png"/>
    <div class="text">1등은 누구일까요?!</div>

    <img class="logo" src="@/assets/images/resultLogo.png"/>
    <div class="podium-container">
      <div class="podium-1">
        <h1>ff</h1>
        <img src="@/assets/images/bronzePodium.png"/>
      </div>
      <div class="podium-3">
        <h1>ff</h1>
        <img src="@/assets/images/goldPodium.png"/>
      </div>
      <div class="podium-2">
        <h1>ff</h1>
        <img src="@/assets/images/silverPodium.png"/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ResultPage"
}
</script>

<style scoped>
#result {
  display: flex;
  justify-content: center;
}

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

#result .anetwork {
  position: absolute;
  top: 30%;
  width: 300px;
  height: 250px;
  overflow: hidden;
  animation: shake .16s linear 20, moveTop both .5s ease-in-out 3.5s, goOut forwards .2s ease 5s;
}

#result .anetwork:before {
  content: '';
  position: absolute;
  width: 100%;
  transform: scale( 1.2 );
  height: 100%;
  animation: fill both 2s linear;
}

#result .anetwork:after {
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

#result .podium-container {
  position: absolute;
  bottom: 0px;
  width: 100%;
  height: 80%;
}

#result .podium-container h1 {
  position: absolute;
  bottom: 100%;
}

#result .podium-1 {
  position: absolute;
  left: 5.5%;
  height: 20%;
  width: 35%;
  bottom: -5%;
  animation: podium-effect .3s ease both 5.5s;
  animation-delay: 5.5s;
}

#result .podium-2 {
  position: absolute;
  right: 5.5%;
  height: 35%;
  width: 35%;
  bottom: 5%;
  animation: podium-effect .3s ease both 5.5s;
  animation-delay: 6.5s;
}

#result .podium-3 {
  position: absolute;
  left: 32.5%;
  height: 50%;
  width: 35%;
  bottom: 5%;
  z-index: 2;
  animation: podium-effect .3s ease both 5.5s;
  animation-delay: 7.5s;
}


#result .podium-1 img,
#result .podium-2 img,
#result .podium-3 img {
  height: 100%;
  width: 100%;
  overflow: hidden;
}

#result .logo {
  position: absolute;
  width: 40vh;
  animation: comeIn both .2s ease 5.5s;
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

@keyframes podium-effect {
  0% {
   opacity: 0;
   bottom: -20%;
  }
  100% {
    opacity: 1;
    bottom: 0%;
  }
}
</style>