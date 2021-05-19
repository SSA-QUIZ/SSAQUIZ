<template>
  <div id="signup-form">
    <InputBox placeholder="E-MAIL" type="text" @change-input="setEmail" />
    <InputBox placeholder="닉네임" type="text" @change-input="setNickname" />
    <InputBox placeholder="비밀번호" type="password" @change-input="setPW" />
    <InputBox placeholder="비밀번호 확인" type="password" @change-input="setPWConfirm" />
    <InputButton @click.native="checkEmail" text="회원가입" />
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
    checkEmail: function () {
      var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
      if (!emailRegExp.test(this.email)) {
        this.$emit("signup-result", "email-fail");
      } else {
        this.checkNickname();
      }
    },
    checkPassword: function () {
      var num = this.password.search(/[0-9]/g);
      var eng = this.password.search(/[a-z]/ig);
      var spe = this.password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
      if (this.password.length < 8 || this.password.length > 20) {
        this.$emit("signup-result", "password-fail-1");
        return false;
      }
      if (this.password.search(/₩s/) != -1) {
        this.$emit("signup-result", "password-fail-2");
        return false;
      }
      if (num < 0 || eng < 0 || spe < 0 ) {
        this.$emit("signup-result", "password-fail-3");
        return false;
      }
      if (this.password !== this.passwordConfirm) {
        this.$emit("signup-result", "password-fail-4");
        return false;
      }
      this.signup();  
    },
    checkNickname: function () {
      var nicknameRegExp = /^[_a-zA-z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{2,15}$/;
      if (!nicknameRegExp.test(this.nickname)) {
        this.$emit("signup-result", "nickname-fail");
      } else {
        this.checkPassword();
      }
    },
    // 회원가입 요청
    signup: function () {
      if (this.password === this.passwordConfirm) {     // 비밀번호 일치할 경우
        const data = { "email": this.email, "name": this.nickname, "password": this.password }
        axios.post("https://k4a304.p.ssafy.io/api-auth/auth/signup", data)
          .then(res => {
            console.log(res)
            if (res.data.status === true){
              this.email = "";
              this.nickname = "";
              this.password = "";
              this.passwordConfirm = "";
              this.$emit("signup-result", "success");
              this.$emit("move-to-login");
            } else {
              if (res.data.data === "signup fail (duplicate email)") {
                this.$emit("signup-result", "duplicate-email");
              }
            }
          })
          .catch(err => { 
            console.log(err)
            this.$emit("signup-result", "fail");
          })
      } else {
        this.$emit("signup-result", "password-fail");
      }
    }
  },
};
</script>

<style scoped>
/* Signup.vue */
#signup-form {
  text-align: center;
  margin-top: 55px;
  height: 350px;
}

#signup-form .hyper-link {
  font-family: "Jua";
  color: grey;
  margin: 5px 0 0 0;
  text-decoration: underline;
}
</style>