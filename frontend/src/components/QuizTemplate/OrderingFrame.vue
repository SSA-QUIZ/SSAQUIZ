<template>
  <div id="ordering-frame-container">
    <template v-for="index in 4">
      <OrderingButton  
        mode="orderingFrame" 
        :key="index"
        :index="index"
      />
    </template>
  </div>
</template>

<script>
import OrderingButton from '@/components/common/OrderingButton.vue';

export default {
  name: "OrderingFrame",
  components: {
    OrderingButton
  },
}
</script>

<style>
#ordering-frame-container {
  display: flex;
  width: 100%;
  height: 98%;
}
</style>