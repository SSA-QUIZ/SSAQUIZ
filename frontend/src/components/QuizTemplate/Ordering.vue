<template>
  <div id="ordering-container">
    <template v-if="mode === 'answer'">
      <template v-for="(i, index) in showAnswer">
        <OrderingButton
          mode="ordering"
          :key="index"
          :index="i+1"
        />
      </template>
    </template>
    <template v-else>
      <template v-for="index in 4">
        <CreatorOrderingButton 
          v-if="mode === 'create'"
          :key="index"
          :index="index"
          :currentIdx="currentIdx"
          @click-button="setOrderingAnswer([index, currentIdx]); changeCurrentIdx();"
        />
        <OrderingButton
          v-else
          mode="ordering"
          :key="index"
          :index="index"
        />
      </template>
    </template>

  </div>
</template>

<script>
import OrderingButton from '@/components/common/OrderingButton.vue';
import CreatorOrderingButton from '@/components/QuizCreator/CreatorOrderingButton.vue';
import { mapActions, mapState } from 'vuex';

export default {
  name: "Ordering",
  components: {
    OrderingButton,
    CreatorOrderingButton
  },
  props: ['mode', 'currentIdx'],
  data: function () {
    return {
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex"]),
    answer: function () {
      return this.quizData.slideList[this.quizIndex].answer;
    },
    showAnswer: function () {
      let temp = [];
      for (let i = 1; i < 5; i ++) {
        temp.push(this.quizData.slideList[this.quizIndex].answer.indexOf(i.toString()));
      }
      return temp;
    },
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setOrderingAnswer"]),
    changeCurrentIdx: function () {
      this.$emit('change-current-idx');
    },
  },
}
</script>

<style>
#ordering-container {
  display: flex;
  width: 100%;
  height: 98%;
}
</style>