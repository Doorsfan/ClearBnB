<template>
  <div v-if="relevantLease" class="firstDatePickerDiv">
    <p class="AvailableFromP">Available from:</p>
    <datepicker v-if="relevantLease"
      v-model="leaseStartDate"
      placeholder="Lease starts at"    
      :lowerLimit="leaseStartDate"
      :upper-limit="leaseEndDate"
      :disabledDates="disabledDays"
    />
  </div>
  <div v-if="relevantLease" class="secondDatePickerDiv">
    <p class="AvailableToP">Available To:</p>
    <datepicker v-if="relevantLease"
      v-model="leaseEndDate"
      placeholder="Lease ends at"
      :lower-limit="leaseStartDate"
      :upper-limit="leaseEndDate"
      :disabledDates="disabledDays"
      :selected="leaseEndDate"
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
export default {
  props: ['leaseStartDate', 'leaseEndDate'],
  beforeMounted(){
    this.startDate = this.relevantLease.startDate;
    this.endDate = this.relevantLease.endDate;
  },
  created(){
    console.log("RELEVANTLEASSE WAS: ", this.relevantLease);
    if(this.relevantLease.startDate == ''){
      this.relevantLease.startDate = new Date();
    }
    if(this.relevantLease.endDate == ''){
      this.relevantLease.endDate = new Date();
    }
  },
  mounted(){
    console.log("THE RELEVANT LEASE WHEN MOUNTED WAS: ", this.relevantLease);
  },
  data() {
    return {
      startDate: ref(new Date()),
      endDate: ref(new Date()),
      relevantLease: this.$store.getters.getLeaseToBuild,
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