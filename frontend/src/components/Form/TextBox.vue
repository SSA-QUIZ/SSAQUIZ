<template>
  <div id="text-box">
    <input placeholder="답안을 작성해주세요." :value="data" @input="onInput">
  </div>
</template>

<script>
export default {
  name: "TextBox",
  data: function () {
    return {
      data: "",
    }
  }
}
</script>

<style>
#text-box input {
  font-family: 'Noto Sans KR', sans-serif !important;
	font-weight: bold;
  font-size: 30px;
	width: 100%;
	height: 150px;
  text-align: center;
	background-color: #FFFFFF;
	border: 3px solid #FFFFFF;
	border-radius: 5px;
	text-align: center;
	margin-left: 2.5%;
}

#text-box input:focus, #text-box input:active {
	outline: none;
	border: 3px solid #545DE3;
}
</style>