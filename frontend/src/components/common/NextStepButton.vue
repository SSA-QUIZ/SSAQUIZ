<template>
  <button class="step-button">
    <img v-if="dark" src="@/assets/images/arrow-black.png">
    <img v-else src="@/assets/images/arrow.png">
  </button>
</template>

<script>
export default {
  name: "NextStepButton",
  props: ["dark"]
}
</script>

<style>
.step-button {
  height: 100px;
}

.step-button:hover {
  transition: all 0.25s linear;
  transform: scale(1.2);
}

</style>