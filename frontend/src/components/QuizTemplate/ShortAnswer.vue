<template>
  <div id="short-answer">
    <TextBox message="답안을 작성해주세요." @change-input="setAnswer"/>
    <InputButton id="short-answer-button" @click.native="clickButton([answer, username, quizIndex]);" text="제출하기"/>
  </div>
</template>

<script>
import TextBox from '@/components/Form/TextBox.vue';
import InputButton from '@/components/common/InputButton.vue';
import { mapState } from 'vuex';
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
    },
    clickButton: function (data) {
      this.$emit('click-button', data);
    },
  },
  computed: {
    ...mapState("PlayQuizStore", ["username", "quizIndex"]),
  },
}
</script>

<style scoped>
#short-answer {
  height: 100%;
	width: 100%;
  display: flex;
	flex-direction: column;
  justify-content: center;
}

#short-answer-button {
  margin-top: 10px;
  width: 100%;
  height: 10%;
  font-size: 30px;
}

@media (max-width: 700px) {
  #short-answer {
    width: 100%;
    justify-content: flex-start;
  }
  #short-answer-button {
    font-size: 20px;
    font-weight: normal;
  }
}
</style>