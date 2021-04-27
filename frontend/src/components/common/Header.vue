<template>
  <header>
    <div style="width: 30%;">
      <img width="100%" src="@/assets/images/SSAQUIZ.png">
    </div>
    <div id="user-info" v-if="mode == 'on'">
      <div id="user-info__nickname">{{ nickname }}</div>
      <div id="user-info__score">{{ score }}</div>
    </div>
    <div id="user-info" v-else></div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  props: [
    'mode',
    'nickname',
    'score'
  ],
}
</script>

<style scoped>
header {
	height: 15%;
	display: flex;
	justify-content: space-between;
	align-items: center;
  margin-bottom: -2%;
	padding: 0vh 3vh 0vh 3vh;
}
#user-info {
  width: 50%;
	display: flex;
	justify-content:flex-end;
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
  margin-right: 3%;
	padding: 1vh 3vh 1vh 3vh;
}
</style>