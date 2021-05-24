<template>
  <div class="LogInPage">
    <h1>Log in</h1>
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
      <button><router-link class="CancelButton" to="/">Cancel</router-link></button>
      <button @click.prevent="login" class="LoginButton">Login</button>
    </div>
    <div class="SignUpFromLogIn">
    <p>New to ClearBnB?</p>
    <router-link to="signUp" class="signUpLink">Sign up</router-link>
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
h1 {
  color: black;
  margin-bottom: 40px;
  margin-top: 50px;
  font-size: 45px;
  font-weight: bold;
}
.LogInPage{
  text-align: center;
  height: 600px;
  width: 500px;
  margin: 180px auto;
  border-radius: 5px ;
  border: 1px solid grey;
  background-color: rgb(255, 255, 255, 0.9);


}
form{
  padding-top: 50px;
}
form input{
  padding: 11.2px 22.4px;
  font-size: 18px;
  border-radius: 5px;
  border:1px solid black ;
  text-align:initial;
  margin-bottom: 15px;
}
button{
  width: 120px;
  height: 50px;
  padding: 10px 20px;
  background-color: #029ebb;
  color: white;
  border: 0;
  border-radius: 2px ;
  cursor: pointer;
  margin-top: 10px;
  font-size: 18px;
  margin-right: 10px;
  margin-left: 10px;
  
}
.ButtonsDiv{
  display:flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 10px;
  
}
.SignUpFromLogIn{
  margin-top:170px;
  display:flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
a{
  text-decoration: none;
  color: white;
}
.signUpLink{
  color:#029ebb;
  margin-left: 5px;
  text-decoration: underline;
  font-size: 16px;
  font-weight: bold;
}
p{
  font-size: 16;
  font-weight: bold;
}

button:hover {
  transform: scale(1.1);
}
</style>