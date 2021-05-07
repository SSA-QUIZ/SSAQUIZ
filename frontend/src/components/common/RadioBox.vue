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
import { mapActions } from 'vuex';

export default {
  name: 'RadioBox',
  props: [
    'title',
    'slideIndex',
    'index',
    'optionList'
  ],
  watch: {
    index: function () {
      document.getElementById(`${this.optionList[this.index].id}`).checked = true;
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setOptions"]),
    selectOption: function (index) {
      let val = [this.title, this.slideIndex, index];
      this.setOptions(val);
      this.$emit("radio-option", index);
    }
  }
}
</script>

<style>

</style>