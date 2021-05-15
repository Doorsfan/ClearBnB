<template>
  <div>
    <div class="startPageDateDiv">
      <LocationInputForm @updateMyLocation="updateMyLocation" />
      <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
      <BedsInputForm @updateMyAmountOfBeds="updateMyAmountOfBeds"/>
      <PriceRangeForm @updateMyMinPrice="updateMyMinPrice" @updateMyMaxPrice="updateMyMaxPrice"/>
    </div>
    <LeaseDisplayBox v-for="(leaseItem, index) of relevantLeases"
      :key="index"
      :lease="leaseItem"/>
    <button @click="populateLeases">Populate Leases</button>
  </div>
</template>
<script>
import LeaseDisplayBox from "../components/StartView/LeaseDisplayBox.vue"
import Lease from '../components/Lease.vue'
import DatePickerOnStartPage from '../components/StartView/DatePickerOnStartPage.vue'
import LocationInputForm from '../components/StartView/LocationInputForm.vue'
import BedsInputForm from '../components/StartView/BedsInputForm.vue'
import PriceRangeForm from '../components/StartView/PriceRangeForm.vue'
import Header from '../components/Header.vue'
import HambugerMenu from '../components/HamburgerMenu.vue'
import User from '../components/User'

  //Load in all the leases of the page from the DB here
  let res = await fetch('/rest/leases')
  let responseInJson = await res.json();
  let originalListOfAllLeases = [];
  for(let eachLease in responseInJson){
    let myLease = {}
    myLease.leaseId = responseInJson[eachLease].id;
    myLease.ownerId = responseInJson[eachLease].ownerId;
    myLease.title = responseInJson[eachLease].title;
    myLease.location = responseInJson[eachLease].location;
    myLease.description = responseInJson[eachLease].description;
    myLease.typeOfHousing = responseInJson[eachLease].typeOfHousing;
    myLease.startDate = responseInJson[eachLease].startDate;
    myLease.endDate = responseInJson[eachLease].endDate;
    myLease.PPPN = responseInJson[eachLease].price;
    myLease.maxGuests = responseInJson[eachLease].maxGuests;
    myLease.beds = responseInJson[eachLease].beds;
    myLease.amenities = {}

    for(let amenity in responseInJson[eachLease].amenities){
      if(amenity.includes("false")){
        if(amenity.includes("wifi")){
          myLease.amenities.wifi = false;
        }
        if(amenity.includes("kitchen")){
          myLease.amenities.kitchen = false;
        }
        if(amenity.includes("washer")){
          myLease.amenities.washer = false;
        }
        if(amenity.includes("heating")){
          myLease.amenities.heating = false;
        }
        if(amenity.includes("airConditioner")){
          myLease.amenities.airConditioner = false;
        }
      }
      if(amenity.includes("true")){
        if(amenity.includes("wifi")){
          myLease.amenities.wifi = true;
        }
        if(amenity.includes("kitchen")){
          myLease.amenities.kitchen = true;
        }
        if(amenity.includes("washer")){
          myLease.amenities.washer = true;
        }
        if(amenity.includes("heating")){
          myLease.amenities.heating = true;
        }
        if(amenity.includes("airConditioner")){
          myLease.amenities.airConditioner = true;
        }
      }
    }
    myLease.imageURLs = []
    for(let i = 0; i < responseInJson[eachLease].imageURLs.length; i++){
      myLease.imageURLs.push(responseInJson[eachLease].imageURLs[i])
    }
    originalListOfAllLeases.push(myLease);
  }
