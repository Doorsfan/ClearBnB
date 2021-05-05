<template>
  <div>
    <DatepickerForAvailableDates @updateDisabledDays="myFunction" /> 
  </div>
</template>

<script setup>
import DatepickerForAvailableDates from './components/DatepickerForAvailableDates.vue'
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
