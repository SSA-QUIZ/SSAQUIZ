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
  position: absolute;
  z-index: 2;
  right: 5%;
  top: 43%;
  z-index: 2;
}

@media (max-width: 1100px) {
  .step-button {
    position: absolute;
    z-index: 2;
    right: 5%;
    top: 87%;
    z-index: 2;
  }
}

</style>