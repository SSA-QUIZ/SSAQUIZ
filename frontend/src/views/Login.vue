<template>
  <v-container id="login" fluid>
    <v-card flat class="mx-auto card-bg">
      <v-img src="@/assets/images/SSAQUIZ.png"></v-img>
      <LoginForm v-if="loadSignUp === false" @signal="moveToSignup" />
      <SignupForm v-else @signal="moveToLogin" />
    </v-card>
  </v-container>
</template>

<script>
import LoginForm from "@/components/LoginForm.vue";
import SignupForm from "@/components/SignupForm.vue";
export default {
  name: "Login",
  components: {
    LoginForm,
    SignupForm,
  },
  data: function () {
    return {
      loadSignUp: false,
    };
  },
  methods: {
    moveToSignup: function (val) {
      this.loadSignUp = val;
    },
    moveToLogin: function (val) {
      this.loadSignUp = val;
    },
  },
};
</script>