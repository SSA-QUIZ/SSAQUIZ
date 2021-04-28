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
import NicknameButton from '@/components/common/NicknameButton.vue';

export default {
  name: "LobbyPageS",
  components: {
    NicknameButton
  },
  data: function () {
    return {
      user: {
        nickname: '서울3반_장주빈',
        color: '#D98EF8'
      },
      PIN: 4964848,
      students: [
        {
        nickname: '서울3반_장주빈',
        color: '#D98EF8'
        },
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