<template>
  <v-container id="login" fluid>
    <v-card flat class="mx-auto card-bg">
      <v-img src="@/assets/images/SSAQUIZ.png"></v-img>
      <v-window v-model="step">
        <v-window-item>
          <LoginForm @move-to-signup="moveToSignup" />
        </v-window-item>
        <v-window-item>
          <SignupForm @move-to-login="moveToLogin" />
        </v-window-item>
      </v-window>
    </v-card>
  </v-container>
</template>

<script>
import LoginForm from "@/components/Form/LoginForm.vue";
import SignupForm from "@/components/Form/SignupForm.vue";
export default {
  name: "Login",
  components: {
    LoginForm,
    SignupForm,
  },
  data: function () {
    return {
      // window slide를 위한 변수
      step: 0,
    };
  },
  methods: {
    moveToSignup: function () {
      this.step++;
    },
    moveToLogin: function () {
      this.step--;
    },
  },
};
</script>