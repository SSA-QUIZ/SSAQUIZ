<template>
  <div id="user-info">
    <Logo/>
    <div id="content">
      <div class="form">
        <ul>
          <li>
            <a class="profile">
              <img src="@/assets/images/BlueWhale.png">
            </a>
          </li>
        </ul>
        <InputBox placeholder="닉네임" @change-input="updateNickname" />
        <InputBox placeholder="비밀번호" @change-input="updatePassword" />
        <InputBox placeholder="비밀번호 확인" @change-input="PasswordConfirm" />
        <div class="signout">
          <a>회원탈퇴</a>
        </div>
        <div>
          <button class="cancel-button">취소</button>
          <button class="update-button">수정</button>
        </div>
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

<style scoped>
#user-info {
  background-color: #CFE1F6;
  width: 100%;
  height: 100%;
}

/* nav bar (Logo component) 제외한 영역 */
#user-info #content  {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

/* form 입력 영역 */
#user-info #content .form {
  max-width: 344px;
  height: 450px;
  text-align: center;
}

/* 프로필 사진 */
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

/* 회원탈퇴 */
#user-info #content .signout {
  text-align: end;
  text-decoration: underline;
  margin-top: 5px;
  margin-right: 30px;
  margin-bottom: 10px;
}

#user-info #content a{
  color: red;
}

/* 수정버튼 */
#user-info #content .update-button {
  font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
	color: #FFFFFF;
  max-width: 100%;
	width: 143px;
	height: 50px;
	padding: 10px;
	background-color: #545DE3;
	border-radius: 5px;
	margin-left: 5px;
  margin-top: 20px;
}

#user-info #content .update-button:hover {
	background-color: #454995;
}

#user-info #content .update-button:focus {
  outline: none;
}

#user-info #content .update-button:active {
	background-color: #989DED;
}

/* 취소버튼 */

#user-info #content .cancel-button {
  font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
	color: #FFFFFF;
  max-width: 100%;
	width: 143px;
	height: 50px;
	padding: 10px;
	background-color: #5e5e64;
	border-radius: 5px;
	margin-right: 5px;
}

#user-info #content .cancel-button:hover {
	background-color: #a9a9b1;
}

#user-info #content .cancel-button:focus {
  outline: none;
}

#user-info #content .cancel-button:active {
	background-color: #898a92;
}

/* 프로필 이미지 slide 효과 (이미지 변경 가능하도록 처리할 예정) */
ul li a {
  background-color: #fff;
  display: block;
  position: relative;
  overflow: hidden;
}

ul li a:before {
  content: "";
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  height: 100%;
  transition: .5s;
}

ul li a:hover:before {
  top: 0;
}

ul li:nth-child(1) a:before{
  background: rgba(19,30,33,0.4);
}

</style>