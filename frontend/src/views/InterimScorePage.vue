<template>
  <div id="interim-page">
    <!-- 배경 -->
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

    <!-- 본문 -->
    <Logo />
    <div id="content">
      <h1 id="title">중간점검</h1>
      <div class="container">
        <div class="scores">
          <!-- 금메달 -->
          <div>
            <div class="row">
              <img src="@/assets/images/gold.png" alt="gold"> 
              <h1>문어</h1>
            </div>
            <h1 class="score">1050점</h1>
          </div>
          <!-- 은메달 -->
          <div>
            <div class="row">
              <img src="@/assets/images/silver.png" alt="gold"> 
              <h1>오징어</h1>
            </div>
            <h1 class="score">947점</h1>

          </div>
          <!-- 동메달 -->
          <div>
            <div class="row">
              <img src="@/assets/images/bronze.png" alt="gold"> 
              <h1>복어</h1>
            </div>
            <h1 class="score">879점</h1>
          </div>
        </div>
      </div>
      <h1 class="mention">문어님이 현재까지 3번 연속이나 답을 맞추셨어요!</h1>
      <!-- 문제 진행 버튼 -->
      <button class="button"><img src="@/assets/images/arrow.png"></button>
      <!-- 배경의 고래 이미지 -->
      <img class="whale-img-1" src="@/assets/images/pinkWhale.png">
      <img class="whale-img-2" src="@/assets/images/purpleWhale.png">
      <img class="whale-img-3" src="@/assets/images/greenWhale.png">
    </div>
  </div>
</template>

<script>
import Logo from '@/components/common/Logo.vue';
export default {
  name: "InterimScore",
  components: {
    Logo
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

#content .container {
  flex-wrap: wrap;
  display: flex;
  justify-content: center;
  width: 50%;
  height: 50%;
  border-radius: 15px;
  background-color: white;
  border: 3px solid black;

  position: relative;
  z-index: 1;
}

/* 각 점수를 나타내는 영역 */

#content .container .scores {
  width: 80%;
  height: 50%;
  margin-top: 8%;
}

.scores > div {
  display: flex;
  justify-content: space-between;
}

/* 각 점수 */

.scores .score{
  color: black;
  font-size: 2.5vw;
  font-family: Jua;
  width: 30%;
  margin-top: 3%;
  margin-left: 3%;
}

.row {
  display: flex;
  width: 100%;
}

.row img {
  width: 15%;
  float: left;
}

.row h1 {
  color: black;
  font-size: 3vw;
  font-family: Jua;
  margin-top: 3%;
  margin-left: 3%;
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

/* 이동 버튼 */

#content .button {
  position: absolute;
  z-index: 2;
  right: 10%;
  top: 43%;
}


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
  background:#f1f1f1;
  border-radius:50%;
  opacity:0.5;
  animation: rise 10s infinite ease-in;
}
.bubble:nth-child(1){
  width:80px;
  height:80px;
  left:10%;
  animation-duration:8s;
}
.bubble:nth-child(2){
  width:80px;
  height:80px;
  left:20%;
  animation-duration:5s;
  animation-delay:1s;
}
.bubble:nth-child(3){
  width:50px;
  height:50px;
  left:35%;
  animation-duration:7s;
  animation-delay:2s;
}
.bubble:nth-child(4){
  width:40px;
  height:40px;
  left:50%;
  animation-duration:11s;
  animation-delay:0s;
}
.bubble:nth-child(5){
  width:30;
  height:30px;
  left:55%;
  animation-duration:6s;
  animation-delay:1s;
}
.bubble:nth-child(6){
  width:45px;
  height:45px;
  left:65%;
  animation-duration:8s;
  animation-delay:3s;
}
.bubble:nth-child(7){
  width:90px;
  height:90px;
  left:70%;
  animation-duration:12s;
  animation-delay:2s;
}
.bubble:nth-child(8){
  width:80px;
  height:80px;
  left:80%;
  animation-duration:6s;
  animation-delay:2s;
}
.bubble:nth-child(9){
  width:25px;
  height:25px;
  left:70%;
  animation-duration:5s;
  animation-delay:1s;
}
.bubble:nth-child(10){
  width:90px;
  height:90px;
  left:25%;
  animation-duration:10s;
  animation-delay:4s;
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