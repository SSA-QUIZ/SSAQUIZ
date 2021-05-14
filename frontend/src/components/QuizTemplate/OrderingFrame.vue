<template>
  <div id="ordering-frame-container">
    <!-- <template v-for="index in 4">
      <OrderingButton  
        mode="orderingFrame" 
        :key="index"
        :index="index"
      />
    </template> -->
    <template v-for="index in 4">
      <OrderingButton
        v-if="solvingArray[index-1] == 0"
        mode="orderingFrame"
        :key="index"
        :index="index"
      />
      <OrderingButton
        v-else
        mode="solving" 
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
  props: {
    selectedIndex: {
      type: Number,
    },
    solvingArray: {
      type: Array,
    },
  },
  computed: {
    temp: function () {
      console.log(this.solvingArray);
      return this.solvingArray;
    }
  },
  watch: {
    solvingArray: {
      deep: true,
      handler(newVal) {
        console.log(newVal);
      }
    }
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