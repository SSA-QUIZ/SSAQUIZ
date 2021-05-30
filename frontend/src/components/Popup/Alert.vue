<template>
  <div>
    <div class="snackbar">
      <span>
        <i v-if="color==='red'" class="fa fa-exclamation-triangle"></i>
        <i v-else class="start-icon fa fa-thumbs-up faa-bounce animated"></i>
        {{ alertMessage }}
      </span>
      <button @click="hideSnackBar">OK</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Alert",
  props: [ 'alertMessage','color', 'flag'],
  watch: {
    flag: function () {
      this.showSnackBar();
    }
  },
  methods: {
    hideSnackBar: function () {
      document.querySelector(".snackbar").classList.remove("active");
      this.$emit('alert-done')
    },
    showSnackBar: function () {
      document.querySelector(".snackbar > span").style.color = this.color;
      document.querySelector(".snackbar > button").style.color = this.color;
      document.querySelector(".snackbar").classList.add("active");

      setTimeout (function() { 
        document.querySelector(".snackbar").classList.remove("active"); 
      }, 2500);
    }
  }
}
</script>

<style scoped>
* {
  outline: none;
}

.snackbar {
  left: 50%;
  bottom: 0;
  z-index: 3;
  width: 60%;
  height: 0px;
  margin-left: -30%;
  line-height: 50px;
  position: absolute;
  background-color: white;
  transition: all .3s;
  border-radius: 15px 15px 0px 0px;
  overflow: hidden;
}

.snackbar.active {
   height: 55px;
}

.snackbar > span {
  display: block;
  border: 0;
  float: left;
  color: #454995;
  cursor: pointer;
  font-size: 15px;
  margin-left: 3%;
  font-weight: bold;
  font-family: 'Noto Sans KR', sans-serif;
  background: transparent;
  text-overflow: ellipsis;
}

.snackbar > button {
  border: 0;
  width:15%;
  height:100%;
  float: right;
  color: #454995;
  display: block;
  cursor: pointer;
  font-family: 'Noto Sans KR', sans-serif;
  position: relative;
  background: transparent;
}

@media (max-width: 500px) {
  .snackbar {
    left: 30%;
    bottom: 0;
    z-index: 3;
    width: 100%;
    height: 0px;
    line-height: 30px;
    position: absolute;
    background-color: white;
    transition: all .3s;
    border-radius: 15px 15px 0px 0px;
    overflow: hidden;
  }
  .snackbar.active {
    height: 55px;
  }
}
</style>