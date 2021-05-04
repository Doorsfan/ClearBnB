<template>
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxPeople" @updatePrice="updatePricePerPerson"/>
</template>

<script setup>
  import InputForPricingBedAndPeople from './components/InputForPricingBedAndPeople.vue'
</script>
<script>
export default {
  data() {
    return {
      nrOfBeds: '',
      maxPeople: '',
      pricePerPerson: ''
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
    }
  }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
