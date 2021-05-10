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
              <InputBox type="number" placeholder="PIN을 입력해주세요" @change-input="changePIN" />
              <InputButton @click.native="moveToNickname" text="퀴즈 입장하기" />
              <router-link class="hyperlink" to="Login">퀴즈를 만들러 오셨어요?</router-link>
            </div>
          </li>
          <li>
            <div id="nickname-form">
              <InputBox type="text" placeholder="닉네임을 입력해주세요" @change-input="changeNickname" />
              <InputButton @click.native="connectQuiz" text="퀴즈 풀러가기" />
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
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
      PIN: Number,
      nickname: String,
    };
  },
  methods: {
    ...mapActions("PlayQuizStore", ["setPINWS"]),
    changePIN: function (data) {
      this.PIN = data;
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
      this.$router.push({ name: "LobbyPageS", params: {nickname: this.nickname} });
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