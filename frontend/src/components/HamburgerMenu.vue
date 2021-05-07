<template>
  <div class="hamburger">
    <!--Get icon-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <div id="router-links">
      <p><router-link to="/">Home</router-link></p>
      <p><router-link to="/userPage">User Page</router-link></p>
      <p><router-link to="/login">Log in</router-link></p>
      <p><router-link to="/signUp">Sign up</router-link></p>
    </div>
    <em @click="operateHamburger" class="fa fa-bars"></em>
  </div>
</template>

<script>
export default {
  methods: {
    operateHamburger() {
      console.log("Activate hamburger menu");
      var x = document.getElementById("router-links");
      if (x.style.display === "block") {
        x.style.display = "none";
      } else {
        x.style.display = "block";
      }
    },
  },
};
</script>

<style scoped>
#router-links {
  display: none;
}
.hamburger {
  overflow: hidden;
  background-color: #333;
  position: relative;
}
.fa {
  background: black;
  display: block;
  position: absolute;
  right: 0;
  top: 0;
}
</style>