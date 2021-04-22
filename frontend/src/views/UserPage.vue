<template>
  <div style="background-color: #CFE1F6; height: 100%;">
    <Header />
    <div style="display: flex; height: 85%;">
      <div style="display: flex; background-color: gray; width:50%; height: 100%; align-items: center;">
        <span style="font-size: 10rem; color: #4F37DE;"><i class="fas fa-user-circle"></i></span>
      </div>
      <div style="background-color: blue; width:50%; height: 100%;"></div>
    </div>

  </div>
</template>

<script>
import Header from '@/components/common/Header.vue';
export default {
  name: 'UserPage',
  components: {
    Header,
  }
}
</script>

<style>

</style>