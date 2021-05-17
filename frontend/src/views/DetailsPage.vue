<template>
  <p>hi</p>
</template>

<script>
import PPPN from "../components/PPPN.js";

let myPPPN = new PPPN();

//Test of computeCost
let days = 7;
let price = 10;
console.log("Testing computCost: ", myPPPN.computeCost(days, price));
console.log("Testing convertToDate: ", myPPPN.convertToDate("2014-10-01"));
console.log(
  "Testing computeDateRange:",
  myPPPN.computeDateRange("2014-10-01", "2014-10-09")
);

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