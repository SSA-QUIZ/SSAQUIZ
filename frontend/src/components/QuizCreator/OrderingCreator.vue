<template>
  <div id="ordering-creator">
    <div id="ordering-creator__question-box">
      <input type="file" name="file" class="image-input">
      <!-- 이미지 등록 안했을 때 -->
      <span v-if="image == ''" class="image-upload-btn" onclick="document.all.file.click();">
        <i class="fas fa-camera camera-img"></i>
      </span>
      <!-- 이미지 등록했을 때 -->
      <span v-else id="image__span" class="tooltip" onclick="document.all.file.click();">
        <img class="image image-input" :src="image" width="260px;" />
        <span class="tooltip-text">이미지 변경하기</span>
      </span>

      <div id="text-container">
        <textarea 
          placeholder="문제를 입력해주세요."
          :value="question"
          @input="changeQuestion"  
          @focus="setSize(true)"
          @blur="setSize(false)"
          class="creator__input-question"
        ></textarea>
      </div>
    </div>
    <div class="ordering-creator__button">
      <button class="ordering-creator__ordering__button" @click="resetOrderingAnswer(); currentIdx=1;">초기화</button>
    </div>
    <div id="ordering-creator__ordering">
      <Ordering mode="create" :currentIdx="currentIdx" @change-current-idx="changeCurrentIdx" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import $ from 'jquery';
import { mapState, mapActions } from 'vuex';
import Ordering from '@/components/QuizTemplate/Ordering.vue';

export default {
  name: "OrderingCreator",
  components: {
    Ordering,
  },
  data: function () {
    return {
      inputQuestion: '',
      image: File,
      currentIdx: 1,
    }
  },
  mounted: function () {
    $('.image-input').change(() => {
      var newThis = document.getElementsByClassName('image-input')[0];

      // 이미지 파일을 선택하고 img 태그에 넣는다.
      $(newThis).parent().parent().find('.image');
      this.image = newThis.files[0];

      // 서버에 이미지 보내기
      const formData = new FormData();
      formData.append("file", this.image);
      const headers = { "Content-Type": "multipart/form-data" };

      axios.post("https://k4a304.p.ssafy.io/api-quiz/image", formData, headers)
        .then(res => {
          this.image = res.data.object;
          this.setImage(this.image)
        })
        .catch(err => console.log(err))
    });
  },
  updated: function () {
    let data = this.quizData.slideList[this.selectedSlideIndex];
    this.image = data.imagePath;
  },
  created: function () {
    let data = this.quizData.slideList[this.selectedSlideIndex];
    this.image = data.imagePath;
  },
  computed: {
    ...mapState("CreateQuizStore", ["quizData", "selectedSlideIndex"]),
    question: function () {
      return this.quizData["slideList"][this.selectedSlideIndex]["question"];
    },
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setSlideQuestion", "setAnswer", "setImageData", "resetOrderingAnswer"]),
    setImage: function (data) {
      let val = [this.selectedSlideIndex, data]
      this.setImageData(val);
    },
    changeQuestion: function (e) {
      this.inputQuestion = e.target.value;
      let val = [this.selectedSlideIndex, this.inputQuestion];
      this.setSlideQuestion(val);
    },
    changeCurrentIdx: function () {
      this.currentIdx++;
    },
    setSize: function (flag) {
      let element = document.getElementsByClassName('creator__input-question')[0];
      let cal;
      if (flag) {
        cal = setInterval(
          function() {
            element.style.height = '1px';
            element.style.height = (element.scrollHeight + 12) + 'px';
          }, 100);
      }
      else {
        clearInterval(cal);
      }
    } 
  }
}
</script>

<style>
#ordering-creator {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#ordering-creator__question-box {
  width: 100%;
  height: 55%;
  border-radius: 30px;
  background-color: #c4c4c4;
  display: flex;
  justify-content: center;
  align-items: center;
}

#ordering-creator__input-question {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  height: 40px;
  width: 100%;
  margin-bottom: 40px;
  text-align: center;
}

#ordering-creator__input-question:focus, #ordering-creator__input-question:active {
	outline: none;
}

#ordering-creator__ordering {
  width: 100%;
  height: 50%;
  margin-top: -1.5%;
  margin-bottom: 2%;
}

input.image-input {
  display: none;
  cursor: pointer;
  opacity: 0;
}

#image__span {
  display: flex;
  justify-content: center;
  align-items: center;
}

/* hover시 이미지 변경 가능 */
.tooltip {
  display: inline-block;
  font-weight: bold;
}

.tooltip-text {
  display: none;
  position: absolute;
  max-width: 200px;
  border: 1px solid;
  border-radius: 5px;
  padding: 5px;
  font-size: 1em;
  color: white;
}

.tooltip:hover .tooltip-text {
  display: block;
}
.camera-img {
  text-align: center;
  font-size: 100px;
}

.camera-img:hover {
  transition: all 0.25s linear;
  transform: scale(1.1);
  cursor: pointer;
}

.choice-row {
	display: flex;
	width: 100%;
	margin: 1% 0% 0% 0%;
}

.ordering-creator__ordering__button {
  width: 80px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 25px;
  font-family: Jua;
  color: white;
  background-color: #c3356a;
  border-radius: 10px;
  margin-right: 0.3%;
}
.ordering-creator__button {
  display: flex;
  justify-content: flex-end;
  margin: 1.5% 0 0 0;
}
</style>