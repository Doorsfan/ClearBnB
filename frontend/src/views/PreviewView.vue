<template>
  <div class="primaryDiv">
    <p class="ResidenceTitle">{{ myLease.title }}</p>
    <div class="bedsAndPartDiv">
      <p class="AvailableBeds">Available Beds: {{ myLease.beds }}</p>
      <p class="splitP">|</p>
      <p class="EntireOrPart">{{ myLease.entireResidence }}</p>
    </div>
    <PreviewCarousel :lease="myLease" />
    <p class="PriceText">Price: {{ myLease.price }} PPPN</p>
    <div class="AmenitiesDiv">
      <p class="AmenitiesTitle">Amenities</p>
      <div class="AmenitiesDiv">
        <p class="wifiText">- {{ myLease.amenities[0] }}</p>
      </div>
      <div class="AmenitiesDiv">
        <p class="kitchenText">- {{ myLease.amenities[1] }}</p>
      </div>
      <div class="AmenitiesDiv">
        <p class="washerText">- {{ myLease.amenities[2] }}</p>
      </div>
      <div class="AmenitiesDiv">
        <p class="heatingText">- {{ myLease.amenities[3] }}</p>
      </div>
      <div class="AmenitiesDiv">
        <p class="AirConditionerText">- {{ myLease.amenities[4] }}</p>
      </div>
      <div class="DescriptionDiv">
        <p class="DescriptionTitle">Description</p>
        <div class="descriptionTextDiv">{{ myLease.description }}</div>
      </div>
      <p class="AvailablePeriods">Available periods</p>
      <div class="fromDateDiv">
        <p class="fromText">From</p>
        <datepicker
          v-model="myStartDate"
          placeholder="Lease is Available From"    
          :lowerLimit="thisDay"
          :upperLimit="myLease.endDate"
          :disabledDates="disabledDays"
        />
      </div>
      <div class="toDateDiv">
        <p class="toText">To</p>
        <datepicker
          v-model="myEndDate"
          placeholder="Lease is Available Until"    
          :lowerLimit="thisDay"
          :upperLimit="myLease.endDate"
          :disabledDates="disabledDays"
        />
      </div>
    </div>
    <div class="buttonDiv">
      <div class="backButtonDiv">
        <router-link class="backButton" to="AddLease">Back</router-link>
      </div>
      <div class="publishButtonDiv">
        <button @click="PublishLease" class="publishButton" value="Publish">Publish</button>
      </div>
    </div>
  </div>
</template>

<script setup="">
  import PreviewCarousel from '../components/PreviewView/PreviewCarousel.vue'
  import Datepicker from 'vue3-datepicker'
</script>
<script>
import store from '../store.js'
export default {
  name: 'Login',
  components: ['PreviewCarousel', 'DatepickerForAvailableDates'],
  methods: {
    PublishLease(){
      console.log("temp");
    }
  },
  mounted() {
    this.myLease = this.$store.getters.getLeaseToBuild
    if(this.myLease.entireResidence == true){
      $('.EntireOrPart').text("Entire Residence")
    }
    else{
      $('.EntireOrPart').text("Part")
    }
    if(this.myLease.amenities[0].includes("true")){
      $('.wifiText').text("Wifi: Yes")
    }
    else{
      $('.wifiText').text("Wifi: No")
    }
    if(this.myLease.amenities[1].includes("true")){
      $('.kitchenText').text("Kitchen: Yes")
    }
    else{
      $('.kitchenText').text("Kitchen: No")
    }
    if(this.myLease.amenities[2].includes("true")){
      $('.washerText').text("Washer: Yes")
    }
    else{
      $('.washerText').text("Washer: No")
    }
    if(this.myLease.amenities[3].includes("true")){
      $('.heatingText').text("Heating: Yes")
    }
    else{
      $('.heatingText').text("Heating: No")
    }
    if(this.myLease.amenities[4].includes("true")){
      $('.AirConditionerText').text("Air Conditioning: Yes")
    }
    else{
      $('.AirConditionerText').text("Air Conditioning: No")
    }
  },
  data(){
    return {
      myStartDate: new Date(),
      myEndDate: new Date(),
      thisDay: new Date(),
      myLease: this.$store.getters.getLeaseToBuild
    } 
  }
}
</script>

<style scoped>
.ResidenceTitle{
  font-weight:bolder;
  font-size:20px;
  margin-top:30px;
}
.splitP{
  width:max-content;
  display:inline-block;
  padding-right:5px;
  padding-left:5px;
  font-weight:bolder;
}

.AvailableBeds, .EntireOrPart{
  width:max-content;
  display:inline-block;
}
.DescriptionTitle{
  font-size:20px;
  font-weight:bolder;
  margin-top:5px;
}
.descriptionTextDiv{
  width:250px;
  margin-left:auto;
  margin-right:auto;
  text-align:left;
}
.AirConditionerText{
  margin-left: -60px;
}
.wifiText{
  padding-left:20.5px;
}
.AmenitiesTitle{
  font-weight:bolder;
  font-size:20px;
  margin-top:5px;
  margin-bottom:2px;
}
.AvailablePeriods{
  margin-top:20px;
  font-weight:bolder;
  font-size:23px;
}
.fromDateDiv, .toDateDiv{
  width:max-content;
  display:inline-block;
  text-align:center;
  margin: 5px;
}
.fromText,.toText{
  font-weight:bolder;
  font-size:20px;
  margin:5px;
}
.primaryDiv{
  text-align:center;
}
button {
  margin:5px;
  padding:3px;
}
.publishButton{
  background-color: lightgreen;
  border-radius:13px;
  padding-left:7px;
  padding-right:7px;
}
.backButtonDiv, .publishButtonDiv{
  width: 111.5px;
  display:inline-block;
}
.backButton{
  background:#eee;
  border: 1px solid black;
  padding:5px;
  padding-left:10px;
  padding-right:10px;
  text-decoration:none;
  color:black;
  font-size:14px;
  border-radius:13px;
}
</style>
