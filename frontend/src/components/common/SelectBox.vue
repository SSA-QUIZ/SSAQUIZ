<template>
  <div>
    <select :id="title" name="select-box" @click="selectOption">
      <option v-for="(option, idx) in optionList" :key="idx" :index="option.value">
        {{ option.name }}
      </option>
    </select>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: 'SelectBox',
  props: [
    'title',
    'optionList',
  ],
  computed: {
    ...mapState("CreateQuizStore", ['quizData', 'selectedSlideIndex']),
    quizDataLength: function () {
      if (this.quizData.slideList === undefined) {
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
      if (this.quizData.slideList.length == 0) {
        let select = document.getElementById(`${this.title}`);
        select.options[0].selected = true;
      }
      else {
        this.getOption(this.selectedSlideIndex);
      }
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setOptions"]),
    selectOption: function () {
      let select = document.getElementById(`${this.title}`);
      this.$emit('select-option', select.selectedIndex);
      let val = [this.title, this.selectedSlideIndex, select.selectedIndex];
      this.setOptions(val);
    },
    getOption: function (idx) {
      let select = document.getElementById(`${this.title}`);
      let index = this.quizData.slideList[idx][this.title];
      select.options[index].selected = true;
    }
  }
}
</script>

<style>
.select-title {
  display: block;
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 25px;
}

select {
  width: 200px;
  padding: 5px 10px;
  background-color: #c4c4c4;
  font-family: 'Nanum Pen Script', cursive;
  font-size: 25px;
  border-radius: 15px;
  background: url('~@/assets/images/caret-down.png') #c4c4c4 no-repeat 95% 50%;
  background-size: 8%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  outline: none;
}

select::-ms-expand {
    display: none;
}
</style>