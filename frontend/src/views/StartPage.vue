<template>
  <div class="mainDiv">
    <div class="thirdDiv">
      <div class="divHoldingTopDivs">
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
    </div>
    <div class="leasesDiv">
      <LeaseDisplayBox
        v-for="(leaseItem, index) of relevantLeases"
        :key="index"
        :lease="leaseItem"
      />
    </div>
  </div>
</template>
<script>
import LeaseDisplayBox from '../components/StartView/LeaseDisplayBox.vue';
import Lease from '../components/Lease.vue';
import DatePickerOnStartPage from '../components/StartView/DatePickerOnStartPage.vue';
import LocationInputForm from '../components/StartView/LocationInputForm.vue';
import BedsInputForm from '../components/StartView/BedsInputForm.vue';
import PriceRangeForm from '../components/StartView/PriceRangeForm.vue';
import Header from '../components/Header.vue';
import HamburgerMenu from '../components/HamburgerMenu.vue';

export default {
  emits: ['loggedIn', 'loggedOut'],
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
      relevantLeases: this.originalListOfAllLeases, //An array of Lease objects
      allLeases: this.originalListOfAllLeases,
      validStartDate: false,
      validEndDate: false,
      shouldAddLease: false,
      myLocation: '',
      removedBasedOnDate: [],
      removedBasedOnLocation: [],
      myAmountOfBeds: '',
      myMinPrice: '',
      myMaxPrice: '',
      choseStartDate: new Date(),
      choseEndDate: this.getAnyDateInCorrectFormat('2021-06-30'),
      originalListOfAllLeases: [],
    };
  },
  async mounted() {
    let firstRes = await fetch('/rest/leases');
    let responseInJson = await firstRes.json();
    this.originalListOfAllLeases = [];
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
        if (amenity.includes('false')) {
          if (amenity.includes('wifi')) {
            myLease.amenities.wifi = false;
          }
          if (amenity.includes('kitchen')) {
            myLease.amenities.kitchen = false;
          }
          if (amenity.includes('washer')) {
            myLease.amenities.washer = false;
          }
          if (amenity.includes('heating')) {
            myLease.amenities.heating = false;
          }
          if (amenity.includes('airConditioner')) {
            myLease.amenities.airConditioner = false;
          }
        }
        if (amenity.includes('true')) {
          if (amenity.includes('wifi')) {
            myLease.amenities.wifi = true;
          }
          if (amenity.includes('kitchen')) {
            myLease.amenities.kitchen = true;
          }
          if (amenity.includes('washer')) {
            myLease.amenities.washer = true;
          }
          if (amenity.includes('heating')) {
            myLease.amenities.heating = true;
          }
          if (amenity.includes('airConditioner')) {
            myLease.amenities.airConditioner = true;
          }
        }
      }
      myLease.imageURLs = [];
      for (let i = 0; i < responseInJson[eachLease].imageURLs.length; i++) {
        myLease.imageURLs.push(responseInJson[eachLease].imageURLs[i]);
      }
      this.originalListOfAllLeases.push(myLease);
    }
    let res = await fetch('/rest/leases', {
      method: 'GET',
    });
    let response = await res.json();
    this.allLeases = response;
    this.relevantLeases = this.allLeases;
    this.$store.dispatch('saveLatestRoute', this.$route.path);
    if (this.$store.getters.getCurrentUser != null) {
      this.$emit('loggedIn', true);
    } else {
      this.$emit('loggedOut', true);
      this.$store.commit('setUser', null);
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
      this.relevantLeases = this.originalListOfAllLeases.filter(
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
      let zeroBeforeMonth = '';
      let zeroBeforeDay = '';
      if (month < 10) {
        zeroBeforeMonth += '0' + month;
        month = zeroBeforeMonth;
      }
      if (day < 10) {
        zeroBeforeDay += '0' + day;
        day = zeroBeforeDay;
      }
      return today.getFullYear() + '-' + month + '-' + day;
    },
    getTodayInCorrectFormat() {
      let today = new Date();
      let month = today.getMonth() + 1;
      let day = today.getDate();
      let zeroBeforeMonth = '';
      let zeroBeforeDay = '';
      if (month < 10) {
        zeroBeforeMonth += '0' + month;
        month = zeroBeforeMonth;
      }
      if (day < 10) {
        zeroBeforeDay += '0' + day;
        day = zeroBeforeDay;
      }
      return today.getFullYear() + '-' + month + '-' + day;
    },
  },
};
</script>
<style scoped>
.divHoldingTopDivs {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  border-radius: 10px;
  padding-bottom: 10px;
}

footer {
  height: 10vh;
}
* {
  font-family: 'mukta';
}
.leasesDiv {
  text-align: center;
  margin-left: auto;
  margin-right: auto;
}
.locationDiv,
.datePickerDiv,
.bedsInputDiv,
.minAndMaxPriceDiv {
  display: block;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.locationDiv {
  padding-top: 20px;
  width: max-content;
}
.mainDiv {
  font-family: 'mukta';
  font-weight: bold;
  padding-left: 5vw;
  padding-right: 5vw;
  padding-bottom: 2.5vh;
  padding-top: 2.5vh;
  background-image: url('../../airplane.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  min-width: 100vw;
  min-height: 90vh;
}
.startPageDateDiv {
  margin-left: auto;
  margin-right: auto;
  font-size: 20px;
  background-color: rgba(255, 255, 255, 0.8);
  padding-bottom: 15px;
  border-radius: 10px;
}
.thirdDiv {
  background-color: rgba(255, 255, 255, 0.8);
  width: 80vw;
  margin-left: auto;
  margin-right: auto;
  border-radius: 10px;
}
@media only screen and (max-width: 400px) {
  .thirdDiv {
    width: 90vw;
  }
}

@media only screen and (max-width: 1550px) {
  .minAndMaxPriceDiv,
  .bedsInputDiv,
  .datePickerDiv,
  .locationDiv {
    display: block;
  }
}
@media only screen and (min-width: 1550px) {
  .minAndMaxPriceDiv,
  .bedsInputDiv,
  .datePickerDiv,
  .locationDiv {
    display: inline-block;
  }
}
</style>
