<template>
  <v-chip
    class="nickname-button"
    large
  >
    <span class="nickname-text">{{student.n}}</span>
  </v-chip>
</template>

<script>
var colorList = [
  "#FFE059",
  "#FF89B4",
  "#ABD0F2",
  "#D98EF8",
  "#A1DBF3",
  "#B9BDFD",
  "#B6F1A1",
  "#FFC178",
  '#545DE3'
];

export default {
  name: "NicknameButton",
  props: ['student', 'index'],
  mounted: function () {
    document.getElementsByClassName("nickname-button")[this.index].style.backgroundColor = colorList[parseInt(this.student.c)]
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