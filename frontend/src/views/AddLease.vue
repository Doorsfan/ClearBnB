<template>
  <div class="mainDiv">
    <div class="greyBackgroundDiv">
      <h3 class="addResidenceHeader">Add residence</h3>
      <p class="myTitle">Title</p>
      <input
        type="text"
        required
        placeholder="My Title"
        v-model="title"
        class="leaseTitle"
      />
      <p class="myLocation">Location</p>
      <LocationInputForm
        class="locDiv"
        v-model="location"
        @updatedLocation="updateLocation"
      />
      <DescriptionForm
        class="descriptionTitle"
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
  mounted() {
    if (this.imageURLs.length > 0) {
      document.getElementsByClassName('mainDiv')[0].style.backgroundImage =
        'url(' + this.imageURLs[0] + ')';
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
    removeFirstIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 0;
      });
      let emptyArray = [];
      for (let image of this.imageURLs) {
        emptyArray.push(image);
      }
      this.lease.imageURLs = emptyArray;
      store.commit('setLeaseToBuild', this.lease);
      if (emptyArray.length == 0) {
        document.getElementsByClassName('mainDiv')[0].style.backgroundImage =
          'url(' + '/public/house.jpg' + ')';
      } else {
        document.getElementsByClassName('mainDiv')[0].style.backgroundImage =
          'url(' + emptyArray[0] + ')';
      }
    },
    removeSecondIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 1;
      });
      let emptyArray = [];
      for (let image of this.imageURLs) {
        emptyArray.push(image);
      }
      this.lease.imageURLs = emptyArray;
      store.commit('setLeaseToBuild', this.lease);
    },
    removeThirdIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 2;
      });
      let emptyArray = [];
      for (let image of this.imageURLs) {
        emptyArray.push(image);
      }
      this.lease.imageURLs = emptyArray;
      store.commit('setLeaseToBuild', this.lease);
    },
    removeFourthIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 3;
      });
      let emptyArray = [];
      for (let image of this.imageURLs) {
        emptyArray.push(image);
      }
      this.lease.imageURLs = emptyArray;
      store.commit('setLeaseToBuild', this.lease);
    },
    removeFifthIMGinURLs() {
      this.imageURLs = this.imageURLs.filter(function (value, index, arr) {
        return index != 4;
      });
      let emptyArray = [];
      for (let image of this.imageURLs) {
        emptyArray.push(image);
      }
      this.lease.imageURLs = emptyArray;
      store.commit('setLeaseToBuild', this.lease);
    },
    updateIMGUrls(newImageURLs) {
      if (this.imageURLs == '') {
        this.imageURLs = [];
      }
      let emptyArray = [];
      for (let image of newImageURLs) {
        emptyArray.push(image);
      }
      for (let oldImage of this.imageURLs) {
        emptyArray.push(oldImage);
      }

      newImageURLs = newImageURLs.flat();

      this.imageURLs = emptyArray;
      this.lease.imageURLs = this.imageURLs;
      store.commit('setLeaseToBuild', this.lease);
      if (this.imageURLs.length > 0) {
        document.getElementsByClassName('mainDiv')[0].style.backgroundImage =
          'url(' + this.imageURLs[0] + ')';
      }
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
@media only screen and (min-width: 1200px) {
  .greyBackgroundDiv {
    max-width: 1500px;
  }
}

input {
  text-align: center;
}
.inputForLocationArea {
  padding: 7.5px 30.4px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid grey;
  text-align: initial;
  margin-bottom: 15px;
}
.descriptionTitle {
  font-family: 'mukta';
  font-size: 20px;
}
.myLocation {
  font-family: 'mukta';
  font-size: 20px;
}
.myTitle {
  font-family: 'mukta';
  font-size: 20px;
}
.addResidenceHeader {
  font-size: 50px;
}
.imageURLInputDiv {
  margin-bottom: 10px;
}
.buttonDiv {
  margin: 5px;
  padding-bottom: 10px;
}
.previewButton {
  width: 120px;
  height: 50px;
  padding: 10px 20px;
  background-color: #029ebb;
  color: white;
  border: 1px solid grey;
  border-radius: 2px;
  cursor: pointer;
  margin-top: 10px;
  font-size: 18px;
  margin-right: 10px;
  margin-left: 10px;
  text-decoration: none;
}
.datePickerDiv {
  position: inherit;
  margin: 10px;
  padding-right: 18px;
  padding-bottom: 10px;
}
p {
  font-weight: bolder;
  margin: 3px;
}
.greyBackgroundDiv {
  width: 90vw;
  margin-top: 20px;
  background-color: rgba(255, 255, 255, 0.9);
  padding: 100px;
  margin: 0px;
  padding: 5px;
  padding-top: 20px;
  padding-bottom: 20px;
  max-width: 1200px;
  margin-left: auto;
  margin-right: auto;
}
.mainDiv {
  padding-left: 5vw;
  padding-right: 5vw;
  padding-bottom: 200px;
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
  font-family: 'mukta';
  font-weight: bold;
  font-size: 17px;
}
.inputForBedsDiv {
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 10px;
  padding-top: 10px;
  margin-top: 5px;
  margin-bottom: 5px;
}

@media only screen and (max-width: 350px) {
  p {
    display: block;
    width: 220px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
  }
  input {
    padding: 5px;
  }
}
</style>

<!--Styling for all subcomponents-->
<style>
@media only screen and (max-width: 500px) {
  #app > main > div > div > div.buttonDiv > a {
    border-radius: 10px;
  }
}

.inputForLocationArea,
.leaseTitle {
  padding: 11.2px 22.4px;
  font-family: 'mukta', sans-serif;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid grey;
  text-align: initial;
  margin-bottom: 15px;
}
@media only screen and (min-width: 1330px) {
  .inputForLocationArea,
  .leaseTitle {
    width: 400px !important;
  }
  .descriptionInput {
    width: 800px !important;
  }
  .leaseTitle {
    margin: auto;
    margin-bottom: 30px;
  }
  .imageURL {
    width: 345px !important;
    margin-bottom: 30px;
  }
}
</style>
