<template>
  <div id="creator-page">
    <div id="creator-page__header">
      <button class="creator-page__header__button" @click="openExitConfirm = true">나가기</button>
      <img class="ssaquiz-logo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ">
      <button class="creator-page__header__button" @click="save">저장하기</button>
    </div>
    <div id="creator-page__body">
      <div id="creator-page__preview-wrap">
        <div id="creator-page__preview">
          <template 
            v-for="(slide, index) in quizData.slideList"
          >
            <QuizSlide
              :key="index"
              :number="index+1"
              :slide="slide"
              :isSelected="isSelectedSlide[index]"
              @click.native="selectSlide(index)"
            />
          </template>
        </div>
        <div id="slide-create-button" @click="openQuizTypeDialog = true">
          <i class="far fa-plus-square"></i>
          <span>슬라이드 추가</span>
        </div>
      </div>
      <div id="creator-page__content">
        <!-- <ShortAnswerCreator v-if="category === '단답형'"/> -->
        <MultipleChoiceCreator 
          :slideIndex="selectedSlide"
          v-if="category === '4지선다'"
        />
        <div v-else>슬라이드 없음</div>
      </div>
      <div id="creator-page__settings">
        <span class="settings__title">문제 설정</span>
        <span class="settings__subtitle">제한 시간</span>
        <SelectBox
          title="timeLimit"
          :slideIndex="selectedSlide"
          :index="timeLimitIndex"
          :optionList="timeLimitList" 
          @select-option="val => timeLimitIndex = val" />
        <span class="settings__subtitle">점수</span>
        <SelectBox 
          title="scoreFactor"
          :slideIndex="selectedSlide"
          :index="scoreFactorIndex"
          :optionList="scoreFactorList"
          @select-option="val => scoreFactorIndex = val" />
        <span class="settings__subtitle">추가 옵션</span>
        <RadioBox 
          title="type"
          :slideIndex="selectedSlide"
          :index="typeIndex"
          :optionList="typeList"
          @radio-option="val => typeIndex = val"/>
      </div>
    </div>
    <Confirm 
      emoticon="🤔"
      content="작업한 내용이 저장되지 않았습니다.<br>정말로 나가시겠습니까?" 
      @close="openExitConfirm = false" 
      @accept="openExitConfirm = false; exit();"
      v-if="openExitConfirm" 
    />
    <QuizTypeDialog
      v-if="openQuizTypeDialog"
      @close="openQuizTypeDialog = false" 
    />
  </div>
</template>

<script>
import MultipleChoiceCreator from '@/components/QuizCreator/MultipleChoiceCreator.vue';
// import ShortAnswerCreator from '@/components/QuizCreator/ShortAnswerCreator.vue';
import QuizSlide from '@/components/QuizCreator/QuizSlide.vue';
import QuizTypeDialog from '@/components/Popup/QuizTypeDialog.vue';
import Confirm from '@/components/Popup/Confirm.vue';
import SelectBox from '@/components/common/SelectBox.vue';
import RadioBox from '@/components/common/RadioBox.vue';
import { mapState, mapActions } from 'vuex';
// import axios from 'axios';

