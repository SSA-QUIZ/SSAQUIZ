<template>
  <div>
    <span class="select-title">{{ title }}</span>
    <select :id="title" name="select-box" @click="selectOption">
      <option v-for="(option, idx) in optionList" :key="idx" :index="option.value">
        {{ option.name }}
      </option>
    </select>
  </div>
</template>

<script>
export default {
  name: 'Select',
  props: [
    'title',
    'optionList',
    'index'
  ],
  watch: {
    index: function () {
      let select = document.getElementById(`${this.title}`);
      select.options[this.index].selected = true;
    }
  },
  methods: {
    selectOption: function () {
      let select = document.getElementById(`${this.title}`);
      this.$emit('select-option', select.selectedIndex);
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