<template>
  <form class="registerForm" @submit.prevent="registerUser">
  <div class="mainDiv">
    <p class="userRegistrationText">User Registration</p>
    <div class="firstDiv">
      <input v-model="firstName" required type="text" placeholder="First Name">
      <input v-model="lastName" required type="text" placeholder="Last Name">
    </div>
    <div class="secondDiv">
      <input v-model="streetAddress" required type="text" class="StreetInput" placeholder="Street address">
    </div>
    <div class="thirdDiv">
      <input required v-model="zipCode" class="zipCodeInput" type="text" placeholder="Zip code"><input required v-model="country" class="countryInput" type="text" placeholder="Country"><input v-model="city" required class="cityInput" type="text" placeholder="City">
    </div>
    <div class="emailDiv">
      <input v-model="email" required type="email" placeholder="e-mail address"><input v-model="repeatEmail" required type="email" placeholder="Repeat e-mail">
    </div>
    <div>
      <input v-model="password" required type="password" placeholder="Password"><input v-model="repeatPassword" required type="password" placeholder="Repeat password">
    </div>
    <div class="phoneDiv">
      <input v-model="phoneNumber" required type="text" placeholder="Phone number"><input v-model="newsLetter" class="newsLetterCheckBox" type="checkbox"> Newsletter
    </div>
    <div class="errorBox">
      <p class="errorText">Passwords do not match up</p>
    </div>
    <div class="takenUserBox">
      <p class="errorText">User with the email {{ email }} already exists.</p>
    </div>
  </div>
  </form>
  <div class="buttonsDiv">
    <router-link to="/" class="cancelButton" value="Cancel">Cancel</router-link><button class="registerButton" @click="registerUser" value="Register">Register</button>
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
      /*
      userId,
    firstName,
    lastName,
    streetAddress,
    zipCode,
    city,
    country,
    phoneNumber,
    newsLetter */
      //Look at again when back from dinner
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


      console.log(responseAsJson);
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
.errorText{
  width:max-content;
}
.countryInput{
  width:100px;
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
.mainDiv{
  margin-top:200px;
}
div{
  width:max-content;
  text-align:center;
  margin-top:20px;
  margin-bottom:20px;
}
input{
  width:max-content;
  margin-left:10px;
  margin-right:10px;
  padding-left: 3px;
  min-width:100px;
}
.newsLetterCheckBox{
  text-align: center;
  width:14px;
  min-width:14px;
  margin-right:3px;
}
.StreetInput{
  width:473px;
}
.buttonsDiv, .mainDiv{
  margin-left:auto;
  margin-right:auto;
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
.zipCodeInput{
  min-width:40px;
  width:80px;
}
.cityInput{
  width:253px;
}
</style>