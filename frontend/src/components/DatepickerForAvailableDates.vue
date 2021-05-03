<script setup>
import Datepicker from 'vue3-datepicker'
import { ref } from 'vue'
import { add } from 'date-fns'
//NOTE: Datepickers are Readonly in properties, meaning you cannot directly attach CSS to them outside of built in
// API - which only affects the input field in terms of selection of Date
//this.disabledDays = { dates: [new Date("2021-05-08"), new Date("2021-05-10")] } //Can utilize object literal
//this.disabledDays.dates.push(new Date("2021-05-12")) //And add disabled dates through various means
let thisDay = ref(new Date())
</script>
<script>
export default {
  data() {
    return {
      disabledDays: {
        dates: []
      }
    }
  },
  methods: {
    addDisabledDate(){
      this.disabledDays.dates.push(new Date("2021-05-20"))
    },
    getDisabledDays(){
      return this.disabledDays.dates
    }
  }
}
</script>


<template>
  <div class="myDatePickerDiv">
  <datepicker
    class="myDatePicker"
    v-model="pickedDate"
    placeholder="Please select a date"
    :lowerLimit="thisDay"
    :disabledDates="disabledDays"
  />
  </div>
  <div>
    <button @click="addDisabledDate" class="addDate" value="add a date">Add Date
    </button>
  </div>
</template>
<style scoped>
  .myDatePickerDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
  }
</style>