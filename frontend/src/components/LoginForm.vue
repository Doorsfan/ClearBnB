<template>
  <div>
    <h1>User log in</h1>
    <div class="imageDiv">
      <img class="LoginImage" src="/Login_form_user.png" alt="Login Image" />
    </div>
    <form class="LoginForm" @submit.prevent="login">
      <div class="emailDiv">
        <input v-model="userName" type="email" placeholder="E-mail" required />
      </div>
      <div class="passwordDiv">
        <input
          v-model="password"
          type="password"
          placeholder="Password"
          required
        />
      </div>
    </form>
    <div class="ButtonsDiv">
      <button @click.prevent="cancel" class="CancelButton">Cancel</button>
      <button @click.prevent="login" class="LoginButton">Login</button>
    </div>
    <a href="api/register">Register</a>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: "",
      password: "",
    };
  },
  methods: {
    login() {
      let credentials = {
        userName: this.userName,
        password: this.password,
      };
      console.log("login is not in yet");
    },

    cancel() {
      console.log("Cancel is not in yet");
    },
  },
};
</script>

<style scoped>
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
  margin: 3px;
  margin-bottom: 8px;
  margin-right: 7px;
  border-radius: 8px;
  outline: none;
  border: none;
  background-color: lightgrey;
}
.LoginButton {
  margin-left: 7px;
  border-radius: 8px;
  outline: none;
  border: none;
  background-color: #32a83a;
}
a {
  font-weight: bolder;
}
a:visited {
  color: blue;
}
</style>