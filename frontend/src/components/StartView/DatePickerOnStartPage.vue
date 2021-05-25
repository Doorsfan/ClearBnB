<template>
<div class="datePickersMainDiv">
  <div class="firstDiv">
    <p class="fromTheText">Check in</p>
    <Datepicker id="fromDatePicker" class="fromDatePicker" v-model="startDate" :lower-limit="minimumStartDate" :upper-limit="endDate"/>
  </div>
  <div class="secondDiv">
    <p class="toTheText">Check out</p>
    <Datepicker id="toDatePicker" class="toDatePicker" v-model="endDate" :lower-limit="minimumEndDate"/>
  </div>
</div>
</template>

<script setup>
  import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue' //Ref transmutes a value to become part of a reactive Object
import PPPN from '../../components/PPPN.js';
  
const minimumDate = ref(new Date()) //The earliest date a user can pick is Today
const startDate = ref(new Date()) //The date for the From datePicker - the start Date

//To make the Date be able to just add 1 day, we can define a function on it's Prototype
let myDateHelper = new PPPN();
let endDateSevenDaysAhead = myDateHelper.addDays(new Date(),7);
const endDate = ref(endDateSevenDaysAhead) //The date for the To datePicker - the end date

export default {
  //emits: ['updateEndDate', 'updateStartDate'],
  data() {
    return {
      minimumStartDate: new Date(),
      minimumEndDate: myDateHelper.addDays(new Date(),1),
      startDate: new Date("2021-05-27"),
      endDate: new Date("2021-07-30")
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
* {
  font-size: 20px;
}
.v3dp__datepicker{
  --text-color: 
  var(--vdp-text-color, #007b8e);
  --elem-hover-bg-color: 
  var(--vdp-hover-bg-color, #009bd5);
  --elem-selected-bg-color: 
  var(--vdp-hover-bg-color, #009bd5);
}
p {
  width:max-content;
  padding-bottom: 8px;
  color: #007b8e;
  font-weight: 700;
}
@media only screen and (max-width: 300px) {
  div {
    width:max-content;
    padding:0px;
    margin: 0px;
  }
}
@media only screen and (min-width: 1330px) {
  .firstDiv, .secondDiv{
    display:inline-block;
  }
  .firstDiv{
    margin-left: 10px;
    margin-right: 15px;
  }
  .secondDiv{
    margin-right:15px;
  }

}
</style>
