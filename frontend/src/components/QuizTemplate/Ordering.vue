<template>
  <div id="ordering-container">
    <template v-for="(answer, index) in answerData">
      <QuizButton  
        mode="ordering" 
        :key="index"
        :answer="answerData[index].answer" 
        :icon="answerData[index].icon"
        :color="answerData[index].color"
      />
    </template>
  </div>
</template>

<script>
import QuizButton from '@/components/common/QuizButton.vue';

export default {
  name: 'Ordering',
  components: {
    QuizButton
  },
  props: ['answerData'],
  data: function () {
    return {
    }
  }
}
</script>

<style>
#ordering-container {
  display: flex;
  width: 100%;
}
</style>