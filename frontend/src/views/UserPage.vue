<template>
  <div id="user-page-container">
    <Clouds />
    <Header mode="off" />
    <div id="user-page-content">
      <div id="profile">
        <div class="img-wrapper">
          <img class="image" :src="profileImg">
        </div>
        <div id="profile__info">
          <div id="profile__username">
            <span id="profile__username-text">{{ nickname }}</span>
            <div v-if="googleLogin==='false'" id="profile__button" @click="moveToUserInfo">
              <i class="material-icons">settings</i>
            </div>
            <div id="profile__button" @click="logoutConfirm">
              <i class="material-icons">power_settings_new</i>
            </div>
          </div>
          <div><u id="profile__email">{{ email }}</u></div>
        </div>
      </div>

      <div id="quiz-set-list">
        <div id="quiz-set-list__title">
          <span id="quiz-set-list__title-text">{{ nickname }}님의 퀴즈</span>
          <button @click="openDialog = true" style="font-size: 5vh; color: #4F37DE;"><i class="material-icons">add</i></button>
        </div>
        <div id="quiz-set-list__list">
          <template v-if="quizList.length === 0">
            <TextDiv message="지금 바로 퀴즈를 만들어보세요!" />
          </template>
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
import Clouds from '@/components/effects/Clouds.vue';
import Header from '@/components/common/Header.vue';
import QuizSet from '@/components/QuizSet.vue';
import Dialog from '@/components/Popup/Dialog';
import Confirm from "@/components/Popup/Confirm.vue";
import Alert from "@/components/Popup/Alert.vue";
import TextDiv from '@/components/common/TextDiv.vue';

export default {
  name: 'UserPage',
  components: {
    Clouds,
    Header,
    QuizSet,
    Confirm,
    Alert,
    Dialog,
    TextDiv
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
    ...mapState("CreateQuizStore", ['quizData']),
    ...mapState("CreateQuizRoomStore", ['PIN']),
    ...mapState("UserStore", ['quizList', 'newQuizId']),
  },
  created: function () {
    this.setQuizList(localStorage.getItem('id'));
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
    ...mapActions("CreateQuizRoomStore", ["setPINWS", "setDefaultData"]),
    ...mapActions("CreateQuizStore", ["getQuizData", "resetQuizData"]),
    ...mapActions("CommonStore", ["setIsStudent"]),
    ...mapActions("UserStore", ["setQuizList", "addQuiz", "removeQuiz"]),
    moveToUserInfo: function () {
      this.$router.push({ name: "UserInfo" });
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
      this.setDefaultData();
      this.setIsStudent(false);
      this.setPINWS()
        .then(() => {
          this.$router.push({name: "LobbyPageT", params: {"PIN": this.PIN, "quizId": value}})
        })
        .catch(err => console.log(err))
    },
    editQuiz: function (id) {
      this.getQuizData(id)
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
          this.getQuizData(this.newQuizId);
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
  height: 100vh;
  overflow: hidden;
  padding: 0 5vw;
}

#user-page-content {
  display: flex;
  height: 85%;
  z-index: 1;
}

#profile {
  display: flex;
  padding-top: 5%;
  width: 40%;
  align-items: baseline;
}

/* 프로필 이미지 */
#profile .img-wrapper {
  position: absolute;
  width: 150px;
  height: 150px;
  transform: translate(17%,-20%);
  border-radius: 70%;
  overflow: hidden;
  margin-right: 30px;
  z-index: 1;
}

#profile .img-wrapper img {
  box-shadow: 0 1px 10px rgba(0,0,0,0.4);
  width:inherit;
  height:inherit;
}

#profile__info {
  margin-left: 200px;
  z-index: 1;
}

#profile__username {
  display: flex;
  align-items: center;
  max-width: 100%;
  font-weight: 900;
  font-family: 'Jua';
}

#profile__username-text {
  font-size: 3rem;
  white-space: nowrap;
  margin-right: 5%;
}

#profile__button {
  height: 3rem;
  margin: 0 1%;
  display: inline-flex;
  align-items: baseline;
}

#profile__email {
  font-size: 1rem;
}
#quiz-set-list {
  display: flex;
  flex-flow: column;
  justify-content: baseline;
  align-items: center;
  width: 60%;
  height: 100%;
  z-index: 1;
}

#quiz-set-list__list {
  display: flex;
  flex-wrap: wrap;
  width: 95%;
  height: 80%;
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

#quiz-set-list__title-text {
  font-size: 3rem;
  font-family: 'Jua';
}

#quiz-set-list__title button:hover {
  transition: all 0.25s linear;
  transform: scale(1.2);
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

/* 아이콘 */

i.material-icons {
    font-size: 2rem;
		color: white;
		position: relative;
		border-radius: 50%;
		padding: 5px;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
		transition: color 0.2s ease, background-color 0.2s ease, transform 0.2s ease;
}

i.material-icons:after {
		content: "";
		width: 100%;
		height: 100%;
		border: solid 2px;
		transform: scale(0.8);
		position: absolute;
		top: -1px;
		left: 0px;
		border-radius: 50%;
		transition: all 0.2s ease;
}

i.material-icons:hover:after {
		transform: scale(1);
}

i.material-icons:nth-of-type(1) {
		background-color: #88b2b9;
}

i.material-icons:nth-of-type(1):hover {
		color: #88b2b9;
}

i.material-icons:nth-of-type(1):after {
		border-color: #88b2b9;
}

i.material-icons:hover {
		background-color: transparent;
		transform: rotate(90deg);
		cursor: pointer;
		box-shadow: none;
}

#quiz-set-list__title > button > i.material-icons:nth-of-type(1) {
		background-color: #636be8;
}

#quiz-set-list__title > button > i.material-icons:nth-of-type(1):hover {
		color: #636be8;
    background-color: transparent;
}

#quiz-set-list__title > button > i.material-icons:nth-of-type(1):after {
		border-color: #636be8;
}

@media (max-width:601px) {
	i.material-icons {
		padding:10px;
		margin:5px;
		font-size:1.8rem;
	}
}

</style>