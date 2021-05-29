<template>
  <div @click="mainWasClicked" class="booking-view-app-main">
    <div class="booking-view-container">
      <div>
        <div class="location-maxguests">
          <h1>{{ title }}</h1>
          <p>{{ location }} | Maximum {{ maxGuests }} guests</p>
        </div>
        <div class="booking-view-images-container">
          <img
            v-if="imageURLs.length > 0"
            class="booking-view-image-1"
            :src="imageURLs[0]"
          />
          <img
            v-if="imageURLs.length > 1"
            class="booking-view-image-2"
            :src="imageURLs[1]"
          />
          <img
            v-if="imageURLs.length > 2"
            class="booking-view-image-3"
            :src="imageURLs[2]"
          />
          <img
            v-if="imageURLs.length > 3"
            class="booking-view-image-4"
            :src="imageURLs[3]"
          />
          <img
            v-if="imageURLs.length > 4"
            class="booking-view-image-5"
            :src="imageURLs[4]"
          />
        </div>
        <div class="type-price">
          <p>
            {{ typeOfHousing }} | Per person per night:
            {{ Math.round(price * 1.15 * 100) / 100 }} Euro
          </p>
        </div>
        <div class="booking-view-description">
          <p>{{ description }}</p>
        </div>
        <div class="amenitiesTitle">Amenities</div>
        <div class="myAmenities">
          <div class="wifiDiv">
            <div v-if="amenities[0] == 'wifi: true'" class="hasWifi">Wifi</div>
          </div>
          <div class="kitchenDiv">
            <div v-if="amenities[1] == 'kitchen: true'" class="hasKitchen">
              Kitchen
            </div>
          </div>
          <div class="washerDiv">
            <div v-if="amenities[2] == 'washer: true'" class="hasWasher0">
              Washer
            </div>
          </div>
          <div class="heatingDiv">
            <div v-if="amenities[3] == 'heating: true'" class="hasHeating">
              Heating
            </div>
          </div>
          <div class="acDiv">
            <div
              v-if="amenities[4] == 'airConditioner: true'"
              class="hasKitchen"
            >
              Air Conditioning
            </div>
          </div>
        </div>
        <div class="amountOfBeds">
          <p class="bedsText">Available Beds</p>
          {{ lease.beds }}
        </div>
        <div v-if="!shouldShowModal" class="DivForGuests">
          <div class="guestsTitle">Guests</div>
          <select class="myGuests" v-model="selectedNumberOfGuests">
            <option value="" disabled>Guests</option>
            <option v-if="maxGuests > 0">1</option>
            <option v-if="maxGuests > 1">2</option>
            <option v-if="maxGuests > 2">3</option>
            <option v-if="maxGuests > 3">4</option>
            <option v-if="maxGuests > 4">5</option>
          </select>
          <p v-if="maxGuests == 1" class="singularGuest">guest</p>
          <p v-if="maxGuests > 1" class="manyGuests">guests</p>
        </div>
        <div class="wholeOrPartialDiv">
          <p class="wholeOrPartialText">You get Access to:</p>
          <p v-if="lease.entireResidence" class="wholeResidenceText">
            The Whole Residence
          </p>
          <p v-if="!lease.entireResidence" class="partResidenceText">
            A Part of The Residence
          </p>
        </div>
        <div v-if="!shouldShowModal" class="myDatePickerDiv">
          <DatepickerForBookingView
            @newDisabledDates="checkDisabledDates"
            @updatedChosenStartDate="newStartDate"
            @updatedChosenEndDate="newEndDate"
            v-if="lease"
            :myLease="lease"
          />
        </div>
      </div>
      <div class="PriceToPayInTotal">
        <p v-if="selectedNumberOfGuests > 0 && amountOfDays > 0">
          Total Price to pay for {{ amountOfDays }} days for
          {{ selectedNumberOfGuests }}
          {{ selectedNumberOfGuests > 1 ? 'people' : 'person' }}:
          {{
            Math.round(1.15 * (price * selectedNumberOfGuests * amountOfDays))
          }}
          Euros
        </p>
        <p v-if="selectedNumberOfGuests == 0 && !dateIsTaken">
          Please select amount of Guests attending to see the total price.
        </p>
      </div>
      <div class="BookingButtonDiv">
        <button
          @click="book"
          v-if="
            !shouldShowModal &&
            user &&
            selectedNumberOfGuests > 0 &&
            !dateIsTaken &&
            amountOfDays > 0
          "
          class="bookingButton"
          type="button"
          value="Book"
        >
          Book Now!
        </button>
        <div
          class="SpecialErrorBox"
          v-if="
            (chosenStartDate.length == 0 ||
              chosenEndDate.length == 0 ||
              selectedNumberOfGuests == 0) &&
            user != null
          "
        >
          Before you can book, you must address:
        </div>
        <div
          class="errorBox"
          v-if="
            (chosenStartDate.length == 0 || chosenStartDate.length == 0) &&
            user != null
          "
          value="Choose a From Date and a To Date First!"
        >
          Choose a From Date and a To Date
        </div>
        <div
          v-if="selectedNumberOfGuests == 0 && user != null && amountOfDays > 0"
          class="errorBox"
          value="Choose Amount of Guests first!"
        >
          Choose amount of Guests
        </div>
        <div
          v-if="amountOfDays < 0"
          class="errorBox"
          Value="Cannot choose a negative amount of days!"
        >
          You cannot choose a negative amount of days.
        </div>
        <div v-if="dateIsTaken" class="errorBox">
          You cannot book across already occupied bookings!
        </div>
        <button
          @click="goToLogin"
          v-else-if="
            amountOfDays > 0 && selectedNumberOfGuests > 0 && user == null
          "
          class="bookingButton"
          type="button"
          value="You have to log in to Book!"
        >
          Log in to Book!
        </button>
      </div>
    </div>
  </div>
  <div v-if="shouldShowModal" class="confirmationModal">
    <div class="thanksTextDiv modalDiv">
      <p class="thanksTextP">Thank you for making a booking at ClearBnB!</p>
    </div>
    <div class="locationText">
      <div class="modalTitle modalDiv">
        You booked a stay at a {{ typeOfHousing }} in {{ location }}!
      </div>
      <div class="fromModalDiv modalDiv">
        <p class="fromModalP">From the:</p>
        {{ priceHelper.getCorrectDateFormat(chosenStartDate) }}
      </div>
      <div class="toModalDiv modalDiv">
        <p class="toModalP">To the:</p>
        {{ priceHelper.getCorrectDateFormat(chosenEndDate) }}
      </div>
      <div class="amountOfDaysModalDiv modalDiv">
        For a total of {{ amountOfDays }}
        {{ amountOfDays > 1 ? 'days' : 'day' }}
      </div>
      <div class="peopleModalDiv modalDiv">
        <p class="peopleModalP">For</p>
        {{ selectedNumberOfGuests }}
        {{ selectedNumberOfGuests > 1 ? 'people' : 'person' }}
      </div>
      <div class="priceModalDiv modalDiv">
        <p class="priceModalP">
          For a grand total of:
          {{
            Math.round(1.15 * (price * selectedNumberOfGuests * amountOfDays))
          }}
          Euros
        </p>
      </div>
      <button @click="goBackToStartPage" class="confirmModalButton">
        Back to Start Page
      </button>
    </div>
  </div>
  <router-link class="hiddenLoginPage hiddenLink" to="/login"
    >Login</router-link
  >
  <router-link class="hiddenStartPage hiddenLink" to="/">Start</router-link>
