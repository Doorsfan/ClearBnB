<script setup="">
import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue'
import { add } from 'date-fns'
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
      disabledDays: {
        dates: []
      }
    }
  },
  methods: {
    addDisabledDate(dateString){ //"2021-05-20"
      this.disabledDays.dates.push(new Date(dateString))
    },
    getDisabledDays(){
      return this.disabledDays.dates
    },
    getStartDate(){
      return this.startDate
    },
    getEndDate(){
      return this.endDate
    }
  }
}
</script>
<template>
  <div class="myDatePickerDiv">
    Available from:
  <datepicker
    v-model="startDate"
    placeholder="Lease starts at"    
    :lowerLimit="thisDay"
    :disabledDates="disabledDays"
  />
  To:
  <datepicker
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