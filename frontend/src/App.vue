<template>
  <header>
    <Header />
  </header>

  <main class="app-main">
    <router-view />
  </main>

  <footer>
    <Footer />
  </footer>
</template>
<script setup="">
</script>
<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: "App",
  components: {
    Header,
    Footer
  },
  data() {
    return {
      
    }
  }
}
</script>
<style>
.app-main{
  height:max-content;
  padding-top:5px;
  padding-bottom:20px;
}
</style>