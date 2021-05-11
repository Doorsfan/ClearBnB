<template>
  <p class="amenitiesText">Amenities:</p>
  <div class="AddAmenitiesDiv">
    <div class="firstDiv">
      <p class="wifiText">Wifi: </p><input type="checkbox" v-model="hasWifi" class="wifiBox">
      <p class="kitchenText">Kitchen: </p><input type="checkbox" v-model="hasKitchen" class="kitchenBox">
    </div>
    <div class="secondDiv">
      <p class="washerText">Washer: </p><input type="checkbox" v-model="hasWasher" class="washerBox">
      <p class="heatingText">Heating: </p><input type="checkbox" v-model="hasHeating" class="heatingBox">
    </div>
    <div class="thirdDiv">
      <p class="airConditionerText">airConditioner: </p><input type="checkbox" v-model="hasAirConditioner" class="airConditionerBox">
    </div>
  </div>
</template>
<script>
export default {
  //Åtgärd:
  // Parprogrammera från och med idag 13:00, sprida kunskap, sprida kompetens, öka produktivitet
  // Sprint 2 - Delegera annan Scrum Master, Mjukt förslag: Syftet är att en person som är aktiv men inte är lika produktiv ska kunna kan få en större förankring i projektet - vilket kan hjälpa produktiviteten av arbetet
  // 
  data() {
    return {
      hasWifi: false,
      hasKitchen: false,
      hasWasher: false,
      hasHeating: false,
      hasAirConditioner: false
    }
  }
}
</script>
<style scoped>
.airConditionerText{
  margin-left:-20px;
}
.amenitiesText{
  margin:3px;
  margin-top:8px;
  font-weight:bolder;
}
.firstDiv, .secondDiv, .thirdDiv{
  text-align:center;
}
.thirdDiv{
  margin:3px;
}
.wifiBox{
  margin-right:20px;
}
.washerBox{
  margin-right:20px;
}
.heatingBox{
  margin-right:32px;
}
input{
  text-align:left;
}
div{
  text-align:left;
}
p{
  width:max-content;
  display:inline-block;
}
input{
  margin:5px;
  margin-left:3px;
}
</style>