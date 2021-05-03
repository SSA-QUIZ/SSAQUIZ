<template>
  <div id="lobby-page--teacher">
    <div id="lobby-page--teacher__header">
      <img class="ssaquiz-logo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ">
    </div>
    <div id="lobby-page--teacher__body">
      <div id="lobby-page--teacher__info">
        <button class="PIN-button">{{ PIN }}</button>
        <img class="QR-code" src="@/assets/images/QRcode.png" alt="QRcode">
      </div>
      <div id="lobby-page--teacher__nickname">
        <div
          v-for="(student, index) in students"
          :key="index"
        ><NicknameButton :student=student :index=index /></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import NicknameButton from '@/components/common/NicknameButton.vue';

export default {
  name: "LobbyPageT",
  components: {
    NicknameButton
  },
  data: function () {
    return {
      PIN: this.$route.params.PIN,
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ['students'])
  }
}
</script>

<style>
#lobby-page--teacher {
  height: 100%;
  background-color: #CFE1F6;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#lobby-page--teacher__header {
  margin: 150px 0 50px 0;
  max-width: 100vw;
  width: 95%;
  display: flex;
  justify-content: center;
}

.ssaquiz-logo {
  max-width: 100%;
	justify-content: center;
	margin: 0 0 15px 0;
}

#lobby-page--teacher__body {
  display: flex;
}

#lobby-page--teacher__info {
  margin-right: 30px;
  width: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.PIN-button {
  font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
  font-size: 40px;
	color: #FFFFFF;
  max-width: 100%;
	width: 250px;
	height: 60px;
	background-color: #545DE3;
	border-radius: 20px;
}

.QR-code {
  width: 100%;
}

#lobby-page--teacher__nickname {
  width: 600px;
  height: 400px;
  border-width: 10px 0 10px 10px;
  border-style: solid;
  border-color: #FFFFFF;
  border-radius: 20px;
  background-color: #FFFFFF;
  overflow: auto;
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
}
</style>