<script setup>
import Datepicker from 'vue3-datepicker'
import { ref } from 'vue'
import { add } from 'date-fns'
//NOTE: Datepickers are Readonly in properties, meaning you cannot directly attach CSS to them outside of built in
// API - which only affects the input field in terms of selection of Date
let disabledDate = ref([new Date("2021-05-08"), new Date("2021-05-09")]) //Just add dates here to have them be disabled
//Look into making it able to parse in loop in terms of Datepicker dates
let thisDay = ref(new Date())
</script>

<template>
  <div class="myDatePickerDiv">
  <datepicker
    class="myDatePicker"
    v-model="pickedDate"
    placeholder="Please select a date"
    :lowerLimit="thisDay"
    :disabledDates="{ dates: [disabledDate[0], disabledDate[1]] }"
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