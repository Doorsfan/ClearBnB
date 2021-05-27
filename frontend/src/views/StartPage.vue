<template>
  <div class="startPageAppMain">
    <div class="startPageContainer">
      <div class="startPageSearchBar">
        <div class="locationDiv">
          <LocationInputForm @updateMyLocation="updateMyLocation" />
        </div>
        <div class="datePickerDiv">
          <DatePickerOnStartPage
            @updateStartDate="updateSearchStartDate"
            @updateEndDate="updateSearchEndDate"
          />
        </div>
        <div class="bedsInputDiv">
          <BedsInputForm @updateMyAmountOfBeds="updateMyAmountOfBeds" />
        </div>
        <div class="minAndMaxPriceDiv">
          <PriceRangeForm
            @updateMyMinPrice="updateMyMinPrice"
            @updateMyMaxPrice="updateMyMaxPrice"
          />
        </div>
      </div>
      <div class="leaseBoxContainer">
        <div class="leasesDiv">
          <LeaseDisplayBox
            v-for="(leaseItem, index) of relevantLeases"
            :key="index"
            :lease="leaseItem"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import LeaseDisplayBox from "../components/StartView/LeaseDisplayBox.vue";
import Lease from "../components/Lease.vue";
import DatePickerOnStartPage from "../components/StartView/DatePickerOnStartPage.vue";
import LocationInputForm from "../components/StartView/LocationInputForm.vue";
import BedsInputForm from "../components/StartView/BedsInputForm.vue";
import PriceRangeForm from "../components/StartView/PriceRangeForm.vue";
import Header from "../components/Header.vue";
import HamburgerMenu from "../components/HamburgerMenu.vue";
import User from "../components/User";
import Booking from "../components/Booking.js";
import UserInfo from "../components/UserInfo.js";
import AdminBooking from "../components/AdminBooking.js";
import Profit from "../components/Profit.js";
//Load in all the leases of the page from the DB here
let res = await fetch("/rest/leases");
let responseInJson = await res.json();
let originalListOfAllLeases = [];
for (let eachLease in responseInJson) {
  let myLease = {};
  myLease.id = responseInJson[eachLease].id;
  myLease.ownerId = responseInJson[eachLease].ownerId;
  myLease.title = responseInJson[eachLease].title;
  myLease.location = responseInJson[eachLease].location;
  myLease.description = responseInJson[eachLease].description;
  myLease.typeOfHousing = responseInJson[eachLease].typeOfHousing;
  myLease.startDate = responseInJson[eachLease].startDate;
  myLease.endDate = responseInJson[eachLease].endDate;
  myLease.price = responseInJson[eachLease].price;
  myLease.maxGuests = responseInJson[eachLease].maxGuests;
  myLease.beds = responseInJson[eachLease].beds;
  myLease.amenities = {};
  for (let amenity in responseInJson[eachLease].amenities) {
    if (amenity.includes("false")) {
      if (amenity.includes("wifi")) {
        myLease.amenities.wifi = false;
      }
      if (amenity.includes("kitchen")) {
        myLease.amenities.kitchen = false;
      }
      if (amenity.includes("washer")) {
        myLease.amenities.washer = false;
      }
      if (amenity.includes("heating")) {
        myLease.amenities.heating = false;
      }
      if (amenity.includes("airConditioner")) {
        myLease.amenities.airConditioner = false;
      }
    }
    if (amenity.includes("true")) {
      if (amenity.includes("wifi")) {
        myLease.amenities.wifi = true;
      }
      if (amenity.includes("kitchen")) {
        myLease.amenities.kitchen = true;
      }
      if (amenity.includes("washer")) {
        myLease.amenities.washer = true;
      }
      if (amenity.includes("heating")) {
        myLease.amenities.heating = true;
      }
      if (amenity.includes("airConditioner")) {
        myLease.amenities.airConditioner = true;
      }
    }
  }
  myLease.imageURLs = [];
  for (let i = 0; i < responseInJson[eachLease].imageURLs.length; i++) {
    myLease.imageURLs.push(responseInJson[eachLease].imageURLs[i]);
  }
  originalListOfAllLeases.push(myLease);
}
export default {
  emits: ["loggedIn", "loggedOut"],
  components: {
    DatePickerOnStartPage,
    LeaseDisplayBox,
    LocationInputForm,
    BedsInputForm,
    PriceRangeForm,
    Header,
    HamburgerMenu,
  },
  data() {
    return {
      relevantLeases: originalListOfAllLeases, //An array of Lease objects
      allLeases: originalListOfAllLeases,
      validStartDate: false,
      validEndDate: false,
      shouldAddLease: false,
      myLocation: "",
      removedBasedOnDate: [],
      removedBasedOnLocation: [],
      myAmountOfBeds: "",
      myMinPrice: "",
      myMaxPrice: "",
      choseStartDate: this.getAnyDateInCorrectFormat("2021-05-27"),
      choseEndDate: this.getAnyDateInCorrectFormat("2021-06-30"),
    };
  },
  async mounted() {
    let res = await fetch("/rest/leases", {
      method: "GET",
    });
    let response = await res.json();
    this.allLeases = response;
    this.relevantLeases = this.allLeases;
    if (document.getElementsByClassName("house_icon").length > 0) {
      document.getElementsByClassName("house_icon")[0].src =
        "/src/assets/clearbnb-logo-wider.png";
      document.getElementsByClassName("house_icon")[0].className =
        "sunIconInHeader";
      document.getElementsByClassName("homeText")[0].style.display = "none";
      document.getElementsByClassName("center")[0].style.height = "200px";
    }
    this.$store.dispatch("saveLatestRoute", this.$route.path);
    if (this.$store.getters.getCurrentUser != null) {
      this.$emit("loggedIn", true);
    } else {
      this.$emit("loggedOut", true);
      this.$store.commit("setUser", null);
    }
  },
  methods: {
    returnRelevantLeases(lease) {
      let passedMaxPriceCheck = false;
      let passedStartDate = false;
      let passedEndDate = false;
      if (this.myMaxPrice == 0) {
        passedMaxPriceCheck = true;
      } else if (this.myMaxPrice >= Math.round(lease.price * 1.15)) {
        passedMaxPriceCheck = true;
      }
      if (this.choseStartDate.length == 0) {
        passedStartDate = true;
      } else if (this.choseStartDate >= lease.startDate) {
        passedStartDate = true;
      }
      if (this.choseEndDate.length == 0) {
        passedEndDate = true;
      } else if (
        this.choseEndDate <= lease.endDate &&
        this.choseEndDate >= lease.startDate
      ) {
        passedEndDate = true;
      }
      let passedLocation = lease.location
        .toLowerCase()
        .includes(this.myLocation.toLowerCase());
      let passedMinPrice = this.myMinPrice <= Math.round(lease.price * 1.15);
      let passedBeds =
        this.myAmountOfBeds <= lease.beds || this.myAmountOfBeds == 0;
      if (
        passedLocation &&
        passedMinPrice &&
        passedBeds &&
        passedEndDate &&
        passedStartDate &&
        passedMaxPriceCheck
      ) {
        return lease;
      }
      return false;
    },
    checkWhichFilterToRun() {
      this.relevantLeases = originalListOfAllLeases.filter(
        this.returnRelevantLeases
      );
    },
    updateMyAmountOfBeds(newAmountOfBeds) {
      if (Number.isNaN(Number(newAmountOfBeds))) {
        return;
      }
      this.myAmountOfBeds = Number(newAmountOfBeds);
      this.checkWhichFilterToRun();
    },
    updateMyLocation(newLocation) {
      this.myLocation = newLocation;
      this.checkWhichFilterToRun();
    },
    updateSearchEndDate(newDate) {
      this.choseEndDate = newDate;
      this.checkWhichFilterToRun();
    },
    updateSearchStartDate(newDate) {
      this.choseStartDate = newDate;
      this.checkWhichFilterToRun();
    },
    updateMyMinPrice(newMinPrice) {
      this.myMinPrice = newMinPrice;
      this.checkWhichFilterToRun();
    },
    updateMyMaxPrice(newMaxPrice) {
      this.myMaxPrice = newMaxPrice;
      this.checkWhichFilterToRun();
    },
    getAnyDateInCorrectFormat(myDate) {
      let today = new Date(myDate);
      let month = today.getMonth() + 1;
      let day = today.getDate();
      let zeroBeforeMonth = "";
      let zeroBeforeDay = "";
      if (month < 10) {
        zeroBeforeMonth += "0" + month;
        month = zeroBeforeMonth;
      }
      if (day < 10) {
        zeroBeforeDay += "0" + day;
        day = zeroBeforeDay;
      }
      return today.getFullYear() + "-" + month + "-" + day;
    },
    getTodayInCorrectFormat() {
      let today = new Date();
      let month = today.getMonth() + 1;
      let day = today.getDate();
      let zeroBeforeMonth = "";
      let zeroBeforeDay = "";
      if (month < 10) {
        zeroBeforeMonth += "0" + month;
        month = zeroBeforeMonth;
      }
      if (day < 10) {
        zeroBeforeDay += "0" + day;
        day = zeroBeforeDay;
      }
      return today.getFullYear() + "-" + month + "-" + day;
    },
  },
};
</script>
<style scoped>
.startPageAppMain {
  background: linear-gradient(
      rgba(250, 250, 250, 0.4),
      rgba(250, 250, 250, 0.2)
    ),
    url("../../airplane.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  height: 100%;
}
.startPageContainer {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 90%;
  margin: 0 auto;
  padding: 50px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: rgb(10, 10, 10);
  margin-top: 30px;
  margin-bottom: 10px;
}
.startPageSearchBar {
  margin: auto;
  justify-content: space-evenly;
  width: 100%;
  height: 160px;
  font-size: 180%;
  font-weight: 700;
  color: #007b8e;
  background-color: rgba(240, 221, 140, 0.8);
  box-shadow: 10px 10px 10px 3px rgba(95, 94, 94, 0.692);
  border-radius: 10px;
  padding: 10px 5px 5px 5px;
  display: flex;
  border: 1px solid rgb(241, 232, 148);
  box-sizing: border-box;
  z-index: 50;
}
.leaseBoxContainer {
  width: 100%;
  display: flex;
  align-content: space-between;
  margin: 30px auto 30px auto;
}
/*
.mainDiv {
  padding-bottom: 2.5vh;
  padding-top: 2.5vh;
  height: 100%;*/
/* The rest of this block sets background opacity. */
/*
  background: linear-gradient(
      rgba(250, 250, 250, 0.4),
      rgba(250, 250, 250, 0.2)
    ),
    url("../../airplane.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
}
*/
@media only screen and (max-width: 300px) {
  .startPageSearchBar {
    margin: 0px;
    padding: 5px;
  }
  .minAndMax + PriceDiv {
    padding-bottom: 20px;
  }
}
@media only screen and (min-width: 1330px) {
  /*
  .locationDiv,
  .datePickerDiv,
  .bedsInputDiv,
  .minAndMaxPriceDiv {
    display: inline-block;
  }
  .locationDiv {
    padding-left: 20px;
  }
  /*
  .minAndMaxPriceDiv {
    padding-left: 10px;
    padding-right: 20px;
  }
  */
}
</style>
