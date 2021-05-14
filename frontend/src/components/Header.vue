<template>
  <div class="navbar">
    <div v-if="!isSmall" class="pc-nav">
      <div class="left">
        <router-link to="/">Home</router-link>
      </div>
      <div class="center">
        <router-link to="userPage">User Page</router-link>
        <router-link to="AddLease">Add Lease</router-link>
        <router-link to="BookingView">Booking View</router-link>
      </div>
      <div class="right">
        <router-link class="signUpLink" :to="signUp"><i class="fas fa-user-plus"></i>Sign Up</router-link>
        <span>|</span>
        <router-link @click="seeIfLoggingOut" class="loginLink" :to="login"><i class="fas fa-sign-in-alt"></i>Login</router-link>
      </div>
    </div>
    <div v-else class="tablet-nav">
      <Hamburger />
      <router-link to="/">Home</router-link>
      <router-link to="login">Login</router-link>
    </div>
  </div>
</template>

<script>
import Hamburger from "./HamburgerMenu.vue";
export default {
  props:['signUp', 'login'],
  emits: ['loggedOut'],
  components: {
    Hamburger,
  },
  data() {
    return {
      isSmall: false,
      big: window.matchMedia("(min-width:750px)"),
    };
  },
  mounted() {
    this.watchMedia(this.big);
    this.big.addEventListener("change", this.watchMedia);
  },
  methods: {
    seeIfLoggingOut(){
      if($('.loginLink').text() == 'Log Out'){
        this.$store.commit('setUser', null)
        this.$emit("loggedOut", true)
      }
    },
    watchMedia: function (x) {
      if (x.matches) this.isSmall = false;
      else this.isSmall = true;
    },
  },
};
</script>

<style scoped>
.navbar {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  border-bottom: 1px solid #00200c;
  height: 70px;
  padding: 0 20px;
}
.pc-nav,
.tablet-nav {
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
a {
  font-size: 17px;
  text-decoration: none;
  color: rgb(63, 63, 63);
}
.center a {
  margin: 0 10px;
  font-weight: bold;
}
.right {
  align-self: flex-start;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: baseline;
}
.right span {
  font-size: 13px;
  margin: 0 10px;
  color: rgb(172, 172, 172);
}
.right a {
  font-size: 13px;
  color: rgb(172, 172, 172);
  transition: color 0.3s;
}
.right i {
  font-size: 13px;
  margin-right: 5px;
}
.right a:hover {
  color: rgb(112, 112, 112);
}
.tablet-nav a {
  font-weight: bold;
}
</style>