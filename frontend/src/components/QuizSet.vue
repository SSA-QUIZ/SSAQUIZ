<template>
  <div>
    <button id="quiz-set">
      <span style="font-size: 1.8rem;">{{ quizTitle }}</span>
    </button>
    <div class="quiz-set__buttons">
      <button style="margin-right: 2%;"><i class="far fa-edit"></i></button>
      <button><i class="far fa-trash-alt"></i></button>
    </div>
  </div>

</template>

<script>
export default {
  name: 'QuizSet',
  props: [
    'quizTitle'
  ]
}
</script>

<style>
#quiz-set {
  width: 100%;
  height: 100%;
  background-color: rgb(202, 202, 202);
  border-radius: 15px;
  margin: 1%;
}
.quiz-set__buttons {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
  font-size: 3vh;
}
</style>