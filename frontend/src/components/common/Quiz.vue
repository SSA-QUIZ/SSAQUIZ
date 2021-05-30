<template>
  <div id="quiz-container">
    <div id="quiz-content">
      <img id="quiz-image" v-if="image==='default'" src="../../assets/images/Default.png"/>
      <img id="quiz-image" v-else :src="image" />
      <span id="quiz-text">{{ title }}</span>
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
  }
}
</script>

<style scoped>
#quiz-container {
  width: 100%;
  height: 50%;
  display: flex;
  flex-flow: column;
}

#quiz-content {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  font-size: 50px;
  font-family: 'Jua';
  height: 100%;
}

#quiz-image {
  max-width: 100%;
  max-height: 75%;
}

#quiz-text {
  text-align: center;
  word-break: keep-all;
  font-size: 0.8em;
}

</style>