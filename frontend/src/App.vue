<template>
  <v-app>
    <router-view />
  </v-app>
</template>

<script>
import '@/assets/style.css';

export default {
  name: "App",
  created: function () {
    window.addEventListener('beforeunload', localStorage.clear());
  }
};
</script>
