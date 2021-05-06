<template>
  <input @change="updateWantedLocation" type="text" placeholder="Location" class="LocationInput" v-model="wantedLocation">
</template>
<script>
  export default {
    data() {
      return {
        wantedLocation: ''
      }
    },
    methods: {
      updateWantedLocation(newLocation){
        console.log(newLocation);
        this.wantedLocation = newLocation
      }
    }
  }
</script>
<style>
</style>