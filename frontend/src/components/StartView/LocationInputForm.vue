<template>
    Location: <input class="locationInput" type="text" v-model="locationInput" placeholder="Location">
</template>
<script>
export default {
  data() {
    return {
      locationInput: ''
    }
  },
  methods: {
    updateMyLocation(){
      this.$emit("updateMyLocation", this.locationInput);
    }
  }
}
</script>
<style scoped>
.locationInput{
  width:150px;
}
</style>