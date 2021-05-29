<template>
  <div class="datePickersMainDiv">
    <div class="firstDiv">
      <p class="fromTheText">Check-in</p>
      <Datepicker
        id="fromDatePicker"
        class="fromDatePicker startPageDatePicker"
        v-model="startDate"
        :lower-limit="minimumStartDate"
        :upper-limit="endDate"
      />
    </div>
    <div class="secondDiv">
      <p class="toTheText">Check-out</p>
      <Datepicker
        id="toDatePicker"
        class="toDatePicker startPageDatePicker"
        v-model="endDate"
        :lower-limit="minimumEndDate"
      />
    </div>
  </div>
</template>

<script setup>
import Datepicker from 'vue3-datepicker';
</script>
<script>
import { ref } from 'vue'; //Ref transmutes a value to become part of a reactive Object
import PPPN from '../../components/PPPN.js';

const minimumDate = ref(new Date()); //The earliest date a user can pick is Today
const startDate = ref(new Date()); //The date for the From datePicker - the start Date

let myDateHelper = new PPPN();
let endDateSevenDaysAhead = myDateHelper.addDays(new Date(), 7);
const endDate = ref(endDateSevenDaysAhead); //The date for the To datePicker - the end date

export default {
  data() {
    return {
      minimumStartDate: new Date(),
      minimumEndDate: myDateHelper.addDays(new Date(), 1),
      startDate: new Date(),
      endDate: new Date('2021-07-30'),
    };
  },
  watch: {
    startDate() {
      let convertedDate = this.convertDate(this.startDate);
      this.$emit('updateStartDate', convertedDate);
    },
    endDate() {
      let convertedDate = this.convertDate(this.endDate);
      this.$emit('updateEndDate', convertedDate);
    },
  },
  methods: {
    convertDate(dateToConvert) {
      let month = dateToConvert.getMonth() + 1;
      let day = dateToConvert.getDate();
      let zeroBeforeMonth = '';
      let zeroBeforeDay = '';
      if (month < 10) {
        zeroBeforeMonth += '0' + month;
        month = zeroBeforeMonth;
      }
      if (day < 10) {
        zeroBeforeDay += '0' + day;
        day = zeroBeforeDay;
      }
      return dateToConvert.getFullYear() + '-' + month + '-' + day;
    },
  },
};
</script>

<style scoped>
* {
  font-size: 20px;
  font-family: 'mukta';
  text-align: center;
}
p {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 3px;
  color: #029ebb;
}

@media only screen and (max-width: 300px) {
  .firstDiv,
  .secondDiv {
    padding-left: 0px;
  }
}
@media only screen and (min-width: 1550px) {
  div {
    display: inline-block;
  }
}
@media only screen and (min-width: 1330px) {
  .firstDiv {
    margin-left: 10px;
    margin-right: 15px;
  }
  .secondDiv {
    margin-right: 15px;
  }
}
</style>
