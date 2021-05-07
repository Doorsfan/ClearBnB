<template>
  <div class="mainDiv">
    Lease From:
    <input @change="updateWantedLocation" type="text" :placeholder="today" class="locationInput" v-model="wantedLocation">
  </div>
</template>
<script>
  export default {
    data() {
      return {
        wantedLocation: '',
        today: base.getFullYear() + '-' + month + '-' + day
      }
    },
    methods: {
      updateWantedLocation(){
        this.$emit("changedLocation", this.wantedLocation)
      }
    }
  }
</script>
<style scoped>
.locationInput{
  width:70px;
  padding: 2px;
}
</style>