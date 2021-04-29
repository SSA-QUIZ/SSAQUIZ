<template>
  <div class="dialog-container">
    <div id="dialog-container">
      <div id="dialog-content">
        <p style="font-size: 4rem;">{{ emoticon }}</p>
        <span v-html="content"></span>
        <InputBox class="input-box" type="text"/>
      </div>
      <footer id="dialog-footer">
        <button style="width: 30%; color: #C3346A;" @click="$emit('close')">취소</button>
        <button style="width: 30%; color: #454995;" @click="$emit('accept')">확인</button>
      </footer>
    </div>
    <div class="overlay" @click="$emit('close')"></div>
  </div>
</template>

<script>
import InputBox from '@/components/common/InputBox.vue';

export default {
  name: 'Dialog',
  components: {
    InputBox
  },
  props: [
    'content',
    'emoticon'
  ]
}
</script>

<style>
.overlay, .dialog-container {
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
#dialog-container {
  width: auto;
  max-width: 60%;
  min-width: 30%;
  height: auto;
  z-index: 10;
  opacity: 1;
  position: absolute;
  background-color: white;
  font-family: Jua;
  border-radius: 15px;
}
#dialog-content {
  font-size: 1.8rem;
  text-align: center;
  padding: 7% 4%;
}
#dialog-footer {
  display: flex;
  justify-content: space-around;
  font-size: 2rem;
  text-align: center;
  padding: 0% 15% 5% 15%;
}
.input-box {
  border: 1px solid black;
  font-family: Jua;
}
</style>