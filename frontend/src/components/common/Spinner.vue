<template>
  <div id="star">
    <span>x1.5</span>
  </div>
</template>

<script>
export default {
  name: "Spinner"
}
</script>

<style>
#star {
  position: absolute;
  width: 200px;
  height: 200px;
  background: no-repeat center/100% url("../../assets/images/star.png");

  display: flex;
  justify-content: center;
  align-items: center;

  animation: motion 0.7s linear 0s infinite alternate;
  transform: rotate(15deg);
}

#star span {
  font-size: 60px;
  font-family: Jua;
  margin-top: 10px;
  transform: rotate(-15deg);
}

@keyframes motion {
	0% {margin-top: 0px;}
	100% {margin-top: 20px;}
}

</style>