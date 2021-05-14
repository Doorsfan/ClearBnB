<template>
  <header>
    <Header @loggedOut="loggedOut" :signUp="signUp" :login="login"/>
  </header>

  <main class="app-main">
    <router-view @loggedIn="loggedIn"/>
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
      signUp: 'signUp',
      login: 'login'
    }
  },
  methods:{
    loggedIn(){
      this.signUp = 'userPage'
      this.login = ''
    },
    loggedOut(){
      this.signUp = 'signUp'
      this.login = 'login'
      $('.signUpLink').text("Sign Up")
      $('.loginLink').text("Log In")
    }
  }
}
</script>
<style>
</style>