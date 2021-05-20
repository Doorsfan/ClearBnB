<template>
  <div class="mainDiv">
    <div class="startPageDateDiv">
      <LocationInputForm @updateMyLocation="updateMyLocation" />
      <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
      <BedsInputForm @updateMyAmountOfBeds="updateMyAmountOfBeds"/>
      <PriceRangeForm @updateMyMinPrice="updateMyMinPrice" @updateMyMaxPrice="updateMyMaxPrice"/>
    </div>
    <LeaseDisplayBox v-for="(leaseItem, index) of relevantLeases"
      :key="index"
      :lease="leaseItem"/>
    <button class="pretendToBookButton" @click="pretendToBook">Pretend to Book</button>
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
import HamburgerMenu from '../components/HamburgerMenu.vue'
import User from '../components/User'
import Booking from '../components/Booking.js'
import UserInfo from '../components/UserInfo.js'
import AdminBooking from '../components/AdminBooking.js'
import Profit from '../components/Profit.js'

  //Load in all the leases of the page from the DB here
  let res = await fetch('/rest/leases')
  let responseInJson = await res.json();
  let originalListOfAllLeases = [];
  for(let eachLease in responseInJson){
    let myLease = {}
    myLease.id = responseInJson[eachLease].id;
    myLease.ownerId = responseInJson[eachLease].ownerId;
    myLease.title = responseInJson[eachLease].title;
    myLease.location = responseInJson[eachLease].location;
    myLease.description = responseInJson[eachLease].description;
    myLease.typeOfHousing = responseInJson[eachLease].typeOfHousing;
    myLease.startDate = responseInJson[eachLease].startDate;
    myLease.endDate = responseInJson[eachLease].endDate;
    myLease.price = responseInJson[eachLease].price;
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
    HamburgerMenu
  },
  data() {
    return {
      relevantLeases: originalListOfAllLeases, //An array of Lease objects
      allLeases: originalListOfAllLeases,
      validStartDate: false,
      validEndDate: false,
      shouldAddLease: false,
      myLocation: '',
      removedBasedOnDate: [],
      removedBasedOnLocation: [],
      myAmountOfBeds: '',
      myMinPrice: '',
      myMaxPrice: '',
      choseStartDate: this.getAnyDateInCorrectFormat("2021-05-27"),
      choseEndDate: this.getAnyDateInCorrectFormat("2021-06-30")
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
    async pretendToBook(){
      console.log("Moved to bookingView");
    },
    returnRelevantLeases(lease){
      let passedMaxPriceCheck = false;
      let passedStartDate = false;
      let passedEndDate = false;
      if(this.myMaxPrice == 0){
        passedMaxPriceCheck = true;
      }
      else if(this.myMaxPrice >= Math.round((lease.price * 1.15))){
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
      let passedMinPrice = this.myMinPrice <= Math.round((lease.price * 1.15));
      let passedBeds = lease.beds <= this.myAmountOfBeds || this.myAmountOfBeds == 0;
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
      this.choseEndDate = newDate
      this.checkWhichFilterToRun()
    },
    updateSearchStartDate(newDate){
      this.choseStartDate = newDate
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
    getAnyDateInCorrectFormat(myDate){
      let today = new Date(myDate)
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
.pretendToBookButton{
  width:max-content;
  padding:2px;
  margin-left:auto;
  margin-right:auto;
}
.mainDiv{
  padding-left:5vw;
  padding-right:5vw;
  padding-bottom:2.5vh;
  padding-top:2.5vh;
  background-image: url("../../airplane.jpg");
  background-size:cover;
  background-repeat:no-repeat;
  opacity: 0.8;
  height:max-content;
  width:100vw;
  background-attachment: fixed;
  overflow-x:hidden;
}
.startPageDateDiv{
  width:max-content;
  margin-left:auto;
  margin-right:auto;
  font-size:20px;
  background-color: rgba(218, 224, 224, 0.8);
  padding-left:20px;
  padding-right:20px;
}
@media only screen and (max-width: 700px) {
  .startPageDateDiv {
    width: 100%;
    padding: 5px 5px;
    margin: 2px 0;
    display: block;
    border: 1px solid #ccc;
    border-radius: 1px;
    box-sizing: border-box;
  }
}
</style>
