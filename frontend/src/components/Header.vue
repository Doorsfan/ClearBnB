<template>
  <div class="navbar">
    <div v-if="!isSmall" class="pc-nav">
      <div class="left">
        <router-link to="/"><img class="homeIcon" src="/home_icon.png"><p class="homeText">Home</p></router-link>
      </div>
      <div class="center">
        <img src="/src/assets/clearbnb-logo.png">
      </div>
      <div class="right">
        <router-link class="signUpLink" :to="signUp"><i class="fas fa-user-plus"></i>Sign Up</router-link>
        <span>|</span>
        <router-link @click="seeIfLoggingOut" class="loginLink" :to="login"><i class="fas fa-sign-in-alt"></i>Login</router-link>
      </div>
    </div>
    <div v-else class="tablet-nav">
      <Hamburger />
      <router-link class="smallLoginLink" to="login">Login</router-link>
      <router-link class="signUpLink smallSignupLink" :to="signUp"><i class="fas fa-user-plus"></i>Sign Up</router-link>
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
.left{
  margin:0px;
  padding:0px;
  position:absolute;
  left: 10px;
  top: 10px;
}
.navbar{
  background: linear-gradient(0.25turn, #f69d3c, #ebf8e1,#ebf8e1, #f69d3c);
  width:100vw;
}
.homeIcon{
  height:30px;
  display:inline-block;
  width:40px;
  margin-top:10px;
  margin-left:5px;
}
.homeText{
  margin-bottom:10px;
  margin-left:3px;
  font-weight:bolder;
  font-size:16px;
  color:black;
}
.navbar {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  border-bottom: 1px solid #00200c;
  height: max-content;
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
.center {
  margin: 0 10px;
  font-weight: bold;
  width:max-content;
  margin-left:auto;
  margin-right:auto;
}
.right {
  align-self: flex-start;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: baseline;
  margin-right:20px;
}
.right span {
  font-size: 13px;
  margin: 0 10px;
  color: black;
}
.right a {
  font-size: 13px;
  color: black;
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