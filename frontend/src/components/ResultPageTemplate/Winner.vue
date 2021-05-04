<template>
  <div class="king-whale-container">
    <img class="king-whale" src="@/assets/images/kingWhale.png"/>
    <h1>장장주빈</h1>
  </div>
</template>

<script>
export default {
  name: "Winner"
}
</script>

<style>
#result .king-whale-container {
  display: flex;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
  z-index: 3;
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
  font-family: 'Nanum Pen Script', cursive;
  font-size: 9vh;
  z-index: 4;

  animation: winner-effect .3s ease both 5.5s;
  animation-delay: 9.5s;
}

/* 모바일 크기별 글씨 크기 변화 */

@media ( max-width: 600px ) {
  #result .king-whale-container h1 {
    position: absolute;
    bottom: 28vh;
    font-family: 'Nanum Pen Script', cursive;
    font-size: 9vh;
    z-index: 4;

    animation: winner-effect .3s ease both 5.5s;
    animation-delay: 9.5s;
  }
}

@media ( max-width: 480px ) {
  #result .king-whale-container h1 {
    position: absolute;
    bottom: 30vh;
    font-family: 'Nanum Pen Script', cursive;
    font-size: 9vh;
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