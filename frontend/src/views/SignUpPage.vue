<template>
  <div class="mainDiv">
    <div class="greyBoxDiv">
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
  mounted(){
    this.$store.dispatch('saveLatestRoute', this.$route.path);
    if(document.getElementsByClassName("sunIconInHeader").length > 0){
      document.getElementsByClassName("sunIconInHeader")[0].src = '/public/home_icon.png'
      document.getElementsByClassName("sunIconInHeader")[0].className = 'house_icon'
      document.getElementsByClassName("homeText")[0].style.display = 'block';
      document.getElementsByClassName("center")[0].style.height = '70px';
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
.cancelButton{
  margin-right:5px;
  margin-left:50px;
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
  border-radius:2px;
}
.registerButton{
  margin-left:5px;
  padding:3px;
  width:60px;
  height:25px;
}
.registerTitle{
  margin-left: 100px;
}
p{
  font-weight:bolder;
  display:inline-block;
  width:140px;
  text-align:right;
  margin-right:5px;
}
.greyBoxDiv{
  margin-top:20vh;
  background-color: rgba(218, 224, 224, 0.8);
  padding-right:140px;
  padding-left:60px;
  padding-bottom:2vh;
  padding-top:2vh;
  width:max-content;
  margin-left:auto;
  margin-right:auto;
  text-align:center;
}
.mainDiv{
  margin:0px;
  text-align:center;
  padding-left:5vw;
  padding-right:5vw;
  padding-bottom:10vh;
  padding-top:2.5vh;
  background-image: url("../../public/nice.jpg");
  background-size:cover;
  background-repeat:no-repeat;
  opacity: 0.8;
  height:max-content;
  width:100vw;
  background-attachment: fixed;
  overflow-x:hidden;
}
div{
  margin:8px;
}
</style>