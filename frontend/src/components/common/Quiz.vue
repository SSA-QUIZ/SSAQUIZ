<template>
  <div id="quiz-container">
    <div class="quiz-content">
      <img v-if="image==='default'" src="../../assets/images/Default.png" style="max-width:25%; margin-bottom: 2%;"/>
      <img v-else :src="image" style="max-width:25%; margin-bottom: 2%;"/>
      <span>{{ title }}</span>
    </div>  
  </div>
</template>

<script>
export default {
  name: 'Quiz',
  props: [
    'title',
    'image'
  ],
  methods: {
    // getSrc: function () {
    //   return require(this.image);
    // }
  }
}
</script>

<style>
#quiz-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-flow: column;
}
.quiz-content {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  font-size: 50px;
  font-family: Jua;
  height: 100%;
}
</style>