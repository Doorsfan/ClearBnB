<template>
  <p class="locationInputText">Location</p>
  <input
    class="locationInput"
    @change="updateMyLocation"
    type="text"
    v-model="locationInput"
    placeholder="Location"
  />
</template>
<script>
export default {
  emits: ["updateMyLocation"],
  data() {
    return {
      locationInput: "",
    };
  },
  watch: {
    locationInput() {
      this.$emit("updateMyLocation", this.locationInput);
    },
  },
  methods: {
    updateMyLocation() {
      this.$emit("updateMyLocation", this.locationInput);
    },
  },
};
</script>
<style scoped>
</style>
