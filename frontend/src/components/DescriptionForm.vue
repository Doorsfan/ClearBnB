<template>
  <div>
    <p class="myDescriptionP">Description:</p>
    <textarea class="myInput" v-model="message" placeholder="Add a description to your Lease.."></textarea>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        message: ""
      }
    },
    methods: {
      getMessage(){
        return this.message
      }
    }
  }
</script>
<style scoped>
  .myInput{
    min-width: 150px;
    width: 250px;
    height: 100px;
    min-height: 80px;
    padding: 5px;
  }
  .myDescriptionP{
    margin:3px;
    padding:0px;
  }
</style>