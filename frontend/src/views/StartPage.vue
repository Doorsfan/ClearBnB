<template>
  <div>
    <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
  </div>
</template>

<script setup="">
  import DatePickerOnStartPage from '../components/StartView/DatePickerOnStartPage.vue'
</script>
<script>
export default {
  components: {
    DatePickerOnStartPage
  },
  data() {
    return {
      relevantLeases: [], //An array of Lease objects
      allLeases: [],
      startDate: new Date()
    }
  },
  methods:{
    updateSearchEndDate(newDate){
      console.log("The updated end date received was: ", newDate);
    },
    updateSearchStartDate(newDate){
      //this.startDate = newDate
      console.log("The updated start date recieved was: ", newDate)
    },
    filterBasedOnDate(newDate){
      this.relevantDates = []
      for(let date of allDates){
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
