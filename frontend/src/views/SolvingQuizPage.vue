<template>
  <div style="background-color: #f2f2f2; height: 100%; position: relative;">
    <Header mode="on" :nickname="username" score="950" />
    <ProgressBar :index="quizIndex" :all="totalNum" />
    <MultipleChoice @click-button="sendAnswer" :choice="choice" v-show="category == '4지선다' || category == 'vote'" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';
// import ShortAnswer from '@/components/QuizTemplate/ShortAnswer.vue';

export default {
  name: 'SolvingQuizPage',
  components: {
    Header,
    ProgressBar,
    MultipleChoice,
    // ShortAnswer
  },
  data: function () {
    return {
      choice: ['문어', '복어', '오징어', '대게'],
    }
  },
  methods: {
    ...mapActions("PlayQuizStore", [""]),
    sendAnswer: function (data) {
      console.log(data)
    }
  },
  computed: {
    ...mapState("PlayQuizStore", ["category", "totalNum", "quizIndex", "username"])
  },
}
</script>

<style>

</style>