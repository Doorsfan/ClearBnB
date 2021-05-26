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
  emits: ['updateMyLocation'],
  data() {
    return {
      locationInput: '',
    };
  },
  watch: {
    locationInput() {
      this.$emit('updateMyLocation', this.locationInput);
    },
  },
  methods: {
    updateMyLocation() {
      this.$emit('updateMyLocation', this.locationInput);
    },
  },
};
</script>
<style scoped>
*{
  font-family:'mukta';
}
p {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 3px;
}

.locationInputText{
  font-size: 20px;
}
.locationInput {
  width: 150px;
  padding: 5px;
  padding: 11.2px 22.4px;
  font-size:18px;
}
</style>
