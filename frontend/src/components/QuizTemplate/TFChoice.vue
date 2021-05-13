<template>
  <div id="tf-choice">
    <TrueFalseButton mode="True" width="50%" height=height fontSize="100px" color="#7CB1FF" class="tf"/>
    <TrueFalseButton mode="False" width="50%" height=height fontSize="100px" color="#ff85b1" class="tf"/>
  </div>
</template>

<script>
import TrueFalseButton from '@/components/QuizCreator/TrueFalseButton.vue';
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
  methods: {
    setStyle: function () {
      if (this.height != undefined) this.style += "height: " + this.height + "%;\n";
    }
  }
}
</script>

<style>
#tf-choice {
  display: flex;
  height: 60%;
  margin: 5%;
}

@media (max-width: 700px) {
	#tf-choice {
    height: 70%;
    margin: 5%;

    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .tf {
    display: block;
    width: 90% !important;
    height: 50% !important;
  }
}
</style>