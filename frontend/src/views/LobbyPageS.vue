<template>
  <div id="lobby-page--student">
    <BubbleBG2/>
    <img class="ssaquiz-image" src="@/assets/images/WelcomPage-icon.png" alt="SSAQUIZ">
    <img class="welcome-msg" src="@/assets/images/대기메세지2.png">
    <div>
      <NicknameButton 
        :student="user"
        :index="0"
      />
    </div>
    <Alert
      :flag="flag"
      :alertMessage=alertMessage
      :color=color    
    />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import NicknameButton from '@/components/common/NicknameButton.vue';
import BubbleBG2 from '@/components/effects/BubbleBG2.vue';
import Alert from '@/components/Popup/Alert.vue';

export default {
  name: "LobbyPageS",
  components: {
    NicknameButton,
    BubbleBG2,
    Alert
  },
  data: function () {
    return {
      user: {
        n: this.$route.params.nickname,
        c: '8',
      },
      flag: false,
      alertMessage: '',
      color: '',
      PIN: Number,
    }
  },
  created: function () {
    this.defaultIsStart();
  },
  mounted: function () {
    this.setUsername(this.user.n);
  },
  computed: {
    ...mapState("PlayQuizStore", ["isStart", "teacherDisconnected", "isBan"])
  },
  watch: {
    teacherDisconnected: function (newVal) {
      if (newVal === true) {
        this.alertMessage = "퀴즈가 종료되었습니다. 잠시 후 메인페이지로 이동합니다.";
        this.color = "red";
        this.flag = !this.flag;
        setTimeout (() =>   {
          this.disconnectWS(); 
          this.$router.push({name: "WelcomePage"});
        }, 2500);
      }
    },
    isStart: function (val) {
      if (val === true) {
        this.$router.push({name: "LoadingPage"})
      }
    },
    isBan: function (newVal) {
      if (newVal === true) {
        this.alertMessage = "강퇴당하셨습니다. 잠시 후 메인페이지로 이동합니다.";
        this.color = "red";
        this.flag = !this.flag;
        setTimeout (() =>   {
          this.disconnectWS(); 
          this.$router.push({name: "WelcomePage"});
        }, 2500);
      }
    },
  },
  methods: {
    ...mapActions("PlayQuizStore", ["defaultIsStart", "setUsername", "disconnectWS"])
  },
}
</script>

<style scoped>
#lobby-page--student {
  height: 100%;
  background-color: #CFE1F6;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-family: Jua;
  font-size: 2rem;
}

.ssaquiz-image {
  max-width: 100%;
	margin: 0 0 15px 0;
	animation: motion 0.8s linear 0s infinite alternate;
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

.welcome-msg {
  width: 500px;
}

@keyframes motion {
	100% {
    transform: translateY(30px);
  }
}

@media (max-width: 600px) {
  .welcome-msg {
    width: 300px;
  }
}

</style>