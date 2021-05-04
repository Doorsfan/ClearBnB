<script setup="">
import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue'
//NOTE: Datepickers are Readonly in properties, meaning you cannot directly attach CSS to them outside of built in
// API - which only affects the input field in terms of selection of Date
let thisDay = ref(new Date())
let startDate = ref(new Date())
let endDate = ref(new Date())
export default {
  data() {
    return {
      thisDay: thisDay,
      startDate: startDate,
      endDate: endDate,
      disabledDays: { //Can integrate so that there are disabled days, just leaving this open as a possibility
        dates: []
      }
    }
  },
  methods: {
    addDisabledDate(dateString){ //"2021-05-20"
      this.disabledDays.dates.push(new Date(dateString))
    },
    updateDisabledLeaseDays(){
      let updatedDisabledDays = this.disabledDays.dates
      $emit("updatedDisabledDays", updatedDisabledDays)
    },
    updateLeaseStartDate(){
      let updatedLeaseStartDate = this.startDate
      $emit("updatedLeaseStartDate", updatedLeaseStartDate)
    },
    updateLeaseEndDate(){
      let updatedLeaseEndDate = this.endDate
      $emit("updatedLeaseEndDate", updatedLeaseEndDate)
    }
  }
}
</script>
<template>
  <div class="myDatePickerDiv">
    Available from:
  <datepicker
    @change="updateLeaseStartDate"
    v-model="startDate"
    placeholder="Lease starts at"    
    :lowerLimit="thisDay"
    :disabledDates="disabledDays"
  />
  To:
  <datepicker
    @change="updateLeaseEndDate"
    v-model="endDate"
    placeholder="Lease ends at"
    :lower-limit="startDate"
  />
  </div>
</template>
<style scoped>
  .myDatePickerDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
  }
</style>