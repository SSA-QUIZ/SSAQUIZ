<template>
  <div>
    <button @click="startQuiz" id="quiz-set">
      <span style="font-size: 1.8rem;">{{ quizTitle }}</span>
    </button>
    <div class="quiz-set__buttons">
      <button style="margin-right: 2%;"><i class="far fa-edit"></i></button>
      <button><i class="far fa-trash-alt"></i></button>
    </div>
  </div>

</template>

<script>
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from 'axios';

var stompClient;
var PIN;

export default {
  name: 'QuizSet',
  props: [
    'quizTitle'
  ],
  methods: {
    startQuiz: function () {
      axios.get("http://k4a304.p.ssafy.io/api-play/pin")
        .then(res => {
          console.log(res)
          // const PIN = res.data.object;
          PIN = 111111;
          
          stompClient = Stomp.over(new SockJS("http://k4a304.p.ssafy.io/api-play/connect"));
          stompClient.connect({},
            () => {
              console.log('ws 연결!')
              const testMessage = {
                sender: localStorage.getItem('nickname'),
                type: 'JOIN',
              };
              stompClient.send(`/quiz/room.enterUser/${PIN}`, {}, JSON.stringify(testMessage))
              stompClient.subscribe(`/pin/${PIN}`, (message) => {
                console.log('구독하고 있는 곳으로부터 메시지가 왔다', message)
              })
            }
          );
          // this.$router.push({ name: "lobbyPageS" })
          this.$router.push({ name: "LobbyPageT", params: { "PIN": PIN }});
        })
        .catch(err => console.log(err))
      
      // var stompClient = Stomp.over(new SockJS("http://k4a304.p.ssafy.io/api-play/connect"))
      // stompClient.subscribe(`quiz/room/${}`)
    }
  }
}
</script>

<style>
#quiz-set {
  width: 100%;
  height: 100%;
  background-color: rgb(202, 202, 202);
  border-radius: 15px;
  margin: 1%;
}
.quiz-set__buttons {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
  font-size: 3vh;
}
</style>