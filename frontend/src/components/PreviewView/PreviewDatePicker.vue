<template>
  <div v-if="relevantLease" class="firstDatePickerDiv">
    <p class="AvailableFromP">Available from:</p>
    <datepicker
      v-if="relevantLease"
      v-model="relevantLease.startDate"
      placeholder="Lease starts at"
      :lowerLimit="$store.getters.getLeaseToBuild.startDate"
      :upper-limit="$store.getters.getLeaseToBuild.endDate"
      :disabledDates="disabledDays"
      :selected="$store.getters.getLeaseToBuild.startDate"
    />
  </div>
  <div v-if="relevantLease" class="secondDatePickerDiv">
    <p class="AvailableToP">Available To:</p>
    <datepicker
      v-if="relevantLease"
      v-model="relevantLease.endDate"
      placeholder="Lease ends at"
      :lower-limit="$store.getters.getLeaseToBuild.startDate"
      :upper-limit="$store.getters.getLeaseToBuild.endDate"
      :disabledDates="disabledDays"
      :selected="$store.getters.getLeaseToBuild.endDate"
    />
  </div>
</template>
<script setup="">
import Datepicker from 'vue3-datepicker';
</script>
<script>
import { ref } from 'vue';
//NOTE: Datepickers are Readonly in properties, meaning you cannot directly attach CSS to them outside of built in
// API - which only affects the input field in terms of selection of Date
export default {
  props: ['leaseStartDate', 'leaseEndDate'],
  beforeMounted() {
    this.startDate = this.relevantLease.startDate;
    this.endDate = this.relevantLease.endDate;
  },
  created() {
    if (this.relevantLease.startDate == '') {
      this.relevantLease.startDate = new Date();
    }
    if (this.relevantLease.endDate == '') {
      this.relevantLease.endDate = new Date();
    }
  },
  data() {
    return {
      startDate: ref(new Date()),
      endDate: ref(new Date()),
      relevantLease: this.$store.getters.getLeaseToBuild,
      disabledDays: {
        //Can integrate so that there are disabled days, just leaving this open as a possibility
        dates: [],
      },
    };
  },
  methods: {
    addDisabledDate(dateString) {
      //"2021-05-20"
      this.disabledDays.dates.push(new Date(dateString));
    },
  },
};
</script>
<style scoped>
*{
  font-family:'mukta';
}
.myDatePickerDiv {
  width: 210px;
}
p {
  margin: 2px;
  font-weight: bolder;
}
* {
  padding-left: 2px;
}
</style>
