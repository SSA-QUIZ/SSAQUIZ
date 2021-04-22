<template>
  <div id="progress_bar">
    <div id="progress"><h1>{{ index }} / {{ all }}</h1></div>
    <div id="progress_all">
      <div id="progress_now"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProgressBar',
  props: [
    'index',
    'all'
  ],
  data: function () {
    return {
      width: this.index / this.all * 100
    }
  },
  mounted: function () {
    this.changeWidth();
  },
  watch: {
    index: function () {
      this.changeWidth();
    }
  },
  methods: {
    changeWidth: function () {
      this.width = this.index / this.all * 100;
      document.getElementById("progress_now").style.width = this.width + "%";
    }
  },
}
</script>

<style>

</style>