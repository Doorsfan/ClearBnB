<template>
  <div>
  </div>
</template>

<script setup>
</script>
<script>
export default {
  data() {
    return {
    }
  },
  methods: {
    updateHousingChosen(choseHousing){
      //Have in Add Residence View that it updates based on this function
      console.log("Chosen housing was: ", choseHousing)
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
