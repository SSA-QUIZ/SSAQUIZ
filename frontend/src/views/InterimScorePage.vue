<template>
  <div id="interim-page">
    <BubbleBG/>
    <Logo />
    <div id="content">
      <h1 id="title">중간점검</h1>
      <!-- 점수판 -->
      <InterimScoreBoard/>
      <NextStepButton/>
      <h1 class="mention">문어님이 현재까지 3번 연속이나 답을 맞추셨어요!</h1>
      <!-- 배경의 고래 이미지 -->
      <img class="whale-img-1" src="@/assets/images/pinkWhale.png">
      <img class="whale-img-2" src="@/assets/images/purpleWhale.png">
      <img class="whale-img-3" src="@/assets/images/greenWhale.png">
    </div>
  </div>
</template>

<script>
import Logo from '@/components/common/Logo.vue';
import NextStepButton from '@/components/common/NextStepButton.vue';
import BubbleBG from '@/components/effects/BubbleBG.vue';
import InterimScoreBoard from '@/components/Form/InterimScoreBoard.vue';
export default {
  name: "InterimScore",
  components: {
    Logo,
    NextStepButton,
    InterimScoreBoard,
    BubbleBG,
  }
}
</script>

<style scoped>
/* 전체 영역 */

#interim-page {
  width: 100%;
  height: 100%;
  background-color: #4f37de;
}

/* 로고를 제외한 나머지 영역 */

#interim-page #content  {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  flex-flow: column;
}

#content #title {
  color: white;
  font-size: 4.5vw;
  font-family: Jua;
  width: 90%;
  display: flex;
  align-items: center;
  justify-content: center;
}

#content .mention {
  color: white;
  font-size: 3vw;
  font-family: Jua;
  width: 90%;
  display: flex;
  justify-content: center;
  margin-top: 3%;
}

/* 배경의 고래 이미지 */

#content .whale-img-1 {
  position: absolute;
  z-index: 2;
  width: 30%;
  left: 0%;
  top: 20%;
  transform: rotate( 35deg );
}

#content .whale-img-2 {
  position: absolute;
  z-index: 2;
  width: 12%;
  right: 7%;
  top: 8%;
  transform: scaleX(-1) rotate( 15deg );
}

#content .whale-img-3 {
  position: absolute;
  z-index: 2;
  width: 20%;
  right: 5%;
  top: 50%;
  transform: scaleX(-1) rotate( -15deg );
}


</style>