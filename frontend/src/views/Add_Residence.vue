<template>
  <LocationInputForm @updatedLocation="updateLocation" />
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxPeople" @updatePrice="updatePricePerPerson"/>
  <HousingRadioButtons @choseHouseType="updateHousingChosen"/>
</template>
<script>
import LocationInputForm from '../components/Add Residence View/LocationInputForm.vue'
import InputForPricingBedAndPeople from '../components/Add Residence View/InputForPricingBedAndPeople.vue'
import HousingRadioButtons from "../components/Add Residence View/HousingRadioButtons.vue"

export default {
  data() {
    return {
      nrOfBeds: '',
      maxPeople: '',
      pricePerPerson: '',
      chosenHousing: '',
      location: ''
    }
  },
  methods: {
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
