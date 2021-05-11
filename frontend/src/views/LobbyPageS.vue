<template>
  <div id="lobby-page--student">
      <img class="ssaquiz-image" src="@/assets/images/WelcomPage-icon.png" alt="SSAQUIZ">
      <NicknameButton 
        :student="user"
        :index="0"
      />
      <div id="lobby-page--student__nickname">
        <div
          v-for="(student, index) in students"
          :key="index"
        ><NicknameButton :student="student" :index="index+1" /></div>
      </div>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import NicknameButton from '@/components/common/NicknameButton.vue';

export default {
  name: "LobbyPageS",
  components: {
    NicknameButton
  },
  data: function () {
    return {
      user: {
        nickname: this.$route.params.nickname,
        color: '#545DE3'
      },
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
    ...mapState("PlayQuizStore", ["students", "isStart"])
  },
  watch: {
    isStart: function (val) {
      if (val === true) {
        this.$router.push({name: "LoadingPage"})
      }
    }
  },
  methods: {
    ...mapActions("PlayQuizStore", ["defaultIsStart", "setUsername"])
  },
}
</script>

<style>
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
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  margin-top: 10px;
}

@media (max-width: 700px) {
  #lobby-page--student__nickname {
    width: 95%;
  }
}

</style>