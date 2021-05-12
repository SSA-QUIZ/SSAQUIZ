<template>
  <div id="welcome-page">
    <div id="PIN-form">
      <img class="ssaquiz-image" src="@/assets/images/WelcomPage-icon.png" alt="logo" />
      <img class="ssaquiz-logo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ"/>
      <div id="slide">
        <input type="radio" name="pos" id="pos1" class="input-style" checked />
        <input type="radio" name="pos" id="pos2" class="input-style" />
        <ul>
          <li>
            <div id="PIN-form">
              <InputBox type="number" placeholder="PIN을 입력해주세요" @change-input="changePIN" @press-enter="checkPIN" />
              <InputButton @click.native="checkPIN" text="퀴즈 입장하기" />
              <router-link class="hyperlink" to="Login">퀴즈를 만들러 오셨어요?</router-link>
            </div>
          </li>
          <li>
            <div id="nickname-form">
              <InputBox type="text" placeholder="닉네임을 입력해주세요" @change-input="changeNickname" @press-enter="connectQuiz" />
              <InputButton @click.native="connectQuiz" text="퀴즈 풀러가기" />
            </div>
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
import { mapActions, mapState } from 'vuex';
import InputBox from "@/components/common/InputBox.vue";
import InputButton from "@/components/common/InputButton.vue";
import Alert from '../components/Popup/Alert.vue';
import axios from 'axios';

export default {
  name: "WelcomePage",
  components: {
    InputBox,
    InputButton,
    Alert
  },
  data: function () {
    return {
      PIN: Number,
      nickname: String,
      flag: false,
      color: '',
      alertMessage: '',
    };
  },
  watch: {
    isValidNickname: function (newVal) {
      if (newVal === 1) {
        this.alertMessage = "닉네임이 너무 깁니다. 15자 이하로 닉네임을 설정해주세요.";
        this.color = "red";
        this.flag = !this.flag;
        this.setIsValidNickname();

      } else if (newVal === 2) {
        this.alertMessage = "닉네임이 이미 존재합니다. 다른 닉네임을 설정해주세요."
        this.color = "red";
        this.flag = !this.flag;
        this.setIsValidNickname();
      } else if (newVal === 3) {
        this.alertMessage = "닉네임을 입력해주세요."
        this.color = "red";
        this.flag = !this.flag;
        this.setIsValidNickname();
      } else if (newVal === 4) {
        this.$router.push({ name: "LobbyPageS", params: {nickname: this.nickname} });
      }
    },
  },
  computed: {
    ...mapState("PlayQuizStore", ["isValidNickname"]),
  },
  mounted: function () {
    this.getToken();
  },
  methods: {
    ...mapActions("PlayQuizStore", ["setPINWS", "setIsValidNickname"]),
    changePIN: function (data) {
      this.PIN = data;
    },
    checkPIN: function () {
      axios.get(`https://k4a304.p.ssafy.io/api-play/pin/${this.PIN}`)
        .then(res => {
          let msg = res.data.data;
          if (msg === "PIN find success") {
            this.moveToNickname();
          } else if (msg === "PIN find fail") {
            this.alertMessage = "PIN을 확인해주세요.";
            this.color = "red";
            this.flag = !this.flag;
          }
        })
        .catch(err => console.log(err))
    },
    changeNickname: function (data) {
      this.nickname = data;
    },
    moveToNickname: function () {
      document.getElementById("pos2").checked = true;
    },
    connectQuiz: function () {
      console.log(this.PIN + " " + this.nickname + " websocket 연결")
      this.setPINWS([this.PIN, this.nickname]);
    },
    // 구글 로그인 token
    getToken() {
      const url = window.location.href;
      const idx = url.indexOf("token=");
      if (idx != -1) {
        // google 계정으로 로그인했다는 사실 저장(회원정보 수정 할 수 없도록.)
        localStorage.setItem("googleLogin", true);

        // url로부터 token 획득하기
        var token = url.slice(idx + 6);
        var last_char = token.slice(token.length-1,token.length);
        if (last_char === "#") {
          token = token.slice(0, token.length-2);
        }
        localStorage.setItem("token", token);
        // 서버에 token 보내기
        const data = { "token": token }
        axios.post('https://k4a304.p.ssafy.io/api-auth/auth/user', data)
          .then(res => {
            console.log(res);
          })
          .catch(err => console.log(err));

        this.$router.push({ name: "UserPage" }).catch(() => {});
      }
    },
  },
};
</script>

<style>
#welcome-page {
  height: 100%;
  background-color: #cfe1f6;
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
  font-family: "Noto Sans KR", sans-serif;
  color: grey;
  margin: 5px 0 0 0;
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