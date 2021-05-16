<template>
  <div class="king-whale-container">
    <img class="king-whale" src="@/assets/images/kingWhale.png"/>
    <h1>{{ winner }}</h1>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "Winner",
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("CreateQuizRoomStore", ["resultData"]),
    ...mapState("PlayQuizStore", ["resultData2"]),
    rankData: function () {
      if (this.isStudent === true) {
        return this.resultData2
      } else {
        return this.resultData
      }
    },
    winner: function () {
      if (this.rankData.length === 0) {
        return ''
      } else {
        return this.rankData[0]["value"]
      }
    }
  },
}
</script>

<style>
#result .king-whale-container {
  display: flex;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
  
  background-color: rgba(51, 51, 51, 0.5);
  z-index: 3;
}
#result .king-whale-container {
  animation: winner-effect .3s ease both 5.5s;
  animation-delay: 9.5s;
}

#result .king-whale-container .king-whale {
  max-width: 100%;
  max-height: 80%;
  z-index: 3;
  overflow: hidden;

  animation: winner-effect .3s ease both 5.5s;
  animation-delay: 9.5s;
}

#result .king-whale-container h1 {
  position: absolute;
  bottom: 21vh;
  font-family:Jua;
  font-size: 4rem;
  z-index: 4;

  animation: winner-effect .3s ease both 5.5s;
  animation-delay: 9.5s;
}

/* 모바일 크기별 글씨 크기 변화 */

@media ( max-width: 700px ) {
  #result .king-whale-container h1 {
    position: absolute;
    bottom: 27vh;
    font-family: Jua;
    font-size: 3rem;
    z-index: 4;

    animation: winner-effect .3s ease both 5.5s;
    animation-delay: 9.5s;
  }
}


@media ( max-width: 500px ) {
  #result .king-whale-container h1 {
    position: absolute;
    bottom: 33vh;
    font-family: Jua;
    font-size: 2rem;
    z-index: 4;
  }
}

/* 애니메이션 효과 */

@keyframes winner-effect {
  0% {
   opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>