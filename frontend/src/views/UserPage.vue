<template>
  <div class="mainDiv">
    <p class="userPageTitle">User Page</p>
    <p class="welcomeMessage"></p>
    <button @click="switchHistoricalDisplay" class="changeHistoricalDisplay">Show Past Bookings</button>
    <div class="currentBookingsDiv">
      <p class="yourBookingsText">Your <b>CURRENT</b> bookings so far:</p>
      <div class="bookings">
        <FutureBookingsList v-for="(futureBooking, futureIndex) of futureBookings"
        :key="futureIndex"
        :myBooking="futureBooking" />
      </div>
    </div>
    <div class="pastBookingsDiv">
      <p class="yourBookingsText">Your <b>PAST</b> bookings so far:</p>
      <div class="bookings">
        <BookingsList v-for="(pastBooking, pastIndex) of pastBookings"
        :key="pastIndex"
        :myBooking="pastBooking" />
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
import User from '../components/User.js'
import UserInfo from '../components/UserInfo.js'
import Booking from '../components/Booking.js'
import Lease from '../components/Lease.vue'
import FutureBookingsList from '../components/UserView/FutureBookingsList.vue'
import PastBookingList from '../components/UserView/PastBookingList.vue'
export default {
  components: {
    BookingsList
  },
  data() {
    return {
      user: '',
      myBookings: [],
      futureBookings: [],
      pastBookings: [],
      myFirstName: '',
      myLastName: '',
      myPhoneNumber:'',
      myStreetAddress:'',
      myCity: '',
      myZipCode: '',
      myCountry: '',
      myNewsLetter: false
    }
  },
  mounted(){
    let emptyUser = new User('', '', [], new UserInfo('','','','','','','','',false));
    let emptyUserInfo = new UserInfo('','','','','','','','',false)
    this.user = this.$store.getters.getCurrentUser
    let currentUser = Object.assign(emptyUser,this.user)
    let myUserInfo = Object.assign(emptyUserInfo,this.user.userInfo)
    currentUser.userInfo = myUserInfo

    let myLease = new Lease(1, currentUser.getUserInfo().getUserId(),'Cozy Winter Cottage', 'The Alps', 'Lorem Ipsum', 'House', 'Entire', '2021-05-05', '2021-05-14', 1000, '2', '1',['Shower: true', 'Heating: true'], ["https://images.unsplash.com/photo-1579627559241-aa855a66df15?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=717&q=80",
      "https://images.unsplash.com/photo-1572120360610-d971b9d7767c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"])
    currentUser.addBooking(new Booking(currentUser.getUserInfo().getUserId(), 2, myLease.getLocation(), '2021-05-05', '2021-05-14', 2,2000, myLease))

    let secondLease = new Lease(2, currentUser.getUserInfo().getUserId(),'Summer Resort', 'Colorado', 'Lorem Ipsum', 'House', 'Entire', '2021-15-16', '2021-05-25', 2000, '3', '2',['Shower: true', 'Heating: true'], ["https://images.unsplash.com/photo-1583845112203-29329902332e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=334&q=80",
      "https://images.unsplash.com/photo-1583845112203-29329902332e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=334&q=80"])
    currentUser.addBooking(new Booking(currentUser.getUserInfo().getUserId(), 1, secondLease.getLocation(), '2021-05-16', '2021-05-20', 1,1000, myLease))
    this.user = currentUser
    this.myBookings = currentUser.getBookings();
    console.log("user bookings was: ", this.myBookings);
    for(let booking of this.myBookings){
      console.log("Checking agaisnt : ", booking);
      console.log("The end date was: ", booking.endDate);
      if(booking.isInTheFuture(booking.endDate)){
        console.log("Added to future")
        this.futureBookings.push(booking)
        console.log("FUTURE BOOKINGS IS NOW: ", this.futureBookings);
      }
      else if(!booking.isInTheFuture(booking.endDate)){
        console.log("Added to past")
        this.pastBookings.push(booking)
        console.log("PAST BOOKINGS IS NOW: ", this.pastBookings);
      }
    }
    console.log("This booking was in the future: ", this.myBookings[0].isInTheFuture()) //Should be false
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
  .yourBookingsText{
    margin-bottom:20px;
  }
  .residencesOutBox{
    margin-top:15px;
  }
  .residencesLink{
    font-weight:bolder;
  }
</style>

