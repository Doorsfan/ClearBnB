<template>
  <LocationInputForm @updatedLocation="updateLocation" />
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxPeople" @updatePrice="updatePricePerPerson" />
  <HousingRadioButtons @choseHouseType="updateHousingChosen" />
  <input type="text" placeholder="Title" v-model="myTitle" />
  <ImageUrlInputForm @updatedImageURLs="updateImageURLs" />
  <DescriptionForm @updatedDescription="updateDescription" />
  <router-link to="preview">Preview</router-link>
</template>
<script>
import LocationInputForm from '../components/Add Residence View/LocationInputForm.vue'
import InputForPricingBedAndPeople from '../components/Add Residence View/InputForPricingBedAndPeople.vue'
import HousingRadioButtons from "../components/Add Residence View/HousingRadioButtons.vue"
import ImageUrlInputForm from '../components/Add Residence View/ImageUrlInputForm.vue'
import DescriptionForm from '../components/Add Residence View/DescriptionForm.vue'

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
      description: ''
    }
  },
  methods: {
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
