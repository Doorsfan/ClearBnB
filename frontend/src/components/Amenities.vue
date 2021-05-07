<template>
  <p class="amenity" v-if="wifi">Wifi&#32;</p>
  <p class="amenity" v-if="kitchen">Kitchen&#32;</p>
  <p class="amenity" v-if="washer">Washer&#32;</p>
  <p class="amenity" v-if="heating">Heating&#32;</p>
  <p class="amenity" v-if="airConditioner">airConditioner&#32;</p>
</template>

<script>
export default {
  data() {
    return {
      wifi: false, // To test: change all true
      kitchen: false,
      washer: false,
      heating: false,
      airConditioner: false,
    };
  },
};
</script>

<style scoped>
.amenity {
  display: inline;
}
</style>