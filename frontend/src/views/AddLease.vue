<template>
  <div class="mainDiv">
    <div class="greyBackgroundDiv">
      <h3 class="addResidenceHeader">Add a residence</h3>
      <p class="myTitle">Title</p>
      <input type="text" required placeholder="Title" v-model="title" />
      <p class="myLocation">Location</p>
      <LocationInputForm v-model="location" @updatedLocation="updateLocation" />
      <DescriptionForm
        v-model="description"
        @updatedDescription="updateDescription"
      />
      <div class="inputForBedsDiv">
        <InputForPricingBedAndPeople
          @updateBeds="updateNrOfBeds"
          @updateMaxPeople="updateNrOfMaxGuests"
          @updatePrice="updatePrice"
        />
      </div>
      <HousingRadioButtons
        @updateChosenHousing="updateChosenHousing"
        @updateChosenSize="updateChosenSize"
      />
      <AddAmenities
        @updateWifi="updateWifi"
        @updateKitchen="updateKitchen"
        @updateWasher="updateWasher"
        @updateHeating="updateHeating"
        @updateAC="updateAC"
      />
      <div class="imageURLInputDiv">
        <ImageUrlInputForm @updatedImgURLs="updateIMGUrls" />
      </div>
      <ImageBox
        @removedFirstImage="removeFirstIMGinURLs"
        @removedSecondImage="removeSecondIMGinURLs"
        @removedThirdImage="removeThirdIMGinURLs"
        @removedFourthImage="removeFourthIMGinURLs"
        @removedFifthImage="removeFifthIMGinURLs"
        :imageURL="imageURLs"
      />
      <div class="datePickerDiv">
        <DatepickerForAvailableDates
          @updatedDisabledDays="updateDisabledDays"
        />
      </div>
      <div class="buttonDiv">
        <router-link class="previewButton" to="preview">Preview</router-link>
      </div>
    </div>
  </div>
