<template>
  <div class="mainLoginDiv">
    <div class="innerDiv">
      <div class="greyColorBox">
        <h1 class="UserLoginTitle">Sign In</h1>
        <form class="LoginForm" @submit.prevent="login">
          <div class="emailDiv">
            <input class="emailInput" v-model="username" type="email" placeholder="E-mail" required />
          </div>
          <div class="passwordDiv">
            <input
              class="passwordInput"
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
  mounted(){
    this.$store.dispatch('saveLatestRoute', this.$route.path);
    if(document.getElementsByClassName("sunIconInHeader").length > 0){
      document.getElementsByClassName("sunIconInHeader")[0].src = '/public/home_icon.png'
      document.getElementsByClassName("sunIconInHeader")[0].className = 'house_icon'
      document.getElementsByClassName("homeText")[0].style.display = 'block';
      document.getElementsByClassName("center")[0].style.height = '70px';
    }
  },
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    async login(){
      console.log("tried to login")
      let user = new User(this.username, this.password);
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(user)
      })
      let response = await res.json()
      if(response.error == "Bad credentials"){ //Failed to log in
        document.getElementsByClassName("failedLoginDiv")[0].style.display = "block";
        document.getElementsByClassName("errorText")[0].style.display = "block";
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
.ButtonsDiv{
  margin-top:5px;
  margin-bottom:5px;
}
.CancelButton{
  background-color: #029ebb;
  font-size: 12px;
  font:arial;
  text-decoration: none;
  border: solid 1px black;
  color:black;
  padding:3px;
  padding-right:5px;
  padding-left:5px;
  padding-top:4.5px;
  padding-bottom:4.5px;
}
.UserLoginTitle{
  margin-bottom:10px;
}
.errorText{
  background-color:red;
  padding:2px;
  margin:5px;
  color:white;
  outline:solid 1px black;
  display:none;
}
.emailInput, .passwordInput, .errorText{
  opacity:1;
  margin:5px;
}
.CancelButton{
  margin-right:10px;
}
.LoginButton{
  margin-left:10px;
  padding:3px;
  width:50px;
  height:25px;
}
.greyColorBox{
  background-color: rgba(218, 224, 224, 0.8);
  padding-left:2.5vw;
  padding-right:2.5vw;
  padding-bottom:2vh;
  padding-top:2vh;
}
.mainLoginDiv{
  height:65vh;
  width:max-content;
  padding-left:3vw;
  padding-right:3vw;
  padding-top:2vh;
  padding-bottom:2vh;
  margin-left:auto;
  margin-right:auto;
  text-align:center;
}
.innerDiv{
  margin-top: 20vh;
}
a:visited{
  color:blue;
}
</style>