<template>
  <div id="fish-effect">
    <div class="ground">
    </div>

    <div class="first-circle circle">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
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
  width: 40%;
  height: 60%;

  overflow: hidden;
  background: transparent;

  position: absolute;
  bottom: -230px;
  
}
.ground {
  position: absolute;
  
  background: #a4caf7;
  top: calc(50%);
  width: 40%;
  height: 10.5%;
  z-index: 2;
  overflow: hidden;
}
.circle {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1001;
}
.circle div {
  position: absolute;
  box-shadow: 0 0 10px #00B4DB;
  border-radius: 50%;
  animation: bounce 3s linear infinite;
  animation-delay: .5s;
}
.first-circle div:nth-child(2) {
    animation-delay: 0.2s;
}

.first-circle div:nth-child(3) {
    animation-delay: .4s;
}

.first-circle div:nth-child(4) {
    animation-delay: .8s;
}

.first-circle div:nth-child(5) {
    animation-delay: 1.6s;
}

.first-circle div:nth-child(6) {
    animation-delay: 2.2s;
}

.fishy {
  position: absolute;
  z-index: 1;
  top: calc(50% + 50px);
  transform: rotate(-90deg);
  animation: jump 5s linear infinite;

  width: 200px;
  height: 200px;
  overflow: hidden;
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