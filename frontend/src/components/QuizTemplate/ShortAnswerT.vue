<template>
  <div id="short-answer-t">
    <div class="row">
      <img v-if="image==='default'" src="../../assets/images/Default.png" />
      <img v-else :src="image" />
    </div>
    <div class="row">
      <span>{{ title }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShortAnswerT",
  props: [
    "title",
    "image"
  ],
}
</script>

<style>
#short-answer-t {
  width: 100%;
  height: 55%;
}
#short-answer-t .row {
  display: flex;
  width: 100%;
  justify-content: center;
}

#short-answer-t img {
  width: 40vh;
  height: 40vh;
}

#short-answer-t .row span {
  margin-top: 5vw;
  font-size: 3rem;
  font-family: Jua;
}
</style>