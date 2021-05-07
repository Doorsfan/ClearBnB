<template>
  <div>
    <Header />
    <BookingView />
    <DatePickerOnStartPage />
    <Carousel />

  </div>
</template>

<script setup>
import Header from "./components/Header.vue";
import BookingView from "./views/BookingView.vue"
import DatePickerOnStartPage from './components/DatePickerOnStartPage.vue'
import Carousel from './components/Carousel.vue'
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