<template>
  <footer>
    <div class="overlay"></div>
    <div class="icons">
      <div class="faceBookIconDiv">
        <i class="fab fa-facebook-f"></i>
      </div>
      <div class="instagramIconDiv">
        <i class="fab fa-instagram"></i>
      </div>
      <div class="twitterIconDiv">
        <i class="fab fa-twitter"></i>
      </div>
    </div>
    <div class="links">
      <router-link to="/">home</router-link>
      <div></div>
      <router-link v-if="!$store.getters.getCurrentUser" to="/Login"
        >login</router-link
      >
      <router-link v-if="$store.getters.getCurrentUser" to="/userPage"
        >My Pages</router-link
      >
      <div></div>
      <router-link v-if="!$store.getters.getCurrentUser" to="/signUp"
        >signup</router-link
      >
      <router-link
        class="logOutLinkInFooter"
        v-if="$store.getters.getCurrentUser"
        to="/"
        ><button @click="logOut" type="button" class="logOutLink">
          LOG OUT
        </button></router-link
      >
    </div>
    <div class="last">&copy; 2021 ClearBNB</div>
  </footer>
</template>
<script>
export default {
  methods: {
    logOut() {
      this.$store.commit('setUser', null);
      this.currentUser = null;
    },
  },
};
</script>

<style scoped>
footer {
  margin-top: 0;
  background-color: #fdbf81;
  background-image: url('../assets/skyline_sunset.png');
  background-repeat: no-repeat;
  background-position: center;
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 55px;
  position: absolute;
  z-index: 1;
}

.icons {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 25px;
}

.icons div {
  width: 45px;
  height: 45px;
  margin: 0px 10px;
  display: grid;
  place-content: center;
  background-color: rgb(255, 255, 255, 0.7);
  border-radius: 50%;
}

.icons div i {
  font-size: 20px;
  color: black;
}

.links {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.links a {
  text-decoration: none;
  text-transform: uppercase;
  font-weight: bolder;
  font-size: 16px;
  color: black;
  -webkit-text-stroke: 0px white;
}

.links a:hover {
  text-decoration: underline;
}

.links div {
  width: 5px;
  height: 5px;
  border-radius: 50%;
  margin: 0 10px;
  background-color: white;
}

.last {
  color: white;
  margin-top: 30px;
  font-size: 14px;
}

.logOutLink {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  color: black;
  background-color: transparent;
  outline: none;
  border: none;
  margin-top: 0px;
  font-size: 28px;
  -webkit-text-stroke: 0px white;
  font-weight: bold;
  font-size: 16px;
}
</style>
