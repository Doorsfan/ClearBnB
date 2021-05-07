<template>
  <div>
    <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
    <LeaseDisplayBox v-for="(leaseItem, index) of allLeases"
      :key="index"
      :lease="leaseItem"/>
  </div>
</template>

<script>
import LeaseDisplayBox from "../components/StartView/LeaseDisplayBox.vue"
import Lease from '../components/Lease.vue'
import DatePickerOnStartPage from '../components/StartView/DatePickerOnStartPage.vue'
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
  let convertedToday = today.getFullYear() + '-' + month + '-' + day
export default {
  components: {
    DatePickerOnStartPage,
    LeaseDisplayBox
  },
  data() {
    return {
      relevantLeases: [
      ], //An array of Lease objects
      allLeases: [
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
        }
      ],
      startDate: convertedToday,
      endDate: ''
    }
  },
  watch:{
    startDate(){
      let index = 0;
      for(let lease of this.allLeases){
        if(lease.startDate >= this.startDate && !this.relevantLeases.includes(lease)){
          console.log("Found a match for", lease);
          this.relevantLeases.push(lease);
          console.log("relevantLeases was: ", this.relevantLeases);
        }
        else if(lease.startDate < this.startDate && this.relevantLeases.includes(lease)){
          console.log("Should remove ", lease);
          this.relevantLeases = [...relevantLeases.slice(0,index), ...relevantLeases.slice(index + 1)]
        }
      }
      index += 1;
    },
    endDate(){
      let index = 0;
      console.log("Should Add relevant leases based on end Date")
      for(let lease of this.allLeases){
        if(lease.endDate <= this.endDate && !this.relevantLeases.includes(lease)){
          console.log("Found a match for", lease);
          this.relevantLeases.push(lease);
          console.log("relevantLeases was: ", this.relevantLeases);
        }
        else if(lease.endDate > this.endDate && this.relevantLeases.includes(lease)){
          console.log("Found a match for", lease);
          //Should remove respective lease
          this.relevantLeases = [...relevantLeases.slice(0,index), ...relevantLeases.slice(index + 1)]
        }
        index += 1;
      }
      //for(let lease of this.allLeases){
        
      //}
    }
  },
  methods:{
    updateSearchEndDate(newDate){
      console.log("The updated end date received was: ", newDate);
      this.endDate = newDate
    },
    updateSearchStartDate(newDate){
      //this.startDate = newDate
      console.log("The updated start date recieved was: ", newDate)
      this.startDate = newDate
    },
    filterBasedOnDate(startDate, endDate){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.date.includes(newDate)){
          this.relevantLeases.push(lease);
        }
      }
    },
    filterBasedOnLocation(newLocation){
      this.relevantLeases = []
      for(let lease of allLeases){
        if(lease.location.includes(newLocation)){
          this.relevantLeases.push(lease);
        }
      }
    }
  }
}
</script>
<style scoped>
</style>
