<template>
	<div id="text-div">
		<p id="text-div-emoticon">🐋</p>
		<p id="text-div-message" v-html="message"></p>
	</div>
</template>

<script>
export default {
	name: 'TextDiv',
	props: ['message']
}
</script>

<style>
#text-div {
	width: 100%;
	height: 100%;
	display: flex;
	flex-flow: column;
	justify-content: center;
	align-items: center;
	font-size: 50px;
	font-family: Jua;
	color: #454995;
}
#text-div-emoticon {
	font-size: 150px;
}
</style>