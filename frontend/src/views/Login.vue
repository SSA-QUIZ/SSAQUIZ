<template>
  <div id="login">
    <div class="bg">
      <img class="logo" src="@/assets/images/SSAQUIZ.png" />
      <div id="slide">
        <input type="radio" name="pos" id="pos1" class="input-style" checked />
        <input type="radio" name="pos" id="pos2" class="input-style" />
        <ul>
          <li>
            <LoginForm @move-to-signup="moveToSignup" @login-fail="loginFail"/>
          </li>
          <li>
            <SignupForm @move-to-login="moveToLogin" @signup-result="signUpResult"/>
          </li>
        </ul>
      </div>
    </div>
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color
    />
  </div>
</template>

<script>
import LoginForm from "@/components/Form/LoginForm.vue";
import SignupForm from "@/components/Form/SignupForm.vue";
import Alert from "@/components/Popup/Alert.vue";

export default {
  name: "Login",
  components: {
    LoginForm,
    SignupForm,
    Alert
  },
  data: function () {
    return {
      // Alert Message parameters
      flag: false,
      alertMessage: '',
      color: '',
    }
  },
  methods: {
    moveToSignup: function () {
      // radio의 check를 설정해주어 슬라이드가 바뀌도록 설정.
      document.getElementById("pos2").checked = true;
    },
    moveToLogin: function () {
      document.getElementById("pos1").checked = true;
    },
    loginFail: function () {
      this.alertMessage = "로그인 실패! 다시 시도해주세요";
      this.color = "red";
      this.flag = !this.flag;
    },
    signUpResult: function (result) {
      if (result === "success") {
        this.alertMessage = "회원가입 완료! 로그인 해주세요~";
        this.color = "#454995";
        this.flag = !this.flag;
      } else if (result === "fail") {
        this.alertMessage = "이메일이 이미 존재합니다!";
        this.color = "red";
        this.flag = !this.flag;
      } else {
        this.alertMessage = "비밀번호가 일치하지 않습니다! 다시 입력해주세요!";
        this.color = "red";
        this.flag = !this.flag;
      }
    }
  },
};
</script>
<style>
/* login css */

#login {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #cfe1f6;
  height: 100%;
}

#login .bg {
  background-color: transparent;
  max-width: 344px;
  height: 450px;
}

#login .logo {
  max-width: 344px;
}

/* slide css */

#slide {
  overflow: hidden;
  max-width: 344px;
}

/* 슬라이드 컨테이너(ul)와 슬라이드 아이템(li) */

#slide ul {
  width: 400%;
  height: 100%;
  transition: 0.3s;
}
#slide ul:after {
  content: "";
  display: block;
  clear: both;
}
#slide li {
  float: left;
  width: 25%;
  height: 100%;
}

/* Radio의 속성인 input 제거 & */

#slide .input-style {
  display: none;
}

/* radio check에 따른 슬라이드 아이템 변경 */
#pos1:checked ~ ul {
  margin-left: -5%;
}
#pos2:checked ~ ul {
  margin-left: -105%;
}
</style>