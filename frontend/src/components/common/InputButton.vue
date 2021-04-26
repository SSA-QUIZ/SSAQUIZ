<template>
  <button class="input-button">{{ text }}</button>
</template>

<script>
export default {
  name: "inputButton",
  props: ["text"]
}
</script>

<style>
.input-button {
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

.input-button:hover {
	background-color: #454995;
}

.input-button:focus {
  outline: none;
}

.input-button:active {
	background-color: #989DED;
}

</style>