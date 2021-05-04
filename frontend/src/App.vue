<template>
  <ImageUrlInputForm @updatedImgURLs="updateImageURLsList" />
</template>

<script setup>
  import ImageUrlInputForm from './components/ImageUrlInputForm.vue'
</script>
<script>
export default {
  data() {
    return {

    }
  },
  methods: {
    updateImageURLsList(updatedList){
      console.log(updatedList)
      console.log("Just needs to be woven into the rest of the design")
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
