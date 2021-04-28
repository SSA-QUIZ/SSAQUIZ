<template>
  <div>
    <div id="component-title">confirm 창</div>
    <button @click="openConfirm = true" style="background-color: skyblue;">confirm창 열기</button>
    <Confirm 
      emoticon="😭"
      content="정말로 탈퇴하시겠어요?" 
      @close="openConfirm = false" 
      @accept="openConfirm = false" 
      v-if="openConfirm" 
    />
  </div>
</template>

<script>
import Confirm from '@/components/Popup/Confirm.vue';
export default {
  name: 'ComponentsPage',
  components: {
    Confirm
  },
  data: function () {
    return {
      openConfirm: false
    }
  }
}
</script>

<style>
#component-title {
  font-size: 2rem;
  font-family: Jua;
}
</style>