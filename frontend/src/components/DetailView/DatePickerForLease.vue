<template>
  <Datepicker :disabledDates="{ dates: bookedDays }"/>
</template>
<script setup="">
  import Datepicker from 'vue3-datepicker'
</script>
<script>
import { ref } from 'vue' //Ref transmutes a value to become part of a reactive Object
let chosenDate = ref(new Date())
export default {
  props: ["relevantLease", "allBookings"],
  data() {
    return {
      bookedDays: this.relevantLease.takenDates
    }
  },
  methods: {
  }
}
</script>