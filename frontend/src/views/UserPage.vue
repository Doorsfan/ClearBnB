<template>
  <div
    v-if="showUserPage && $store.getters.getCurrentUser != null"
    class="mainDiv"
  >
    <div v-if="$store.getters.getCurrentUser != null" class="userPageMainDiv">
      <div class="menuDiv">
        <p
          v-if="$store.getters.getCurrentUser.username != 'admin@ClearBnB.se'"
          class="welcomeMessage"
        >
          Welcome
          {{ currentUserInfo.firstName + ' ' + currentUserInfo.lastName }}!
        </p>
        <p
          v-if="$store.getters.getCurrentUser.username == 'admin@ClearBnB.se'"
          class="welcomeMessage"
        >
          Welcome Admin!
        </p>
        <div class="addNewLeaseDiv">
          <router-link to="/addLease">
            <button @click="addNewLease" class="addNewLeaseButton">
              Add New Lease
            </button>
          </router-link>
        </div>
        <div class="historicalButtonDiv">
          <button
            v-if="showFutureBookings"
            @click="switchToPastDisplay"
            class="showPastBookingsButton"
          >
            Show Past Bookings
          </button>
          <button
            v-if="showPastBookings"
            @click="switchToFutureDisplay"
            class="showPastBookingsButton"
          >
            Show Future Bookings
          </button>
          <p v-if="showPastBookings && !user.username == 'admin@ClearBnB.se'" class="currentBookingsP">
            Your Past Bookings
          </p>
          <p v-if="showFutureBookings && !user.username == 'admin@ClearBnB.se'" class="currentBookingsP">
            Your Current Bookings
          </p>
          <p v-if="showPastBookings && user.username == 'admin@ClearBnB.se'" class="currentBookingsP">
            All Past Bookings
          </p>
          <p v-if="showFutureBookings && user.username == 'admin@ClearBnB.se'" class="currentBookingsP">
            All Current Bookings
          </p>
        </div>
      </div>
      <div v-if="showFutureBookings" class="currentBookingsDiv">
        <div v-if="futureBookings.length == 0" class="noBookingsDiv">
          <p v-if="futureBookings.length == 0" class="noBookingsP">
            No future bookings at the moment
          </p>
        </div>
        <div class="bookings">
          <FutureBookingsList
            @cancelBooking="cancelABooking"
            v-for="(futureBooking, futureIndex) of futureBookings"
            :key="futureIndex"
            :futureBooking="futureBooking"
          />
        </div>
      </div>
      <div v-if="showPastBookings" class="pastBookingsDiv">
        <div class="bookings">
          <PastBookingsList
            v-for="(pastBooking, pastIndex) of pastBookings"
            :key="pastIndex"
            :pastBooking="pastBooking"
          />
          <div v-if="pastBookings.length == 0" class="noBookingsDiv">
            <p v-if="pastBookings.length == 0" class="noBookingsP">
              No past bookings at the moment
            </p>
          </div>
        </div>
      </div>
      <div class="changeUserInfoButtonDiv">
        <button class="changeUserInfoButton" @click="changeUserInfo">
          Change User Info
        </button>
      </div>
    </div>
  </div>
  <div v-if="changeInfo" class="changeUserInfoDiv">
    <div class="mainDiv changeUserInfoMainDiv">
      <form class="userInfoForm">
        <p class="changeUserInfoTitle">Change Info</p>
        <div class="mainFormDiv">
          <div class="divForInputs">
            <div class="InputDiv">
              <p class="newUserInfo">First Name:</p>
              <input
                type="text"
                name="firstNameInput"
                class="firstNameInput"
                v-model="myFirstName"
                placeholder="First Name"
              />
            </div>
            <div class="InputDiv">
              <p class="newUserInfo">Last Name:</p>
              <input
                type="text"
                name="lastNameInput"
                class="lastNameInput"
                v-model="myLastName"
                placeholder="Last Name"
              />
            </div>
            <div class="InputDiv">
              <p class="newUserInfo">Phone Number:</p>
              <input
                type="text"
                name="phoneNumberInput"
                class="phoneNumberInput"
                v-model="myPhoneNumber"
                placeholder="Phone Number"
              />
            </div>
            <div class="InputDiv">
              <p class="newUserInfo">Street Address:</p>
              <input
                type="text"
                name="streetInput"
                class="streetInput"
                v-model="myStreetAddress"
                placeholder="Street Address"
              />
            </div>
            <div class="InputDiv">
              <p class="newUserInfo">City:</p>
              <input
                type="text"
                name="cityInput"
                class="cityInput"
                v-model="myCity"
                placeholder="City"
              />
            </div>
            <div class="InputDiv">
              <p class="newUserInfo">Zip Code:</p>
              <input
                type="text"
                name="zipInput"
                class="zipInput"
                v-model="myZipCode"
                placeholder="Zip Code"
              />
            </div>
            <div class="InputDiv">
              <p class="newUserInfo">Country:</p>
              <input
                type="text"
                name="countryInput"
                class="countryInput"
                v-model="myCountry"
                placeholder="Country"
              />
            </div>
            <div class="newsLetterInputDiv">
              <p class="newUserInfo newsLetterP">Newsletter:</p>
              <input
                type="checkbox"
                name="newsLetterInput"
                class="newsLetterInput"
                v-model="myNewsLetter"
              />
            </div>
          </div>
        </div>
      </form>
      <div class="buttonsDiv">
        <button @click="redisplayUserPage" class="cancelButton" value="Cancel">
          Cancel</button
        ><button
          class="saveChangesButton"
          @click="updateUserInfo"
          value="Save Changes"
        >
          Save Changes
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import User from '../components/User.js';
import UserInfo from '../components/UserInfo.js';
import Booking from '../components/Booking.js';
import FutureBookingsList from '../components/UserView/FutureBookingsList.vue';
import PastBookingsList from '../components/UserView/PastBookingsList.vue';
import AdminBooking from '../components/AdminBooking.js';
export default {
  components: {
    FutureBookingsList,
    PastBookingsList,
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
      myPhoneNumber: '',
      myStreetAddress: '',
      myCity: '',
      myZipCode: '',
      myCountry: '',
      myNewsLetter: false,
      myRoute: this.$route.fullPath,
      changeInfo: false,
      showPastBookings: false,
      showFutureBookings: true,
      showUserPage: true,
    };
  },
  async mounted() {
    this.user = this.$store.getters.getCurrentUser;
    let secondRes = await fetch('/rest/userinfos/' + this.user.id, {
      method: 'GET',
    });
    let result = await secondRes.json();
    this.currentUserInfo = result;
    this.profits = 0;

    if (this.user.username == 'admin@ClearBnB.se') {
      let secondRes = await fetch('/rest/adminBookings', {
        method: 'GET',
      });
      let theResponse = await secondRes.json();
      for (let eachBooking of theResponse) {
        let emptyBooking = new AdminBooking('', '', '', '', '', '', '', '');
        let filledBooking = Object.assign(emptyBooking, eachBooking);
        filledBooking.id = eachBooking.id;
        this.myBookings.push(filledBooking);
      }
      let profitRes = await fetch('/rest/profit/', {
        method: 'GET',
      });
      let myProfit = await profitRes.json();
      let extractedProfit = myProfit[0].totalProfit;
    } else {
      let secondRes = await fetch('/rest/bookings', {
        method: 'GET',
      });
      let theResponse = await secondRes.json();
      for (let eachBooking of theResponse) {
        if (eachBooking.userId == this.user.id) {
          let emptyBooking = new Booking('', '', '', '', '', '', '', '');
          let filledBooking = Object.assign(emptyBooking, eachBooking);
          filledBooking.id = eachBooking.id;
          this.myBookings.push(filledBooking);
        }
      }
    }
    for (let booking of this.myBookings) {
      let splitStartDate = booking.startDate.split('-');
      let today = new Date();
      if (
        splitStartDate[0] <= today.getFullYear() &&
        splitStartDate[1] <= today.getMonth() + 1 &&
        splitStartDate[2] <= today.getDate()
      ) {
        this.pastBookings.push(booking);
      } else {
        this.futureBookings.push(booking);
      }
    }
    if (
      this.currentUserInfo.firstName == 'admin' &&
      this.currentUserInfo.lastName == 'admin'
    ) {
      $('.welcomeMessage').text('Welcome Admin User!');
    } else {
      $('.welcomeMessage').text(
        'Welcome ' +
          this.currentUserInfo.firstName +
          ' ' +
          this.currentUserInfo.lastName +
          '!'
      );
    }
  },
  methods: {
    async updateCurrentUserInfo() {
      this.user = this.$store.getters.getCurrentUser;
      let secondRes = await fetch('/rest/userinfos/' + this.user.id, {
        method: 'GET',
      });
      let result = await secondRes.json();
      this.currentUserInfo = result;
    },
    async cancelABooking(idToCancel) {
      let index = 0;
      for (let bookingToRemoveFromAll of this.myBookings) {
        if (bookingToRemoveFromAll.id == idToCancel) {
          this.myBookings = [
            ...this.myBookings.slice(0, index),
            ...this.myBookings.slice(index + 1),
          ];
        }
        index += 1;
      }
      index = 0;
      for (let bookingToRemoveFromFuture of this.futureBookings) {
        if (bookingToRemoveFromFuture.id == idToCancel) {
          this.futureBookings = [
            ...this.futureBookings.slice(0, index),
            ...this.futureBookings.slice(index + 1),
          ];
        }
        index += 1;
      }
      let deleteAdminResponse = await fetch(
        '/rest/adminBookingsAsAdmin/' + idToCancel,
        {
          method: 'DELETE',
        }
      );

      let deleteAdminResponseInJson = await deleteAdminResponse.json();
      idToCancel = deleteAdminResponseInJson;
      let deleteResponse = await fetch('/rest/bookings/' + idToCancel, {
        method: 'DELETE',
      });
      let deleteResponseInJson = await deleteResponse.json();
    },
    async updateUserInfo() {
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
      );
      let res = await fetch('/rest/userinfos/' + this.user.id, {
        method: 'POST',
        body: JSON.stringify(newUserInfo),
      });
      let responseAsJson = await res.json();
      alert("Your user information has been updated!");
    },
    switchToPastDisplay() {
      if (this.showFutureBookings == true) {
        this.showPastBookings = true;
        this.showFutureBookings = false;
      }
    },
    switchToFutureDisplay() {
      if (this.showPastBookings == true) {
        this.showPastBookings = false;
        this.showFutureBookings = true;
      }
    },
    redisplayUserPage() {
      this.updateCurrentUserInfo();
      this.showUserPage = true;
      this.changeInfo = false;
    },
    changeUserInfo() {
      this.showUserPage = false;
      this.changeInfo = true;
    },
  },
};
</script>
<style scoped>
.newUserInfo {
  background-color: #029ebb;
  color: white;
  border-radius: 10px;
  padding-left: 7px;
  padding-right: 7px;
  width: 50vw;
  max-width: 155px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
  margin-bottom: 10px;
}
.newsLetterInput {
  margin-left: 3px;
  margin-top: 3px;
}
.menuDiv {
  background-color: rgba(255, 255, 255, 0.8);
  margin-left: auto;
  margin-right: auto;
  padding: 7px;
  border-radius: 10px;
  width: 75vw;
  min-width: max-content;
  padding-left: 5vw;
  padding-right: 5vw;
  padding-top: 20px;
  padding-bottom: 20px;
  max-width: 1200px;
}
.noBookingsP {
  background-color: red;
  margin-left: auto;
  margin-right: auto;
  border-radius: 10px;
  font-size: 20px;
  margin-top: 40px;
  margin-bottom: 40px;
  width: 20vw;
  min-width: 150px;
  max-width: 400px;
  height: max-content;
  max-height: 100px;
  color: white;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 2px;
}
.noBookingsDiv {
  background-color: rgba(255, 255, 255, 0.8);
  width: 25vw;
  min-width: 220px;
  border-radius: 10px;
  min-height: 180px;
  height: 13vw;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 2.5%;
}
.userPageMainDiv {
  width: 85vw;
  max-width: 1300px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 5vh;
  padding-bottom: 5vh;
}
.changeUserInfoTitle {
  max-width: 200px;
  min-width: 200px;
  font-size: 25px;
  padding-left: 25px;
  padding-right: 25px;
  margin-bottom: 15px;
  margin-left: auto;
  margin-right: auto;
  background-color: #029ebb;
  border-radius: 10px;
  color: white;
}
.welcomeMessage {
  padding: 10px;
  background-color: #029ebb;
  border-radius: 10px;
  color: white;
  margin-left: auto;
  margin-right: auto;
  width: 50vw;
  min-width: 20vw;
  max-width: 400px;
}
.currentBookingsP {
  margin-top: 10px;
  background-color: #029ebb;
  color: white;
  border-radius: 10px;
  width: 50vw;
  padding-left: 5px;
  padding-right: 5px;
  min-width: 20vw;
  max-width: 400px;
  margin-left: auto;
  margin-right: auto;
}
.mainDiv {
  width: 100vw;
  padding: 10px;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  background-image: url('../../public/Login-signup.jpg');
  font-family: 'mukta';
  background-position: right;
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  overflow-x: hidden;
  min-height: 83vh;
}
.addNewLeaseButton,
.showPastBookingsButton,
.cancelButton,
.saveChangesButton {
  width: 50vw;
  min-width: 20vw;
  max-width: 400px;
  min-height: max-content;
  border-radius: 10px;
  background-color: #029ebb;
  color: white;
  border: 1px solid grey;
  cursor: pointer;
  margin-top: 10px;
  font-size: 18px;
  margin-right: 10px;
  margin-left: 10px;
  padding-left: 3px;
  padding-right: 3px;
}
.cancelButton {
  background-color: rgba(246, 69, 37, 0.842);
  margin-top: 0px;
}
.newsLetterP {
  width: max-content;
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
  padding-right: 5px;
}
.addNewLeaseButton:hover,
.showPastBookingsButton:hover,
.changeUserInfoButton:hover {
  transform: scale(1.08);
}
.changeUserInfoButton {
  width: 50vw;
  min-width: max-content;
  padding-left: 3px;
  padding-right: 3px;
  max-width: 400px;
  height: 40px;
  border-radius: 10px;
  background-color: #029ebb;
  color: white;
  border: 1px solid grey;
  cursor: pointer;
  margin-top: 3px;
  font-size: 18px;
}
.changeUserInfoDiv {
  background-color: rgba(255, 255, 255, 0.8);
}
.userInfoForm {
  background-color: rgba(255, 255, 255, 0.8);
  width: 80vw;
  max-width: 840px;
  margin-left: auto;
  margin-right: auto;
  border-radius: 10px;
  padding-top: 5vh;
}

.newsLetterInput {
  display: inline-block;
  width: max-content;
}
.buttonsDiv {
  background-color: rgba(218, 224, 224, 0.8);
  padding-bottom: 20px;
  max-width: 840px;
  margin-top: 8px;
  margin-left: auto;
  margin-right: auto;
  border-radius: 10px;
  padding-top: 10px;
}
.changeUserInfoButtonDiv {
  background-color: rgba(255, 255, 255, 0.8);
  margin-left: auto;
  margin-right: auto;
  padding: 10px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 10px;
  width: 75vw;
  max-width: 1200px;
}
.cancelButton:hover,
.saveChangesButton:hover {
  transform: scale(1.05);
}

@media only screen and (max-width: 1050px) {
  .buttonsDiv {
    width: 80vw;
    max-width: 80vw;
  }
}
</style>
