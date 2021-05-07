<template>
  <div>
    <Header />
    <router-view></router-view>
    <HamburgerMenu />
  </div>
</template>

<script>
import Header from "./components/Header.vue";
import TestingFetches from "./components/TestingFetches.vue";
import HamburgerMenu from "./components/HamburgerMenu.vue";

export default {
  name: "App",
  components: {
    Header,
    TestingFetches,
    HamburgerMenu,
  },
  data() {
    return {};
  },
};
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
