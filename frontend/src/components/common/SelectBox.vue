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
import { mapActions } from 'vuex';

export default {
  name: 'SelectBox',
  props: [
    'title',
    'slideIndex',
    'index',
    'optionList',
  ],
  watch: {
    index: function () {
      let select = document.getElementById(`${this.title}`);
      select.options[this.index].selected = true;
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setOptions"]),
    selectOption: function () {
      let select = document.getElementById(`${this.title}`);
      this.$emit('select-option', select.selectedIndex);
      let val = [this.title, this.slideIndex, select.selectedIndex];
      this.setOptions(val);
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