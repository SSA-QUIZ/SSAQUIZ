<template>
  <button class="step-button"><img src="@/assets/images/arrow.png"></button>
</template>

<script>
export default {
  name: "NextStepButton"
}
</script>

<style>
.step-button {
  position: absolute;
  z-index: 2;
  right: 10%;
  top: 43%;
}

.step-button:hover {
  color: blue;
}
</style>