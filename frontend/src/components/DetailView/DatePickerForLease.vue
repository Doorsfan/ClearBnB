<template>
  <Datepicker v-model="pickedDate" :disabledDates="{ dates: bookedDays }"/>
  <button @click="triggerAddDisabledDates">add disable dates</button>
</template>
<script setup="">
  import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue' //Ref transmutes a value to become part of a reactive Object
let chosenDate = ref(new Date())
export default {
  props: ["relevantLease", "allBookings"],
  emits: ["addDisabledDates"],
  data() {
    return {
      pickedDate: chosenDate,
      bookedDays: this.relevantLease.takenDates
    }
  },
  methods: {
    triggerAddDisabledDates(){
      console.log(this.relevantLease);
    },
    findBookedDays(){
      //relevantLease has - Title, location, description, typeOfHousing, startDate, endDate, PPPN, maxGuests, numberOfBeds, amenities, imageURLs
      //
      let result = new Date('2021-05-07')
      console.log(result)
    }
  }
}
</script>