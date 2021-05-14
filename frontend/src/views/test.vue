<template>
  <div style="background-color: #f2f2f2; height: 100%; position: relative;">
    <Header mode="userInfo" :nickname="username" :score="score" />
    <ProgressBar :index="1" :all="12" />
    <div id="solving-quiz-page-content">
      <MultipleChoice @click-button="sendAnswer" :choice="choice" v-if="category == '4지선다' || category == 'vote'" />
      <ShortAnswer v-else-if="category==='단답형'" @click-button="sendAnswer" />
      <TFChoice v-else-if="category==='TF'" :height="75" @click-button="sendAnswer" />
      <template>
        <OrderingFrame class="solving-quiz-page-content__ordering" />
        <Ordering mode="solving" class="solving-quiz-page-content__ordering" />
        <div class="solving-quiz-page__button">
          <button class="solving-quiz-page__ordering__button" style="background-color: #c3356a;" @click="resetOrdering">초기화</button>
          <button class="solving-quiz-page__ordering__button" @click="resetOrdering">제출</button>
        </div>
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
import TFChoice from '@/components/QuizTemplate/TFChoice.vue';

export default {
  name: 'SolvingQuizPage',
  components: {
    Header,
    ProgressBar,
    MultipleChoice,
    ShortAnswer,
    Ordering,
    OrderingFrame,
    TFChoice
  },
  data: function () {
    return {
      choice: ["", "", "", ""],
    }
  },
  methods: {
    ...mapActions("PlayQuizStore", ["sendAnswer"]),
    resetOrdering: function () {

    }
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
.solving-quiz-page__ordering__button {
  width: 120px;
  height: 65px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 35px;
  font-family: Jua;
  color: white;
  background-color: #454995;
  border-radius: 10px;
  margin-right: 0.3%;
}
.solving-quiz-page__button {
  display: flex;
  justify-content: flex-end;
  margin: 1.5% 0 0 0;
}
.solving-quiz-page-content__ordering {
  height: 100%;
}
</style>