<template>
  <!-- 퀴즈 풀러가기 의 로딩창 -->
  <button v-if="blocked" id="loading-button" disabled>
		<div class="pulse-container">  
			<div class="pulse-bubble pulse-bubble-1"></div>
			<div class="pulse-bubble pulse-bubble-2"></div>
			<div class="pulse-bubble pulse-bubble-3"></div>
		</div>
	</button>
	<!-- 그 외의 모든 기능 -->
  <button v-else id="input-button">{{ text }}</button>
</template>

<script>
export default {
  name: "inputButton",
  props: ["text", "blocked"],
}
</script>

<style scoped>
#input-button {
	font-family: 'Jua', sans-serif;
	color: #FFFFFF;
  max-width: 100%;
	width: 300px;
	height: 50px;
	padding-top: 4px;
	background-color: #545DE3;
	border-radius: 5px;
	margin: 5px 0;
}

#input-button:hover {
	background-color: #454995;
}

#input-button:focus {
  outline: none;
}

#input-button:active {
	background-color: #989DED;
}

/* 로딩바 */
#loading-button {
	font-family: 'Jua', sans-serif;
	color: #FFFFFF;
  max-width: 100%;
	width: 300px;
	height: 50px;
	padding-top: 4px;
	background-color: #545DE3;
	border-radius: 5px;
	margin: 5px 0;

	display: flex;
	justify-content: center;
}

.pulse-container {
  width: 30%;
	margin-top: 3%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.pulse-bubble {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: #ffffff;
}

.pulse-bubble-1 {
    animation: pulse .4s ease 0s infinite alternate;
}
.pulse-bubble-2 {
    animation: pulse .4s ease .2s infinite alternate;
}
.pulse-bubble-3 {
    animation: pulse .4s ease .4s infinite alternate;
}

@keyframes pulse {
  from {
    opacity: 1;
    transform: scale(1);
  }
  to {
    opacity: .25;
    transform: scale(.75);
  }
}
</style>