export default {
  name: "CreatorPage",
  components: {
    MultipleChoiceCreator,
    // ShortAnswerCreator,
    QuizSlide,
    QuizTypeDialog,
    Confirm,
    SelectBox,
    RadioBox
  },
  data: function () {
    return {
      openQuizTypeDialog: false,
      openExitConfirm: false,
      selectedSlide: 0,
      category: '',

      isSelectedSlide: [],

      scoreFactorIndex: 0,
      timeLimitIndex: 0,
      typeIndex: 0,

      timeLimitList: [
        {"name" :"10초", "value": 10},
        {"name" :"15초", "value": 15},
        {"name" :"20초", "value": 20},
      ],
      scoreFactorList: [
        {"name" :"x1", "value": 1},
        {"name" :"x1.5", "value": 1.5},
        {"name" :"x2", "value": 2},
      ],
      typeList: [
        {"id": "none" ,"name" :"없음"},
        {"id": "FIFO" ,"name" :"선착순"},
        {"id": "random" ,"name" :"랜덤뽑기"},
      ],
    }
  },
  created: function () {
    this.getQuizData(this.$route.params.workbookId)
      .then(res=>{
        console.log(res)
        if (this.quizData.slideList.length == 0)
          return "none";
        else this.selectSlide(0);
      })
      .catch(err=>console.log(err))
  },
  computed: {
    ...mapState("CreateQuizStore", ['quizData']),
  },
  methods: {    
    ...mapActions("CreateQuizStore", [
      "addQuiz", "getQuizData", "resetQuizData"
    ]),
    selectSlide: function (idx) {
      let val = this.quizData.slideList[idx];
      this.isSelectedSlide = 
        Array.from({length: this.quizData.slideList.length}, () => false);
      this.isSelectedSlide[idx] = true;
      this.category = val.category;
      this.timeLimitIndex = val.time;
      this.scoreFactorIndex = val.scoreFactor;
      this.typeIndex = val.type;
      this.selectedSlide = idx;
    },
    exit: function () {
      this.$router.push({ name: "UserPage" });
    },
    save: function () {
      console.log(this.quizData)
    },
  },
}
</script>

<style>
#creator-page {
  height: 100%;
  background-color: #f2f2f2;
  padding: 0 10px 0 10px;
}

#creator-page__header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 13vh;
}

#creator-page__header .ssaquiz-logo {
  height: 100px;
  margin-bottom: 0;
}

#creator-page__header .creator-page__header__button {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  width: 164px;
  height: 78px;
  border-radius: 30px;
}

#creator-page__header .creator-page__header__button:nth-child(1) {
  background-color: #D0A9E1;
}

#creator-page__header .creator-page__header__button:nth-child(3) {
  background-color: #F1DD83;
}

#creator-page__body {
  display: flex;
  flex-direction: row;
  height: 85vh;
}

#creator-page__preview-wrap {
  margin: 5px 0 5px 0;
  padding: 5px 0 0 0;
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border: 4px solid #9B9797;
  box-sizing: border-box;
  border-radius: 15px;
}

#creator-page__preview {
  overflow: auto;

  width: 100%;
  height: 90%;

}

#slide-create-button {
  width: 70%;
  height: 10%;
  margin: 10px;
  background-color: #989DED;
  border-radius: 15px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'Nanum Pen Script', cursive;
  font-size: 30px;
  cursor: pointer;
}

#slide-create-button span {
  margin-left: 10px;
}

#creator-page__content {
  flex: 4;
  margin: 0 10px 0 10px;
  padding: 5px;
}

#creator-page__settings {
  margin: 5px 0 5px 0;
  padding: 5px 0 0 10px;
  flex: 1;
  border: 4px solid #9B9797;
  box-sizing: border-box;
  border-radius: 15px;
}

.settings__title {
  display: block;
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  padding-bottom: 10px;
}

.settings__subtitle {
  display: block;
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 25px;
}

.settings__option {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 20px;
  display: flex;
  align-items: center;
}

.settings__option label {
  margin-left: 10px;
}

.settings__option input[type="radio"], .settings__option input[type="radio"]:checked {
  appearance: none;
  width: 20px;
  height: 20px;
  border-radius: 100%;
}

.settings__option input[type="radio"] {
  background-color: #ffffff;
  border: 2px solid #c4c4c4;
}

.settings__option input[type="radio"]:checked {
  background-color: #c4c4c4;
  outline: none;
}

select {
  width: 200px;
  padding: 5px 10px;
  background-color: #c4c4c4;
  font-family: 'Nanum Pen Script', cursive;
  font-size: 25px;
  border-radius: 15px;
  background: url('~@/assets/images/caret-down.png') #c4c4c4 no-repeat 95% 50%;
  background-size: 8%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  outline: none;
}

select::-ms-expand {
    display: none;
}
</style>