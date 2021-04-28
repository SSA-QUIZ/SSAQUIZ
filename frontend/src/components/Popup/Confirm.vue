<template>
  <div class="confirm-container">
    <div id="confirm-container">
      <div id="confirm-content">
        <p style="font-size: 4rem;">{{ emoticon }}</p>
        <span v-html="content"></span>
      </div>
      <footer id="confirm-footer">
        <button style="width: 30%; color: #C3346A;" @click="$emit('close')">아니오</button>
        <button style="width: 30%; color: #454995;" @click="$emit('accept')">예</button>
      </footer>
    </div>
    <div class="overlay" @click="$emit('close')"></div>
  </div>
</template>

<script>
export default {
  name: 'Confirm',
  props: [
    'content',
    'emoticon'
  ]
}
</script>

<style>
.overlay, .confirm-container {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.overlay {
  opacity: 0.5;
  background-color: black;
}
#confirm-container {
  width: auto;
  max-width: 60%;
  min-width: 40%;
  height: auto;
  z-index: 10;
  opacity: 1;
  position: absolute;
  background-color: white;
  font-family: Jua;
  border-radius: 15px;
}
#confirm-content {
  font-size: 1.8rem;
  text-align: center;
  padding: 7% 4%;
}
#confirm-footer {
  display: flex;
  justify-content: space-between;
  font-size: 2rem;
  text-align: center;
  padding: 0% 15% 5% 15%;
}
</style>