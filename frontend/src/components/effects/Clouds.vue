<template>
  <div class="clouds">
    <div class="cloudsWrapper cloudImg"></div>
  </div>
</template>

<script>
export default {
  name: "Clouds"
}
</script>

<style scoped>
/* 구름 */
.cloudsWrapper:before,
.cloudsWrapper:after {
	position: absolute;
	content: "";
	display: block;
	height: 100%;
	min-width: 200vw;
	background-size: auto 100vw;
	-webkit-animation: clouds linear infinite alternate;
	animation: clouds linear infinite alternate;
}

.clouds {
	position: absolute;
	width: 200vw;
	bottom: 0;
	left: 0;
}

.cloudsWrapper {
	position: absolute;
	overflow: hidden;
	top: -150px;
	height: 150px;
	width: 200vw;
}

.cloudsWrapper:after {
	background: url("https://imgur.com/GaR2ZvO.png") bottom repeat-x;
	-webkit-animation-duration: 120s;
	animation-duration: 120s;
}

.cloudsWrapper:before {
	background: url("https://imgur.com/zYklUEb.png") bottom repeat-x;
	-webkit-animation-duration: 120s;
	animation-duration: 120s;
}

@keyframes stars {
	0% {
		-webkit-transform: rotate(-14deg);
		transform: rotate(-14deg);
	}
	100% {
		-webkit-transform: rotate(14deg);
		transform: rotate(14deg);
	}
}

@keyframes clouds {
	0% {
		-webkit-transform: translateX(0);
		transform: translateX(0);
	}
	100% {
		-webkit-transform: translateX(-1000px);
		transform: translateX(-1000px);
	}
}
</style>