</template>
<script setup="">
import DatepickerForAvailableDates from '../components/AddLeaseView/DatepickerForAvailableDates.vue';
import DescriptionForm from '../components/AddLeaseView/DescriptionForm.vue';
import HousingRadioButtons from '../components/AddLeaseView/HousingRadioButtons.vue';
import InputForPricingBedAndPeople from '../components/AddLeaseView/InputForPricingBedAndPeople.vue';
import ImageUrlInputForm from '../components/AddLeaseView/ImageUrlInputForm.vue';
import LocationInputForm from '../components/AddLeaseView/LocationInputForm.vue';
import AddAmenities from '../components/AddLeaseView/AddAmenities.vue';
import Lease from '../components/Lease.vue';
import ImageBox from '../components/AddLeaseView/ImageBox.vue';
import store from '../store.js';
</script>
<script>
export default {
  name: 'AddResidence',
  components: [
    'DatepickerForAvailableDates',
    'DescriptionForm',
    'HousingRadioButtons',
    'InputForPricingBedAndPeople',
    'LocationInputForm',
    'AddAmenities',
    'ImageBox',
  ],
  watch: {
    title() {
      this.lease.title = this.title;
      store.commit('setLeaseToBuild', this.lease);
    },
    lease() {},
  },
  beforeMount() {
    if (this.$store.getters.getLeaseToBuild != null) {
      let latestLease = this.$store.getters.getLeaseToBuild;
      this.title = latestLease.title;
      this.location = latestLease.location;
      this.description = latestLease.description;
      this.typeOfHousing = latestLease.typeOfHousing;
      if (latestLease.entireResidence == true) {
        this.size = 'Entire residence';
      } else {
        this.size = 'Part';
      }
      this.amenities = latestLease.amenities;
      this.imageURLs = latestLease.imageURLs;
    } else {
      this.lease.startDate = new Date();
      this.lease.endDate = new Date();
    }
  },
  data() {
    return {
      user: this.$store.getters.getCurrentUser,
      lease:
        this.$store.getters.getLeaseToBuild == null
          ? new Lease(
              this.$store.getters.getCurrentUser.id,
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              ''
            )
          : this.$store.getters.getLeaseToBuild,
      title: '',
      location:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.location,
      description:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.description,
      typeOfHousing:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.typeOfHousing,
      size:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.entireResidence,
      startDate: new Date(),
      endDate: new Date(),
      price:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.price,
      maxGuests:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.maxGuests,
      beds:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.beds,
      amenities:
        this.$store.getters.getLeaseToBuild == null
          ? ''
          : this.$store.getters.getLeaseToBuild.amenities,
      hasWifi: false,
      hasKitchen: false,
      hasWasher: false,
      hasHeating: false,
      hasAirConditioner: false,
      imageURLs: [],
      disabledDays: [],
    };
  },
  methods: {
    mounted() {
      if (document.getElementsByClassName('sunIconInHeader').length > 0) {
        document.getElementsByClassName('sunIconInHeader')[0].src =
          '/public/home_icon.png';
        document.getElementsByClassName('sunIconInHeader')[0].className =
          'house_icon';
        document.getElementsByClassName('homeText')[0].style.display = 'block';
        document.getElementsByClassName('center')[0].style.height = '70px';
      }
      this.$store.commit('setLeaseToBuild', this.lease);
    },
    removeFirstIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 0;
      });
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
      document.getElementsByClassName('mainDiv')[0].style.backgroundImage =
        'url(' + '/public/house.jpg' + ')';
    },
    removeSecondIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 1;
      });
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
    },
    removeThirdIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 2;
      });
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
    },
    removeFourthIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 3;
      });
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
    },
    removeFifthIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 4;
      });
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
    },
    updateIMGUrls(newImageURLs) {
      console.log('this imageURLs was: ', this.imageURLs);
      this.imageURLs.push(newImageURLs);
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
      document.getElementsByClassName('mainDiv')[0].style.backgroundImage =
        'url(' + this.imageURLs[0][0] + ')';
    },
    updateTitle(newTitle) {
      this.title = newTitle;
      this.lease.title = this.title;
      store.commit('setLeaseToBuild', this.lease);
    },
    updateLocation(newLocation) {
      this.location = newLocation;
      this.lease.setLocation(this.location);
      store.commit('setLeaseToBuild', this.lease);
    },
    updateDescription(newDescription) {
      this.description = newDescription;
      this.lease.setDescription(this.description);
      store.commit('setLeaseToBuild', this.lease);
    },
    updateChosenHousing(chosenHousing) {
      this.typeOfHousing = chosenHousing;
      this.lease.setTypeOfHousing(this.typeOfHousing);
      store.commit('setLeaseToBuild', this.lease);
    },
    updateChosenSize(chosenSize) {
      this.size = chosenSize;
      if (this.size == 'Part') {
        this.lease.setEntireResidence(false);
      } else {
        this.lease.setEntireResidence(true);
      }
      store.commit('setLeaseToBuild', this.lease);
    },
    updatePrice(updatedPrice) {
      this.price = updatedPrice;
      this.lease.setPrice(this.price);
      store.commit('setLeaseToBuild', this.lease);
    },
    updateNrOfMaxGuests(maxPeople) {
      this.maxPeople = maxPeople;
      this.lease.setMaxGuests(this.maxPeople);
      store.commit('setLeaseToBuild', this.lease);
    },
    updateNrOfBeds(amountOfBeds) {
      this.nrOfBeds = amountOfBeds;
      this.lease.setBeds(this.nrOfBeds);
      store.commit('setLeaseToBuild', this.lease);
    },
    updateWifi(hasWifi) {
      this.hasWifi = hasWifi;
      this.updateAmenities();
    },
    updateKitchen(hasKitchen) {
      this.hasKitchen = hasKitchen;
      this.updateAmenities();
    },
    updateWasher(hasWasher) {
      this.hasWasher = hasWasher;
      this.updateAmenities();
    },
    updateHeating(hasHeating) {
      this.hasHeating = hasHeating;
      this.updateAmenities();
    },
    updateAC(hasAirConditioner) {
      this.hasAirConditioner = hasAirConditioner;
      this.updateAmenities();
    },
    updateDisabledDays(newDisabledDays) {
      this.disabledDays = newDisabledDays;
    },
    updateAmenities() {
      this.amenities = [];
      if (this.hasWifi) {
        this.amenities.push('wifi: true');
      } else {
        this.amenities.push('wifi: false');
      }
      if (this.hasKitchen) {
        this.amenities.push('kitchen: true');
      } else {
        this.amenities.push('kitchen: false');
      }
      if (this.hasWasher) {
        this.amenities.push('washer: true');
      } else {
        this.amenities.push('washer: false');
      }
      if (this.hasHeating) {
        this.amenities.push('heating: true');
      } else {
        this.amenities.push('heating: false');
      }
      if (this.hasAirConditioner) {
        this.amenities.push('airConditioner: true');
      } else {
        this.amenities.push('airConditioner: false');
      }
      this.lease.amenities = this.amenities;
      store.commit('setLeaseToBuild', this.lease);
    },
  },
};
</script>
<style scoped>
* {
  font-family: 'mukta';
}
.imageURLInputDiv {
  margin-bottom: 10px;
}
.buttonDiv {
  margin: 5px;
}
.previewButton {
  border: outset 5px green;
  outline: 1px solid black;
  opacity: 1;
  background-color: green;
  padding: 5px;
  color: black;
  font-weight: bolder;
  text-decoration: none;
  width: max-content;
  font-size: 15px;
  margin-bottom: 15px;
}
.datePickerDiv {
  margin: 10px;
  padding-right: 18px;
  padding-bottom: 10px;
}
p {
  font-weight: bolder;
  margin: 3px;
}
.greyBackgroundDiv {
  background-color: rgba(218, 224, 224, 0.8);
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  padding: 20px;
}
.mainDiv {
  padding-left: 5vw;
  padding-right: 5vw;
  padding-bottom: 2.5vh;
  padding-top: 2.5vh;
  background-image: url('/public/house.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  opacity: 0.8;
  height: max-content;
  width: 100vw;
  background-attachment: fixed;
  overflow-x: hidden;
  min-height: 900px;
  align-items: center;
  align-self: center;
  text-align: center;
  background-position: center;
}
.inputForBedsDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: 5px;
  margin-bottom: 5px;
}
@media only screen and (max-width: 300px) {
  .greyBackgroundDiv {
    margin: 0px;
    padding: 5px;
    padding-top: 20px;
    padding-bottom: 20px;
  }
}
</style>
