<template>
  <DetailView />
</template>
<script setup="">
</script>
<script>
import StartPage from './views/StartPage.vue'
import DetailView from './views/DetailView.vue'

export default {
  name: "App",
  components: {
    StartPage,
    DetailView
  },
  data() {
    return {
      
    }
  }
}
</script>
<style>
</style>