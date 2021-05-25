<template>
  <p class="locationInputText">Location</p><input class="locationInput" @change="updateMyLocation" type="text" v-model="locationInput" placeholder="Location">
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
p {
  width:max-content;
  margin-bottom: 10px;
  font-weight: 700;
  color: #007b8e
}
* {
  font-size:20px;
}
.locationInput{
  width:150px;
  padding-left:2px;
  border-radius: 5px;
  border: 1px solid rgb(241, 232, 148);
}
</style>