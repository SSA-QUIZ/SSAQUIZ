<template>
  <div id="star">
    <span>{{ scores[scoreFactor] }}</span>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "Spinner",
  data: function () {
    return {
      scores: ['', 'x1.5', 'x2']
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex"]),
    scoreFactor: function () {
      return this.quizData["slideList"][this.quizIndex]["scoreFactor"];
    },
  }
}
</script>

<style>
#star {
  position: absolute;
  left: 10vw;
  top: 25vh;
  width: 200px;
  height: 200px;
  background: no-repeat center/100% url("../../assets/images/star.png");
  z-index: 999;
  display: flex;
  justify-content: center;
  align-items: center;

  animation: motion 0.7s linear 0s infinite alternate;
  transform: rotate(15deg);
}

#star span {
  font-size: 60px;
  font-family: Jua;
  margin-top: 10px;
  transform: rotate(-15deg);
}

@keyframes motion {
	0% {margin-top: 0px;}
	100% {margin-top: 20px;}
}

</style>