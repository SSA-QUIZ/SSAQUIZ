<template>
  <div id="await">
    <!-- 고래 배경 -->
    <div class="wheel">
      <img class="inner-wheel" src="@/assets/images/pinkWhale.png" alt="SSAQUIZ">
    </div>
    <!-- 메인 -->
    <div class="container">
      <div class="row">
        <img src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ">
      </div>
      <div class="row">
        <h1>남은시간</h1>
      </div>
      <div class="row">
        <span class="text-2">5초</span>
      </div>
      <div class="row">
        <h1 class="msg">잘 하고 있어요!</h1>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AwaitPage"
}
</script>

<style scoped>

#await {
  background-color: #cfe1f6;
  height: 100%;
  position: relative;
  overflow:hidden;
}

#await .container {
  width: 100%
}

#await .row {
  width: 100%;
  display: flex;
  justify-content: center;
}

#await .row > h1,
#await .row > img {
  z-index: 2;
}

#await .container .row > img {
  height: 80px;
  margin-top: 2%;
  margin-bottom: 7%;
}

#await .container h1 {
  color: black;
  font-size: 65px;
  font-family: Jua;
}

#await .container span {
  color: black;
  font-size: 200px;
  font-family: Jua;
  margin-bottom: 7%;
}

#await .container .row .msg {
  position: absolute;
  bottom: 100px;
}


/* 고래 rotation 배경*/
.wheel-con{
  background-color: #f2f2f2;
  height: 100%;
  position: relative;
  overflow:hidden;
}
.wheel{
  position: absolute;
  left:50%;
  display: block;
  width: 100px;
  height: 100px;
  transform-origin: center 500%;
  animation: spin 3s linear infinite;
}

.inner-wheel{
  top: -150px;
  left: -50px;
  width: 300px;
  height: 300px;

  transform-origin: center center;
  transform: scaleY(-1);
}

@keyframes spin {
  50% { transform: rotate(180deg); }
  100% { transform: rotate(360deg); }
}
</style>