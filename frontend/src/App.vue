<template>
  <header>
    <Header />
  </header>
  <main class="app-main">
    <router-view />
  </main>
  <footer class="myFooter">
    <Footer />
  </footer>
</template>
<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
  data() {
    return {
      signUp: 'signUp',
      login: 'login',
      currentUser: this.$store.getters.getCurrentUser,
    };
  },
  methods: {
    loggedIn() {
      this.signUp = 'userPage';
      this.login = '';
    },
    loggedOut() {
      this.signUp = 'signUp';
      this.login = 'login';
      $('.signUpLink').text('Sign Up');
      $('.loginLink').text('Log In');
    },
  },
};
</script>
<style scoped></style>
