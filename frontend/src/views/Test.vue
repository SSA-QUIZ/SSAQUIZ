<template>
  <div id="con">
    <button @click="signal">rr</button>
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color
    />
  </div>
</template>

<script>
import Alert from "@/components/Popup/Alert.vue";
export default {
  name: "Test",
  components: {
    Alert
  },
  data: function () {
    return {
      flag: false,
      alertMessage: '',
      color: '',
    }
  },
  methods: {
    signal: function () {
      this.alertMessage = "테스트으";
      this.color = "#454995";
      this.flag = !this.flag;
    }
  }
}
</script>

<style>
#con {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #cfe1f6;
  height: 100%;
}

</style>