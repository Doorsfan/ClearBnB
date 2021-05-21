<template>
  <div class="firstDatePickerDiv">
    <p class="AvailableFromP">Available from:</p>
    <datepicker
      v-model="startDate"
      placeholder="Lease starts at"    
      :lowerLimit="originalStartDate"
      :upper-limit="originalEndDate"
      :disabledDates="disabledDays"
    />
  </div>
  <div class="secondDatePickerDiv">
    <p class="AvailableToP">Available To:</p>
    <datepicker
      v-model="endDate"
      placeholder="Lease ends at"
      :lower-limit="originalStartDate"
      :upper-limit="originalEndDate"
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
  emits: ['updatedLeaseEndDate', 'updatedLeaseStartDate', 'updatedDisabledDays'],
  mounted(){
    this.startDate = ref(new Date(this.leaseStartDate))
    this.endDate = ref(new Date(this.leaseEndDate))
    this.originalStartDate = new Date(this.leaseStartDate);
    this.originalEndDate = new Date(this.leaseEndDate);
    if(this.$store.getters.getLeaseToBuild != null){
      this.startDate = this.$store.getters.getLeaseToBuild.startDate
      if(this.startDate == ''){
        this.startDate = new Date();
      }
      this.endDate = this.$store.getters.getLeaseToBuild.endDate
      if(this.endDate == ''){
        this.endDate = new Date();
      }
    }
  },
  data() {
    return {
      thisDay: thisDay,
      startDate: startDate,
      endDate: endDate,
      originalStartDate: new Date(),
      originalEndDate: new Date(),
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