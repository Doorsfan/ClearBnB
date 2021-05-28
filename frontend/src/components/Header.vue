<template>
  <div class="navbar">
    <div v-if="!isSmall" class="pc-nav">
      <div class="left">
        <router-link to="/">
          <img class="sunIconInHeader" src="../assets/ClearBnBLogo2.png" />
        </router-link>
      </div>
      <div class="center"></div>
      <div class="right">
        <router-link
          class="signUpLink"
          v-if="!$store.getters.getCurrentUser"
          to="signUp"
          ><i class="fas fa-user-plus"></i>Sign Up</router-link
        ><router-link
          class="signUpLink"
          v-if="$store.getters.getCurrentUser"
          to="/userPage"
          ><i class="fas fa-user-plus"></i>My Page</router-link
        >
        <span>|</span>
        <router-link
          v-if="!$store.getters.getCurrentUser"
          class="loginLink"
          to="login"
          ><i class="fas fa-sign-in-alt"></i>Login</router-link
        ><router-link
          class="logOutLink"
          v-if="$store.getters.getCurrentUser"
          to="/"
          ><i class="fas fa-sign-in-alt logOutIcon"></i
          ><button @click="logOut" type="button" class="logOutLink">
            Log Out
          </button></router-link
        >
      </div>
    </div>
    <div v-else class="tablet-nav">
      <Hamburger />
      <div class="smallMyPageDiv" v-if="$store.getters.getCurrentUser">
        <router-link
          class="smallMyPagesLink"
          v-if="$store.getters.getCurrentUser"
          to="/userPage"
          ><i class="fas fa-user-plus smallMyPageIcon"></i>My Page</router-link
        >
      </div>
      <div class="smallLogOutDiv" v-if="$store.getters.getCurrentUser">
        <router-link
          class="smallLogOutLink"
          v-if="$store.getters.getCurrentUser"
          to="/"
          ><i class="fas fa-sign-in-alt smallLogOutIcon"></i
          ><button @click="logOut" type="button" class="smallLogOutLink">
            Log Out
          </button></router-link
        >
      </div>
      <div class="smallSignUpDiv" v-if="!$store.getters.getCurrentUser">
        <router-link
          v-if="!$store.getters.getCurrentUser"
          class="signUpLink smallSignupLink"
          to="signUp"
          ><i class="fas fa-user-plus smallSignUpIcon"></i>Sign Up</router-link
        >
      </div>
      <div class="smallLoginDiv" v-if="!$store.getters.getCurrentUser">
        <i class="fas fa-sign-in-alt smallLogInIcon"></i
        ><router-link
          v-if="!$store.getters.getCurrentUser"
          class="smallLoginLink"
          to="login"
          >Login</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import Hamburger from "./HamburgerMenu.vue";
export default {
  props: ["signUp", "login"],
  emits: ["loggedOut"],
  components: {
    Hamburger,
  },
  data() {
    return {
      isSmall: false,
      big: window.matchMedia("(min-width:750px)"),
      currentUser: this.$store.getters.getCurrentUser,
    };
  },
  mounted() {
    this.watchMedia(this.big);
    this.big.addEventListener("change", this.watchMedia);
  },
  methods: {
    logOut() {
      this.$store.commit("setUser", null);
      this.currentUser = null;
    },
    watchMedia: function (x) {
      if (x.matches) this.isSmall = false;
      else this.isSmall = true;
    },
  },
};
</script>

<style scoped>
.smallLogOutDiv {
  width: max-content;
}
.smallLogOutIcon {
  margin-right: 5px;
  display: flex;
  width: max-content;
  top: 9px;
  right: 65px;
}
.smallLogInIcon {
  display: flex;
  top: 22px;
  right: 72px;
  margin-left: 10px;
}
.smallLogOutLink {
  display: flex;
  width: max-content;
  top: 10px;
  right: 15px;
  background: none;
  border: none;
}
.sunIconInHeader {
  max-width:100vw;
  min-height:107px;
}
.logOutLink {
  background: none;
  border: none;
  font-size: 13px;
}
.homeText {
  display: none;
}
.left {
  margin: 0px;
  padding: 0px;
  position: absolute;
  left: 0px;
  top: 0px;
}
.navbar {
  background: linear-gradient(0.25turn, #f69d3c, #ebf8e1, #ebf8e1, #f69d3c);
  width: 100%;
  height: 106px;
}
.homeIcon {
  display: inline-block;
  margin-top: 10px;
  margin-left: 5px;
}
.homeText {
  margin-bottom: 10px;
  margin-left: 3px;
  font-weight: bolder;
  font-size: 16px;
  color: black;
}
.navbar {
  font-family: "mukta", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  padding: 0 20px;
}
.pc-nav,
.tablet-nav {
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
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  height: 210px;
}
.right {
  align-self: flex-start;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: baseline;
  margin-right: 20px;
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
.logOutIcon {
  color: black;
}
</style>
