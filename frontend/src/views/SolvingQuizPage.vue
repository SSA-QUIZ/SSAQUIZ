<template>
  <div style="background-color: #f2f2f2; height: 100%; position: relative;">
    <Header mode="userInfo" :nickname="username" :score="score" />
    <ProgressBar :index="quizIndex+1" :all="totalNum" />
    <!-- <OrderingFrame v-show="category == '순서맞히기'" /> -->
    <MultipleChoice @click-button="sendAnswer" :choice="choice" v-if="category == '4지선다' || category == 'vote'" />
    <ShortAnswer v-else-if="category==='단답형'" @click-button="sendAnswer" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';
// import OrderingFrame from '../components/QuizTemplate/OrderingFrame.vue';
// import ShortAnswer from '@/components/QuizTemplate/ShortAnswer.vue';   
import ShortAnswer from '@/components/QuizTemplate/ShortAnswer.vue';   

export default {
  name: 'SolvingQuizPage',
  components: {
    Header,
    ProgressBar,
    MultipleChoice,
    // OrderingFrame,
    // ShortAnswer
    ShortAnswer
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

</style>