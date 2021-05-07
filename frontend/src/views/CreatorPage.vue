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
        <ShortAnswerCreator v-if="category === '단답형'"/>
        <MultipleChoiceCreator 
          :slideIndex="selectedSlide"
          v-if="category === '4지선다'"
        />
        <div v-else>슬라이드 없음</div>
      </div>
      <div id="creator-page__settings">
        <span class="settings__title">문제 설정</span>
        <span class="settings__subtitle">제한 시간</span>
        <select name="timeLimit" id="time-limit">
          <option value="10">10초</option>
          <option value="15">15초</option>
          <option value="20">20초</option>
        </select>
        <span class="settings__subtitle">점수</span>
        <select name="scoreFactor" id="score-factor">
          <option value="1">x1</option>
          <option value="1.5">x1.5</option>
          <option value="2">x2</option>
          <option value="2.5">x2.5</option>
        </select>
        <span class="settings__subtitle">추가 옵션</span>
        <div>
          <div class="settings__option">
            <input type="radio" name="additionalOption" id="no-option" checked>
            <label for="no-option">없음</label>
          </div>
          <div class="settings__option">
            <input type="radio" name="additionalOption" id="FIFO">
            <label for="FIFO">선착순</label>
          </div>
          <div class="settings__option">
            <input type="radio" name="additionalOption" id="random-select">
            <label for="random-select">랜덤뽑기</label>
          </div>
        </div>
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
import ShortAnswerCreator from '@/components/QuizCreator/ShortAnswerCreator.vue';
import QuizSlide from '@/components/QuizCreator/QuizSlide.vue';
import QuizTypeDialog from '@/components/Popup/QuizTypeDialog.vue';
import Confirm from '@/components/Popup/Confirm.vue';
import { mapState, mapActions } from 'vuex';
// import axios from 'axios';

export default {
  name: "CreatorPage",
  components: {
    MultipleChoiceCreator,
    ShortAnswerCreator,
    QuizSlide,
    QuizTypeDialog,
    Confirm
  },
  data: function () {
    return {
      openQuizTypeDialog: false,
      openExitConfirm: false,
      selectedSlide: 0,
      category: '',
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
      console.log("slide 클릭할 때 : " + this.quizData.slideList.length)
      this.selectedSlide = idx;
      this.setSettings(idx);
    },
    exit: function () {
      this.$router.push({ name: "UserPage" });
    },
    save: function () {
    },
    setSettings: function (idx) {
      let val = this.quizData.slideList[idx];
      this.category = val.category;
      let selectTime = document.getElementById('time-limit');
      for (let i = 0; i < 3; i++) {
        if (selectTime.options[i].value == val.time) {
          selectTime.options[i].selected = true;
          break;
        }
      }
      let selectScoreFactor = document.getElementById('score-factor');
      for (let i = 0; i < 4; i++) {
        if (selectScoreFactor.options[i].value == val.scoreFactor) {
          selectScoreFactor.options[i].selected = true;
          break;
        }
      }
      if (val.type == 0) {
        document.getElementById('no-option').checked = true;
      } else if (val.type == 1) {
        document.getElementById('FIFO').checked = true;
      } else {
        document.getElementById('random-select').checked = true;
      }
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