<template>
  <div class="wheel-con">
    <div class="wheel">
      <img class="inner-wheel" src="@/assets/images/pinkWhale.png" alt="SSAQUIZ">
    </div>
  </div>
</template>

<script>
export default {
  name: "Test"
}
</script>

<style scoped>
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
  animation: spin 6s linear infinite;
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