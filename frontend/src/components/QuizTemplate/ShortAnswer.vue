<template>
  <div id="short-answer">
    <TextBox message="답안을 작성해주세요." @change-input="setAnswer"/>
    <div class="submit">
      <InputButton text="제출하기"/>
    </div>
  </div>
</template>

<script>
import TextBox from '@/components/Form/TextBox.vue';
import InputButton from '@/components/common/InputButton.vue';
export default {
  name: "ShortAnswer",
  components: {
    TextBox,
    InputButton,
  },
  data: function () {
    return {
      answer: ''
    }
  },
  methods: {
    setAnswer: function (data) {
      this.answer = data
    }
  }
}
</script>

<style>
#short-answer {
	width: 95%;
	flex-direction: column;
  margin-top: 1.5vh;
	padding: 3vh;
  height: 70%;
}

#short-answer .submit {
  display: flex;
  justify-content: end;

  position: absolute;
  bottom: 8%;
  right: 5%;
}

@media (max-width: 480px) {

  #short-answer .submit {
    display: flex;
    justify-content: end;
    position: absolute;
    top: 48%;
    left: 9%;
  }
}
</style>