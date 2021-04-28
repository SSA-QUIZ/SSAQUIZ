<template>
  <div id="login-form">
    <InputBox placeholder="ID를 입력해주세요." @change-input="setEmail" />
    <InputBox placeholder="PW를 입력해주세요." @change-input="setPassword" />
    <InputButton @click.native="login" text="로그인 하기" />
    <InputButton @click.native="googleLogin" text="구글로 로그인하기" />
    <br />
    <router-link class="hyper-link" to="">퀴즈를 풀러 오셨어요?</router-link>
    <br />
    <a class="hyper-link" @click="moveToSignup">아직 저희 회원이 아니신가요?</a>
  </div>
</template>

<script>
import axios from 'axios'
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
      email: "",
      password: "",
    };
  },
  methods: {
    moveToSignup: function () {
      this.$emit("move-to-signup");
    },
    setEmail: function (data) {
      this.email = data;
    },
    setPassword: function (data) {
      this.password = data;
    },
    login: function () {
      const data = {"email": this.email, "password": this.password}
      axios.post("http://k4a304.p.ssafy.io/api-auth/auth/login", data)
        .then(res => {
          console.log(res)
          if (res.data.status === true) {     //로그인 성공시
            localStorage.setItem('token',res.data.object.accessToken)
            localStorage.setItem('nickname', res.data.object.nickname)
            localStorage.setItem('email', this.email)

            this.$router.push({ name: "UserPage" });
          } else {
            // 로그인 실패시 alert 처리 예정
          }
        })
    },
    // google login
    googleLogin: function () {
      window.location.href = GOOGLE_AUTH_URL;
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