<template>
  <div>
    <Header />
    <router-view></router-view>
    <TestingFetches />
  </div>
</template>

<script setup>
import Header from "./components/Header.vue";
//import HelloWorld from "./components/HelloWorld.vue";

// This starter template is using Vue 3 experimental <script setup> SFCs
// Check out https://github.com/vuejs/rfcs/blob/script-setup-2/active-rfcs/0000-script-setup.md
import TestingFetches from "./components/TestingFetches.vue";
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
