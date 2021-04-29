<template>
  <div id="user-page-container">
    <Header mode="off" />
    <div id="user-page-content">
      <div id="profile">
        <div class="img-wrapper">
          <img class="image" :src="profileImg">
        </div>
        <div id="profile__info">
          <div style="font-size: 5vh; font-weight: 900; font-family: Jua;">
            {{ nickname }}
            <button v-if="googleLogin==='false'" style="font-size: 3vh; color: gray;" @click="moveToUserInfo"><i class="fas fa-pen"></i></button>
            <button style="font-size: 3.5vh; color: gray; margin-left: 10px; margin-top: 3px;" @click="logoutConfirm"><i class="fas fa-sign-out-alt"></i></button>
          </div>
          <div><u>{{ email }}</u></div>
        </div>
      </div>
      <div id="quiz-set-list">
        <div id="quiz-set-list__title">
          <span style="font-size: 4vh; font-family: Jua;">{{ nickname }}님의 퀴즈</span>
          <button style="font-size: 5vh; color: #4F37DE;"><i class="fa fa-plus-circle"></i></button>
        </div>
        <div id="quiz-set-list__list">
          <QuizSet quizTitle="퀴즈1" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈2" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈3" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈4" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈4" class="quiz-set"/>
          <QuizSet quizTitle="퀴즈4" class="quiz-set"/>
        </div>
      </div>
    </div>
    <Confirm 
      v-if="confirmFlag===true"
      :content="content"
      :emoticon="emoticon"
      @close="confirmFlag=false"
      @accept="logout"
    />
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color
    />
  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
import QuizSet from '@/components/QuizSet.vue';
import Confirm from "@/components/Popup/Confirm.vue";
import Alert from "@/components/Popup/Alert.vue";
export default {
  name: 'UserPage',
  components: {
    Header,
    QuizSet,
    Confirm,
    Alert
  },
  data: function () {
    return {
      nickname: localStorage.getItem('nickname'),
      email: localStorage.getItem('email'),

      //프로필
      profileImg: "",

      // 구글 로그인 여부
      googleLogin: localStorage.getItem('googleLogin'),

      // 로그아웃 Confirm
      confirmFlag: false,
      content: '',
      emoticon: '',

      // alert
      flag: false,
      alertMessage: '',
      color: ''
    }
  },
  mounted: function () {
    this.profileImg = localStorage.getItem('imageUrl');

    // 로그인하지 않으면 접속 불가
    if (localStorage.getItem('token') === null) {
      this.$router.push({ name: "Login" });
    } else if (this.$route.params.modify === "success") {
      this.setAlert();
    }
  },
  methods: {
    moveToUserInfo: function () {
      this.$router.push({ name: "UserInfo" });
    },
    logoutConfirm: function () {
      this.content = "로그아웃 하시겠습니까?";
      this.emoticon = "😳";
      this.confirmFlag = true;
    },
    logout: function () {
      localStorage.clear();
      this.$router.push({ name: "WelcomePage" });
    },
    setAlert: function () {
      this.alertMessage = "회원 정보가 수정되었습니다!";
      this.color = "#454995";
      this.flag = !this.flag;
    }
  }
}
</script>

<style scoped>
#user-page-container {
  background-color: #CFE1F6;
  height: 100%;
}

#user-page-content {
  display: flex;
  height: 87.5%;
}

#user-page-content > #profile {
  display: flex;
  width: 40%;
  height: 60%;
  align-items: center;
}

#profile__image {
  font-size: 18vh;
  color: #4F37DE;
  margin-left: 7%;
}

/* 프로필 이미지 */
#profile .img-wrapper {
  width:150px;
  height:150px;
  transform:translate(17%,-20%);
  border-radius: 70%;
  overflow: hidden;
  margin-right: 30px;
}

#profile .img-wrapper img {
  box-shadow: 0 1px 10px rgba(0,0,0,0.4);
  width:inherit;
  height:inherit;
}

#quiz-set-list {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  width:60%;
  height: 100%;
}

#profile__info {
  margin-left: 5%;
  font-size: 2vh;
}

#quiz-set-list__list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 90%;
  height: 70%;
  border-radius: 15px;
  background-color: white;
  overflow-x: no-display;
  overflow-y: scroll;
}

#quiz-set-list__title {
  width: 90%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1%;
}

.quiz-set {
  width: 37%;
  height: 35%;
  margin: 5%;
}

::-webkit-scrollbar { width: 30px; }

::-webkit-scrollbar-track { 
  background-color: #F2F2F2;
  border-radius: 0px 15px 15px 0px; 
}

::-webkit-scrollbar-thumb { 
  background: #c4c4c4; 
  border-radius: 15px;
  background-clip: padding-box;
  border : 8px solid transparent;
}

</style>