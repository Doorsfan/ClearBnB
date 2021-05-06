<template>
<div>
  <div class="firstDiv">
    <label for="fromDatePicker">From the:</label>
    <Datepicker @change="updateStartDate" id="fromDatePicker" class="fromDatePicker" v-model="startDate" :lower-limit="minimumDate" :upper-limit="endDate"/>
  </div>
  <div class="secondDiv">
    <label for="toDatePicker">To the:</label>
    <Datepicker @change="updateEndDate" id="toDatePicker" class="toDatePicker" v-model="endDate" :lower-limit="startDate"/>
  </div>
  <button @click="hello" class="fel">hi</button>
</div>
</template>

<script setup>
  import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue' //Ref transmutes a value to become part of a reactive Object
  
const minimumDate = ref(new Date()) //The earliest date a user can pick is Today
const startDate = ref(new Date()) //The date for the From datePicker - the start Date
const endDate = ref(new Date()) //The date for the To datePicker - the end date

export default {
  //emits: ['updateEndDate', 'updateStartDate'],
  data() {
    return {
      minimumDate: minimumDate,
      startDate: startDate,
      endDate: endDate
    }
  },
  watch: {
    startDate(){
      console.log("Start date was changed");
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
