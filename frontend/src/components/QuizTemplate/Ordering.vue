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
          class="solving-button"
          v-else-if="mode === 'solving'"
          mode="solving"
          :key="index"
          :index="index"
          @click.native="clickAnswer(index)"
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
  props: ['mode', 'currentIdx', 'setColor'],
  data: function () {
    return {
      answerColor: ["#ffdc46", "#ff85b1", "#7cb1ff", "#aaed81"],
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
  watch: {
    setColor: function () {
      for (let i = 0; i < 4; i++) {
        document.getElementsByClassName('solving-button')[i].style.backgroundColor = this.answerColor[i];
      }
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setOrderingAnswer"]),
    changeCurrentIdx: function () {
      this.$emit('change-current-idx');
    },
    clickAnswer: function (idx) {
      document.getElementsByClassName('solving-button')[idx-1].style.backgroundColor = "gray";
      this.$emit('click-answer', idx);
    }
  },
}
</script>

<style scoped>
#ordering-container {
  display: flex;
  width: 100%;
  height: 40%;
  margin-top: 20px;
}
</style>