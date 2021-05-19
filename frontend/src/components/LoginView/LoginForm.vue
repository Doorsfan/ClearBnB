<template>
  <div class="backgroundDiv">
    <div class="innerDiv">
      <div class="mainDiv">
        <h1 class="UserLoginTitle">Sign In</h1>
        <form class="LoginForm" @submit.prevent="login">
          <div class="emailDiv">
            <input v-model="username" type="email" placeholder="E-mail" required />
          </div>
          <div class="passwordDiv">
            <input
              v-model="password"
              type="password"
              placeholder="Password"
              required
            />
          </div>
          <div class="failedLoginDiv">
            <p class="errorText">Error - Bad credentials</p>
          </div>
        </form>
        <div class="ButtonsDiv">
          <router-link class="CancelButton" to="/">Cancel</router-link>
          <button @click.prevent="login" class="LoginButton">Login</button>
        </div>
        <a href="api/register">New here?</a>
      </div>
    </div>
  </div>
</template>

<script>
import store from '../../store.js'
import User from '../../components/User.js'
import UserInfo from '../UserInfo.js'
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async login(){
      let user = new User(this.username, this.password);
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(user)
      })
      let response = await res.json()
      if(response.error == "Bad credentials"){ //Failed to log in
        document.getElementsByClassName("failedLoginDiv")[0].style.display = "block";
      }
      else{
        let currentUser = new User('','');
        user = Object.assign(currentUser,response)
        document.getElementsByClassName("failedLoginDiv")[0].style.display = "none";
        this.$store.dispatch('login', user)
        //window.location = '/'
        document.getElementsByClassName('CancelButton')[0].click(); //Simulate a click on the Cancel button to go to the start page
        //To interact with the stores getters, write this.$store.getters.<gettersName>
        //console.log(this.$store.getters.getCurrentUser) Example
      }
    }
  },
};
</script>

<style scoped>
.backgroundDiv{
  margin-top:3vh;
  width:79.6vw;
  height:61vh;
  padding-top:10vh;
  background-image:url("/nice.jpg");
  background-repeat:no-repeat;
  background-size:contain;
}
.mainDiv{
  padding-left:3vw;
  padding-right:3vw;
  width:max-content;
  background-color: rgba(218, 224, 224, 0.8);
  padding-top:5vh;
  padding-bottom:5vh;
  margin-top:10vh;
  height:max-content;
  opacity:1;
}
.errorText{
  margin: 0px;
  padding: 0px;
}
.failedLoginDiv{
  margin: 0px;
  margin-left: 12px;
  background-color: red;
  color: white;
  padding: 3px;
  height: 16px;
  width: 148px;
  border: 1px solid darkred;
  text-align: center;
  display: none;
}
.LoginForm{
  margin-left: 15px;
}
div {
  margin-left: auto;
  margin-right: auto;
  width: max-content;
  text-align: center;
}
.ButtonsDiv{
  width:max-content;
  margin:0px;
  margin-left:auto;
  margin-right:auto;
}
.UserLoginTitle{
  margin-bottom:20px;
}
.LoginImage {
  margin: 3px;
}
.emailDiv {
  margin: 3px;
  margin-bottom: 10px;
}
.passwordDiv {
  margin: 3px;
  margin-bottom: 7px;
}
.CancelButton {
  text-decoration: none;
  color: black;
  text-decoration-color: black;
  -moz-text-decoration-color: black;
  margin: 0px;
  padding: 4px;
  display: inline-block;
  margin-right: 10px;
  font-weight: bolder;
  height: 23px;
  font-size: 13px;
  width: max-content;
  margin-bottom: 8px;
  border-radius: 8px;
  outline: none;
  border: none;
  background-color: lightgrey;
  margin-right: 10px;
}
.LoginButton {
  border-radius: 8px;
  outline: none;
  border: none;
  background-color: #32a83a;
  padding:4px;
}
a {
  font-weight: bolder;
}
a:visited {
  color: blue;
}
</style>