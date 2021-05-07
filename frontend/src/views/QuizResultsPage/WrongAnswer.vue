<template>
  <div id="quiz-results-container--red">
    <Header mode="userInfo" nickname="[서울_3반_최나현]" score="354" />
    <div id="quiz-results-content">
    <p id="title">아쉬워요...</p>
    <p id="emoticon">😭</p>
    <p>토닥토닥💦</p>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Header from '@/components/common/Header.vue';

export default {
  name: 'WrongAnswer',
  components: {
    Header
  },
	watch: {
		isNext: function (newVal) {
			if (newVal === true) {
				this.setIsFin(false);
				this.setIsSolved(false);
				this.setIsCorrect(false);
				this.setIsNext(false);
				this.$router.push({ name: "SolvingQuizPage" });
			}
		},
		isEnd: function (newVal) {
			if (newVal === true) {
				this.$router.push({ name: "ResultPage" });
			}
		}
	},
	methods: {
		...mapActions("PlayQuizStore", ["setIsFin", "setIsSolved", "setIsCorrect", "setIsNext"])
	},
	computed: {
		...mapState("PlayQuizStore", ["isNext", "isEnd"])
	}
}
</script>

<style>
#quiz-results-container--red {
	background-color: #FF85B1;
	height: 100%;
}
</style>