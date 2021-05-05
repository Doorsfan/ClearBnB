<template>
  <div class="mainDiv">
  <LocationInputForm @updatedLocation="updateLocation" />
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxPeople" @updatePrice="updatePricePerPerson" />
  <HousingRadioButtons @choseHouseType="updateHousingChosen" />
  <p class="myTitle">Title</p>
  <input type="text" placeholder="Title" v-model="myTitle" />
  <ImageUrlInputForm @updatedImageURLs="updateImageURLs" />
  <DescriptionForm @updatedDescription="updateDescription" />
  <DatepickerForAvailableDates @updatedLeaseStartDate="updateStartDate" @updatedLeaseEndDate="updateEndDate" 
  @updatedDisabledDays="updateDisabledDays" />
  <router-link class="previewButton" to="preview">Preview</router-link>
  </div>
</template>
<script setup="">
import LocationInputForm from '../components/AddResidenceView/LocationInputForm.vue'
import InputForPricingBedAndPeople from '../components/AddResidenceView/InputForPricingBedAndPeople.vue'
import HousingRadioButtons from '../components/AddResidenceView/HousingRadioButtons.vue'
import ImageUrlInputForm from '../components/AddResidenceView/ImageUrlInputForm.vue'
import DescriptionForm from '../components/AddResidenceView/DescriptionForm.vue'
import DatepickerForAvailableDates from '../components/AddResidenceView/DatepickerForAvailableDates.vue'
</script>
<script>

export default {
  data() {
    return {
      nrOfBeds: '',
      maxPeople: '',
      pricePerPerson: '',
      chosenHousing: '',
      location: '',
      myTitle: '',
      imageURLs: [],
      description: '',
      startDate: '',
      endDate: '',
      disabledDays: []
    }
  },
  methods: {
    updateDisabledDays(newDisabledDays){
      this.disabledDays = newDisabledDays
    },
    updateStartDate(newStartDate){
      this.startDate = newStartDate
    },
    updateEndDate(newEndDate){
      this.endDate = newEndDate
    },
    updateDescription(newDescription){
      this.description = newDescription
    },
    updateImageURLs(newImageURLs){
      this.imageURLs = newImageURLs
    },
    updateLocation(newLocation){
      this.location = newLocation
    },
    updateNrOfBeds(amountOfBeds){
      this.nrOfBeds = amountOfBeds
      console.log("new amount of beds was: ", this.nrOfBeds);
    },
    updateNrOfMaxPeople(maxPeople){
      this.maxPeople = maxPeople
      console.log("new amount of beds was: ", this.maxPeople);
    },
    updatePricePerPerson(updatedPrice){
      this.pricePerPerson = updatedPrice
      console.log("new amount of beds was: ", this.pricePerPerson);
    },
    updateHousingChosen(choseHousing){
      //Have in Add Residence View that it updates based on this function
      console.log("Chosen housing was: ", choseHousing)
      this.chosenHousing = chosenHousing
    }
  }
}
</script>
<style scoped>
div{
  margin:5px;
  width: 205px;
  padding:0px;
  margin-left:auto;
  margin-right:auto;
}
textarea{
  width:205px;
}
.addResidenceDiv{
  width: 205px;
}
.myTitle{
  width:205px;
  margin:0px;
  padding:0px;
  font-weight:bolder;
  margin-top:3px;
  margin-bottom:3px;
}
.previewButton, .previewButton:visited{
  display:block;
  width:max-content;
  margin-left:auto;
  margin-right:auto;
  margin-top:20px;
  background-color:rgb(243, 189, 10);
  color:black;
  border: 2px solid rgb(200, 159, 23);
  border-radius:25px;
  text-decoration:none;
  font-weight:bolder;
  height: 14px;
  padding:5px;
  padding-top:3px;
  font-size:13px;
}
.mainDiv{
  height: 500px;
}
</style>