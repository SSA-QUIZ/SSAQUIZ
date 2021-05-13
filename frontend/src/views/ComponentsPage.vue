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
    <div id="component-title">QuizTypeDialog</div>
    <button @click="openQuizTypeDialog = true" style="background-color: yellow;">quiztypedialog 열기</button>
    <QuizTypeDialog
      v-if="openQuizTypeDialog"
      @close="openQuizTypeDialog = false"
    />
    <div id="component-title">Header</div>
    <Header mode="playQuiz" second="30" />
    <div id="component-title">Ordering</div>
    <Ordering :answerData="answerData" />
    <OrderingFrame />
    <TrueFalseButton mode="True" width="50%" height="10%" fontSize="100px" color="#7CB1FF" class="choice" />
    <TrueFalseButton mode="False" width="50%" height="10%" fontSize="100px" color="#FF85B1" class="choice" />
  </div>
</template>

<script>
import Confirm from '@/components/Popup/Confirm.vue';
import Dialog from '@/components/Popup/Dialog.vue';
import QuizTypeDialog from '@/components/Popup/QuizTypeDialog.vue';
import Header from '@/components/common/Header.vue';
import Ordering from '@/components/QuizTemplate/Ordering.vue';
import OrderingFrame from '@/components/QuizTemplate/OrderingFrame.vue';
import TrueFalseButton from '@/components/QuizCreator/TrueFalseButton.vue';

export default {
  name: 'ComponentsPage',
  components: {
    Confirm,
    Dialog,
    QuizTypeDialog,
    Header,
    Ordering,
    OrderingFrame,
    TrueFalseButton,
  },
  data: function () {
    return {
      openConfirm: false,
      openDialog: false,
      openQuizTypeDialog: false,
      contentsArray: ["최나현의 강아지 이름은?", "최나현의 생일은?", "최나현의 ??"],
      answerData: [
        {
          "answer": "강채원",
          "icon": "fas fa-cat",
          "color": "#ffdc46"
        },
        {
          "answer": "김성진",
          "icon": "fas fa-leaf",
          "color": "#ff85b1"
        },
        {
          "answer": "장주빈",
          "icon": "fa fa-car",
          "color": "#7cb1ff"
        },
        {
          "answer": "최나현",
          "icon": "fas fa-pills",
          "color": "#aaed81"
        }
      ]
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