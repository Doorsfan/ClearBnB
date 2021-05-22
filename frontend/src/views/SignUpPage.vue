<template>
<div class="mainDiv">
    <form class="SignUpForm">
      <div class="UserInputFirstAndLastName">
    <div class="titleDiv">
      <h1 class="registerTitle">Create new account</h1>
    </div>
    <div class="firstNameDiv">
      <p class="nameP">First name: </p><input v-model="firstName" placeholder="First Name" class="firstNameInput">
    </div>
    <div class="lastNameDiv">
      <p class="lastNameP">Last name: </p><input v-model="lastName" placeholder="Last Name" class="lastNameInput">
    </div>
    </div>
    <div class="emailDiv">
      <p class="emailP">Email: </p><input v-model="email" type="email" placeholder="Email" class="emailInput">
    </div>
    <div class="userInputPassword">
    <div class="passwordDiv">
      <p class="passwordP">Password: </p><input v-model="password" type="password" placeholder="Password" class="passwordInput">
    </div>
    <div class="repeatPasswordDiv">
      <p class="repeatPasswordP">Repeat Password: </p><input v-model="repeatPassword" type="password" placeholder="Repeat Password" class="repeatPasswordInput">
    </div>
    </div>
    <div class="userInputAdressInfo">
    <div class="countryDiv">
      <p class="countryP">Country: </p><input v-model="country" placeholder="Country" class="countryInput">
    </div>
    <div class="cityDiv">
      <p class="cityP">City: </p><input v-model="city" placeholder="City" class="cityInput">
    </div>
    <div class="streetDiv">
      <p class="streetP">Street: </p><input v-model="streetAddress" placeholder="My Street" class="streetInput">
    </div>
    <div class="zipDiv">
      <p class="zipP">Zip Code: </p><input v-model="zipCode" placeholder="Zip Code" class="zipInput">
    </div>
    </div>
    <div class="phoneNumber">
      <p class="phoneNumberP">Phone Number: </p><input v-model="phoneNumber" placeholder="Phone Number" class="phoneNumberInput">
    </div>
    <div class="newsletterAndRegister">
    <div class="newsLetterDiv">
      <p class="newsLetterP">Newsletter: </p><input v-model="newsLetter" type="checkbox" class="newsLetterBox">
    </div>
    
     <button class="registerButton" @click="registerUser" value="Register">Register</button>
     </div>
    <div class="takenUserBox">
      <p class="errorBox">A user with that Email already exists.</p>
    </div>
    </form>
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
        document.getElementsByClassName('errorBox')[0].style.display = 'block';
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
};
</script>

<style scoped>

.mainDiv{
  background-image: url("../../public/nice.jpg");
  background-position:center;
  background-size:cover;
  background-repeat:no-repeat;
  height:max-content;
  width:100vw;
  background-attachment: fixed;
  overflow-x:hidden;
}

.SignUpForm{
  text-align: center;
  height: 750px;
  width: 1200px;
  margin: 180px auto;
  border-radius: 2px ;
  border: 2px solid grey;
  background-color: rgb(255, 255, 255, 0.8);
}
form input{
  padding: 2px 15px;
  font-size: 18px;
  border-radius: 5px;
  border:1px solid grey ;
  text-align:initial;
  margin-bottom: 15px;
  color: black;
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
h1 {
  color: black;
  margin-bottom: 50px;
  margin-top: 35px;
  font-size: 45px;
  font-weight: bold;
}
p{
  display:inline-block;
  width:140px;
  text-align:right;
  margin-right:5px;
  font-size: 16;
  font-weight: bold;

}

.takenUserBox{
  margin:0px;
  margin-top:10px;
  margin-left:5.5vw;
  margin-right:auto;
}
.errorBox{
  display:none;
  width:max-content;
  margin-left:auto;
  margin-right:auto;
  background-color: red;
  color:white;
  padding:3px;
  outline: 1px solid black;
}
.newsletterAndRegister{
  display:flex;
  justify-content: center;
  align-content: space-between;
}
</style>