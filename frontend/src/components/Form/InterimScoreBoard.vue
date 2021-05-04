<template>
  <div id="board">
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
</template>

<script>
export default {
  name: "InterimScoreBoard"
}
</script>

<style>
#board {
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

#board .scores {
  width: 80%;
  height: 50%;
  margin-top: 8%;
}

#board .scores > div {
  display: flex;
  justify-content: space-between;
}

/* 각 점수 */

#board .scores .score{
  color: black;
  font-size: 2.5vw;
  font-family: Jua;
  width: 30%;
  margin-top: 3%;
  margin-left: 3%;
}

#board .scores .row {
  display: flex;
  width: 100%;
}

#board .scores .row img {
  width: 15%;
  float: left;
}

#board .scores .row h1 {
  color: black;
  font-size: 3vw;
  font-family: Jua;
  margin-top: 3%;
  margin-left: 3%;
}
</style>