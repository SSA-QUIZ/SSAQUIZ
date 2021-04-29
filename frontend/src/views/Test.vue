<template>
  <div id="test">
    <div class="profile">
      <div class="img-wrapper">
        <img class="image file-input" src="https://ssaquiz.s3.ap-northeast-2.amazonaws.com/2281a4ad-cc38-4099-b483-862639c71869.jpg">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Test"
}
</script>

<style scoped>
#test {
  background-color: black;
}
/* 프로필 이미지 영역 */
.profile {
  height:200px;
  position:relative;
  max-width: 250px;
  margin: auto;
}

/* 프로필 이미지 */
.img-wrapper {
  width:195px;
  height:195px;
  position:absolute;
  transform:translate(17%,-20%);
  border-radius: 70%;
  overflow: hidden;
}

.img-wrapper img {
  box-shadow: 0 1px 10px rgba(0,0,0,0.4);
  width:inherit;
  height:inherit;
}
</style>