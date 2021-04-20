<template>
  <v-container id="login" class="bg" fluid d-flex align-center>
    <v-card flat class="mx-auto card" max-width="344">
      <v-img src="@/assets/images/SSAQUIZ.png"></v-img>
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
          <a href="" class="atag">퀴즈를 풀러 오셨어요?</a>
          <a href="" class="atag">아직 저희 회원이 아니신가요?</a>
        </v-list-item-content>
      </v-list-item>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "Login",
};
</script>

<style>
</style>