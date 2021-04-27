<template>
  <div id="quiz-results-container--blue">
    <Header mode="on" nickname="[서울_3반_최나현]" score="354" />
    <div id="quiz-results-content">
      <p id="title">대단해요!</p>
      <p id="emoticon">🥰</p>
      <p>+ {{ quizScore }}🔥</p>
    </div>
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
export default {
  name: 'CorrectAnswer',
  components: {
    Header
  },
  data: function () {
    return {
      quizScore: 324
    }
  }

}
</script>

<style>
#quiz-results-container--blue {
	background-color: #7CB1FF;
	height: 100%;
}

#quiz-results-content {
	height: 85%;
	display: flex;
	flex-flow: column;
	justify-content: center;
	align-items: center;
	font-size: 4rem;
	font-family: Jua;
}

#quiz-results-content > #title {
	font-size: 7.5rem;
}

#quiz-results-content > #emoticon {
	font-size: 16rem;
}

@media (max-width: 700px) {
	#quiz-results-content {
		font-size: 2rem;
	}

	#quiz-results-content > #title {
		font-size: 4rem;
	}

	#quiz-results-content > #emoticon {
	font-size: 10rem;
	}
}
</style>