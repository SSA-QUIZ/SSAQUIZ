<template>
  <div>
    <div id="component-title">confirm</div>
    <button @click="openConfirm = true" style="background-color: skyblue;">confirm 열기</button>
    <Confirm 
      emoticon="😭"
      content="정말로 탈퇴하시겠어요?" 
      @close="openConfirm = false" 
      @accept="openConfirm = false" 
      v-if="openConfirm" 
    />
    <div id="component-title">Dialog</div>
    <button @click="openDialog = true" style="background-color: pink;">dialog 열기</button>
      <Dialog 
        emoticon="🤓"
        content="문제집의 이름을 작성해주세요." 
        @close="openDialog = false" 
        @accept="openDialog = false" 
        v-if="openDialog" 
      />
  </div>
</template>

<script>
import Confirm from '@/components/Popup/Confirm.vue';
import Dialog from '@/components/Popup/Dialog.vue';

export default {
  name: 'ComponentsPage',
  components: {
    Confirm,
    Dialog
  },
  data: function () {
    return {
      openConfirm: false,
      openDialog: false,
      contentsArray: ["최나현의 강아지 이름은?", "최나현의 생일은?", "최나현의 ??"]
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