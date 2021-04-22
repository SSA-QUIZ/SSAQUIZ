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
      <v-window-item class="SSAQUIZSignup">
        <InputBox placeholder="E-MAIL" @change-input="setEmail" />
        <InputBox placeholder="비밀번호" @change-input="setPW" />
        <InputBox placeholder="비밀번호 확인" @change-input="setPWConfirm" />
        <InputButton @click.prevent="" text="회원가입" />
        <br />
        <a class="hyperLink" @click="moveToLogin">로그인 화면으로 돌아가기</a>
      </v-window-item>
      <v-window-item class="googleSignup">
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
</style>