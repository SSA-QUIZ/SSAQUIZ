<template>
  <div id="tf-choice">
    <TrueFalseButton @click.native="clickButton(['0', username, quizIndex])" mode="True" class="tf"/>
    <TrueFalseButton @click.native="clickButton(['1', username, quizIndex])" mode="False" class="tf"/>
  </div>
</template>

<script>
import TrueFalseButton from '@/components/QuizCreator/TrueFalseButton.vue';
import { mapState } from 'vuex';
export default {
  name: "TFChoice",
  props: [
    'height'
  ],
  components: {
    TrueFalseButton,
  },
  created: function () {
    this.setStyle();
  },
  computed: {
    ...mapState("CommonStore", ["isStudent"]),
    ...mapState("PlayQuizStore", ["username", "quizIndex"]),
  },
  methods: {
    setStyle: function () {
      if (this.height != undefined) this.style += "height: " + this.height + "%;\n";
    },
    clickButton: function (data) {
      if (this.isStudent) {
        this.$emit('click-button', data);
      }
    },
  }
}
</script>

<style scoped>
#tf-choice {
  display: flex;
  height: 100%;
}

@media (max-width: 700px) {
	#tf-choice {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .tf {
    display: block;
    width: 100% !important;
    height: 100% !important;
  }
}
</style>