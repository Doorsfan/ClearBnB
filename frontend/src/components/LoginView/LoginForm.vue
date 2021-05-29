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
export default {
  mounted() {
    this.$store.dispatch('saveLatestRoute', this.$route.path);
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
        alert('Error - Failed to login, bad credentials!');
      } else {
        let currentUser = new User('', '');
        user = Object.assign(currentUser, response);
        this.$store.dispatch('login', user);
        document.getElementsByClassName('CancelButton')[0].click();
      }
    },
  },
};
</script>

<style scoped>
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
  margin-bottom: 20px;
  margin-top: 40px;
  font-size: 50px;
  font-weight: bold;
}
.LogInPage {
  text-align: center;
  height: 600px;
  width: 500px;
  margin: 310px auto;
  border-radius: 5px;
  border: 1px solid grey;
  background-color: rgb(255, 255, 255, 0.8);
}
form {
  padding-top: 50px;
}
form input {
  padding: 7.5px 30.4px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid grey;
  text-align: initial;
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
  margin-top: 162px;
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
  font-size: 17px;
  font-weight: bold;
}
p {
  font-size: 17px;
  font-weight: bold;
}

button:hover {
  transform: scale(1.1);
}
@media only screen and (max-width: 1920px) {
  .LogInPage {
    margin: 135px auto;
  }
}
@media only screen and (max-width: 1800px) {
  .LogInPage {
    margin: 150px auto;
  }
}

@media only screen and (max-width: 1025px) {
  .LogInPage {
    margin: 241px auto;
  }
}
@media only screen and (max-width: 1023px) {
  .LogInPage {
    margin: 70px auto;
  }
}
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
    margin-top: 60px;
  }
  .ButtonsDiv {
    display: flex;
    flex-direction: column-reverse;
    justify-content: center;
    align-items: center;
  }
  button {
    width: 200px;
    height: 50px;
    border-radius: 40px;
  }
  .hiddenptag {
    display: inline;
    margin-top: 5px;
    font-weight: normal;
  }
}
@media only screen and (max-width: 374px) {
  input {
    width: 70%;
  }
  p {
    font-size: 16px;
  }
}
@media only screen and (max-width: 324px) {
  .SignUpFromLogIn {
    margin-top: 35px;
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
