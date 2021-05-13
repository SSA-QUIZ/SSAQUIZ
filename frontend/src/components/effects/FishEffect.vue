<template>
  <div id="fish-effect">
    <div class="ground"></div>
    <img class="fishy" src="@/assets/images/yellowWhale.png">
  </div>
</template>

<script>
export default {
  name: "FishEffect"
}
</script>

<style scoped>
#fish-effect {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50%;
  height: 40%;

  overflow: hidden;
  background: transparent;

  position: absolute;
  bottom: 0px;
}
.ground {
  position: absolute;
  background: #a4caf7;
  bottom: 0px;
  width: 40%;
  height: 17%;
  z-index: 2;
  overflow: hidden;
}
.fishy {
  position: relative;
  z-index: 1;
  top: calc(50% + 50px);
  transform: rotate(-90deg);
  animation: jump 5s linear infinite;

  width: 200px;
  height: 200px;
}


@keyframes bounce {
  from {width: 20px; height: 15px; 
  box-shadow: 0 0 10px #00B4DB; opacity: 1;}
  to {width: 380px; height: 80px; 
  box-shadow: 0 0 10px #00B4DB; opacity: 0;}
}

@keyframes jump {
  0% {
    top: calc(40% + 70px);
    transform: rotate(-90deg);
  }
  20% {
    top: calc(40% - 250px);
    transform: rotate(0deg);
  }
  50% {
    top: calc(40% + 70px);
    transform: rotate(90deg);
  }
  80% {
    top: calc(40% + 70px);
    transform: rotate(90deg);
  }
  100% {
    top: calc(40% + 70px);
    transform: rotate(90deg);
  }
}
</style>