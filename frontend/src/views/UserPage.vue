<template>
  <div class="mainDiv">
    <p class="userPageTitle">User Page</p>
    <p class="welcomeMessage"></p>
    <button @click="switchHistoricalDisplay" class="changeHistoricalDisplay">Show Past Bookings</button>
    <div class="currentBookingsDiv">
      <p class="yourBookingsText">Your <b>CURRENT</b> bookings so far:</p>
      <div class="bookings">
        <div class="bookingBox">temp</div>
        <div class="bookingBox">temp</div>
        <div class="bookingBox">temp</div>
        <div class="bookingBox">temp</div>
      </div>
    </div>
    <div class="pastBookingsDiv">
      <p class="yourBookingsText">Your <b>PAST</b> bookings so far:</p>
      <div class="bookings">
        <div class="bookingBox">temp</div>
        <div class="bookingBox">temp</div>
        <div class="bookingBox">temp</div>
        <div class="bookingBox">temp</div>
      </div>
    </div>
    <p class="showAdventure">Show me my next adventure!</p>
    <div class="residencesOutBox">
      <router-link class="residencesLink" to="/">Your residences out for rent</router-link>
    </div>
    <div class="changeUserInfoButtonDiv">
      <button class="changeUserInfoButton" @click="changeUserInfo">Change user info</button>
    </div>
  </div>
  <div class="changeUserInfoDiv">
    <form class="userInfoForm">
      <p class="changeUserInfoTitle">Change User Info</p>
      <div class="mainFormDiv">
        <div class="divForInputs">
          <div class="InputDiv">
            <p class="newUserInfo">First Name:</p><input type="text" name="firstNameInput" class="firstNameInput" v-model="myFirstName" placeholder="First Name">
          </div>
          <div class="InputDiv">
            <p class="newUserInfo">Last Name:</p><input type="text" name="lastNameInput" class="lastNameInput" v-model="myLastName" placeholder="Last Name">
          </div>
          <div class="InputDiv">
            <p class="newUserInfo">Phone Number:</p><input type="text" name="phoneNumberInput" class="phoneNumberInput" v-model="myPhoneNumber" placeholder="Phone Number">
          </div>
          <div class="InputDiv">
            <p class="newUserInfo">Street Address:</p><input type="text" name="streetInput" class="streetInput" v-model="myStreetAddress" placeholder="Street Address">
          </div>
          <div class="InputDiv">
            <p class="newUserInfo">City:</p><input type="text" name="cityInput" class="cityInput" v-model="myCity" placeholder="City">
          </div>
          <div class="InputDiv">
            <p class="newUserInfo">Zip Code:</p><input type="text" name="zipInput" class="zipInput" v-model="myZipCode" placeholder="Zip Code">
          </div>
          <div class="InputDiv">
            <p class="newUserInfo">Country:</p><input type="text" name="countryInput" class="countryInput" v-model="myCountry" placeholder="Country">
          </div>
          <div class="newsLetterInputDiv">
            <p class="newUserInfo">Newsletter:</p><input type="checkbox" name="newsLetterInput" class="newsLetterInput"
            v-model="myNewsLetter">
          </div>
        </div>
      </div>
    </form>
    <div class="buttonsDiv">
      <button @click="redisplayUserPage" class="cancelButton" value="Cancel">Cancel</button><button class="saveChangesButton" @click="updateUser" value="Save Changes">Save Changes</button>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      user: '',
      myBooking: ['temp', 'temp', 'temp'],
      myFirstName: '',
      myLastName: '',
      myPhoneNumber:'',
      myStreetAddress:'',
      myCity: '',
      myZipCode: '',
      myCountry: '',
      myNewsLetter: ''
    }
  },
  mounted(){
    this.user = this.$store.getters.getCurrentUser
    console.log(this.user.userInfo);
    $('.welcomeMessage').text("Welcome " + this.user.userInfo.firstName + " " + this.user.userInfo.lastName + "!");
  },
  methods:{
    switchHistoricalDisplay(){
      if($('.changeHistoricalDisplay').text() == "Show Past Bookings"){
        $('.changeHistoricalDisplay').text("Show Current Bookings")
        document.getElementsByClassName('currentBookingsDiv')[0].style.display = 'none';
        document.getElementsByClassName('pastBookingsDiv')[0].style.display = 'block';
      }
      else if($('.changeHistoricalDisplay').text() == "Show Current Bookings"){
        $('.changeHistoricalDisplay').text("Show Past Bookings")
        document.getElementsByClassName('currentBookingsDiv')[0].style.display = 'block';
        document.getElementsByClassName('pastBookingsDiv')[0].style.display = 'none';
      }
      
    },
    changeUserInfo(){
      this.myFirstName = this.user.userInfo.firstName;
      this.myLastName = this.user.userInfo.lastName;
      this.myPhoneNumber = this.user.userInfo.phoneNumber;
      this.myStreetAddress = this.user.userInfo.streetAddress;
      this.myCity = this.user.userInfo.city;
      this.myZipCode = this.user.userInfo.zipCode;
      this.myCountry = this.user.userInfo.country;
      this.myNewsLetter = this.user.userInfo.newsLetter;
      document.getElementsByClassName('mainDiv')[0].style.display = "none";
      document.getElementsByClassName('changeUserInfoDiv')[0].style.display = "block";
    },
    redisplayUserPage(){
      document.getElementsByClassName('changeUserInfoDiv')[0].style.display = "none";
      document.getElementsByClassName('mainDiv')[0].style.display = "block";
    }
  }
}
</script>
<style scoped>
.changeHistoricalDisplay, .changeHistoricalDisplay:active{
  background: none!important;
  background-color:none;
  border: none;
  padding: 0!important;
  /*optional*/
  font-family: arial, sans-serif;
  /*input has OS specific font-family*/
  color: #069;
  text-decoration: none;
  width:max-content;
  font-size:20px;
  margin-bottom:15px;
}
.pastBookingsDiv{
  display:none;
}
.userPageTitle{
  text-align:center;
}
  .bookingBox{
    text-align:center;
  }
  .bookings{
    margin-bottom:20px;
  }
  .showAdventure{
    color:green;
  }
  .changeUserInfoButtonDiv{
    margin-top:20px;
  }
  .changeUserInfoButton{
    background: none!important;
    border: none;
    padding: 0!important;
    /*optional*/
    font-family: arial, sans-serif;
    /*input has OS specific font-family*/
    color: #069;
    text-decoration: none;
    cursor: pointer;
    width:max-content;
  }
  .changeUserInfoDiv{
    display:none;
  }
  .saveChangesButton,.cancelButton{
    margin-left:2.5px;
    margin-right:2.5px;
    padding:2px;
    margin-top: 5px;
  }
  .buttonsDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
  }
  .newsLetterInputDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
  }
  input{
    padding-left:2px;
  }
  .changeUserInfoTitle{
    margin-top:20px;
    width:max-content;
    margin-left:auto;
    margin-right:auto;
    font-weight:bolder;
  }
  .InputDiv{
    margin:8px;
  }
  .newUserInfo{
    display:inline-block;
    margin-right:3px;
  }
  .mainFormDiv{
    text-align:right;
    width:max-content;
    margin-left:auto;
    margin-right:auto;
  }
  .divForText{
    width:max-content;
    text-align:right;
    display:inline-block;
    margin-right:2px;
  }
  .divForInputs{
    display:inline-block;
  }
  input{
    display:inline-block;
    font-size:14px;
  }
  .mainDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
    margin-top:20px;
    text-align:center;
  }
  .userPageTitle{
    margin-bottom:20px;
    font-weight:bolder;
  }
  .welcomeMessage{
    margin-bottom:20px;
  }
  .residencesLink:visited{
    color:blue;
  }
  .yourBookingsText{
    margin-bottom:20px;
  }
  .userInfoForm{
    
  }
</style>

