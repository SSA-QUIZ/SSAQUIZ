<template>
  <header>
    <div>
      <img width="40%" src="@/assets/images/SSAQUIZ.png">
    </div>
    <div id="user-info" v-if="mode == 'student'">
      <div id="user-info__nickname">{{ nickname }}</div>
      <div id="user-info__score">{{ score }}</div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  props: [
    'nickname',
    'score'
  ],
  data: function () {
    return {
      mode: 'student'
    }
  }
}
</script>

<style>
header {
	height: 15%;
	display: flex;
	justify-content: center;
	align-items: center;
  margin-top: -0.5%;
  margin-bottom: -2%;
	padding: 0vh 3vh 0vh 3vh;
}
#user-info {
	display: flex;
	justify-content: center;
	align-items: center;
}
#user-info__nickname {
  width:8vh;
	font-family: "Jua";
	font-size: 3.5vh;
	font-weight: 900;
	margin-right: 3%;
}
#user-info__score {
	font-size: 3.5vh;
	font-weight: 900;
	color: rgb(255, 255, 255);
	background-color: rgb(170, 170, 170);
	border-radius: 15px;
	padding: 1vh 3vh 1vh 3vh;
}
</style>