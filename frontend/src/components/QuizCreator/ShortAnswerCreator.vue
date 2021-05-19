<template>
  <div id="short-answer-creator">
    <div id="short-answer-creator__question-box">
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
    <div id="short-answer-creator__answer-box">
      <span>정답</span>
      <TextBox message="" @change-input="changeAnswer" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import $ from 'jquery';
import TextBox from '@/components/Form/TextBox.vue';
import { mapActions, mapState } from 'vuex';
export default {
  name: "ShortAnswerCreator",
  data: function () {
    return {
      inputQuestion: '',
      image: File,
    }
  },
  components: {
    TextBox
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
    ...mapActions("CreateQuizStore", ["setSlideQuestion", "setAnswer", "setImageData"]),
    setImage: function (data) {
      let val = [this.selectedSlideIndex, data]
      this.setImageData(val);
    },
    changeAnswer: function (data) {
      this.inputAnswer = data;
      let val = [this.selectedSlideIndex, this.inputAnswer];
      this.setAnswer(val);
    },
    changeQuestion: function (e) {
      this.inputQuestion = e.target.value;
      let val = [this.selectedSlideIndex, this.inputQuestion];
      this.setSlideQuestion(val);
    },
    setSize: function (flag) {
      let element = document.getElementsByClassName('creator__input-question')[0];
      console.log(element, this.selectedSlideIndex)
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

<style scoped>
#short-answer-creator {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#short-answer-creator__question-box {
  width: 100%;
  height: 50%;
  border-radius: 30px;
  background-color: #c4c4c4;
  display: flex;
  justify-content: center;
  align-items: center;
}

#short-answer-creator__input-question {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  height: 40px;
  width: 100%;
  margin-bottom: 40px;
  text-align: center;
  outline: none;
}

#image__span {
  display: flex;
  justify-content: center;
  align-items: center;
}

.camera-img {
  text-align: center;
  font-size: 100px;
}

#short-answer-creator__answer-box {
  width: 100%;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

#short-answer-creator__answer-box span {
  font-family: 'Nanum Pen Script', cursive;
  font-size: 72px;
  margin-right: 10%;
}

input.image-input {
  display: none;
  cursor: pointer;
  opacity: 0;
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

</style>