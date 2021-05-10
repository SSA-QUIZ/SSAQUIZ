<template>
  <div id="multiple-choice-creator">
    <div id="multiple-choice-creator__question-box">
      <input type="text" 
        placeholder="문제를 입력해주세요." 
        :value="question" 
        @input="changeQuestion" 
        id="multiple-choice-creator__input-question"
      >
      <input type="file" name="file" class="image-input">
      <span v-if="image == ''" class="image-upload-btn" onclick="document.all.file.click();">
        <i class="fas fa-camera camera-img"></i>
      </span>
      <span v-else id="image__span"><img class="image image-input" :src="image" width="150px;" /></span>
    </div>
    <div>
      <div class="choice-row">
        <CreatorQuizButton :slideIndex=slideIndex :index=0 height="225px" margin="0 5px 0 0" color="#ffdc46" icon="fas fa-cat" class="choice" />
        <CreatorQuizButton :slideIndex=slideIndex :index=1 height="225px" margin="0 0 0 5px" color="#ff85b1" icon="fas fa-leaf" class="choice" />
      </div>
      <div class="choice-row">
        <CreatorQuizButton :slideIndex=slideIndex :index=2 height="225px" margin="0 5px 0 0" color="#7cb1ff" icon="fa fa-car" class="choice" />
        <CreatorQuizButton :slideIndex=slideIndex :index=3 height="225px" margin="0 0 0 5px" color="#aaed81" icon="fas fa-pills" class="choice" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import $ from 'jquery';
import { mapActions, mapState } from 'vuex';
import CreatorQuizButton from '@/components/QuizCreator/CreatorQuizButton.vue';

export default {
  name: "MultipleChoiceCreator",
  props: [
    "slideIndex"
  ],
  components: {
    CreatorQuizButton
  },
  data: function () {
    return {
      question: "",
      defaultImg: "",
      image: File
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
  created: function () {
    let data = this.quizData.slideList[this.slideIndex];
    this.choices = data.answerList;
    this.question = data.question;
    this.image = data.imagePath;
  },
  computed: {
    ...mapState("CreateQuizStore", ['quizData']),
  },
  watch: {
    slideIndex: function (newVal) {
      let data = this.quizData.slideList[newVal];
      this.question = data.question;
      this.choices = data.answerList;
      this.image = data.imagePath;
    }
  },
  methods: {
    ...mapActions("CreateQuizStore", ["setSlideQuestion", "setImageData"]),
    changeQuestion: function (e) {
      this.question = e.target.value;
      let val = [this.slideIndex, this.question];
      this.setSlideQuestion(val);
    },
    setImage: function (data) {
      let val = [this.slideIndex, data]
      this.setImageData(val);
    }
  },
}
</script>

<style>
#multiple-choice-creator {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#multiple-choice-creator__question-box {
  width: 100%;
  height: 55%;
  border-radius: 30px;
  background-color: #c4c4c4;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#multiple-choice-creator__input-question {
  font-family: 'Nanum Pen Script', cursive;
  font-weight: bold;
  font-size: 38px;
  height: 40px;
  width: 100%;
  margin-bottom: 40px;
  text-align: center;
}

#multiple-choice-creator__input-question:focus, #multiple-choice-creator__input-question:active {
	outline: none;
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

.camera-img {
  text-align: center;
  font-size: 100px;
}

.choice-row {
	display: flex;
	width: 100%;
	margin: 1% 0% 0% 0%;
}

</style>