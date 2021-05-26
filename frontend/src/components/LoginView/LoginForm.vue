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
      <button>
        <router-link class="CancelButton" to="/">Cancel</router-link>
      </button>
      <p class="hiddenptag">or</p>
      <button @click.prevent="login" class="LoginButton">Login</button>
    </div>
    <div class="SignUpFromLogIn">
      <p>New to ClearBnB?</p>
      <router-link to="signUp" class="signUpLink">Sign up</router-link>
    </div>
  </div>
</template>

<script>
import store from '../../store.js';
import User from '../../components/User.js';
import UserInfo from '../UserInfo.js';
export default {
  mounted() {
    this.$store.dispatch('saveLatestRoute', this.$route.path);
    if (document.getElementsByClassName('sunIconInHeader').length > 0) {
      document.getElementsByClassName('sunIconInHeader')[0].src =
        '/public/home_icon.png';
      document.getElementsByClassName('sunIconInHeader')[0].className =
        'house_icon';
      document.getElementsByClassName('homeText')[0].style.display = 'block';
      document.getElementsByClassName('center')[0].style.height = '70px';
    }
  },
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    async login() {
      let user = new User(this.username, this.password);
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(user),
      });
      let response = await res.json();
      if (response.error == 'Bad credentials') {
        //Failed to log in
        document.getElementsByClassName('failedLoginDiv')[0].style.display =
          'block';
        document.getElementsByClassName('errorText')[0].style.display = 'block';
      } else {
        let currentUser = new User('', '');
        user = Object.assign(currentUser, response);
        document.getElementsByClassName('failedLoginDiv')[0].style.display =
          'none';
        this.$store.dispatch('login', user);
        //window.location = '/'
        document.getElementsByClassName('CancelButton')[0].click(); //Simulate a click on the Cancel button to go to the start page
        //To interact with the stores getters, write this.$store.getters.<gettersName>
        //console.log(this.$store.getters.getCurrentUser) Example
      }
    },
  },
};
</script>

<style scoped>
*{
  font-family:'mukta';
}
.hiddenptag {
  display: none;
}
.failedLoginDiv {
  display: flex;
  align-items: center;
  color: red;
  justify-content: center;
  display: none;
}
h1 {
  color: black;
  margin-bottom: 40px;
  margin-top: 50px;
  font-size: 50px;
  font-weight: bold;
}
.LogInPage {
  text-align: center;
  height: 600px;
  width: 500px;
  margin: 310px auto;
  border-radius: 5px ;
  border: 1px solid grey;
  background-color: rgb(255, 255, 255, 0.9);
}
form {
  padding-top: 50px;
}
form input {
  padding: 11.2px 22.4px;
  font-size: 18px;
  border-radius: 5px;
  border:1px solid grey ;
  text-align:initial;
  margin-bottom: 15px;
}
button {
  width: 120px;
  height: 50px;
  padding: 10px 20px;
  background-color: #029ebb;
  color: white;
  border: 1px solid grey;
  border-radius: 2px;
  cursor: pointer;
  margin-top: 10px;
  font-size: 18px;
  margin-right: 10px;
  margin-left: 10px;
}
.ButtonsDiv {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 10px;
}
.SignUpFromLogIn {
  margin-top: 170px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
a {
  text-decoration: none;
  color: white;
}
.signUpLink {
  color: #029ebb;
  margin-left: 5px;
  text-decoration: underline;
  font-size: 16px;
  font-weight: bold;
}
p {
  font-size: 16;
  font-weight: bold;
}

button:hover {
  transform: scale(1.1);
}
@media only screen and (max-width: 1920px) {
  .LogInPage{
 
  margin: 135px auto;
 
  }
}
@media only screen and (max-width: 1800px) {
.LogInPage{
  margin: 150px auto;
}}

@media only screen and (max-width: 1025px) {
.LogInPage{
  margin: 250px auto;
  
}}
@media only screen and (max-width: 1023px) {
.LogInPage{
  margin: 80px auto;
}}
@media only screen and (max-width: 540px) {
  .LogInPage {
    width: clamp(280px, 100vw, 500px);
    margin: 20px auto;
  }
}
@media only screen and (max-width: 500px) {
  .LogInPage {
    width: clamp(280px, 100vw, 500px);
    border-radius: 0px;
    margin: auto;
  }

  form {
    padding-top: 20px;
  }
  .LogInPage {
    height: 570px;
  }
  h1 {
    margin-top: 50px;
  }
  .SignUpFromLogIn {
    margin-top: 80px;
  }
  .ButtonsDiv {
    display: flex;
    flex-direction: column-reverse;
    justify-content: center;
    align-items: center;
  }
  button {
    width: 270px;
    height: 50px;

    border-radius: 40px;
  }
  .hiddenptag {
    display: inline;
    margin-top: 5px;
    font-weight: normal;
  }
}
.LogInPage{
  height: 570px;
}
h1{
  margin-top:50px;
  
}
.SignUpFromLogIn{
  margin-top:80px;
}
.ButtonsDiv{
  display:flex;
  flex-direction: column-reverse;
  justify-content: center;
  align-items: center;
}
button{
  width: 200px;
  height: 50px;
  border-radius: 40px ;
  
}
.hiddenptag{
  display:inline;
  margin-top:5px;
  font-weight: normal;
}
@media only screen and (max-width: 374px) {
  input {
    width: 70%;
  }
  button {
    width: 70%;
  }
  p {
    font-size: 16px;
  }
}
p{
  font-size: 16px;
}
@media only screen and (max-width: 324px) {
  .SignUpFromLogIn {
    margin-top: 55px;
    display: flex;
    flex-direction: column;
  }
  p {
    font-size: 17px;
  }
  .signUpLink {
    font-size: 18px;
    text-decoration: none;
  }
  .hiddenptag {
    font-size: 16px;
  }
}
</style>
