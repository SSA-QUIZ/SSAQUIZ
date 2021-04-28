<template>
  <div id="quiz-container">
    <div class="quiz-content">
      <img width="30%" src="../../assets/images/Default.png" />
    </div>
    <div class="quiz-content">
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
  height: 55%;
  display: flex;
  flex-flow: column;
}
.quiz-content {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  font-size: 5rem;
  font-family: Jua;
  height: 60%;
}
@media (max-width: 700px) {
  .quiz-content {
    font-size: 2rem;
  }
}
</style>