export default {
  emits: ['loggedIn', 'loggedOut'],
  components: {
    DatePickerOnStartPage,
    LeaseDisplayBox,
    LocationInputForm,
    BedsInputForm,
    PriceRangeForm,
    Header,
    HambugerMenu
  },
  data() {
    return {
      relevantLeases: originalListOfAllLeases, //An array of Lease objects
      allLeases: originalListOfAllLeases,
      startDate: this.getTodayInCorrectFormat(),
      endDate: '',
      validStartDate: false,
      validEndDate: false,
      shouldAddLease: false,
      myLocation: '',
      removedBasedOnDate: [],
      removedBasedOnLocation: [],
      myAmountOfBeds: '',
      myMinPrice: '',
      myMaxPrice: ''
    }
  },
  mounted(){
    if(this.$store.getters.getCurrentUser != null){
      $('.signUpLink').text("My Page")
      $('.loginLink').text("Log Out")
      this.$emit("loggedIn", true)
    }
    else{
      $('.signUpLink').text("Sign Up")
      $('.loginLink').text("Log In")
      this.$emit("loggedOut", true)
      this.$store.commit('setUser', null)
    }
  },
  methods:{
    //Can be used to populate the DB with dummy data
    /*
    async populateLeases(){
      let lease = {
        ownerId: 1,
        title: "House in Alaska",
        location: "USA, Alaska",
        description: "Lorem Ipsum",
        typeOfHousing: "House",
        startDate: "2021-05-15",
        endDate: "2021-06-25",
        price: 1200,
        maxGuests: 5,
        beds: 5,
        amenities: ['wifi: true', 'kitchen: true', 'washer: true', 'heating: true', 'airConditioner: true'],
        imageURLs: ["https://images.unsplash.com/photo-1583845112203-29329902332e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=334&q=80",
      "https://images.unsplash.com/photo-1572120360610-d971b9d7767c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"]
      } 
      let res = await fetch('/rest/leases', {
        method: 'POST',
        body: JSON.stringify(lease)
      })
      console.log(await res.json());
    }, */
    returnRelevantLeases(lease){
      let passedMaxPriceCheck = false;
      let passedStartDate = false;
      let passedEndDate = false;
      if(this.myMaxPrice == 0){
        passedMaxPriceCheck = true;
      }
      else if(this.myMaxPrice >= lease.PPPN){
        passedMaxPriceCheck = true;
      }
      if(this.choseStartDate.length == 0){
        passedStartDate = true;
      }
      else if(this.choseStartDate >= lease.startDate){
        passedStartDate = true;
      }
      if(this.choseEndDate.length == 0){
        passedEndDate = true;
      }
      else if(this.choseEndDate <= lease.endDate && this.choseEndDate >= lease.startDate){
        passedEndDate = true;
      }
      let passedLocation = lease.location.toLowerCase().includes(this.myLocation.toLowerCase());
      let passedMinPrice = this.myMinPrice <= lease.PPPN;
      let passedBeds = this.myAmountOfBeds <= lease.beds;
      if(passedLocation && passedMinPrice && passedBeds && passedEndDate && passedStartDate && passedMaxPriceCheck){
        return lease
      }
      return false
    },
    checkWhichFilterToRun(){
      this.relevantLeases = originalListOfAllLeases.filter(this.returnRelevantLeases)
    },
    updateMyAmountOfBeds(newAmountOfBeds){
      if(Number.isNaN(Number(newAmountOfBeds))){
        return
      }
      this.myAmountOfBeds = Number(newAmountOfBeds);
      this.checkWhichFilterToRun()
    },
    updateMyLocation(newLocation){
      this.myLocation = newLocation
      this.checkWhichFilterToRun()
    },
    updateSearchEndDate(newDate){
      this.endDate = newDate
      this.checkWhichFilterToRun()
    },
    updateSearchStartDate(newDate){
      this.startDate = newDate
      this.checkWhichFilterToRun()
    },
    updateMyMinPrice(newMinPrice){
      this.myMinPrice = newMinPrice
      this.checkWhichFilterToRun()
    },
    updateMyMaxPrice(newMaxPrice){
      this.myMaxPrice = newMaxPrice
      this.checkWhichFilterToRun()
    },
    getTodayInCorrectFormat(){
      let today = new Date()
      let month = today.getMonth() + 1;
      let day = today.getDate();
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
      return today.getFullYear() + '-' + month + '-' + day
    },
  }
}
</script>
<style scoped>
.startPageDateDiv{
  width:max-content;
  margin-left:auto;
  margin-right:auto;
  font-size:20px;
}
</style>
