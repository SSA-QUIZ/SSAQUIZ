<template>
  <v-container id="lobbyPageT" fluid fill-height>
    <v-card class="mx-auto card d-flex flex-column align-center" color="rgba(0,0,0,0)" flat>
      <v-img class="ssaquizLogo" src="@/assets/images/SSAQUIZ.png" alt="SSAQUIZ"></v-img>
      <div>
        
        <v-img class="ssaquizLogo" src="@/assets/images/QRcode.png" alt="SSAQUIZ"></v-img>

        <div class="nicknameArea">
          <div
            class="nicknameBox"
            v-for="(student, index) in students"
            :key="index"
          ><NicknameButton :student=student :index=index /></div>
        </div>
      </div>
      
    </v-card>
  </v-container>
</template>

<script>
import NicknameButton from '@/components/common/NicknameButton.vue';

export default {
  name: "LobbyPageT",
  components: {
    NicknameButton
  },
  data: function () {
    return {
      students: [
        {
          nickname: '기능 정의서',
          color: '#FFE059'
        },
        {
          nickname: '김성진',
          color: '#FF89B4'
        },
        {
          nickname: 'LG모니터',
          color: '#ABD0F2'
        },
        {
          nickname: '장주빈',
          color: '#D98EF8'
        },
        {
          nickname: '대전_1반_최나현',
          color: '#A1DBF3'
        },
        {
          nickname: '강채원',
          color: '#B9BDFD'
        },
        {
          nickname: 'SSAFY',
          color: '#B6F1A1'
        },
        {
          nickname: '송은주',
          color: '#FFC178'
        },
        {
          nickname: 'JBJB',
          color: '#A6D4FF'
        },
        {
          nickname: '최나현',
          color: '#FFE059'
        },
        {
          nickname: '서울컨설턴트 이상현',
          color: '#FF89B4'
        },
        {
          nickname: '황현승',
          color: '#ABD0F2'
        },
        {
          nickname: '한기철 컨설턴트',
          color: '#D98EF8'
        },
        {
          nickname: 'ciao',
          color: '#A1DBF3'
        },
        {
          nickname: '기능 정의서',
          color: '#FFC178'
        },
        {
          nickname: '기능 정의서',
          color: '#A6D4FF'
        }
      ]
    }
  },
}
</script>

<style>
#lobbyPageT {
  background-color: #CFE1F6;
}

#lobbyPageT .ssaquizImage {
  width: 70%;
}

#lobbyPageT .ssaquizLogo {
  width: 70%;
	justify-content: center;
	margin: 0 0 15px 0;
}

#lobbyPageT .PINQR {
  background-color: red;
}

#lobbyPageT .nicknameArea {
  width: 500px;
  height: 300px;
  background-color: #FFFFFF;
  overflow: auto;
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
}
</style>