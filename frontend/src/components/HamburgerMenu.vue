<template>
  <div class="hamburger">
    <!--Get icon-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <em @click="operateHamburger" class="fa fa-bars"></em>
    <div id="router-links">
      <p><router-link to="/">Home</router-link></p>
      <p><router-link to="/userPage">User Page</router-link></p>
      <p><router-link to="/login">Log in</router-link></p>
      <p><router-link to="/signUp">Sign up</router-link></p>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    operateHamburger() {
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
a, a:visited{
  color:black;
}
#router-links {
  display: none;
}
.hamburger {
  overflow: hidden;
  position: relative;
}
.fa {
  display: inline-block;
  left: 0;
  top: 0;
  height: max-content;
  width: max-content;
  font-size:60px;
}
</style>