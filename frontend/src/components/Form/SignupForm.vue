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
      this.Email = data;
    },
    setNickname: function (data) {
      this.nickname = data
    },
    setPW: function (data) {
      this.Password = data;
    },
    setPWConfirm: function (data) {
      this.PasswordConfirm = data;
    },
    signup: function () {
      const userInfo = [this.Email, this.nickname, this.Password]
      this.$store.dispatch("setUserInfo", userInfo);
      const data = {"email": this.email, "name": this.nickname, "password": this.password}

      axios.post("http://k4a304.p.ssafy.io/api-auth/auth/modify", data)
        .then(res => {
          // 변수 초기화 및 페이지 이동
          console.log(res)
          this.email = ''
          this.nickname = ''
          this.password = ''
          this.passwordConfirm = ''

          this.$router.push({ name: "Login" });
        })
        .catch(err => { console.log(err) })
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