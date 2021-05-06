<template>
<div>
  <div class="firstDiv">
    <label for="fromDatePicker">From the:</label>
    <Datepicker id="fromDatePicker" class="fromDatePicker" v-model="startDate" :lower-limit="minimumStartDate" :upper-limit="endDate"/>
  </div>
  <div class="secondDiv">
    <label for="toDatePicker">To the:</label>
    <Datepicker id="toDatePicker" class="toDatePicker" v-model="endDate" :lower-limit="minimumEndDate"/>
  </div>
</div>
</template>

<script setup>
  import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue' //Ref transmutes a value to become part of a reactive Object
  
const minimumDate = ref(new Date()) //The earliest date a user can pick is Today
const startDate = ref(new Date()) //The date for the From datePicker - the start Date

//To make the Date be able to just add 1 day, we can define a function on it's Prototype
Date.prototype.addDays = function(days) {
  var date = new Date(this.valueOf());
  date.setDate(date.getDate() + days);
  return date;
}
let endDateOneDayAhead = new Date().addDays(1);
const endDate = ref(endDateOneDayAhead) //The date for the To datePicker - the end date

export default {
  //emits: ['updateEndDate', 'updateStartDate'],
  data() {
    return {
      minimumStartDate: new Date(),
      minimumEndDate: new Date().addDays(1),
      startDate: startDate,
      endDate: endDate
    }
  },
  watch: {
    startDate(){ //Keeps a track of when startDate has changed
      let convertedDate = this.convertDate(this.startDate)
      this.$emit("updateStartDate", convertedDate)
    },
    endDate(){
      let convertedDate = this.convertDate(this.endDate)
      this.$emit("updateEndDate", convertedDate)     
    }
  },
  methods: {
    hello(){
      console.log("hi");
      let updateStartDate = this.startDate
      updateStartDate = this.convertDate(updateStartDate)
      this.$emit("updateStartDate", updateStartDate);
    },
    updateStartDate(){
      console.log("Was updated");
      //let updateStartDate = this.startDate
      //updateStartDate = this.convertDate(updateStartDate)
      //this.$emit("updateStartDate", updateStartDate)
    },
    updateEndDate(){
      //let updateEndDate = this.endDate
      //updateEndDate = this.convertDate(updateStartDate)
      //this.$emit("updateEndDate", 5)
    },
    convertDate(dateToConvert){
      let month = dateToConvert.getMonth() + 1;
      let day = dateToConvert.getDate();
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
      return dateToConvert.getFullYear() + '-' + month + '-' + day
    } 
  }
}
</script>


<style scoped>
div{
  width:max-content;
  display:inline-block;
  margin: 10px;
}
</style>
