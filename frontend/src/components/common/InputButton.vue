<template>
  <button class="PINButton">{{ text }}</button>
</template>

<script>
export default {
  name: "inputButton",
  props: ["text"]
}
</script>

<style>
.PINButton {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
	color: #FFFFFF;
  max-width: 100%;
	width: 300px;
	height: 50px;
	padding: 10px;
	background-color: #545DE3;
	border-radius: 5px;
	margin: 5px 0;
}

.PINButton:hover {
	background-color: #454995;
}

.PINButton:focus {
  outline: none;
}

.PINButton:active {
	background-color: #989DED;
}

</style>