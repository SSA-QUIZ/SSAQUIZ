<template>
  <div id="signup-form">
    <InputBox placeholder="E-MAIL" @change-input="setEmail" />
    <InputBox placeholder="닉네임" @change-input="setNickname" />
    <InputBox placeholder="비밀번호" @change-input="setPW" />
    <InputBox placeholder="비밀번호 확인" @change-input="setPWConfirm" />
    <InputButton @click.native="signup" text="회원가입" />
    <br />
    <a class="hyper-link" @click="moveToLogin">로그인 화면으로 돌아가기</a>
  </div>
</template>

<script>
import axios from 'axios'
import InputBox from "@/components/common/InputBox.vue";
import InputButton from "@/components/common/InputButton.vue";
export default {
  name: "Signup",
  components: {
    InputBox,
    InputButton,
  },
  data: function () {
    return {
      // input parameter
      email: "",
      nickname: "",
      password: "",
      passwordConfirm: "",
    };
  },
  methods: {
    moveToLogin: function () {
      this.$emit("move-to-login");
    },
    setEmail: function (data) {
      this.email = data;
    },
    setNickname: function (data) {
      this.nickname = data
    },
    setPW: function (data) {
      this.password = data;
    },
    setPWConfirm: function (data) {
      this.passwordConfirm = data;
    },
    // 회원가입 요청
    signup: function () {
      if (this.password === this.passwordConfirm) {     // 비밀번호 일치할 경우
        const data = { "email": this.email, "name": this.nickname, "password": this.password }
        axios.post("http://k4a304.p.ssafy.io/api-auth/auth/signup", data)
          .then(res => {
            console.log(res)
            if (res.data.status === true){
              this.email = "";
              this.nickname = "";
              this.password = "";
              this.passwordConfirm = "";

              this.$emit("move-to-login");
            }
          })
          .catch(err => { console.log(err) })
          // 이메일 중복 처리(alert 띄울 예정)

      } else {
        //비밀번호 일치하지 않을 경우 (alert 띄울 예정)
      }
    }
  },
};
</script>

<style>
/* Signup.vue */
#signup-form {
  text-align: center;
  margin-top: 55px;
  height: 350px;
}

#signup-form .hyper-link {
  font-family: "Noto Sans KR", sans-serif;
  color: grey;
  margin: 5px 0 0 0;
}
</style>