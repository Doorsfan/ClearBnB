<template>
  <div>
    <!--Get icon-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <div class="router-links"></div>
    <em class="fa fa-bars"></em>
  </div>
</template>

<script>
function operateHamburger() {
  var x = document.getElementById("router-links");
  if (x.style.display === "block") {
    x.style.display = "none";
  } else {
    x.style.display = "block";
  }
}
export default {};
</script>

<style>
</style>