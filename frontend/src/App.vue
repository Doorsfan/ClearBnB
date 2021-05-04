<template>
  <div>
    <HousingRadioButtons @choseHouseType="updateHousingChosen"/>
  </div>
</template>

<script setup>
  import HousingRadioButtons from "./components/HousingRadioButtons.vue"
</script>
<script>
export default {
  data() {
    return {
      choseHousing: '',
    }
  },
  methods: {
    updateHousingChosen(choseHousing){
      //Have in Add Residence View that it updates based on this function
      console.log(choseHousing)
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
