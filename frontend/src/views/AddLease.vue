<template>
  <div class="mainDiv">
  <h3 class="addResidenceHeader">Add a residence</h3>
  <p class="myTitle">Title</p>
  <input type="text" required placeholder="Title" v-model="myTitle" />
  <p class="myLocation">Location</p>
  <LocationInputForm @updatedLocation="updateLocation" />
  <DescriptionForm @updatedDescription="updateDescription" />
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxPeople" @updatePrice="updatePricePerPerson" />
  <HousingRadioButtons @updateChosenHousing="updateChosenHousing" @updateChosenSize="updateChosenSize"/>
  <ImageUrlInputForm @updatedImageURLs="updateImageURLs" />
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
</script>
<script>
export default {
  name: 'AddResidence',
  components: ['DatepickerForAvailableDates', 'DescriptionForm', 'HousingRadioButtons', 'InputForPricingBedAndPeople',  'ImageUrlInputForm','LocationInputForm'],
  data() {
    return {
      myTitle: '',
      beds: '',
      maxGuests: '',
      PPPN: '',
      typeOfHousing: '',
      size: '',
      location: '',
      title: '',
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
    },
    updateNrOfMaxPeople(maxPeople){
      this.maxPeople = maxPeople
    },
    updatePricePerPerson(updatedPrice){
      this.pricePerPerson = updatedPrice
    },
    updateChosenHousing(chosenHousing){
      //Have in Add Residence View that it updates based on this function
      this.typeOfHousing = chosenHousing
      console.log("new type of housing is: ", this.typeOfHousing);
    },
    updateChosenSize(chosenSize){
      this.size = chosenSize
      console.log("new chosen size is", this.size)
    }
  }
}
</script>
<style scoped>
.mainDiv{
  text-align: center;
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
.mainDiv{
  height: 500px;
}

</style>