<template>
  <v-chip
    class="nicknameButton"
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
    console.log(this.student)
    document.getElementsByClassName("nicknameButton")[this.index].style.backgroundColor = this.student.color
  },
}
</script>
<style>
.nicknameButton {
  margin: 1vh;
  vertical-align: baseline;
}

.v-chip__content {
  font-family: Jua;
  font-size: 30px;
}

</style>