<template>
  <DescriptionForm @updatedDescription="updatedDescription"/>
</template>

<script setup="">
  import DescriptionForm from './components/DescriptionForm.vue'
</script>
<script>
export default {
  methods: {
    updatedDescription(newText){
      //Integrate this as being an update to the Object in the Add Residence View
      //for each respective attribute that is propagated upwards
      console.log("The new message was: ", newText)
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
