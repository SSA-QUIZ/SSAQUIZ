<template>
  <input :value="data" @input="onInput" @keyup.enter="pressEnter" class="input-box">
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
		},
		pressEnter: function () {
			this.$emit('press-enter')
		}
	},
}
</script>

<style scoped>
.input-box {
  font-family: 'Jua', sans-serif !important;
	max-width: 100%;
	width: 300px;
	height: 50px;
	background-color: #FFFFFF;
	border: 3px solid #FFFFFF;
	padding-top: 4px;
	border-radius: 5px;
	text-align: center;
	margin: 5px 0;
	border: 1px solid black;
}

.input-box:focus, .input-box:active {
	outline: none;
	border: 3px solid #545DE3;
}
/* input[type=password] 글씨 보이게 하기 */
input[type=password] {
	font-family: "Noto Sans KR", sans-serif !important;
	padding-top: 0px;
}

input[type=password]::placeholder {
	font-family: "Jua";
}

</style>