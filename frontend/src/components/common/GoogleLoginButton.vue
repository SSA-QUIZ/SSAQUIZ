<template>
  <div class='g-sign-in-button'>
    <div class=content-wrapper>
      <div class='logo-wrapper'>
        <img src='https://developers.google.com/identity/images/g-logo.png'>
      </div>
      <span class='text-container'>
        <span>구글 계정으로 로그인</span>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "GoogleLoginButton"
}
</script>

<style scoped>
.g-sign-in-button {
    text-align: start;
    margin: 5px;
    display: inline-block;
    max-width: 100%;
    width: 300px;
    height: 50px;
    background-color: #545DE3;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.25);
    transition: background-color .218s, border-color .218s, box-shadow .218s;
}

.g-sign-in-button:hover {
    cursor: pointer;
    background-color: #454995;
}

.g-sign-in-button:active {
    background-color: #989DED;
    transition: background-color 0.2s;
}

.g-sign-in-button .content-wrapper {
    height: 100%;
    width: 100%;
}

.g-sign-in-button img {
    width: 18px;
    height: 18px;
}

.g-sign-in-button .logo-wrapper {
    padding: 15px;
    background: #fff;
    width: 48px;
    height: 100%;
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
    display: inline-block;
}

.g-sign-in-button .text-container {
    font-family: 'Jua', sans-serif;
    /* font-weight: bold; */
    line-height: 48px;
    padding-top: 3px;
    vertical-align: top;
    border: none;
    display: inline-block;
    text-align: center;
    width: 230px;
}

.g-sign-in-button .text-container span{
  margin-left: 20px;
}
</style>