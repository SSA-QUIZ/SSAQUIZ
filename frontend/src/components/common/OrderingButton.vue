<template>
  <button class="ordering-button" :style="style">
    <template v-if="mode === 'ordering' || mode === 'solving'">
      <div class="ordering-button">
        <p class="ordering-button__icon"><i :class="answerStyle[index-1].icon"></i></p>
        <p class="ordering-button__input" v-if="mode == 'ordering'">{{ choice }}</p>
      </div>
    </template>
    <template v-else>
      <div class="ordering-button__frame">
        <p class="ordering-button__index">{{ index+1 }}</p>
      </div>
    </template>
  </button>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: "OrderingButton",
  props: [
    'mode',
    'index',
    'answer',
  ],
  data: function () {
    return {
      style: '',
      answerStyle: [
        {
          "icon": "fas fa-cat",
          "color": "#ffdc46"
        },
        {
          "icon": "fas fa-leaf",
          "color": "#ff85b1"
        },
        {
          "icon": "fa fa-car",
          "color": "#7cb1ff"
        },
        {
          "icon": "fas fa-pills",
          "color": "#aaed81"
        }
      ]
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ["quizData", "quizIndex"]),
    choice: function () {
      return this.quizData["slideList"][this.quizIndex]["answerList"][this.index-1];
    },
  },
  mounted: function () {
    if (this.mode === 'ordering' || this.mode === 'solving') {
      this.style = 'background-color: ' + this.answerStyle[this.index-1].color;
    }
    else {
      this.style = 'background-color: #cfcfcf';
    }
  },
  updated: function () {
    if (this.mode === 'ordering' || this.mode === 'solving') {
      this.style = 'background-color: ' + this.answerStyle[this.index-1].color;
    }
    else {
      this.style = 'background-color: #cfcfcf';
    }
  }
}
</script>

<style scoped>
.ordering-button {
	display: flex;
  flex-flow: column;
	justify-content: center;
	align-items: center;
  width: 100%;
	height: 100%;
	margin: 0 0.3% 0 0.3%;
	border-radius: 15px;
	font-family: 'Jua';
	font-size: 3rem;
  /* padding: 0 auto; */
}
.ordering-button, .ordering-button__frame {
	justify-content: center;
  width: 100%;
}
.ordering-button__frame {
	color: gray;
  font-size: 2.5rem;
}
.ordering-button__input {
  font-family: "Jua";
  font-size: 50px;
  height: 50px;
  max-height: 100px;
  width: 100%;
  text-align: center;
  word-break: keep-all;
}
.ordering-button__icon, .ordering-button__index {
  font-size: 5rem;
}

@media (max-width: 700px) {
  .ordering-button__icon, .ordering-button__index {
    font-size: 3rem;
  }
  .ordering-button {
    margin: 1%;
  }
}

</style>