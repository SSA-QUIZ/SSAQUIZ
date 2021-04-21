<template>
  <input @keyup="onInput" class="inputBox" v-model="data">
</template>

<script>
export default {
  name: "inputBox",
	data: function () {
		return {
			data: "",
		}
	},
	methods: {
		onInput: function () {
			this.$emit('change-input', this.data)
		}
	}
}
</script>

<style>
.inputBox {
  font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
	width: 300px;
	height: 50px;
	padding: 10px;
	background-color: #FFFFFF;
	border: 3px solid #FFFFFF;
	border-radius: 5px;
	text-align: center;
	margin: 5px 0;
}

.inputBox:focus, .inputBox:active {
	outline: none;
	border: 3px solid #545DE3;
}
</style>