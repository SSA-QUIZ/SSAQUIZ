<template>
  <div id="ordering-frame-container">
    <template v-for="(i, index) in solvingString">
      <OrderingButton
        v-if="i == '0'"
        mode="orderingFrame"
        :key="index"
        :index="index"
      />
      <OrderingButton
        v-else
        mode="solving" 
        :key="index"
        :index="i"
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
  props: {
    selectedIndex: {
      type: Number,
    },
    solvingString: {
      type: String,
    },
  },
}

</script>

<style scoped>
#ordering-frame-container {
  display: flex;
  width: 100%;
  height: 40%;
}
</style>