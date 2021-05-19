<template>
  <div>
    <div class="settings__option" v-for="(option, index) in optionList" :key="index">
      <input type="radio" name="radioBox" :id="option.id" v-if="index == 0" checked @click="selectOption(index)">
      <input type="radio" name="radioBox" :id="option.id" v-else @click="selectOption(index)">
      <label :for="option.id">{{ option.name }}</label>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'RadioBox',
  props: [
    'title',
    'optionList'
  ],
  computed: {
    ...mapState("CreateQuizStore", ['quizData', 'selectedSlideIndex']),
    quizDataLength: function () {
      if (this.quizData.slideList == undefined) {
        return 0;
      }
      return this.quizData.slideList.length;
    }
  },
  watch: {
    selectedSlideIndex: function () {
      this.getOption(this.selectedSlideIndex);
    },
    quizDataLength: function () {
      if (this.quizDataLength == 0) {
        document.getElementById(`${this.optionList[0].id}`).checked = true;
      }
      else {
        this.getOption(this.selectedSlideIndex);
      }
    }
  },
  created: function () {
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setOptions"]),
    selectOption: function (idx) {
      let val = [this.title, this.selectedSlideIndex, idx];
      this.setOptions(val);
      this.$emit("radio-option", idx);
    },
    getOption: function (idx) {
      let index = this.quizData.slideList[idx][this.title];
      document.getElementById(`${this.optionList[index].id}`).checked = true;
    }
  }
}
</script>

<style scoped>

</style>