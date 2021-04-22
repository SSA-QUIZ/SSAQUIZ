<template>
  <button><div class="button" :style="style"><i :class="icon"></i></div></button>
</template>

<script>
export default {
  name: 'QuizButton',
  props: [
    'color',
    'width',
    'height',
    'icon'
  ],
  data: function () {
    return {
      style: ''
    }
  },
  created: function () {
    this.setStyle();
  },
  methods: {
    setStyle: function () {
      if (this.color != undefined) this.style += "background-color: " + this.color + ";\n";
      if (this.width != undefined) this.style += "width: " + this.width + ";\n";
      if (this.height != undefined) this.style += "height: " + this.height + ";\n";
      console.log(this.style)
    }
  }
}
</script>

<style>

</style>