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
  mounted: function () {
    // 로그인하지 않았을 때만 접속 가능
    if (localStorage.getItem('token') !== null) {
      this.$router.push({ name: "UserPage" });
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
    loginFail: function (result) {
      if (result === "password mismatch") {
        this.alertMessage = "비밀번호가 틀렸어요! 다시 시도해주세요~";
        this.color = "red";
        this.flag = !this.flag;
      } else if (result === "email does not exist") {
        this.alertMessage = "등록된 회원이 아닙니다! 회원가입 해주세요~";
        this.color = "red";
        this.flag = !this.flag;
      } else if (result === "loginRequest is null") {
        this.alertMessage = "이메일이나 비밀번호를 입력하지 않으셨습니다~";
        this.color = "red";
        this.flag = !this.flag;
      }
      
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
      } else if (result === "email-fail") {
        this.alertMessage = "이메일 형식을 확인해주세요.";
        this.color = "red";
        this.flag = !this.flag;
      } else if (result === "password-fail-1") {
        this.alertMessage = "비밀번호는 8~20자리 이내로 영문,숫자,특수문자를 혼합하여 입력해주세요.";
        this.color = "red";
        this.flag = !this.flag;
      } else if (result === "password-fail-2") {
        this.alertMessage = "비밀번호는 공백없이 입력해주세요.";
        this.color = "red";
        this.flag = !this.flag;        
      } else if (result === "password-fail-3") {
        this.alertMessage = "비밀번호는 영문,숫자,특수문자를 혼합하여 입력해주세요.";
        this.color = "red";
        this.flag = !this.flag;      
      } else if (result === "password-fail-4") {
        this.alertMessage = "비밀번호와 비밀번호 확인이 다릅니다.";
        this.color = "red";
        this.flag = !this.flag;
      } else if (result === "nickname-fail") {
        this.alertMessage = "닉네임은 영문 대소문자, 한글, 숫자, '_'로 이루어진 2~15글자로 작성해주세요.";
        this.color = "red";
        this.flag = !this.flag;
      } else if (result === "duplicate-email") {
        this.alertMessage = "이미 가입된 이메일입니다.";
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