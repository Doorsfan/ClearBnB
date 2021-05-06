<template>
  <div class="mainDiv">
    Lease From:
    <input @change="updateWantedLocation" type="text" :placeholder="today" class="locationInput" v-model="wantedLocation">
  </div>
</template>
<script>
let base = new Date();
let month = base.getMonth()+1;
let day = base.getDate();
let zeroBeforeMonth = '';
let zeroBeforeDay = '';
if(month < 10){
  zeroBeforeMonth += '0' + month
  month = zeroBeforeMonth
}
if(day < 10){
  zeroBeforeDay += '0' + day
  day = zeroBeforeDay
}
  export default {
    data() {
      return {
        wantedLocation: '',
        today: base.getFullYear()+'-'+month+'-'+day
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