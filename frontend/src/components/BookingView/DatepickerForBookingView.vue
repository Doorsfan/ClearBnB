<template>
  <div class="myDatePickerDiv">
  <p class="AvailableFromP">Book From:</p>
  <datepicker
    v-model="startDate"
    :placeholder="myLease.startDate"
    :selected="startDate"
    :lowerLimit="originalStartDate"
    :upper-limit="originalEndDate"
    :disabledDates="{ dates: disabledDays.dates }"
  />
  <p class="AvailableToP">Book To:</p>
  <datepicker
    v-model="endDate"
    :selected="endDate"
    :placeholder="myLease.endDate"
    :lower-limit="startDate"
    :upper-limit="originalEndDate"
    :disabledDates="{ dates: disabledDays.dates }"
  />
  </div>
</template>
<script setup="">
import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue'
import { add } from 'date-fns'
//NOTE: Datepickers are Readonly in properties, meaning you cannot directly attach CSS to them outside of built in
// API - which only affects the input field in terms of selection of Date

const disabledDate = ref(add(new Date(), { days: 5 }))
export default {
  props: ['myLease'],
  emits: ['updatedLeaseEndDate', 'updatedLeaseStartDate', 'updatedDisabledDays'],
  async mounted(){
    let splitMyStartDate = this.myLease.startDate.split('-');
    let myStartYear = splitMyStartDate[0];
    let myStartMonth = Number(splitMyStartDate[1]) - 1;
    let myStartDay = splitMyStartDate[2];
    this.startDate = new Date(myStartYear, myStartMonth, myStartDay)
    this.originalStartDate = new Date(splitMyStartDate[0], Number(splitMyStartDate[1])-1, splitMyStartDate[2])

    let splitMyEndDate = this.myLease.endDate.split('-');
    let myEndYear = splitMyEndDate[0];
    let myEndMonth = Number(splitMyEndDate[1]) - 1;
    let myEndDay = splitMyEndDate[2];
    this.endDate = new Date(myEndYear, myEndMonth, myEndDay);
    this.originalEndDate = new Date(splitMyEndDate[0], Number(splitMyEndDate[1])-1, splitMyEndDate[2])

    let res = await fetch('/rest/bookings')
    let responseInJson = await res.json();
    console.log("my leases attributes were: ", this.myLease);
    for(let booking of responseInJson){
      if(booking.leaseId == this.myLease.id){
        console.log("This lease had bookings on it: ", booking);
        let bookingStartDate = booking.startDate;
        let mySplitStartDate = bookingStartDate.split('-')
        let myStartYear = Number(mySplitStartDate[0]);
        let myStartMonth = Number(mySplitStartDate[1]);
        let myStartDay = Number(mySplitStartDate[2]);

        let bookingEndDate = booking.endDate;
        console.log("The startdate was: ", bookingStartDate);
        console.log("the enddate was: ", bookingEndDate)
        let mySplitEndDate = bookingEndDate.split("-")
        let myEndYear = Number(mySplitEndDate[0]);
        let myEndMonth = Number(mySplitEndDate[1]);
        let myEndDay = Number(mySplitEndDate[2]);
        let takenDay = ''
        console.log(myEndYear >= myStartYear);
        console.log(myStartMonth >= myEndMonth);
        console.log(myStartDay <= myEndDay);
        let differenceInYears = myEndYear - myStartYear;
        let differenceInMonths = myEndMonth - myStartMonth;
        let differenceInDays = myEndDay - myStartDay;
        console.log("The difference in years was: ", differenceInYears)
        console.log("The difference in months was: ", differenceInMonths)
        console.log("The difference in days was: ", differenceInDays)
        while(differenceInDays > 0){
          differenceInDays -= 1;
          console.log("hi");
          this.addDisabledDate(myEndYear, myEndMonth, myEndDay);
          if(myEndDay == 0){
            myEndMonth -= 1;
            if(myEndMonth == 0){
              myEndYear -= 1;
              myEndMonth = 12;
            }
            if(myEndMonth == 11 || myEndMonth == 9 || myEndMonth == 6 || myEndMonth == 4){
              myEndDay = 30
            }
            else if(myEndMonth == 12 || myEndMonth == 10 || myEndMonth == 8 || myEndMonth == 7 || myEndMonth == 5 || myEndMonth == 3 || myEndMonth == 1){
              myEndDay = 31
            }
          }
          else{
            myEndDay -= 1;
          }
        }
      }
    }
    console.log("the response i got was : ", responseInJson);
  },
  data() {
    return {
      startDate: new Date(),
      endDate: new Date(),
      originalEndDate: new Date(),
      originalStartDate: new Date(),
      disabledDate: new Date(),
      disabledDays: { //Can integrate so that there are disabled days, just leaving this open as a possibility
        dates: []
      }
    }
  },
  watch: {
    endDate(){
      this.$emit("updatedLeaseEndDate", this.endDate);
    },
    startDate(){
      this.$emit("updatedLeaseStartDate", this.startDate)
    },
    disabledDays(){
      this.$emit("updatedDisabledDays", this.disabledDays);
    }
  },
  methods: {
    addDisabledDate(year, month, day){ //"2021-05-20"
      console.log("Was called with year: ", year);
      console.log("Was called with month: ", month);
      console.log("Was called with day: ", day);
      this.disabledDays.dates.push(new Date(year + '-' + month + '-' + day))
      console.log(this.disabledDays.dates);
    }
  }
}
</script>
<style scoped>
  .myDatePickerDiv{
    width:210px;

  }
  p{
    margin:2px;
    font-weight:bolder;
  }
  *{
    padding-left: 2px;
  }
</style>