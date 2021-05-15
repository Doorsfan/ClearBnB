<template>
  <div>
    <h1>User log in</h1>
    <div class="imageDiv">
      <img class="LoginImage" src="/Login_form_user.png" alt="Login Image" />
    </div>
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
    <a href="api/register">Register</a>
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
      let user = new User(this.username, this.password, [], new UserInfo(
        'N/A',
        'N/A',
        'N/A',
        'N/A',
        'N/A',
        'N/A',
        'N/A',
        'N/A',
        false
        ));
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(user)
      })
      let response = await res.json()
      if(response.error == "Bad credentials"){ //Failed to log in
        document.getElementsByClassName("failedLoginDiv")[0].style.display = "block";
      }
      else{
        user.password = response['password']
        let responseForUserInfo = await fetch('/rest/userinfos')
        let responseForUserInfoAsJson = await responseForUserInfo.json()
        for(let userInfo of responseForUserInfoAsJson){
          if(userInfo.userId == response['id']){
            response.userInfo = userInfo
            user.setUserInfo(userInfo)
          }
        }
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
h1 {
  margin: 3px;
  padding: 0px;
  margin-top: 170px;
  margin-bottom: 30px;
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
  padding: 3px;
  display: inline-block;
  margin-right: 10px;
  font-weight: bolder;
  height: 14px;
  font-size: 13px;
  width: max-content;
  margin-bottom: 8px;
  border-radius: 8px;
  outline: none;
  border: none;
  background-color: lightgrey;
  margin-right: 10px;
  padding:4px;
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