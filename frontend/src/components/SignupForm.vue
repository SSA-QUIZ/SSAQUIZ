<template>
  <div id="signup">
    <InputBox placeholder="E-MAIL" @change-input="setEmail" />
    <InputBox placeholder="비밀번호" @change-input="setPW" />
    <InputBox placeholder="비밀번호 확인" @change-input="setPWConfirm" />
    <InputButton @click.prevent="" text="회원가입" />
    <br />
    <a class="hyperLink" @click="moveToLogin">로그인 화면으로 돌아가기</a>
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
      Email: "",
      Password: "",
      PasswordConfirm: "",
    };
  },
  methods: {
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