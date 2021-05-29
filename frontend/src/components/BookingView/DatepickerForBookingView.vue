<template>
  <div class="firstDatePickerDiv">
    <p class="AvailableFromP">Book From The:</p>
    <datepicker
      class="myDatePicker"
      v-model="startDate"
      :lowerLimit="originalStartDate"
      :upper-limit="originalEndDate"
      :disabledDates="{ dates: disabledDays.dates }"
    />
  </div>
  <div class="secondDatePickerDiv">
    <p class="AvailableToP">Book To The:</p>
    <datepicker
      class="myDatePicker"
      v-model="endDate"
      :lower-limit="startDate"
      :upper-limit="originalEndDate"
      :disabledDates="{ dates: disabledDays.dates }"
    />
  </div>
</template>
<script setup="">
import Datepicker from 'vue3-datepicker';
</script>
<script>
import { ref } from 'vue';
import { add } from 'date-fns';
import BookingHelper from '../../components/BookingHelper.js';
import store from '../../store.js';
//NOTE: Datepickers are Readonly in properties, meaning you cannot directly attach CSS to them outside of built in
// API - which only affects the input field in terms of selection of Date

const disabledDate = ref(add(new Date(), { days: 5 }));
export default {
  props: ['myLease'],
  emits: [
    'updatedChosenStartDate',
    'updatedChosenEndDate',
    'updatedDisabledDays',
    'updatedBookingHelper',
    'newDisabledDates',
  ],
  async mounted() {
    this.convertDate();
    this.periodicallyUpdateLeases();
  },
  data() {
    return {
      startDate: ref(new Date()),
      endDate: ref(new Date()),
      originalEndDate: new Date(),
      originalStartDate: new Date(),
      disabledDate: new Date(),
      disabledDays: {
        //Can integrate so that there are disabled days, just leaving this open as a possibility
        dates: [],
      },
      newBookingHelper: '',
    };
  },
  watch: {
    newBookingHelper() {
      this.$emit('updatedBookingHelper', this.newBookingHelper);
    },
    endDate() {
      this.$emit('updatedChosenEndDate', this.endDate);
    },
    startDate() {
      this.$emit('updatedChosenStartDate', this.startDate);
    },
    disabledDays() {
      this.$emit('updatedDisabledDays', this.disabledDays);
    },
  },
  methods: {
    convertDate() {
      let splitMyStartDate = this.myLease.startDate.split('-');
      let myStartYear = splitMyStartDate[0];
      let myStartMonth = Number(splitMyStartDate[1]) - 1;
      let myStartDay = splitMyStartDate[2];
      this.startDate = new Date(myStartYear, myStartMonth, myStartDay);
      this.originalStartDate = new Date(
        splitMyStartDate[0],
        Number(splitMyStartDate[1]) - 1,
        splitMyStartDate[2]
      );

      let splitMyEndDate = this.myLease.endDate.split('-');
      let myEndYear = splitMyEndDate[0];
      let myEndMonth = Number(splitMyEndDate[1]) - 1;
      let myEndDay = splitMyEndDate[2];
      this.endDate = new Date(myEndYear, myEndMonth, myEndDay);
      this.originalEndDate = new Date(
        splitMyEndDate[0],
        Number(splitMyEndDate[1]) - 1,
        splitMyEndDate[2]
      );
    },
    async periodicallyUpdateLeases() {
      let local = this.disabledDays.dates;
      let store = this.$store;
      let getDisabledDatesFunction = this.getDisabledDates;
      let thisContext = this;
      setInterval(function () {
        store.commit('updateBookedDates', local);
        getDisabledDatesFunction();
        thisContext.$emit('newDisabledDates', thisContext.disabledDays.dates);
      }, 100);

    },
    async getDisabledDates() {
      let res = await fetch('/rest/bookings');
      let responseInJson = await res.json();
      this.disabledDays.dates = [];
      for (let booking of responseInJson) {
        if (booking.leaseId == this.myLease.id) {
          let bookingStartDate = booking.startDate;
          let mySplitStartDate = bookingStartDate.split('-');
          let myStartYear = Number(mySplitStartDate[0]);
          let myStartMonth = Number(mySplitStartDate[1]) - 1;
          let myStartDay = Number(mySplitStartDate[2]);
          let bookingEndDate = booking.endDate;
          let mySplitEndDate = bookingEndDate.split('-');
          let myEndYear = Number(mySplitEndDate[0]);
          let myEndMonth = Number(mySplitEndDate[1] - 1);
          let myEndDay = Number(mySplitEndDate[2]);
          let takenDay = '';

          let date1 = new Date(myStartYear, myStartMonth, myStartDay);
          let date2 = new Date(myEndYear, myEndMonth, myEndDay);
          let differenceInTime = date2.getTime() - date1.getTime();
          let differenceInDays = differenceInTime / (1000 * 3600 * 24);
          while (differenceInDays > -1) {
            differenceInDays -= 1;
            this.disabledDays.dates.push(new Date(myEndYear, myEndMonth, myEndDay));
            if (myEndDay == 1) {
              myEndMonth -= 1;
              if (myEndMonth == -1) {
                myEndYear -= 1;
                myEndMonth = 11;
              }
              if ( //december, october, augusti, july, may, march, january
              // 11, 9, 7, 6, 4, 2, 0
                myEndMonth == 11 ||
                myEndMonth == 9 ||
                myEndMonth == 7 ||
                myEndMonth == 6 ||
                myEndMonth == 4 ||
                myEndMonth == 2 ||
                myEndMonth == 0
              ) {
                myEndDay = 31;
              }
              else if ( 
                myEndMonth == 10 
                || myEndMonth == 8 
                || myEndMonth == 5 
                || myEndMonth == 3 
              ) 
              {
                myEndDay = 30;
              }
              else {
                myEndDay = 28;
              }
            } else {
              myEndDay -= 1;
            }
          }
        }
      }
    }
  },
};
</script>
<style scoped>
*{
  font-family:'mukta';
}
div {
  width: max-content;
  display: inline-block;
}
p {
  padding-bottom: 5px;
}
.firstDatePickerDiv {
  margin-right: 20px;
}
.secondDatePickerDiv {
  margin-left: 20px;
}
@media only screen and (max-width: 680px) {
  .firstDatePickerDiv,
  .secondDatePickerDiv {
    display: block;
    margin: 0px;
  }
}
@media only screen and (max-width: 550px) {
  .firstDatePickerDiv,
  .secondDatePickerDiv {
    display: block;
    margin: 0px;
  }
}
@media only screen and (max-width: 300px) {
  .firstDatePickerDiv,
  .secondDatePickerDiv {
    margin: 0px;
  }
}
</style>
