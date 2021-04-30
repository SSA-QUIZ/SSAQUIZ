<template>
  <input :value="data" @input="onInput" class="input-box">
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
		onInput: function (e) {
			this.data = e.target.value;
			this.$emit('change-input', this.data)
		}
	}
}
</script>

<style>
.input-box {
  font-family: 'Noto Sans KR', sans-serif !important;
	font-weight: bold;
	max-width: 100%;
	width: 300px;
	height: 50px;
	padding: 10px;
	background-color: #FFFFFF;
	border: 3px solid #FFFFFF;
	border-radius: 5px;
	text-align: center;
	margin: 5px 0;
}

.input-box:focus, .input-box:active {
	outline: none;
	border: 3px solid #545DE3;
}
</style>