<template>
  <div id="lobby-page--student">
    <img class="ssaquiz-image" src="@/assets/images/WelcomPage-icon.png" alt="SSAQUIZ">
    <NicknameButton 
      :student="user"
      :index="0"
    />
    <div id="lobby-page--student__nickname">
      <template
        v-for="(student, index) in students"><NicknameButton :key="index" :student="student" :index="index+1" /></template>
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
import Alert from '@/components/Popup/Alert.vue';

export default {
  name: "LobbyPageS",
  components: {
    NicknameButton,
    Alert
  },
  data: function () {
    return {
      user: {
        nickname: this.$route.params.nickname,
        color: '#545DE3',
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
    this.setUsername(this.user.nickname);
  },
  computed: {
    ...mapState("PlayQuizStore", ["students", "isStart", "teacherDisconnected"])
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
    }
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
}

.ssaquiz-image {
  max-width: 100%;
	margin: 0 0 15px 0;
}

#lobby-page--student__nickname {
  width: 600px;
  height: 400px;
  border-width: 10px 0 10px 10px;
  border-style: solid;
  border-color: #FFFFFF;
  border-radius: 20px;
  background-color: #FFFFFF;
  overflow: auto;
  display: table-cell;
  text-align: center;
  margin-top: 10px;
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

@media (max-width: 700px) {
  #lobby-page--student__nickname {
    width: 95%;
    height: 40%;
  }
}

</style>