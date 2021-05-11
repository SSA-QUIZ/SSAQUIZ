<template>
  <div class="podium-container">
    <div class="podium-1">
      <div>
        <img class="crown" src="@/assets/images/bronzeCrown.png"/>
        <span>{{ bronze }}</span>
      </div>
      <img class="podium-image" src="@/assets/images/bronzePodium.png"/>
    </div>

    <div class="podium-3">
      <div>
        <img class="crown" src="@/assets/images/goldCrown.png"/>
        <span>{{ gold }}</span>
      </div>
      <img class="podium-image" src="@/assets/images/goldPodium.png"/>
    </div>

    <div class="podium-2">
      <div>
        <img class="crown" src="@/assets/images/silverCrown.png"/>
        <span>{{ silver }}</span>
      </div>
      <img class="podium-image" src="@/assets/images/silverPodium.png"/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "Podium",
  mounted: function () {
    console.log(mapState)
  },
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
    gold: function () {
      if (this.rankData.length === 0) {
        return ''
      } else {
        return this.rankData[0]["value"]
      }
    },
    silver: function () {
      if (this.rankData.length >= 2) {
        return this.rankData[1]["value"]
      } else {
        return ''
      }
    },
    bronze: function () {
      if (this.rankData.length === 3) {
        return this.rankData[2]["value"]
      } else {
        return ''
      }
    }
  },

}
</script>

<style>
/* podium 영역 */
#result .podium-container {
  position: absolute;
  display: flex;
  bottom: 0px;
  width: 100%;
  height: 85%;
}

/* 각 podium */
#result .podium-1 {
  position: absolute;
  left: 1vw;
  height: 38%;
  width: 40vw;
  bottom: 0px;
  animation: podium-effect .3s ease both 5.5s;
  animation-delay: 5.5s;
}

#result .podium-2 {
  position: absolute;
  right: 1vw;
  height: 55%;
  width: 40vw;
  bottom: 0px;
  animation: podium-effect .3s ease both 5.5s;
  animation-delay: 6.5s;
}

#result .podium-3 {
  position: absolute;
  left: 30vw;
  height: 80%;
  width: 40vw;
  bottom: 0px;
  z-index: 2;
  animation: podium-effect .3s ease both 5.5s;
  animation-delay: 7.5s;
}

/* 각 podium의 멘트와 뱃지 */

#result .podium-1 div,
#result .podium-2 div,
#result .podium-3 div {
  position: absolute;
  bottom: 75%;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

#result .podium-container .crown {
  width: 7vw;
  height: 7vw;
  float: left;
  margin-right: 5%;
}

#result .podium-container span {
  font-family: 'Nanum Pen Script', cursive;
  font-size: 7vh;
}

@media (max-width: 600px) {
  #result .podium-container span {
    font-family: 'Nanum Pen Script', cursive;
    font-size: 6vh;
  }
}

@media (max-width: 480px) {
  #result .podium-container span {
    font-family: 'Nanum Pen Script', cursive;
    font-size: 5vh;
  }
}

/* 각 podium 이미지 (단상) */

#result .podium-1 .podium-image,
#result .podium-2 .podium-image,
#result .podium-3 .podium-image {
  position: absolute;
  height: 76%;
  width: 100%;
  bottom: 0px;
  overflow: hidden;
}


/* 애니메이션 효과 */

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