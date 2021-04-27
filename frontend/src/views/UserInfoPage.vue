<template>
  <div id="user-info">
    <Logo/>
    <div id="content">
      <div class="form">
        <a class="profile">
          <img src="@/assets/images/BlueWhale.png">
        </a>
        <InputBox placeholder="닉네임" @change-input="updateNickname" />
        <InputBox placeholder="비밀번호" @change-input="updatePassword" />
        <InputBox placeholder="비밀번호 확인" @change-input="PasswordConfirm" />
      </div>
    </div>

  </div>
</template>

<script>
import Logo from '@/components/common/Logo.vue';
import InputBox from '@/components/common/InputBox.vue';
export default {
  name: "UserInfo",
  components: {
    Logo,
    InputBox
  },
  data: function () {
    return {
      
    }
  },
  methods: {
    updateNickname: function () {},
    updatePassword: function () {},
    PasswordConfirm: function () {}
  }
}
</script>

<style>
#user-info {
  background-color: #CFE1F6;
  width: 100%;
  height: 100%;
}

#user-info #content  {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#user-info #content .form {
  max-width: 344px;
  height: 450px;
  text-align: center;
}

#user-info #content .profile {
	display: inline-block;
	border-radius: 50%;
  height: 128px;
  width: 128px;
  margin-bottom: 7%;
}

#user-info #content .profile img {
	border-radius: 50%;
  height: 128px;
  width: 128px;
}

</style>