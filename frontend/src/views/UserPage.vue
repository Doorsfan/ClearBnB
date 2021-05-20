<template>
  <div class="mainDiv">
    <p class="userPageTitle">User Page</p>
    <p class="welcomeMessage"></p>
    <button @click="switchHistoricalDisplay" class="changeHistoricalDisplay">Show Past Bookings</button>
    <div class="currentBookingsDiv">
      <p class="yourFutureBookingsText">Your <b>CURRENT</b> bookings so far:</p>
      <div class="bookings">
        <FutureBookingsList @cancelBooking="cancelABooking" v-for="(futureBooking, futureIndex) of futureBookings"
        :key="futureIndex"
        :futureBooking="futureBooking" />
      </div>
    </div>
    <div class="pastBookingsDiv">
      <p class="yourPastBookingsText">Your <b>PAST</b> bookings so far:</p>
      <div class="bookings">
        <PastBookingsList v-for="(pastBooking, pastIndex) of pastBookings"
        :key="pastIndex"
        :pastBooking="pastBooking" />
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
      <button @click="redisplayUserPage" class="cancelButton" value="Cancel">Cancel</button><button class="saveChangesButton" @click="updateUserInfo" value="Save Changes">Save Changes</button>
    </div>
  </div>
</template>

<script>
import User from '../components/User.js'
import UserInfo from '../components/UserInfo.js'
import Booking from '../components/Booking.js'
import FutureBookingsList from '../components/UserView/FutureBookingsList.vue'
import PastBookingsList from '../components/UserView/PastBookingsList.vue'
import AdminBooking from '../components/AdminBooking.js'
export default {
  components: {
    FutureBookingsList,
    PastBookingsList
  },
  data() {
    return {
      user: '',
      currentUserInfo: '',
      myBookings: [],
      futureBookings: [],
      pastBookings: [],
      profits: 0,
      myFirstName: '',
      myLastName: '',
      myPhoneNumber:'',
      myStreetAddress:'',
      myCity: '',
      myZipCode: '',
      myCountry: '',
      myNewsLetter: false,
      myRoute: this.$route.fullPath
    }
  },
  watch: {
    myRoute(){
      console.log("my route changed!");
    }
  },
  async mounted(){
    if(document.getElementsByClassName("sunIconInHeader").length > 0){
      document.getElementsByClassName("sunIconInHeader")[0].src = '/public/home_icon.png'
      document.getElementsByClassName("sunIconInHeader")[0].className = 'house_icon'
      document.getElementsByClassName("homeText")[0].style.display = 'block';
      document.getElementsByClassName("center")[0].style.height = '70px';
    }
    this.user = this.$store.getters.getCurrentUser
    let secondRes = await fetch('/rest/userinfos/' + this.user.id, {
        method: 'GET'
      });
    let result = await secondRes.json()
    this.currentUserInfo = result;
    this.profits = 0;

    //Load from the DB all the Bookings that are tied to this users userId based on the userInfo
    if(this.user.username == 'admin@ClearBnB.se'){
      $(".yourPastBookingsText").text("All PAST Bookings in the System so far")
      $(".yourFutureBookingsText").text("All FUTURE Bookings in the System so far")
      let secondRes = await fetch('/rest/adminBookings', {
        method: 'GET'
      });
      let theResponse = await secondRes.json();
      for(let eachBooking of theResponse){
        let emptyBooking = new AdminBooking('','','','','','','','');
        let filledBooking = Object.assign(emptyBooking, eachBooking);
        filledBooking.id = eachBooking.id;
        this.myBookings.push(filledBooking);
      }
      let profitRes = await fetch('/rest/profit/', {
        method: 'GET'
      });
      let myProfit = await profitRes.json();
      let extractedProfit = myProfit[0].totalProfit;
      console.log("my profit was: ", extractedProfit);
    }
    else{
      let secondRes = await fetch('/rest/bookings', {
        method: 'GET'
      });
      let theResponse = await secondRes.json();
      for(let eachBooking of theResponse){
        if(eachBooking.userId == this.user.id){
          let emptyBooking = new Booking('','','','','','','','');
          let filledBooking = Object.assign(emptyBooking, eachBooking);
          filledBooking.id = eachBooking.id;
          this.myBookings.push(filledBooking);
        }
      }
    }
    
    for(let booking of this.myBookings){
      if(booking.isInTheFuture(booking.endDate)){
        this.futureBookings.push(booking)
      }
      else if(!booking.isInTheFuture(booking.endDate)){
        this.pastBookings.push(booking)
      }
    }
    if(this.currentUserInfo.firstName == "admin" && this.currentUserInfo.lastName == "admin"){
      $('.welcomeMessage').text("Welcome Admin User!");
    }
    else{
      $('.welcomeMessage').text("Welcome " + this.currentUserInfo.firstName + " " + this.currentUserInfo.lastName + "!");
    }
    
  },
  methods:{
    async cancelABooking(idToCancel){
      let index = 0;
      for(let bookingToRemoveFromAll of this.myBookings){
        if(bookingToRemoveFromAll.id == idToCancel){
          this.myBookings = [...this.myBookings.slice(0,index), ...this.myBookings.slice(index+1)]
        }
        index += 1;
      }
      index = 0;
      for(let bookingToRemoveFromFuture of this.futureBookings){
        if(bookingToRemoveFromFuture.id == idToCancel){
          this.futureBookings = [...this.futureBookings.slice(0,index), ...this.futureBookings.slice(index+1)]
        }
        index += 1;
      }
      let deleteAdminResponse = await fetch('/rest/adminBookingsAsAdmin/' + idToCancel, {
        method: 'DELETE'
      });

      let deleteAdminResponseInJson = await deleteAdminResponse.json();
      idToCancel = deleteAdminResponseInJson;
      let deleteResponse = await fetch('/rest/bookings/' + idToCancel, {
        method: 'DELETE'
      });
      let deleteResponseInJson = await deleteResponse.json();
    },
    async updateUserInfo(){
      let newUserInfo = new UserInfo(
        this.user.id,
        this.myFirstName,
        this.myLastName,
        this.myStreetAddress,
        this.myZipCode,
        this.myCity,
        this.myCountry,
        this.myPhoneNumber,
        this.myNewsLetter
      )
      let res = await fetch('/rest/userinfos/' + this.user.id, {
        method: 'POST',
        body: JSON.stringify(newUserInfo)
      })
      let responseAsJson = await res.json();
    },
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
      this.myFirstName = this.currentUserInfo.firstName;
      this.myLastName = this.currentUserInfo.lastName;
      this.myPhoneNumber = this.currentUserInfo.phoneNumber;
      this.myStreetAddress = this.currentUserInfo.streetAddress;
      this.myCity = this.currentUserInfo.city;
      this.myZipCode = this.currentUserInfo.zipCode;
      this.myCountry = this.currentUserInfo.country;
      this.myNewsLetter = this.currentUserInfo.newsLetter;
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
    font-size:20px;
    margin-top:25px;
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
  .yourFutureBookingsText, .yourPastBookingsText{
    margin-bottom:20px;
  }
  .residencesOutBox{
    margin-top:15px;
  }
  .residencesLink{
    font-weight:bolder;
  }
</style>

