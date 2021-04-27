<template>
  <div id="user-info">
    <Logo/>
    <div id="content">
      <div class="form">
        <!------ 프로필 이미지 ------>
        <div class="profile">
          <div class="img-wrapper">
            <img class="image file-input" src="@/assets/images/Default.png">
            <input type="file" name="file" class="file-input">
            <div class="hover"></div>
            <div class="image-upload-btn" onclick="document.all.file.click();">
              <span draggable="false"><i class="far fa-file-image"></i></span>
            </div>
          </div>
        </div>
        <!-- 닉네임, 비밀번호, 비밀번호 확인, 회원탈퇴 -->
        <InputBox :placeholder="nickname" @change-input="updateNickname" />
        <InputBox placeholder="비밀번호" @change-input="updatePassword" />
        <InputBox placeholder="비밀번호 확인" @change-input="updatePasswordConfirm" />
        <div class="signout">
          <a>회원탈퇴</a>
        </div>
        <!-- 취소,수정 버튼 -->
        <div>
          <button class="cancel-button" @click="cancel">취소</button>
          <button class="update-button">수정</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery';
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
      nickname: this.$store.state.userInfo[1],
      password: '',
      passwordConfirm: ''
    }
  },
  mounted: function () {
    $('.file-input').change(function(){
      var curElement = $(this).parent().parent().find('.image');
      console.log(curElement);
      var reader = new FileReader();
      reader.onload = function (e) {
        curElement.attr('src', e.target.result);
      };
      reader.readAsDataURL(this.files[0]);
    });
  },
  methods: {
    updateNickname: function (data) {
      this.nickname = data;
    },
    updatePassword: function (data) {
      this.password = data;
    },
    updatePasswordConfirm: function (data) {
      this.passwordConfirm = data;
    },
    cancel: function () {
      this.$router.push({ name: "UserPage" });
    }
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
  font-family: 'Noto Sans KR', sans-serif;
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

/* 프로필 이미지 영역 */
#user-info #content .form .profile {
  height:200px;
  position:relative;
  max-width: 250px;
  margin: auto;
}

/* 프로필 이미지 */
#user-info #content .form .profile .img-wrapper {
  width:195px;
  height:195px;
  position:absolute;
  cursor:pointer;
  transform:translate(17%,-20%);
  border-radius: 70%;
  overflow: hidden;
}

#user-info #content .form .profile .img-wrapper img {
  box-shadow: 0 1px 10px rgba(0,0,0,0.4);
  width:inherit;
  height:inherit;
}

/* hover */
.hover {
  position:absolute;
  top:0;
  left:0;
  width:inherit;
  height:inherit;
  background:#000000;
  opacity:0;
  transition:all .6s linear;
}

/* hover 후 나타나는 수정 아이콘 */
.image-upload-btn {
  position:absolute;
  top:70px;
  left:70px;
  /* background:rgb(180, 179, 179); */
  width:55px;
  height:55px;
  border-radius:50%;
  text-align:center;
  opacity:0;
  transform:scale(2);
  transition:all .3s linear;
}

.image-upload-btn span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}

.img-wrapper:hover .hover{
  opacity:0.4;
}

.img-wrapper:hover .image-upload-btn {
  opacity:1;
  transform:scale(1);
}

/* 파일 입력  */
input.file-input {
  display: none;
  position: absolute;
  top: 0;
  right: 0;
  margin: 0;
  padding: 0;
  font-size: 20px;
  cursor: pointer;
  opacity: 0;
  filter: alpha(opacity=0);
  height:100%;
}
</style>