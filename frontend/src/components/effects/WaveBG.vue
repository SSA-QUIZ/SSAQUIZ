<template>
  <div class="ocean">
    <div class="spin circle"></div>
  </div>
  
</template>

<script>
export default {
  name: "WaveBG"
}
</script>

<style>
.ocean {
  position:absolute;
  width:100%;
  height: 100%;
  z-index:0;
  overflow:hidden;
  top:0;
  left:0;
  background-color: #a4caf7;
  /* z-index: -1; */
}

/* .circle {
  position: relative;
  overflow: hidden;
  width: 180px;
  height: 180px;
  border-radius: 100%;
  background: transparant;
  box-shadow: #E94E3D;
  z-index:99;  
} */

.ocean:before,
.ocean:after {
  content: "";
  position: absolute;
  left: 50%;
  min-width: 300vw;
  min-height: 300vw;
  background-color: #CFE1F6;
  
  animation-name: rotate;
  animation-iteration-count: infinite;
  animation-timing-function: linear;
}

.ocean:before {
  bottom: 15vh;
  border-radius: 45%;
  animation-duration: 10s;
}

.ocean:after {
  bottom: 12vh;
  opacity: .5;
  border-radius: 47%;
  animation-duration: 10s;
}

@keyframes rotate {
  0% {transform: translate(-50%, 0) rotateZ(0deg);}
  50% {transform: translate(-50%, -2%) rotateZ(180deg);}
  100% {transform: translate(-50%, 0%) rotateZ(360deg);}
}
</style>