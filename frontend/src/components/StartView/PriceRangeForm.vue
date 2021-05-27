<template>
  <div class="firstPriceDiv">
    <p class="minPriceText">Min Price</p>
    <input
      class="minPriceInputForm"
      type="text"
      v-model="minPriceWanted"
      placeholder="Min Price"
    />
  </div>
  <div class="secondPriceDiv">
    <p class="maxPriceText">Max Price</p>
    <input
      class="maxPriceInputForm"
      type="text"
      v-model="maxPriceWanted"
      placeholder="Max Price"
    />
  </div>
</template>
<script>
export default {
  emits: ["updateMyMinPrice", "updateMyMaxPrice"],
  data() {
    return {
      minPriceWanted: "",
      maxPriceWanted: "",
    };
  },
  watch: {
    minPriceWanted() {
      this.$emit("updateMyMinPrice", this.minPriceWanted);
    },
    maxPriceWanted() {
      this.$emit("updateMyMaxPrice", this.maxPriceWanted);
    },
  },
};
</script>
<style scoped>
</style>
