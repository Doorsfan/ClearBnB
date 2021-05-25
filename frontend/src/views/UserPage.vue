<template>
  <div class="primaryDiv">
  <div v-if="$store.getters.getCurrentUser != null" class="mainDiv userPageMainDiv">
    <p class="userPageTitle">User Page</p>
    <hr class="hrDiv">
    <p v-if="$store.getters.getCurrentUser.username == 'admin@ClearBnB.se'" class="welcomeMessage">Welcome Admin!</p>
    
    
    <div class="addNewLeaseDiv">

      <hr>
      <router-link to="/addLease">
        <button @click="addNewLease" class="addNewLeaseButton">Add New Lease</button>
      </router-link>
    </div>
    <hr>
    <div class="historicalButtonDiv">
    
      <hr>
      <button @click="switchHistoricalDisplay" class="addNewLeaseButton">Show Past Bookings</button>
    </div>
    <hr>
    <div class="currentBookingsDiv">
      <p class="pApartment">Current bookings:</p>
      <hr>
      <p class="pDiv">No bookings at the moment</p>
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
    <hr>

    <div class="residencesOutBox">
      <router-link class="residencesLink" to="/">Your residences</router-link>
    </div>
    <div class="changeUserInfoButtonDiv">
      <button class="changeUserInfoButton" @click="changeUserInfo">Change info</button>
    </div>
  </div>
  </div>
  <div class="changeUserInfoDiv primaryDiv">
    <div class="mainDiv changeUserInfoMainDiv">
    <form class="userInfoForm">
      <p class="changeUserInfoTitle">Change Info</p>
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
      if(booking.isInThePast(booking.endDate)){
        this.pastBookings.push(booking)
      }
      else if(!booking.isInThePast(booking.endDate)){
        this.futureBookings.push(booking)
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
.hrDiv{
  margin-top: 5px;
}
.otherDiv{
  margin-bottom: 5px;
  font-family: 'mukta';
  font-weight: bold
  ;
}
.residencesLink{
  border: outset 5px #007b8e;
  outline:1px solid black;
  opacity:1;
  background-color: #007b8e;
  padding:3px;
  /*optional*/
  font-family: 'mukta';
  /*input has OS specific font-family*/
  color: black;
  font-weight:bolder;
  text-decoration: none;
  width:max-content;
  font-size:10px;
  margin-bottom:15px;
  margin-top: 10px;
  width: 150px;
    
    
}
.pDiv{
  font-size: 10px;
  font-family: 'mukta';
  color: red;
}

.pApartment{
  font-family: 'mukta';
  text-decoration: none;
  font-weight: bold;
}
.addNewLeaseButton{
  border: outset 5px #007b8e;
  outline:1px solid black;
  opacity:1;
  background-color: #007b8e;
  padding:3px;
  /*optional*/
  font-family: 'mukta';
  /*input has OS specific font-family*/
  color: black;
  font-weight:bolder;
  text-decoration: none;
  width:max-content;
  font-size:10px;
  margin-bottom:15px;
  margin-top: 10px;
  width: 150px;
}
.primaryDiv{
  padding-left:5vw;
  padding-right:5vw;
  padding-bottom:2.5vh;
  padding-top:2.5vh;
  background-image: url('/public/nice.jpg');
  background-position:center;
  background-size:cover;
  background-repeat:no-repeat;
  opacity: 0.8;
  height:max-content;
  padding-bottom: 20vh;
  width:100vw;
  background-attachment: fixed;
  overflow-x:hidden;
}
.changeHistoricalDisplay, .changeHistoricalDisplay:active{
  border: outset 5px #007b8e;;
  outline:1px solid black;
  opacity:1;
  background-color: #007b8e;
  padding:3px;
  /*optional*/
  font-family: 'mukta';
  
  /*input has OS specific font-family*/
  color: black;
  font-weight:bolder;
  text-decoration: none;
  width:max-content;
  font-size:10px;
  margin-bottom:15px;
  margin-top: 10px;
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
    border: outset 5px #007b8e;;
    outline:1px solid black;
    opacity:1;
    background-color: #007b8e;
    padding:3px;
    /*optional*/
    font-family: 'mukta';
    /*input has OS specific font-family*/
    color: black;
    font-weight:bolder;
    text-decoration: none;
    cursor: pointer;
    width:max-content;
    font-size:10px;
    margin-top: 10px;
    background-color: #007b8e;
    width: 150px;
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
  .cancelButton{
    margin-right:5px;
    margin-left:50px;
    background-color: #007b8e;
    font-size: 12px;
    font:'mukta';
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
    background-color:rgba(218, 224, 224, 0.8);
    padding:150px;
  }
  .userPageTitle{
    font-size: 50px;
    margin-top: -100px;
    margin-bottom:20px;
    font-weight:bolder;
  }
  .welcomeMessage{
    font-family: 'mukta';
    margin-bottom:20px;
    opacity:1;
    padding:3px;
  
  }
  .yourFutureBookingsText, .yourPastBookingsText{
    margin-bottom:20px;
    
  
    border:outset 2px;
  }
  .residencesOutBox{
    margin-top:15px;
  }
  .residencesLink{
    font-weight:bolder;
  }

  @media only screen and (max-width: 450px) {
    .mainDiv{
      margin:0px;
      padding:0px;
      margin-left:auto;
      margin-right:auto;
      padding: 10px;
    }
  }
  @media only screen and (max-width: 400px) {
    .mainDiv{
      margin:0px;
      padding:0px;
      margin-left:auto;
      margin-right:auto;
      padding: 10px;
    }
    .changeUserInfoMainDiv{
      transform:scale(0.8);
      padding:10px;
      margin:0px;
    }
  }

  @media only screen and (max-width: 330px) {
    * {
      text-align:center;
      width:max-content;
      margin-left:auto;
      margin-right:auto;
    }
    .changeUserInfoDiv{
      margin:0px;
    }
    .changeUserInfoTitle{
      padding-left:0px;
    }
    .userPageMainDiv{
      margin-top: 50px;
      padding-top: 10px;
      padding-bottom: 10px;
    }
    .changeUserInfoMainDiv{
      transform:scale(0.8);
      padding:5px;
      margin-left:auto;
      margin-right:auto;
      width:240px;
    }
    p {
      display:block;
      width:max-content;
      text-align:center;
      margin-left:auto;
      margin-right:auto;
    }
    input {
      display: block;
      width: max-content;
      margin-left:auto;
      margin-right:auto;
    }
    .mainFormDiv, .divForInputs, .InputDiv{
      width: 220px;
      text-align:center;
      margin:0px;
      margin-top: 2px;
      margin-bottom: 4px;
    }
    .buttonsDiv{
      padding:0px;
      margin:0px;
    }
    .cancelButton{
      margin-left:40px;
    }
  }

  @media only screen and (max-width: 290px) {
    *{
      padding:0px;
      margin:0px;
      text-align:center;
      align-content:center;
      width:max-content;
      margin-left:auto;
      margin-right: auto;
    }
    .mainDiv{
      transform:scale(0.75);
      margin:0px;
      padding:0px;
      padding: 10px;
      
    }
  }
</style>

