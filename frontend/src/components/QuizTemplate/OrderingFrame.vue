<template>
  <div id="ordering-frame-container">
    <template v-for="index in 4">
      <QuizButton  
        mode="orderingFrame" 
        :key="index"
        :index="index"
        answer="끌어서 놓으세요" 
        color="#cfcfcf"
      />
    </template>
  </div>
</template>

<script>
import QuizButton from '@/components/common/QuizButton.vue';

export default {
  name: 'OrderingFrame',
  components: {
    QuizButton
  },
}
</script>

<style>
#ordering-frame-container {
  display: flex;
  width: 100%;
}
</style>