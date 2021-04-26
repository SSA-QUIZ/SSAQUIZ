<template>
  <div id="login-form">
    <InputBox placeholder="ID를 입력해주세요." @change-input="setID" />
    <InputBox placeholder="PW를 입력해주세요." @change-input="setPW" />
    <InputButton @click.prevent="" text="로그인 하기" />
    <InputButton @click.native="googleLogin" text="구글로 로그인하기" />
    <br />
    <router-link class="hyper-link" to="">퀴즈를 풀러 오셨어요?</router-link>
    <br />
    <a class="hyper-link" @click="moveToSignup">아직 저희 회원이 아니신가요?</a>
  </div>
</template>

<script>
import { GOOGLE_AUTH_URL } from "@/config/index.js";

import InputBox from "@/components/common/InputBox.vue";
import InputButton from "@/components/common/InputButton.vue";
export default {
  name: "LoginForm",
  components: {
    InputBox,
    InputButton,
  },
  data: function () {
    return {
      ID: "",
      PW: "",
    };
  },
  methods: {
    // google login
    googleLogin() {
      window.location.href = GOOGLE_AUTH_URL;
    },
    moveToSignup: function () {
      this.$emit("move-to-signup");
    },
    setID: function (data) {
      this.ID = data;
    },
    setPW: function (data) {
      this.PW = data;
    },
  },
};
</script>

<style>
#login-form {
  text-align: center;
  margin-top: 55px;
  height: 300px;
}

#login-form .hyper-link {
  font-family: "Noto Sans KR", sans-serif;
  color: grey;
  margin: 5px 0 0 0;
}
</style>