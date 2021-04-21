<template>
  <v-container id="welcomePage" fluid fill-height>
    <v-card class="mx-auto card d-flex flex-column align-center" color="rgba(0,0,0,0)" flat>
      <v-img class="ssaquizImage" src="@/assets/images/WelcomPage-icon.png"></v-img>
      <v-img class="ssaquizLogo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ"></v-img>
      <InputBox type="number" placeholder="PIN을 입력해주세요" @change-input="changePIN" />
      <InputButton @click.prevent="sendPIN" text="퀴즈 입장하기" />
      <router-link class="hyperLink" to="Login">퀴즈를 만들러 오셨어요?</router-link>
    </v-card>
  </v-container>
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
#welcomePage {
  background-color: #CFE1F6;
}

#welcomePage .ssaquizImage {
  width: 70%;
}

#welcomePage .ssaquizLogo {
  width: 70%;
	justify-content: center;
	margin: 0 0 15px 0;
}

#welcomePage .hyperLink {
	font-family: 'Noto Sans KR', sans-serif;
	color: grey;
	margin: 5px 0 0 0;
}
</style>