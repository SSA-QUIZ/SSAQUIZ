<template>
  <div style="background-color: #f2f2f2; height: 100%; position: relative;">
    <Header mode="userInfo" :nickname="username" :score="score" />
    <ProgressBar :index="quizIndex+1" :all="totalNum" />
    <div id="solving-quiz-page-content">
      <MultipleChoice @click-button="sendAnswer" :choice="choice" v-if="category == '4지선다' || category == 'vote'" />
      <ShortAnswer v-else-if="category==='단답형'" @click-button="sendAnswer" />
      <template v-else-if="category==='순서맞히기'">
        <OrderingFrame class="solving-quiz-page-content__ordering" />
        <Ordering class="solving-quiz-page-content__ordering" />
      </template>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';
import ShortAnswer from '@/components/QuizTemplate/ShortAnswer.vue';
import Ordering from '../components/QuizTemplate/Ordering.vue';
import OrderingFrame from '../components/QuizTemplate/OrderingFrame.vue';

export default {
  name: 'SolvingQuizPage',
  components: {
    Header,
    ProgressBar,
    MultipleChoice,
    ShortAnswer,
    Ordering,
    OrderingFrame,
  },
  data: function () {
    return {
      choice: ["", "", "", ""],
    }
  },
  methods: {
    ...mapActions("PlayQuizStore", ["sendAnswer"]),
  },
  watch: {
    isFin: function (newVal) {
      if (newVal === true) {
        this.$router.push({ name: "WrongAnswer" })
      }
    },
    isSolved: function (newVal) {
      if (newVal === true) {
        this.$router.push({ name: "AwaitPage" })
      }
    }
  },
  computed: {
    ...mapState("PlayQuizStore", ["category", "totalNum", "quizIndex", "username", "score", "isFin", "isSolved"])
  },
}
</script>

<style>
#solving-quiz-page-content {
  display: flex;
  flex-flow: column;
	width: 95%;
  margin: 1% 2.5%;
  height: 70%;
}
.solving-quiz-page-content__ordering {
  display: flex;
  height: 100%;
}
</style>