<template>
  <div id="ordering-container">
    <template v-for="index in 4">
      <CreatorOrderingButton 
        v-if="mode === 'create'"
        :key="index"
        :index="index"
      />
      <OrderingButton
        v-else
        mode="ordering"
        :key="index"
        :index="index"
        :buttonId="buttonId + index"
      />
    </template>
  </div>
</template>

<script>
import OrderingButton from '@/components/common/OrderingButton.vue';
import CreatorOrderingButton from '@/components/QuizCreator/CreatorOrderingButton.vue';
import { mapState } from 'vuex';

export default {
  name: "Ordering",
  components: {
    OrderingButton,
    CreatorOrderingButton
  },
  props: ['mode'],
  data: function () {
    return {
      buttonId: ''
    }
  },
  created: function () {
    this.buttonId = 'button'
  },
  computed: {
    ...mapState("CreateQuizStore", ["quizData", "selectedSlideIndex"]),
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