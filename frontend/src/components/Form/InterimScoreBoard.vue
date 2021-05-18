<template>
  <div id="board">
    <div class="scores">
      <template v-for="(student, index) in scoreBoardData">
        <div :key="index">
          <div class="row">
            <img v-if="index===0" src="@/assets/images/gold.png" alt="gold"> 
            <img v-else-if="index===1" src="@/assets/images/silver.png" alt="silver"> 
            <img v-else-if="index===2" src="@/assets/images/bronze.png" alt="bronze"> 
            <h1>{{ scoreBoardData[index]["value"] }}</h1>
          </div>
          <h1 class="score">{{ scoreBoardData[index]["score"] }}점</h1>
        </div>

      </template>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "InterimScoreBoard",
  computed: {
    ...mapState("CreateQuizRoomStore", ["scoreBoardData"])
  },
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