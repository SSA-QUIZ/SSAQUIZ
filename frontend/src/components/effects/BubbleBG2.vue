<template>
  <div class="bubbles">
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
    <div class="bubble"></div>
  </div>
</template>

<script>
export default {
  name: "BubbleBG"
}
</script>

<style scoped>

/* 배경 효과 */
.bubbles{
  position:absolute;
  width:100%;
  height: 100%;
  z-index:0;
  overflow:hidden;
  top:0;
  left:0;
}
.bubble{
  position: absolute;
  bottom:-100px;
  width:40px;
  height: 40px;
  border-radius:50%;
  animation: rise 10s infinite ease-in;
}
.bubble:nth-child(1){
  width:140px;
  height:140px;
  left:10%;
  animation-duration:8s;

  background: rgb(231, 129, 129);
  opacity:0.3;
}
.bubble:nth-child(2){
  width:100px;
  height:100px;
  left:20%;
  animation-duration:5s;
  animation-delay:1s;

  background: rgb(231, 221, 129);
  opacity:0.3;
}
.bubble:nth-child(3){
  width:180px;
  height:180px;
  left:35%;
  animation-duration:7s;
  animation-delay:2s;

  background: rgb(152, 231, 120);
  opacity:0.3;
}
.bubble:nth-child(4){
  width:140px;
  height:140px;
  left:50%;
  animation-duration:11s;
  animation-delay:0s;

  background: rgb(129, 144, 231);
  opacity:0.3;
}
.bubble:nth-child(5){
  width:90px;
  height:90px;
  left:55%;
  animation-duration:6s;
  animation-delay:1s;

  background: rgb(202, 129, 231);
  opacity:0.3;
}
.bubble:nth-child(6){
  width:200px;
  height:200px;
  left:65%;
  animation-duration:8s;
  animation-delay:3s;

  background: rgb(231, 129, 197);
  opacity:0.3;
}
.bubble:nth-child(7){
  width:90px;
  height:90px;
  left:70%;
  animation-duration:12s;
  animation-delay:2s;

  background: rgb(231, 167, 94);
  opacity:0.3;
}
.bubble:nth-child(8){
  width:100px;
  height:100px;
  left:80%;
  animation-duration:6s;
  animation-delay:2s;

  background: rgb(129, 224, 231);
  opacity:0.3;
}
.bubble:nth-child(9){
  width:100px;
  height:100px;
  left:70%;
  animation-duration:5s;
  animation-delay:1s;

  background: rgb(141, 158, 121);
  opacity:0.3;
}
.bubble:nth-child(10){
  width:90px;
  height:90px;
  left:25%;
  animation-duration:10s;
  animation-delay:4s;

  background: rgb(0, 0, 255);
  opacity:0.3;
}
@keyframes rise{
  0%{
    bottom:-100px;
    transform:translateX(0);
  }
  50%{
    transform:translate(100px);
  }
  100%{
    bottom:100%;
    transform:translateX(-100px);
  }
}
</style>