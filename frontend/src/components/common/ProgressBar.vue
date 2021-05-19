<template>
  <div id="progress-bar">
    <div id="progress"><h1>{{ index }} / {{ all }}</h1></div>
    <div id="progress-all">
      <div id="progress-now"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProgressBar',
  props: [
    'index',
    'all'
  ],
  data: function () {
    return {
      width: this.index / this.all * 100
    }
  },
  mounted: function () {
    this.changeWidth();
  },
  watch: {
    index: function () {
      this.changeWidth();
    }
  },
  methods: {
    changeWidth: function () {
      this.width = this.index / this.all * 100;
      document.getElementById("progress-now").style.width = this.width + "%";
    }
  },
}
</script>

<style scoped>
#progress-bar {
  width: 100%;
  height: 10%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#progress {
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Jua';
	font-size: 2rem;
	font-weight: bold;
}
#progress-all {
	position: relative; 
	background-color: #989ded; 
	width: 100%; 
	height: 2vh; 
	border-radius: 15px;
}
#progress-now {
	position: absolute;
	background-color: #4f38de;
	height: 2vh;
	border-radius: 15px;
}
</style>