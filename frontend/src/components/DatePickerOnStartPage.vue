<template>
  <div class="fromDatePickerDiv">
      <div><label for="fromDatePicker">From the:</label>
      <datepicker id="fromDatePicker" class="fromDatePicker" v-model="startDate" :lower-limit="minimumDate" :upper-limit="endDate"/></div>
  </div>
  <div class="toDatePickerDiv">
    <label for="toDatePicker">To the:</label>
    <datepicker id="toDatePicker" class="toDatePicker" v-model="endDate" :lower-limit="startDate"/>
  </div>
</template>

<script setup>
  import Datepicker from 'vue3-datepicker'
  import { ref } from 'vue' //Ref transmutes a value to become part of a reactive Object
  
  const minimumDate = ref(new Date()) //The earliest date a user can pick is Today
  const startDate = ref(new Date()) //The date for the From datePicker - the start Date
  const endDate = ref(new Date()) //The date for the To datePicker - the end date
  
</script>

<style scoped>
.fromDatePickerDiv, .toDatePickerDiv{
  margin-left:auto;
  margin-right:auto;
  width:max-content;
}
</style>
