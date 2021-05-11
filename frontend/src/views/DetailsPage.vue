<template>
  <p>hi</p>
</template>

<script>
import PPPN from "../components/PPPN.vue";

let myPPPN = new PPPN();
myPPPN.computeCost();

export default {
  data() {
    return {
      myPrice: "",
      totalPrice: "",
    };
  },
};
</script>

<style>
</style>