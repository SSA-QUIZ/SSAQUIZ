<template>
  <div id="cloud">
    <div class="clouds">
      <div class="cloudsWrapper cloudImg"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Test"
}
</script>

<style scoped>
#cloud {
	background: red;
	position: relative;
	width: 100%;
	height: 100vh;
}

.cloudsWrapper:before,
.cloudsWrapper:after {
	position: absolute;
	content: "";
	display: block;
	height: 100%;
	min-width: 2560px;
	width: 100%;
	background-size: auto 100%;
	-webkit-animation: clouds linear infinite alternate;
	animation: clouds linear infinite alternate;
}

.clouds {
	position: absolute;
	width: 100%;
	bottom: 0;
}

.cloudsWrapper {
	position: absolute;
	overflow: hidden;
	top: -149px;
	height: 150px;
	width: 100%;
}
.cloudsWrapper:after {
	background: url("https://imgur.com/GaR2ZvO.png") bottom repeat-x;
	-webkit-animation-duration: 210s;
	animation-duration: 210s;
}
.cloudsWrapper:before {
	background: url("https://imgur.com/zYklUEb.png") bottom repeat-x;
	-webkit-animation-duration: 120s;
	animation-duration: 120s;
}

.block {
	position: relative;
	height: 100%;
	background-image: radial-gradient(
		circle at 50% 90%,
		#385c78,
		#011735 79%,
		#010e1f
	);
	overflow: hidden;
	box-sizing: border-box;
}

.block:after {
	position: absolute;
	content: "";
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: url("https://imgur.com/B2VVbTo.png");
	background-size: cover;
	animation: stars 40s linear infinite alternate;
}

.block:before {
	position: absolute;
	content: "";
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: url("https://imgur.com/OzQPJqM.png");
	background-size: cover;
	animation: stars 70s linear infinite alternate;
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