<template>
  <div class="hamburger">
    <i @click="openMenu" class="fa fa-bars"></i>
  </div>
  <div v-bind:class="[open ? 'active' : null, 'router-links']">
    <i @click="openMenu" class="fas fa-times"></i>
    <p><router-link to="/">Home</router-link></p>
    <p>
      <router-link v-if="$store.getters.getCurrentUser" to="/userPage"
        >User Page</router-link
      >
    </p>
    <p>
      <router-link v-if="!$store.getters.getCurrentUser" to="/login"
        >Log in</router-link
      >
    </p>
    <p>
      <router-link v-if="!$store.getters.getCurrentUser" to="/signUp"
        >Sign up</router-link
      >
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      open: false,
    };
  },
  methods: {
    openMenu() {
      this.open = !this.open;
    },
  },
};
</script>

<style scoped>
.router-links {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 2;
  background: linear-gradient(0.2turn, #fb8f51, #ffe694);
  min-height: 100vh;
  transform: translateX(-100%);
  transition: all 0.3s;
  display: flex;
  flex-direction: column;
  border-right: 2px solid grey;
  font-family:'mukta';
}
.router-links p {
  width: 180px;
  font-family:'mukta';
}
.router-links p a {
  display: inline-block;
  text-decoration: none;
  color: rgb(63, 63, 63);
  text-transform: uppercase;
  font-size: 12px;
  font-weight: bold;
  width: 100%;
  padding: 20px 0 20px 20px;
  font-family:'mukta';
}
.fa {
  font-size: 40px;
  cursor: pointer;
  margin-top: 10px;
}
.active {
  transform: translateX(0);
}
.fas {
  align-self: flex-end;
  padding: 10px 10px 0 0;
  display: inline-block;
  color: rgb(63, 63, 63);
  font-size: 20px;
  cursor: pointer;
}
.router-links p .router-link-active {
  color: blueviolet;
  font-family:'mukta';
}
</style>
