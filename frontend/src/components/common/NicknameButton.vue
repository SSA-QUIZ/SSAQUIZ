<template>
  <v-chip
    class="nickname-button"
    large
  >
    <span class="nickname-text">{{student.nickname}}</span>
  </v-chip>
</template>

<script>
export default {
  name: "NicknameButton",
  props: ['student', 'index'],
  mounted: function () {
    document.getElementsByClassName("nickname-button")[this.index].style.backgroundColor = this.student.color
  },
}
</script>
<style scoped>
.nickname-button {
  margin: 1vh;
  vertical-align: baseline;
}

.nickname-text {
  font-family: 'Jua';
  font-size: 30px;
}

@media (max-width: 700px) {
  .nickname-text {
    font-family: 'Jua';
    font-size: 18px;
  }
}

</style>