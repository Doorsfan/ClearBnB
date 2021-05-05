<template>
  <div>
    <Datepicker />
    <DatepickerForAvailableDates @updateDisabledDays="myFunction" /> 
  </div>
</template>

<script setup>
import Datepicker from './components/DatepickerForAvailableDates.vue'
</script>
<script>
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
