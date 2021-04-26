<template>
  <div id="welcome-page">
    <div id="PIN-form">
      <img class="ssaquiz-image" src="@/assets/images/WelcomPage-icon.png" alt="">
      <img class="ssaquiz-logo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ">
      <InputBox type="number" placeholder="PIN을 입력해주세요" @change-input="changePIN" />
      <InputButton @click.prevent="sendPIN" text="퀴즈 입장하기" />
      <router-link class="hyperlink" to="Login">퀴즈를 만들러 오셨어요?</router-link>
    </div>
  </div>
</template>

<script>
import InputBox from "@/components/common/InputBox.vue";
import InputButton from "@/components/common/InputButton.vue";

export default {
  name: "WelcomePage",
  components: {
    InputBox,
    InputButton,
  },
  data: function () {
    return {
      PIN: Number
    }
  },
  methods: {
    changePIN: function (data) {
      this.PIN = data
    },
    sendPIN: function () {
      console.log("클릭하면 PIN 서버로 보내기")
    },
  }
}
</script>

<style>
#welcome-page {
  height: 100%;
  background-color: #CFE1F6;
  display: flex;
  justify-content: center;
}

#welcome-page #PIN-form {
  display: flex;
  max-width: 100vw;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#welcome-page #PIN-form .ssaquiz-image {
  width: 95%;
}

#welcome-page #PIN-form .ssaquiz-logo {
  width: 95%;
	justify-content: center;
	margin: 0 0 15px 0;
}

#welcome-page #PIN-form .hyperlink {
	font-family: 'Noto Sans KR', sans-serif;
	color: grey;
	margin: 5px 0 0 0;
}
</style>