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
          <button @click="openDialog = true" style="font-size: 5vh; color: #4F37DE;"><i class="fa fa-plus-circle"></i></button>
        </div>
        <div id="quiz-set-list__list">
          <template v-for="(quiz, index) in quizList">
            <QuizSet 
              :key="index" 
              :quizTitle="quiz.title"
              :quizImage="quiz.imagePath"
              @start-quiz="startQuiz(quiz.id)"
              @edit-quiz="editQuiz(quiz.id)"
              @delete-quiz="deleteQuiz(quiz.id)"
              class="quiz-set"
            />
          </template>
        </div>
      </div>
    </div>
    <Confirm 
      v-if="confirmFlag===true"
      :content="content"
      :emoticon="emoticon"
      @close="confirmFlag=false"
      @accept="confirmAccept(type)"
    />
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color
    />
    <Dialog
      emoticon="🤓"
      content="퀴즈 제목을 작성해주세요." 
      @close="openDialog = false" 
      @accept="createQuiz(); openDialog = false;"
      @change-input-value="changeQuizTitle"
      v-if="openDialog"
    />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Header from '@/components/common/Header.vue';
import QuizSet from '@/components/QuizSet.vue';
import Dialog from '@/components/Popup/Dialog';
import Confirm from "@/components/Popup/Confirm.vue";
import Alert from "@/components/Popup/Alert.vue";
// import axios from 'axios';

export default {
  name: 'UserPage',
  components: {
    Header,
    QuizSet,
    Confirm,
    Alert,
    Dialog
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
      color: '',

      openDialog: false,
      quizTitle: '',
      type: '',

      workbookId: ''
    }
  },
  computed: {
    ...mapState("CreateQuizRoomStore", ['PIN']),
    ...mapState("UserStore", ['quizList', 'newQuizId']),
  },
  created: function () {
    this.setQuizList(localStorage.getItem('id'));
  },
  mounted: function () {
    this.getToken();

    this.profileImg = localStorage.getItem('imageUrl');
    // 로그인하지 않으면 접속 불가
    if (localStorage.getItem('token') === null) {
      this.$router.push({ name: "Login" });
    } else if (this.$route.params.modify === "success") {
      this.setAlert();
    }
  },
  methods: {
    ...mapActions("CreateQuizRoomStore", ["setPINWS", "sendAnswerList"]),
    ...mapActions("CreateQuizStore", ["getQuizData", "resetQuizData"]),
    ...mapActions("CommonStore", ["setIsStudent"]),
    ...mapActions("UserStore", ["setQuizList", "addQuiz", "removeQuiz"]),
    moveToUserInfo: function () {
      this.$router.push({ name: "UserInfo" });
    },
    // 구글 로그인 token (메인페이지 완성되는 대로 이동시킬 예정)
    getToken() {
      const url = window.location.href;
      const idx = url.indexOf("token=");
      console.log(idx);
      if (idx != -1) {
        // google 계정으로 로그인했다는 사실 저장(회원정보 수정 할 수 없도록.)
        localStorage.setItem("googleLogin", true);

        // url로부터 token 획득하기
        const token = url.slice(idx + 6);
        localStorage.setItem("token", token);
        this.$router.push({ name: "UserPage" }).catch(() => {});
      }
    },
    logoutConfirm: function () {
      this.emoticon = "😳";
      this.type = "logout";
      this.content = "로그아웃 하시겠습니까?";
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
    },
    // quizSet methods
    startQuiz: function (value) {
      this.setIsStudent(false);
      this.setPINWS()
        .then(() => {
          this.$router.push({name: "LobbyPageT", params: {"PIN": this.PIN, "quizId": value}})
        })
        .catch(err => console.log(err))
    },
    editQuiz: function (id) {
      this.getQuizData(id);
      this.$router.push({ name: "CreatorPage", params: {"workbookId" : id} });
    },
    deleteQuiz: function (id) {
      this.emoticon = "😲";
      this.type = "delete";
      this.content = "퀴즈를 삭제하시겠습니까?";
      this.confirmFlag = true;
      this.workbookId = id;
    },
    createQuiz: function () {
      const params = {"userId": localStorage.getItem('id'), "workbookTitle": this.quizTitle}
      this.addQuiz(params)
        .then(() => {
          this.$router.push({ name: "CreatorPage", params: {"workbookId" : this.newQuizId} })
        })
        .catch(err => console.log(err))
    },
    changeQuizTitle: function (data) {
      this.quizTitle = data
    },
    confirmAccept: function (type) {
      if (type === "logout") {
        localStorage.clear();
        this.$router.push({ name: "WelcomePage" });
      } else if (type === "delete") {
        let val = [this.workbookId, localStorage.getItem('id')]
        this.removeQuiz(val)
        .then(() => {
          this.confirmFlag = false;
          this.alertMessage = "퀴즈가 삭제되었습니다.";
          this.color = "#454995";
          this.flag = !this.flag;
        })
        .catch(err=>{console.log(err)});
      }
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
  min-height: 100%;
}

#profile__info {
  margin-left: 5%;
  font-size: 2vh;
}

#quiz-set-list__list {
  display: flex;
  flex-wrap: wrap;
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
  width: 40%;
  height: 38%;
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