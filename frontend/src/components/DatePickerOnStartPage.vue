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
</script>
<script>
export default {
  methods:
  {
    
  }
}
</script>


<style scoped>
.fromDatePickerDiv, .toDatePickerDiv{
  margin-left:auto;
  margin-right:auto;
  width:max-content;
}
</style>
