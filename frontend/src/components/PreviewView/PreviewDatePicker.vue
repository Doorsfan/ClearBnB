<template>
  <div class="firstDatePickerDiv">
    <p class="AvailableFromP">Available from:</p>
    <datepicker
      v-model="leaseStartDate"
      placeholder="Lease starts at"    
      :lowerLimit="leaseStartDate"
      :upper-limit="leaseEndDate"
      :disabledDates="disabledDays"
    />
  </div>
  <div class="secondDatePickerDiv">
    <p class="AvailableToP">Available To:</p>
    <datepicker
      v-model="leaseEndDate"
      placeholder="Lease ends at"
      :lower-limit="leaseStartDate"
      :upper-limit="leaseEndDate"
    />
  </div>
</template>
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
  props: ['leaseStartDate', 'leaseEndDate'],
  mounted(){
    
  },
  data() {
    return {
      thisDay: thisDay,
      startDate: this.startDate,
      endDate: this.endDate,
      disabledDays: { //Can integrate so that there are disabled days, just leaving this open as a possibility
        dates: []
      }
    }
  },
  methods: {
    addDisabledDate(dateString){ //"2021-05-20"
      this.disabledDays.dates.push(new Date(dateString))
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