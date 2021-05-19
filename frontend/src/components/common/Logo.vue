<template>
  <header>
    <nav id="logo">
      <img @click="moveToWelcomPage" width="100%" src="@/assets/images/SSAQUIZ.png">
    </nav>
  </header>
</template>

<script>
export default {
  name: "Logo",
  methods: {
    moveToWelcomPage: function () {
      this.$router.push({ name: "UserPage" });
    }
  }
}
</script>

<style scoped>
#logo {
  position: fixed;
  display: inline-block;
  margin-top: 3%;
  margin-left: 3%;
  width: 15%;
  cursor : pointer;
}

#logo img:hover {
  transition: all 0.25s linear;
  transform: scale(1.1);
}
</style>
