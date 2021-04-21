<template>
  <v-list-item>
    <v-list-item-content>
      <v-text-field
        label="ID를 입력해주세요."
        solo
        hide-details
        class="mt-10 mb-3"
      ></v-text-field>
      <v-text-field
        label="PW를 입력해주세요."
        solo
        hide-details
        class="mb-3"
      ></v-text-field>
      <v-btn class="login-btn mb-3">로그인 하기</v-btn>
      <v-btn class="login-btn mb-3">구글로그인 하기</v-btn>
      <p class="atag">퀴즈를 풀러 오셨어요?</p>
      <p class="atag" @click="moveToSignup">아직 저희 회원이 아니신가요?</p>
    </v-list-item-content>
  </v-list-item>
</template>

<script>
export default {
  name: "LoginForm",
  methods: {
    moveToSignup: function () {
      this.$emit("signal", true);
    },
  },
};
</script>

<style>
</style>