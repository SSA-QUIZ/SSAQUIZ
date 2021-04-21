<template>
  <header>
    <v-row id="header-row">
      <v-col cols="4">
        <img
          width="100%"
          src="@/assets/images/SSAQUIZ.png">
      </v-col>
      <v-spacer></v-spacer>
      <v-col cols="7" align="end">
        <div id="nickname">{{ nickname }}</div>
        <div id="score">{{ score }}</div>
      </v-col>
    </v-row>
  </header>
</template>

<script>
export default {
  props: [
    'nickname',
    'score'
  ]
}
</script>

<style>

</style>