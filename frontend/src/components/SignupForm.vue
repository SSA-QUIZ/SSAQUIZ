<template>
  <div id="signup">
    <v-tabs v-model="tab" centered background-color="transparent">
      <v-tab v-for="item in items" :key="item">
        {{ item }}
      </v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab" style="background-color: transparent">
      <v-tab-item>
        <v-card> 로컬로그인 </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card> 소셜 로그인 </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
export default {
  name: "Signup",
  data: function () {
    return {
      tab: null,
      items: ["SSAQUIZ", "Google"],
    };
  },
};
</script>

<style>
</style>