<template>
  <div id="short-answer">
    <TextBox/>
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