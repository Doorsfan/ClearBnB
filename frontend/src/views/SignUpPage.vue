<template>
  <div class="signUpPageDiv">
    <div class="mainDiv">
      <form @submit.prevent class="SignUpForm">
        <div class="UserInputFirstAndLastName">
          <div class="titleDiv">
            <h1 class="registerTitle">Create new account</h1>
          </div>
          <div class="firstLast">
            <div class="firstNameDiv formhandler">
              <p class="nameP">First name:</p>
              <input
                v-model="firstName"
                placeholder="First Name"
                class="firstNameInput"
              />
            </div>
            <div class="lastNameDiv formhandler">
              <p class="lastNameP">Last name:</p>
              <input
                v-model="lastName"
                placeholder="Last Name"
                class="lastNameInput"
              />
            </div>
          </div>
        </div>
        <div class="emailDiv formhandler">
          <p class="emailP">Email:</p>
          <input
            v-model="email"
            type="email"
            placeholder="Email"
            class="emailInput"
          />
        </div>
        <div class="passwordsDiv">
          <div class="passwordDiv formhandler">
            <p class="passwordP">Password:</p>
            <input
              v-model="password"
              type="password"
              placeholder="Password"
              class="passwordInput"
            />
          </div>
          <div class="repeatPasswordDiv formhandler">
            <p class="repeatPasswordP">Repeat Password:</p>
            <input
              v-model="repeatPassword"
              type="password"
              placeholder="Repeat Password"
              class="repeatPasswordInput"
            />
          </div>
        </div>
        <div class="phoneNumber formhandler">
          <p class="phoneNumberP">Phone Number:</p>
          <input
            v-model="phoneNumber"
            placeholder="Phone Number"
            class="phoneNumberInput"
          />
        </div>
        <div class="streetZip">
          <div class="streetDiv formhandler">
            <p class="streetP">Street:</p>
            <input
              v-model="streetAddress"
              placeholder="My Street"
              class="streetInput"
            />
          </div>
          <div class="zipDiv formhandler">
            <p class="zipP">Zip Code:</p>
            <input v-model="zipCode" placeholder="Zip Code" class="zipInput" />
          </div>
        </div>
        <div class="countryCity">
          <div class="countryDiv formhandler">
            <p class="countryP">Country:</p>
            <input
              v-model="country"
              placeholder="Country"
              class="countryInput"
            />
          </div>
          <div class="cityDiv formhandler">
            <p class="cityP">City:</p>
            <input v-model="city" placeholder="City" class="cityInput" />
          </div>
        </div>

        <div class="letterRegister">
          <div class="newsLetterDiv">
            <input v-model="newsLetter" type="checkbox" class="newsLetterBox" />
            <p class="newsLetterP">Newsletter</p>
          </div>

          <button class="registerButton" @click="registerUser" value="Register">
            Register
          </button>
        </div>
        <div class="takenUserBox">
          <p class="errorBox">A user with that Email already exists.</p>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import User from '../components/User.js';
import UserInfo from '../components/UserInfo.js';
export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      streetAddress: '',
      zipCode: '',
      country: '',
      city: '',
      email: '',
      repeatEmail: '',
      password: '',
      repeatPassword: '',
      phoneNumber: '',
      newsLetter: false,
    };
  },
  methods: {
    async registerUser() {
      if (this.password != this.repeatPassword) {
        alert('The passwords do not match up!');
        return;
      }
      let newUser = new User(this.email, this.password);
      let newUserInfo = new UserInfo(
        '',
        this.firstName,
        this.lastName,
        this.streetAddress,
        this.zipCode,
        this.city,
        this.country,
        this.phoneNumber,
        this.newsLetter
      );
      let myRes = await fetch('/rest/userinfos/Pw-26FQNIcS6gnM5Bk8nR', {
        method: 'DELETE',
      });

      let res = await fetch('/api/register', {
        method: 'POST',
        body: JSON.stringify(newUser),
      });
      let responseAsJson = await res.json();

      if (responseAsJson['error'] == 'User already exists') {
        alert('A user with that username already exists!');
        return;
      }
      newUserInfo.userId = responseAsJson['id'];
      await fetch('/rest/userinfos', {
        method: 'POST',
        body: JSON.stringify(newUserInfo),
      });
      document.getElementsByClassName('cancelButton')[0].click();
    },
  },
};
</script>

<style scoped>
* {
  font-family: 'mukta';
}
.firstLast {
  display: flex;
  flex-direction: row;
}

