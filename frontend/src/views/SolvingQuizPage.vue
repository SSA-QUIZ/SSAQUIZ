<template>
  <div style="background-color: #f2f2f2; height: 100%; position: relative;">
    <Header mode="userInfo" nickname="[서울_3반_최나현]" score="950" />
    <ProgressBar index="2" all="12" />
    <MultipleChoice :choice="choice" v-show="type == 'multiple' || type == 'vote'" />
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
import MultipleChoice from '@/components/QuizTemplate/MultipleChoice.vue';

export default {
  name: 'SolvingQuizPage',
  components: {
    Header,
    ProgressBar,
    MultipleChoice,
  },
  data: function () {
    return {
      type: 'multiple',
      choice: ['문어', '복어', '오징어', '대게'],
    }
  }
}
</script>

<style>

</style>