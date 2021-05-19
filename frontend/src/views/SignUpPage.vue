<template>
  <div class="mainDiv">
    <div class="titleDiv">
      <h1 class="registerTitle">Register New User</h1>
    </div>
    <div class="firstNameDiv">
      <p class="nameP">First name: </p><input v-model="firstName" placeholder="First Name" class="firstNameInput">
    </div>
    <div class="lastNameDiv">
      <p class="lastNameP">Last name: </p><input v-model="lastName" placeholder="Last Name" class="lastNameInput">
    </div>
    <div class="streetDiv">
      <p class="streetP">Street: </p><input v-model="streetAddress" placeholder="My Street" class="streetInput">
    </div>
    <div class="zipDiv">
      <p class="zipP">Zip Code: </p><input v-model="zipCode" placeholder="Zip Code" class="zipInput">
    </div>
    <div class="countryDiv">
      <p class="countryP">Country: </p><input v-model="country" placeholder="Country" class="countryInput">
    </div>
    <div class="cityDiv">
      <p class="cityP">City: </p><input v-model="city" placeholder="City" class="cityInput">
    </div>
    <div class="emailDiv">
      <p class="emailP">Email: </p><input v-model="email" type="email" placeholder="Email" class="emailInput">
    </div>
    <div class="repeatEmailDiv">
      <p class="repeatEmailP">Repeat Email: </p><input v-model="repeatEmail" type="email" placeholder="Repeat Email" class="repeatEmailInput">
    </div>
    <div class="passwordDiv">
      <p class="passwordP">Password: </p><input v-model="password" type="password" placeholder="Password" class="passwordInput">
    </div>
    <div class="repeatPasswordDiv">
      <p class="repeatPasswordP">Repeat Password: </p><input v-model="repeatPassword" type="password" placeholder="Repeat Password" class="repeatPasswordInput">
    </div>
    <div class="phoneNumber">
      <p class="phoneNumberP">Phone Number: </p><input v-model="phoneNumber" placeholder="Phone Number" class="phoneNumberInput">
    </div>
    <div class="newsLetterDiv">
      <p class="newsLetterP">Newsletter: </p><input v-model="newsLetter" type="checkbox" class="newsLetterBox">
    </div>
    <div class="buttonsDiv">
      <router-link to="/" class="cancelButton" value="Cancel">Cancel</router-link><button class="registerButton" @click="registerUser" value="Register">Register</button>
    </div>
  </div>
  
</template>
<script>
import User from '../components/User.js'
import UserInfo from '../components/UserInfo.js'
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
      newsLetter: false
    }
  },
  methods: {
    async registerUser(){
      if(this.password != this.repeatPassword){
        document.getElementsByClassName('errorBox')[0].style.display = 'block';
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
      )
      let myRes = await fetch('/rest/userinfos/Pw-26FQNIcS6gnM5Bk8nR',{
        method: 'DELETE'
      });
      
      let res = await fetch('/api/register', {
        method: 'POST',
        body: JSON.stringify(newUser)
      })
      let responseAsJson = await res.json();


      if(responseAsJson['error'] == "User already exists"){
        document.getElementsByClassName('takenUserBox')[0].style.display = 'block';
        return;
      }
      newUserInfo.userId = responseAsJson['id']
      await fetch('/rest/userinfos', {
        method: 'POST',
        body: JSON.stringify(newUserInfo)
      })
      document.getElementsByClassName('cancelButton')[0].click();
    }
  }
}
</script>
<style scoped>
input{
  margin:5px;
}
h1{
  margin-left:150px;
}
p{
  display:inline-block;
  width:150px;
  text-align:right;
  color:black;
  font-weight:bolder;
}
.buttonsDiv{
  padding-left:160px;
  margin-top:10px;
}
div{
  display:block;
  width:max-content;
  margin-left:auto;
  margin-right:auto;
}
.mainDiv{
  background-color:red;
  background-image: url("/new_user.jpg");
  background-repeat:no-repeat;
  height:60vh;
  width:80vw;
  max-width:80vw;
  margin:0px;
  margin-top:3vh;
  padding-top:10vh;
}
.errorBox{
  margin-left:auto;
  margin-right:auto;
  background-color:rgb(199, 25, 25);
  padding:5px;
  border:1px solid black;
  color:white;
  display:none;
}
.takenUserBox{
  margin-left:auto;
  margin-right:auto;
  background-color:rgb(199, 25, 25);
  padding:5px;
  border:1px solid black;
  color:white;
  display:none;
}
.userRegistrationText{
  font-weight:bolder;
}
.newsLetterCheckBox{
  text-align: center;
  width:14px;
  min-width:14px;
  margin-right:3px;
}

.buttonsDiv{
  margin-left:auto;
  margin-right:auto;
  width:max-content;
}
.cancelButton{
  margin-right:5px;
  font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-radius:10px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;
  font-size:14px;
}
.registerButton{
  margin-right:5px;
  font: bold 11px Arial;
  text-decoration: none;
  background-color: rgb(24, 173, 24);
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-radius:10px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;
  font-size:14px;
}
</style>