.firstNameDiv {
  flex-grow: 1;
  padding-right: 5px;
}
.lastNameDiv {
  flex-grow: 1;
  padding-left: 5px;
}
.passwordsDiv {
  display: flex;
  flex-direction: row;
}
.emailDiv {
  padding-top: 25px;
  width: 50%;
  padding-right: 5px;
}
.passwordDiv {
  flex-grow: 1;
  padding-right: 5px;
}
.repeatPasswordDiv {
  flex-grow: 1;
  padding-left: 5px;
}
.countryCity {
  display: flex;
  flex-direction: row;
}
.countryDiv {
  flex-grow: 1;
  padding-right: 5px;
}
.cityDiv {
  flex-grow: 1;
  padding-left: 5px;
}
.streetZip {
  display: flex;
  flex-direction: row;
  padding-top: 25px;
}
.streetDiv {
  flex-grow: 0.8;
  padding-right: 5px;
}
.zipDiv {
  flex-grow: 0.2;
  padding-left: 5px;
}
.phoneNumber {
  padding-top: 25px;
  width: 50%;
  padding-right: 5px;
}
.letterRegister {
  margin-top: 65px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.newsLetterP {
  margin: 0;
  padding-left: 4px;
}
.newsLetterBox {
  padding-top: 5px;
  margin-top: 1px;
}

.mainDiv {
  background-image: url('../../public/Login-signup.jpg');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  height: 90vh;
  background-attachment: fixed;
  overflow-x: hidden;
}

.SignUpForm {
  text-align: center;
  height: 880px;
  width: clamp(600px, 80vw, 1200px);
  margin: 180px auto;
  border-radius: 5px;
  border: 1px solid grey;
  background-color: rgb(255, 255, 255, 0.9);
  padding: 0 50px;
}
form input {
  padding: 7.5px 30.4px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid grey;
  text-align: initial;
  margin-bottom: 8px;
}

button {
  width: 120px;
  height: 50px;
  padding: 10px 20px;
  background-color: #029ebb;
  color: white;
  border: none;
  border-radius: 2px;
  cursor: pointer;
  font-size: 18px;
}
button:hover {
  transform: scale(1.1);
}
h1 {
  color: black;
  margin-bottom: 35px;
  margin-top: 35px;
  font-size: 45px;
  font-weight: bold;
}
p {
  display: inline-block;
  text-align: left;
  margin-right: 5px;
  margin-bottom: 3px;
  font-size: 16px;
  font-weight: bold;
}
.signUpPageDiv {
  min-height: 85vh;
}
.takenUserBox {
  margin: 0px;
  margin-top: 10px;
  margin-left: 5.5vw;
  margin-right: auto;
}
.errorBox {
  display: none;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  background-color: red;
  color: white;
  padding: 3px;
  outline: 1px solid black;
}
.formhandler {
  display: flex;
  flex-direction: column;
}
.mainDiv {
  min-height: 75vh;
  min-width:100vw;
}
@media only screen and (max-width: 1920px) {
  .SignUpForm {
    margin: 80px auto;
  }
}
@media only screen and (max-width: 1800px) {
  .SignUpForm {
    margin: 50px auto;
  }
}
@media only screen and (max-width: 1025px) {
  .SignUpForm {
    margin: 120px auto;
  }
}
@media only screen and (max-width: 1023px) {
  .SignUpForm {
    margin: 50px auto;
  }
}
@media only screen and (max-width: 600px) {
  .SignUpForm {
    height: 1170px;
    width: clamp(280px, 100vw, 600px);
    margin: auto;
    border-radius: 0px;
  }
  .firstLast {
    display: flex;
    flex-direction: column;
  }
  b {
    border-radius: 40px;
  }

  .firstNameDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .lastNameDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .passwordsDiv {
    display: flex;
    flex-direction: column;
  }
  .emailDiv {
    padding-top: 15px;
    width: 100%;
    padding-left: 2px;
    padding-right: 2px;
  }
  .passwordDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .repeatPasswordDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .countryCity {
    display: flex;
    flex-direction: column;
  }
  .countryDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .cityDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .streetZip {
    display: flex;
    flex-direction: column;
    padding-top: 15px;
  }
  .streetDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .zipDiv {
    flex-grow: 1;
    padding-left: 2px;
    padding-right: 2px;
  }
  .phoneNumber {
    padding-top: 15px;
    width: 100%;
    padding-left: 2px;
    padding-right: 2px;
  }
  .letterRegister {
    margin-top: 50px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .newsLetterP {
    margin: 0;
    padding-left: 4px;
  }
  .newsLetterBox {
    padding-top: 5px;
    margin-top: 1px;
  }
  h1 {
    font-size: 40px;
  }
}
@media only screen and (max-width: 527px) {
  .SignUpForm {
    height: 1165px;
  }
  h1 {
    font-size: 35px;
    margin-bottom: 40px;
  }
}
@media only screen and (max-width: 481px) {
  h1 {
    font-size: 30px;
    margin-bottom: 35px;
  }
  .SignUpForm {
    height: 1150px;
  }
  #app > main > div > div > form > div.letterRegister > div{
    width: 100px;
  }
}
@media only screen and (max-width: 387px) {
  h1 {
    font-size: 29px;
    margin-bottom: 35px;
  }
  .SignUpForm {
    height: 1145px;
    padding: 0 30px;
  }
  .errorBox {
    display: none;
    width: 300px;
    margin-right: auto;
    background-color: red;
    color: white;
    padding: 3px;
    outline: 1px solid black;
  }
}
@media only screen and (max-width: 339px) {
  h1 {
    font-size: 25px;
    margin-bottom: 35px;
  }
  .SignUpForm {
    height: 1140px;
  }
}
@media only screen and (max-width: 301px) {
  h1 {
    font-size: 22px;
    margin-bottom: 35px;
  }
  .SignUpForm {
    height: 1140px;
  }
  .letterRegister {
    margin-top: 10px;
    display: flex;
    flex-direction: column;
  }

  .newsLetterDiv {
    display: flex;
    justify-content: center;
    margin-bottom: 15px;
  }
  button {
    align-self: center;
  }
  form input {
    padding: 0px;
    padding-left: 3px;
    max-width: 200px;
    width: 200px;
  }
}
</style>
