<template>
  <div>
    <Header />
    <BookingView />
  </div>
</template>

<script setup>
import Header from "./components/Header.vue";
import BookingView from "./views/BookingView.vue"
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
