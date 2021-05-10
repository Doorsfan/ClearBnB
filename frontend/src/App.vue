<template>
  <Header/>
  <router-view></router-view>
</template>
<script setup="">
</script>
<script>
import Header from './components/Header.vue'

export default {
  name: "App",
  components: {
    Header
  },
  data() {
    return {
      
    }
  }
}
</script>
<style>
</style>