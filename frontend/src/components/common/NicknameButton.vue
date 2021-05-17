<template>
  <v-chip
    class="nickname-button"
    large
  >
    {{student.nickname}}
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
<style>
.nickname-button {
  margin: 1vh;
  vertical-align: baseline;
}

.v-chip__content {
  font-family: Jua;
  font-size: 30px;
}

@media (max-width: 700px) {
  .v-chip__content {
    font-family: Jua;
    font-size: 25px;
  }
}

</style>