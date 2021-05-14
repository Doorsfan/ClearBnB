<template>
  <div class="mainDiv">
    <p class="userPageTitle">User Page</p>
    <p class="welcomeMessage"></p>
    <p class="yourBookingsText">Your bookings so far:</p>
    <p class="showAdventure">Show me my next adventure!</p>
    <div class="residencesOutBox">
      <router-link class="residencesLink" to="/">Your residences out for rent</router-link>
    </div>
    <button click="changeUserInfo">Change user info</button>
  </div>
</template>

<script>

export default {
  data() {
    return {
      user: ''
    }
  },
  mounted(){
    this.user = this.$store.getters.getCurrentUser
    $('.welcomeMessage').text("Welcome " + this.user.userInfo.firstName + " " + this.user.userInfo.lastName + "!");
  }
}
</script>
<style scoped>
  .mainDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
    margin-top:20px;
  }
  .userPageTitle{
    margin-bottom:20px;
  }
  .welcomeMessage{
    margin-bottom:20px;
  }
  .residencesLink:visited{
    color:blue;
  }
</style>

