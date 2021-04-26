<template>
  <div id="signup">
    <!---------- Tab ------------>
    <v-tabs
      v-model="tabs"
      class="tabs"
      background-color="transparent"
      centered
      height="30"
    >
      <v-tab v-for="item in items" :key="item.id" @click="changeTabs(item.id)">
        {{ item.name }}
      </v-tab>
    </v-tabs>

    <!---------- window slide ------------>
    <v-window v-model="turn">
      <v-window-item class="SSAQUIZ-signup">
        <InputBox placeholder="E-MAIL" @change-input="setEmail" />
        <InputBox placeholder="비밀번호" @change-input="setPW" />
        <InputBox placeholder="비밀번호 확인" @change-input="setPWConfirm" />
        <InputButton @click.prevent="" text="회원가입" />
        <br />
        <a class="hyper-link" @click="moveToLogin">로그인 화면으로 돌아가기</a>
      </v-window-item>
      <v-window-item class="google-signup">
        <InputButton @click.prevent="" text="구글 계정으로 회원가입" />
      </v-window-item>
    </v-window>
  </div>
</template>

<script>
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
      // tab change를 위한 변수
      tabs: null,
      items: [
        { id: 0, name: "SSAQUIZ" },
        { id: 1, name: "Google" },
      ],
      turn: 0,

      // input parameter
      Email: "",
      Password: "",
      PasswordConfirm: "",
    };
  },
  methods: {
    changeTabs: function (id) {
      if (id === 0) {
        this.turn--;
      } else {
        this.turn++;
      }
    },
    moveToLogin: function () {
      this.$emit("move-to-login");
    },
    setEmail: function (data) {
      this.Email = data;
    },
    setPW: function (data) {
      this.Password = data;
    },
    setPWConfirm: function (data) {
      this.PasswordConfirm = data;
    },
  },
};
</script>

<style>
/* Signup.vue */
#signup {
  text-align: center;
  margin-top: 8px;
  height: 350px;
}

#signup .hyper-link {
  font-family: "Noto Sans KR", sans-serif;
  color: grey;
  margin: 5px 0 0 0;
}

/* 탭 설정 */

#signup .tabs {
  margin-bottom: 20px;
  margin-top: 20px;
}

#signup
  > div.v-tabs.tabs.v-tabs--centered.theme--light
  > div
  > div.v-slide-group__wrapper
  > div
  > div.v-tab.v-tab--active {
  color: #545de3;
}

#signup
  > div.v-tabs.tabs.v-tabs--centered.theme--light
  > div
  > div.v-slide-group__wrapper
  > div
  > div.v-tabs-slider-wrapper
  > div {
  color: #545de3;
  width: 70px;
  margin: auto;
}

/* 각 탭 레이아웃 */

#signup .SSAQUIZ-signup {
  height: 350px;
}

#signup .google-signup {
  margin-top: 50px;
  height: 350px;
}
</style>