<template>
  <div id="multipleChoice">
    <div class="choice_row">
      <QuizButton color="#ffdc46" icon="fas fa-cat" class="choice" />
      <QuizButton color="#ff85b1" icon="fas fa-leaf" class="choice" />
    </div>
    <div class="choice_row">
      <QuizButton color="#7cb1ff" icon="fa fa-car" class="choice" />
      <QuizButton color="#aaed81" icon="fas fa-pills" class="choice" />
    </div>
  </div>
</template>

<script>
import QuizButton from '@/components/common/QuizButton.vue';
export default {
  name: 'MultipleChoice',
  components: {
    QuizButton
  },
  props: [
    'choice'
  ]
}
</script>
<style>

</style>