</template>

<script setup="">
import DatepickerForAvailableDates from '../components/AddLeaseView/DatepickerForAvailableDates.vue';
import DatepickerForBookingView from '../components/BookingView/DatepickerForBookingView.vue';
</script>
<script>
import User from '../components/User.js';
import Booking from '../components/Booking';
import AdminBooking from '../components/AdminBooking';
import Lease from '../components/Lease.vue';
import PPPN from '../components/PPPN.js';
import Profit from '../components/Profit.js';
import BookingHelper from '../components/BookingHelper.js';
import store from '../components/../store.js';

export default {
  watch: {
    selectedNumberOfGuests() {
      let formattedStartDate = this.priceHelper.getCorrectDateFormat(
        this.chosenStartDate
      );
      let formattedEndDate = this.priceHelper.getCorrectDateFormat(
        this.chosenEndDate
      );
      let amountOfDays = this.priceHelper.computeDateRange(
        this.chosenStartDate,
        this.chosenEndDate
      );
      this.amountOfDays = amountOfDays;
      if (this.amountOfDays == 0) {
        this.amountOfDays = 1;
      }
    },
    chosenStartDate() {
      let formattedStartDate = this.priceHelper.getCorrectDateFormat(
        this.chosenStartDate
      );
      let formattedEndDate = this.priceHelper.getCorrectDateFormat(
        this.chosenEndDate
      );
      let amountOfDays = this.priceHelper.computeDateRange(
        this.chosenStartDate,
        this.chosenEndDate
      );
      this.amountOfDays = amountOfDays;
      if (this.amountOfDays == 0) {
        this.amountOfDays = 1;
      }
      let splitFormattedStartDate = formattedStartDate.split('-');
      let splitFormattedEndDate = formattedEndDate.split('-');
    },
    chosenEndDate() {
      let formattedStartDate = this.priceHelper.getCorrectDateFormat(
        this.chosenStartDate
      );
      let formattedEndDate = this.priceHelper.getCorrectDateFormat(
        this.chosenEndDate
      );
      let amountOfDays = this.priceHelper.computeDateRange(
        this.chosenStartDate,
        this.chosenEndDate
      );
      this.amountOfDays = amountOfDays;
      if (this.amountOfDays == 0) {
        this.amountOfDays = 1;
      }
    },
    lease() {
      this.title = this.lease.title;
      this.location = this.lease.location;
      this.maxGuests = this.lease.maxGuests;
      this.imageURLs = this.lease.imageURLs;
      this.typeOfHousing = this.lease.typeOfHousing;
      this.price = this.lease.price;
      this.description = this.lease.description;
      this.amenities = this.lease.amenities;
    },
  },
  data() {
    return {
      title: 'Default',
      location: '',
      maxGuests: '',
      imageURLs: ['', ''],
      typeOfHousing: '',
      price: '',
      description: '',
      lease: '',
      selectedNumberOfGuests: '',
      amenities: '',
      chosenStartDate: '',
      chosenEndDate: '',
      amountOfDays: '',
      startDateInBasicFormat: '',
      endDateInBasicFormat: '',
      user: this.$store.getters.getCurrentUser,
      priceHelper: new PPPN(),
      allTakenDates: '',
      dateIsTaken: false,
      didNotBookYet: true,
      shouldShowModal: false,
    };
  },
  methods: {
    checkDisabledDates(bookedDates) {
      for (let date of bookedDates) {
        if (
          date.getTime() >= this.chosenStartDate.getTime() &&
          date.getTime() <= this.chosenEndDate.getTime()
        ) {
          this.dateIsTaken = true;
          return true;
        }
      }
      this.dateIsTaken = false;
      return false;
    },
    mainWasClicked(event) {
      if (
        this.shouldShowModal &&
        event.target.className != 'confirmationModal'
      ) {
        document.getElementsByClassName(
          'booking-view-app-main'
        )[0].style.opacity = 1;
        this.shouldShowModal = false;
      }
    },
    goToLogin() {
      document.getElementsByClassName('hiddenLoginPage')[0].click();
    },
    goBackToStartPage() {
      document.getElementsByClassName('hiddenStartPage')[0].click();
    },
    newStartDate(myNewStartDate) {
      let today = new Date();
      if (
        myNewStartDate.getFullYear() < today.getFullYear() &&
        myNewStartDate.getMonth() <= today.getMonth() &&
        myNewStartDate.getDate() <= today.getDate()
      ) {
        myNewStartDate = today;
      }
      this.chosenStartDate = myNewStartDate;
      this.startDateInBasicFormat =
        this.priceHelper.getCorrectDateFormat(myNewStartDate);
      if (this.startDateInBasicFormat == this.endDateInBasicFormat) {
        this.amountOfDays = 1;
      }
    },
    newEndDate(myNewEndDate) {
      this.chosenEndDate = myNewEndDate;
      this.endDateInBasicFormat =
        this.priceHelper.getCorrectDateFormat(myNewEndDate);
      if (this.startDateInBasicFormat == this.endDateInBasicFormat) {
        this.amountOfDays = 1;
      }
    },
    async book() {
      let myPriceHelper = new PPPN();
      let emptyUser = new User('', '');
      let filledUser = Object.assign(emptyUser, this.user);
      let formattedStartDate = myPriceHelper.getCorrectDateFormat(
        this.chosenStartDate
      );
      let formattedEndDate = myPriceHelper.getCorrectDateFormat(
        this.chosenEndDate
      );
      if (this.amountOfDays != 1) {
        let newAmountOfDays = myPriceHelper.computeDateRange(
          this.chosenStartDate,
          this.chosenEndDate
        );
        this.amountOfDays = newAmountOfDays;
      }
      let basePrice =
        this.amountOfDays * this.selectedNumberOfGuests * this.price;
      let toPay = Math.round(
        1.15 * (this.amountOfDays * this.selectedNumberOfGuests * this.price)
      );
      let profit = toPay - basePrice;
      let myBooking = new Booking(
        filledUser.id,
        this.lease.id,
        this.location,
        formattedStartDate,
        formattedEndDate,
        this.selectedNumberOfGuests,
        toPay,
        this.lease
      );
      let res = await fetch('/rest/bookings');
      let responseInJson = await res.json();
      let alreadyTakenDates = [];
      for (let booking of responseInJson) {
        if (booking.leaseId == this.lease.id) {
          let bookingStartDate = booking.startDate;
          let mySplitStartDate = bookingStartDate.split('-');
          let myStartYear = Number(mySplitStartDate[0]);
          let myStartMonth = Number(mySplitStartDate[1]) - 1;
          let myStartDay = Number(mySplitStartDate[2]);

          let bookingEndDate = booking.endDate;
          let mySplitEndDate = bookingEndDate.split('-');
          let myEndYear = Number(mySplitEndDate[0]);
          let myEndMonth = Number(mySplitEndDate[1] - 1);
          let myEndDay = Number(mySplitEndDate[2]);

          let takenDay = '';

          let date1 = new Date(myStartYear, myStartMonth, myStartDay);
          let date2 = new Date(myEndYear, myEndMonth, myEndDay);
          let differenceInTime = date2.getTime() - date1.getTime();
          let differenceInDays = differenceInTime / (1000 * 3600 * 24);
          while (differenceInDays > -1) {
            differenceInDays -= 1;
            alreadyTakenDates.push(new Date(myEndYear, myEndMonth, myEndDay));
            if (myEndDay == 1) {
              myEndMonth -= 1;
              if (myEndMonth == -1) {
                myEndYear -= 1;
                myEndMonth = 11;
              }
              if (
                myEndMonth == 11 ||
                myEndMonth == 9 ||
                myEndMonth == 7 ||
                myEndMonth == 6 ||
                myEndMonth == 4 ||
                myEndMonth == 2 ||
                myEndMonth == 0
              ) {
                myEndDay = 31;
              } else if (
                myEndMonth == 10 ||
                myEndMonth == 8 ||
                myEndMonth == 5 ||
                myEndMonth == 3
              ) {
                myEndDay = 30;
              } else {
                myEndDay = 28;
              }
            } else {
              myEndDay -= 1;
            }
          }
        }
      }
      if (this.checkDisabledDates(alreadyTakenDates)) {
        alert(
          'Sadly, across the dates you chose - Someone else beat you to book it. Sorry!'
        );
        return;
      }
      this.shouldShowModal = true;
      document.getElementsByClassName(
        'booking-view-app-main'
      )[0].style.opacity = 0.4;
      let secondRes = await fetch('/rest/bookings', {
        method: 'POST',
        body: JSON.stringify(myBooking),
      });
      let myProfit = new Profit(profit);

      let thirdRes = await fetch('/rest/profit/', {
        method: 'POST',
        body: JSON.stringify(myProfit),
      });

      let secondResponseAsJson = await secondRes.json();

      let filledAdminBooking = new AdminBooking(
        secondResponseAsJson['id'],
        filledUser.id,
        this.lease.id,
        this.lease.location,
        formattedStartDate,
        formattedEndDate,
        this.selectedNumberOfGuests,
        toPay,
        this.lease
      );
      let adminRes = await fetch('/rest/adminBookings', {
        method: 'POST',
        body: JSON.stringify(filledAdminBooking),
      });
      let adminResponseAsJson = await adminRes.json();
      this.getMyLease();
    },
    async getMyLease() {
      let res = await fetch('/rest/leases/' + this.$route.query.id);
      let responseInJson = await res.json();
      let splitMyDate = responseInJson.startDate.split('-');
      let today = new Date();

      let pastYear = splitMyDate[0];
      let pastMonth = splitMyDate[1];
      let pastDay = splitMyDate[2];

      if (
        pastYear <= today.getFullYear() &&
        pastMonth <= today.getMonth() + 1 &&
        pastDay <= today.getDate()
      ) {
        responseInJson.startDate =
          today.getFullYear() +
          '-' +
          (today.getMonth() + 1 < 10
            ? '0' + (today.getMonth() + 1)
            : today.getMonth() + 1);
        if (today.getDate() < 10) {
          responseInJson.startDate += '-' + '0' + today.getDate();
        } else {
          responseInJson.startDate += '-' + today.getDate();
        }
      }
      this.lease = responseInJson;
      let myHelper = new BookingHelper();
      store.commit('setBookingHelper', myHelper);
    },
  },
  async created() {
    await this.getMyLease();
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&family=Lobster&family=Merriweather+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Raleway:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap');

* {
  font-family: 'mukta';
}
.hiddenLink {
  display: hidden;
}
.thanksTextP {
  font-size: 26px;
  margin-bottom: 10px;
}
.priceModalDiv {
  padding-bottom: 20px;
}
.confirmModalButton {
  width: max-content;
  padding: 5px;
  height: 50px;
  font-size: 25px;
  background-color: rgb(40, 182, 40);
  border-radius: 15px;
}
.modalDiv {
  margin: 5px;
  font-size: 18px;
}
.fromModalP,
.toModalP {
  width: max-content;
  display: inline-block;
  text-align: right;
  margin-right: 5px;
}
.peopleModalP {
  display: inline-block;
}
.confirmationModal {
  font: 'mukta';
  position: fixed;
  left: 35vw;
  z-index: 5;
  color: black;
  background: radial-gradient(#ebf8e1 30%, #f69d3c);
  top: 20vh;
  width: 500px;
  min-width: 200px;
  text-align: center;
  height: max-content;
  min-height: 300px;
  padding-top: 0px;
  display: block;
  border-radius: 50px;
  max-width: 90vw;
}
.PriceToPayInTotal {
  margin-top: 10px;
}
.SpecialErrorBox {
  margin-bottom: 5px;
  text-decoration: underline;
}
.errorBox {
  background-color: red;
  color: white;
  padding: 7px;
  outline: 1px solid black;
  margin-top: 8px;
  margin-bottom: 10px;
}
#app > main > div.confirmationModal > div.locationText > button {
  min-width: 150px;
  width: 25vw;
  min-height: max-content;
  height: 7vh;
  max-height: max-content;
  margin-bottom: 30px;
  max-width: 250px;
  color: white;
  -webkit-text-stroke: 0.2px black;
}
#app > main > div.confirmationModal > div.thanksTextDiv.modalDiv > p {
  margin-top: 10vh;
}
#app
  > main
  > div.confirmationModal
  > div.locationText
  > div.peopleModalDiv.modalDiv
  > p {
  display: inline-block;
  width: max-content;
}
#app > main > div.confirmationModal > div.locationText > button {
  margin-top: -20px;
}
#app > main > div.confirmationModal > div.thanksTextDiv.modalDiv > p {
  padding: 10px;
}
.hiddenLink {
  display: none;
}
.BookingButtonDiv {
  padding-top: 20px;
}
.wholeOrPartialText {
  width: max-content;
  display: inline-block;
  margin-right: 5px;
  margin-bottom: 8px;
}
.wholeResidenceText {
  width: max-content;
  display: inline-block;
}
.partResidenceText {
  width: max-content;
  display: inline-block;
}
.bedsText {
  font-weight: bolder;
}
.myAmenities {
  margin-top: 3px;
  margin-bottom: 10px;
}
.guestsTitle {
  font-weight: 700;
  padding: 10px;
}
.singularGuest,
.manyGuests {
  width: max-content;
  display: inline-block;
}
.myGuests {
  width: max-content;
}
.bookingButton {
  padding: 5px;
  border-radius: 5px;
  background-color: #029ebb;
  color: white;
  min-height: 50px;
  min-width: max-content;
  font-size: 2rem;
  cursor: pointer;
  border: 1px solid grey;
}
.booking-view-app-main {
  background-image: url(https://images.unsplash.com/photo-1573088870079-67b4526aa950?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80);
  background-size: cover;
  background-position: center;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  padding-bottom: 200px;
  padding-top: 200px;
}
.DivForGuests {
  margin-bottom: 20px;
}
select {
  border-radius: 4px;
  width: 50px;
  padding: 7.5px 30.4px;
  background-color: rgb(255, 255, 255);
  margin-right: 10px;
}

.booking-view-container {
  background-color: rgba(255, 255, 255, 0.8);
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  width: 90%;
  margin: 0 auto;
  padding: 50px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: rgb(10, 10, 10);
  margin-top: 10px;
  margin-bottom: 10px;
}

.location-maxguests {
  font-weight: 700;
  padding: 30px 0 30px;
}
.location-maxguests h1 {
  padding: 0 0 10px;
}

.booking-view-images-container {
  display: inline-block;
  align-items: center;
}

.booking-view-image-1 {
  width: 50%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-1:hover {
  transform: scale(1.08);
}
.booking-view-image-2 {
  width: 30%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-2:hover {
  transform: scale(1.08);
}
.booking-view-image-3 {
  width: 30%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-3:hover {
  transform: scale(1.08);
}
.booking-view-image-4 {
  width: 50%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-4:hover {
  transform: scale(1.08);
}

.booking-view-image-5 {
  width: 50%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}

.booking-view-image-5:hover {
  transform: scale(1.08);
}
.type-price {
  font-weight: 700;
  padding: 10px 0 20px;
}

.booking-view-description {
  text-align: justify;
  padding: 0 90px;
  line-height: 160%;
  font-size: 110%;
}
.myDatePickerDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.amenitiesTitle {
  font-weight: 700;
  padding: 10px;
}
.amenities p {
  padding: 10px 0 20px;
}

* {
  max-width: 100vw;
}

@media only screen and (max-width: 550px) {
  * {
    margin: 0px;
    padding: 0px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container {
    max-width: 90vw;
  }
  #app > main > div > div {
    padding-top: 10px;
    margin-top: 10px;
    padding-right: 10px;
    padding-left: 10px;
    margin-bottom: 10px;
  }
  #app > main > div > div > div:nth-child(1) > div.booking-view-description {
    margin: 0px;
    padding: 0px;
    text-align: center;
  }
  h1 {
    width: 200px;
    margin-left: auto;
    margin-right: auto;
  }
  p {
    width: 200px;
    margin-left: auto;
    margin-right: auto;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-1 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-2 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-3 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-4 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-5 {
    height: 100px;
  }
}

@media only screen and (max-width: 300px) {
  * {
    margin: 0px;
    padding: 0px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container {
    max-width: 90vw;
  }
  #app > main > div > div {
    padding-top: 10px;
    margin-top: 10px;
    padding-right: 10px;
    padding-left: 10px;
    margin-bottom: 10px;
  }
  #app > main > div > div > div:nth-child(1) > div.booking-view-description {
    margin: 0px;
    padding: 0px;
    text-align: center;
  }
  h1 {
    width: 200px;
    margin-left: auto;
    margin-right: auto;
  }
  p {
    width: 200px;
    margin-left: auto;
    margin-right: auto;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-1 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-2 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-3 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-4 {
    height: 100px;
  }
  #app
    > main
    > div
    > div
    > div:nth-child(1)
    > div.booking-view-images-container
    > img.booking-view-image-5 {
    height: 100px;
  }
}
@media only screen and (max-width: 750px) {
  .confirmationModal {
    left: 15vw;
  }
}
@media only screen and (max-width: 650px) {
  .confirmationModal {
    left: 10vw;
  }
}
@media only screen and (max-width: 600px) {
  .confirmationModal {
    left: 5vw;
  }
}
@media only screen and (max-width: 550px) {
  .confirmationModal {
    top: 10vh;
  }
}
@media only screen and (max-width: 420px) {
  .confirmationModal {
    top: 10vh;
  }
}
@media only screen and (max-width: 375px) {
  .confirmationModal {
    top: 10vh;
  }
}
@media only screen and (max-width: 500px) {
  #app > main > div > div > div.BookingButtonDiv > button {
    border-radius: 10px;
  }
}
</style>
