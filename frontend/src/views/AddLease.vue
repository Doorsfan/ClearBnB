<template>
  <div class="mainDiv">
  <h3 class="addResidenceHeader">Add a residence</h3>
  <p class="myTitle">Title</p>
  <input @change="updateMyTitle" type="text" required placeholder="Title" v-model="myTitle" />
  <p class="myLocation">Location</p>
  <LocationInputForm @updatedLocation="updateLocation" />
  <DescriptionForm @updatedDescription="updateDescription" />
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxGuests" @updatePrice="updatePrice" />
  <HousingRadioButtons @updateChosenHousing="updateChosenHousing" @updateChosenSize="updateChosenSize"/>
  <AddAmenities @updateWifi="updateWifi" @updateKitchen="updateKitchen" @updateWasher="updateWasher" @updateHeating="updateHeating" @updateAC="updateAC" />
  <ImageUrlInputForm @updatedImgURLs="updateIMGUrls" />
  <ImageBox :imageURL="imageURLs"/>
  <DatepickerForAvailableDates @updatedLeaseStartDate="updateStartDate" @updatedLeaseEndDate="updateEndDate" 
  @updatedDisabledDays="updateDisabledDays" />
  <router-link class="previewButton" to="preview">Preview</router-link>
  </div>
</template>
<script setup="">
import DatepickerForAvailableDates from '../components/AddLeaseView/DatepickerForAvailableDates.vue'
import DescriptionForm from '../components/AddLeaseView/DescriptionForm.vue'
import HousingRadioButtons from '../components/AddLeaseView/HousingRadioButtons.vue'
import InputForPricingBedAndPeople from '../components/AddLeaseView/InputForPricingBedAndPeople.vue'
import ImageUrlInputForm from '../components/AddLeaseView/ImageUrlInputForm.vue'
import LocationInputForm from '../components/AddLeaseView/LocationInputForm.vue'
import AddAmenities from '../components/AddLeaseView/AddAmenities.vue'
import Lease from '../components/Lease.vue'
import ImageBox from '../components/AddLeaseView/ImageBox.vue'
</script>
<script>
export default {
  name: 'AddResidence',
  components: ['DatepickerForAvailableDates', 'DescriptionForm', 'HousingRadioButtons', 'InputForPricingBedAndPeople','LocationInputForm', 'AddAmenities'],
  mounted(){
    this.user = this.$store.getters.getCurrentUser
    //this.lease = this.$store.getters.getLeaseToBuild
  },
  data() {
    return {
      user: '',
      lease: new Lease('', this.myTitle, this.wantedLocation, this.description, this.typeOfHousing, this.entireResidence, this.startDate, this.endDate, this.price, this.maxGuests, this.beds, this.amenities, this.imageURLs),
      myTitle: '',
      location: '',
      description: '',
      typeOfHousing: '',
      size: '',
      startDate: '',
      endDate: '',
      price: '',
      maxGuests: '',
      beds: '',
      amenities: [],
      hasWifi: false,
      hasKitchen: false,
      hasWasher: false,
      hasHeating: false,
      hasAirConditioner: false,
      imageURLs: [],
      disabledDays: []
    }
  },
  methods: {
    updateIMGUrls(newImageURLs){
      this.imageURLs.push(newImageURLs);
      this.lease.setImageURLs(newImageURLs);
    },
    updateMyTitle(newTitle){
      console.log("This.myTitle was: ", this.myTitle);
      this.lease.setTitle(this.myTitle);
    },
    updateLocation(newLocation){
      this.location = newLocation
      this.lease.setLocation(this.location);
    },
    updateDescription(newDescription){
      this.description = newDescription
      this.lease.setDescription(this.description)
    },
    updateChosenHousing(chosenHousing){
      this.typeOfHousing = chosenHousing
      this.lease.setTypeOfHousing(this.typeOfHousing);
    },
    updateChosenSize(chosenSize){
      this.size = chosenSize
      if(this.size == "Part"){
        this.lease.setEntireResidence(false);
      }
      else{
        this.lease.setEntireResidence(true);
      }
    },
    updateStartDate(newStartDate){
      this.startDate = newStartDate
      this.lease.setStartDate(this.startDate);
    },
    updateEndDate(newEndDate){
      this.endDate = newEndDate
      this.lease.setEndDate(this.endDate);
    },
    updatePrice(updatedPrice){
      this.price = updatedPrice
      this.lease.setPrice(this.price);
    },
    updateNrOfMaxGuests(maxPeople){
      this.maxPeople = maxPeople
      this.lease.setMaxGuests(this.maxPeople);
    },
    updateNrOfBeds(amountOfBeds){
      this.nrOfBeds = amountOfBeds
      this.lease.setBeds(this.nrOfBeds);
    },
    updateWifi(hasWifi){
      this.hasWifi = hasWifi
      this.updateAmenities()
    },
    updateKitchen(hasKitchen){
      this.hasKitchen = hasKitchen;
      this.updateAmenities()
    },
    updateWasher(hasWasher){
      this.hasWasher = hasWasher;
      this.updateAmenities()
    },
    updateHeating(hasHeating){
      this.hasHeating = hasHeating;
      this.updateAmenities()
    },
    updateAC(hasAirConditioner){
      this.hasAirConditioner = hasAirConditioner;
      this.updateAmenities()
    },
    updateDisabledDays(newDisabledDays){
      this.disabledDays = newDisabledDays
    },
    updateAmenities(){
      this.amenities = [];
      if(this.hasWifi){
        this.amenities.push("wifi: true")
      }
      else{
        this.amenities.push("wifi: false")
      }
      if(this.hasKitchen){
        this.amenities.push("kitchen: true")
      }
      else{
        this.amenities.push("kitchen: false")
      }
      if(this.hasWasher){
        this.amenities.push("washer: true")
      }
      else{
        this.amenities.push("washer: false")
      }
      if(this.hasHeating){
        this.amenities.push("heating: true")
      }
      else{
        this.amenities.push("heating: false")
      }
      if(this.hasAirConditioner){
        this.amenities.push("airConditioner: true")
      }
      else{
        this.amenities.push("airConditioner: false")
      }
      this.lease.setAmenities(this.amenities);
      console.log("So far, this Lease was: ", this.lease);
    }
  }
}
</script>
<style scoped>
.special{
  height:100px;
}
.mainDiv{
  text-align: center;
  height: max-content;
}
.addResidenceHeader{
  margin: 8px;
}
div{
  margin:5px;
  width: 210px;
  padding: 0px;
  margin-left: auto;
  margin-right: auto;
}
textarea{
  width:210px;
}
.addResidenceDiv{
  width: 210px;
}
.myTitle, .myLocation{
  width: 210px;
  margin: 0px;
  padding: 0px;
  font-weight: bolder;
  margin-top: 3px;
  margin-bottom: 3px;
}
.myTitle{
  text-align:center;
  width:200px;
}
.previewButton, .previewButton:visited{
  display: block;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
  background-color:rgb(243, 189, 10);
  color:black;
  border: 2px solid rgb(200, 159, 23);
  border-radius: 25px;
  text-decoration: none;
  font-weight: bolder;
  padding: 5px;
  padding-top: 3px;
  font-size: 13px;
}

</style>