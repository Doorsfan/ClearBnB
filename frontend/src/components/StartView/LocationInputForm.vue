<template>
  Location: <input class="locationInput" @change="updateMyLocation" type="text" v-model="locationInput" placeholder="Location">
</template>
<script>
export default {
  emits: ['updateMyLocation'],
  data() {
    return {
      locationInput: ''
    }
  },
  watch: {
    locationInput(){
      this.$emit("updateMyLocation", this.locationInput)
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
* {
  font-size:20px;
}
.locationInput{
  width:150px;
  padding-left:2px;
}
</style>