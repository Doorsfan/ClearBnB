<template>
  <div>
    <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
  </div>
</template>

<script>
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
    DatePickerOnStartPage
  },
  data() {
    return {
      relevantLeases: [], //An array of Lease objects
      allLeases: [],
      startDate: convertedToday,
      endDate: ''
    }
  },
  watch:{
    startDate(){
      console.log("Add relevant leases based on start Date")
      for(let lease of allLeases){
        if(lease.date >= startDate){
          console.log("Found a match for", lease);
        }
      }
    },
    endDate(){
      console.log("Add relevant leases based on end Date")
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
