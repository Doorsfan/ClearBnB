<template>
  <div>
    <div class="startPageDateDiv">
      <LocationInputForm @updateMyLocation="updateMyLocation" />
      <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
    </div>
    <LeaseDisplayBox v-for="(leaseItem, index) of relevantLeases"
      :key="index"
      :lease="leaseItem"/>
  </div>
</template>
<script>
import LeaseDisplayBox from "../components/StartView/LeaseDisplayBox.vue"
import Lease from '../components/Lease.vue'
import DatePickerOnStartPage from '../components/StartView/DatePickerOnStartPage.vue'
import LocationInputForm from '../components/StartView/LocationInputForm.vue'

  //Load in all the leases of the page from the DB here
  let originalListOfAllLeases = [
      {
    "ownerId": "rmarkie5@list-manage.com",
    "title": "Entire Flat",
    "location": "Holiday Resort",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ipsum mi, finibus ac placerat congue, placerat a enim. Phasellus eleifend, nulla et tincidunt pharetra, metus magna venenatis magna, quis mollis turpis tortor eget quam. Curabitur porta enim non augue auctor, vel bibendum sem iaculis. Nam orci mi, hendrerit quis pulvinar sed, rutrum nec lorem. Pellentesque euismod purus fringilla, mollis odio quis, fringilla purus. Aliquam erat volutpat. Aliquam id neque vel massa mattis hendrerit. Vestibulum finibus egestas eleifend. Etiam ut erat rutrum, maximus odio porta, euismod est.",
    "typeOfHousing": "Flat",
    "startDate": "2021-08-01",
    "endDate": "2021-09-30",
    "PPPN": 220,
    "maxGuests": 4,
    "beds": 3,
    "amenities": {
      "wifi": true,
      "kitchen": true,
      "washer": true,
      "heating": false,
      "airConditioner": true
    },
    "imageURLs": [
      "https://images.unsplash.com/photo-1613377739358-92aca92e9e13?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
      "https://images.unsplash.com/photo-1618660920685-4505debb785a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80"
    ]
  },
  {
          "ownerId": "balder0@fda.gov",
          "title": "Lovely cottage",
          "location": "Bellevue Island",
          "description": "Cosy 4 room cottage in central Bellevue. The cottage is located in the wonderful district, St Palmas with city nearby as well as green parks, local shops, and restaurants. Within walking distance to the heart of Bellevue. You will have the whole cottage for yourself.",
          "typeOfHousing": "Entire Residence",
          "startDate": "2021-05-27",
          "endDate": "2021-07-30",
          "PPPN": 45,
          "maxGuests": 4,
          "beds": 3,
          "amenities": {
            "wifi": true,
            "kitchen": true,
            "washer": true,
            "heating": true,
            "airConditioner": false
          },
          "imageURLs": [
            "https://images.unsplash.com/photo-1588046130717-0eb0c9a3ba15?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1438&q=80",
            "https://images.unsplash.com/photo-1598204326847-aeedbc139508?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1534&q=80"
          ]
        }];
export default {
  components: {
    DatePickerOnStartPage,
    LeaseDisplayBox,
    LocationInputForm
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
      removedBasedOnLocation: []
    }
  },
  watch:{
    startDate(){
      this.filterBasedOnDate()
    },
    endDate(){
      this.filterBasedOnDate()
    }
  },
  methods:{
    filterBasedOnDateAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && lease.startDate <= this.startDate && this.endDate <= lease.endDate){
          this.relevantLeases.push(lease)
        }
      }
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
    filterBasedOnDateAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDate(){
      if(this.myLocation.length > 0){
        this.filterBasedOnDateAndLocation()
        return
      }
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnLocation(){
      if(this.endDate.length > 0){
        this.filterBasedOnDateAndLocation()
        return;
      }
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    updateMyLocation(newLocation){
      this.myLocation = newLocation
      this.filterBasedOnLocation()
    },
    updateSearchEndDate(newDate){
      this.endDate = newDate
    },
    updateSearchStartDate(newDate){
      this.startDate = newDate
    },
  }
}
</script>
<style scoped>
.startPageDateDiv{
  width:max-content;
  margin-left:auto;
  margin-right:auto;
}
</style>
