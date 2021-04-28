<template>
  <div id="user-info">
    <Logo/>
    <div id="content">
      <div class="form">
        <!------ 프로필 이미지 ------>
        <div class="profile">
          <div class="img-wrapper">
            <img class="image file-input" src="https://ssaquiz.s3.ap-northeast-2.amazonaws.com/2281a4ad-cc38-4099-b483-862639c71869.jpg">
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
          <button class="update-button" @click="update">수정</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
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
      // parameters
      nickname: localStorage.getItem('nickname'),
      password: '',
      passwordConfirm: '',

      // image file data
      imgData: {},
    }
  },
  mounted: function () {
    $('.file-input').change(function(){
      // 이미지 파일을 선택하고 img 태그에 넣는다.
      var curElement = $(this).parent().parent().find('.image');
      var reader = new FileReader();
      reader.onload = function (e) {
        curElement.attr('src', e.target.result);
      };
      // 화면에 바뀐 이미지를 띄운다.
      reader.readAsDataURL(this.files[0]);
      this.imgData = this.files[0];
    });
  },
  methods: {
    // 변수들 변경하는 함수
    updateNickname: function (data) {
      this.nickname = data;
    },
    updatePassword: function (data) {
      this.password = data;
    },
    updatePasswordConfirm: function (data) {
      this.passwordConfirm = data;
    },
    // 취소 함수
    cancel: function () {
      this.$router.push({ name: "UserPage" });
    },
    // 변경 함수
    update: function () {
      const formData = new FormData();

      if (Object.keys(this.imgData).length === 0) {
        // 1. 사진을 바꾸지 않을 때
        formData.append("email", localStorage.getItem('email'));
        formData.append("name", this.nickname);
        formData.append("password", this.password);
      } else {  
        // 2. 사진을 바꿀 때
        formData.append("file", this.imgData);
        formData.append("email", localStorage.getItem('email'));
        formData.append("name", this.nickname);
        formData.append("password", this.password);
      }

      const headers = { "Content-Type": "multipart/form-data" };

      axios.post("http://k4a304.p.ssafy.io/api-auth/auth/modify", formData, headers)
        .then(res => console.log(res))
        .catch(err => console.log(err))
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