<template>
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxPeople" @updatePrice="updatePricePerPerson"/>
  <HousingRadioButtons @choseHouseType="updateHousingChosen"/>
</template>
<script>
import HousingRadioButtons from "../components/Add Residence View/HousingRadioButtons.vue"
import InputForPricingBedAndPeople from '../components/Add Residence View/InputForPricingBedAndPeople.vue'

export default {
  data() {
    return {
      nrOfBeds: '',
      maxPeople: '',
      pricePerPerson: '',
      chosenHousing: ''
    }
  },
  methods: {
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
