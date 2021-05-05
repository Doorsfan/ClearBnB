<template>
  <div>
    <p class="myDescriptionP">Description:</p>
    <textarea @change="sendUpMessage" class="myInput" v-model="message" placeholder="Add a description to your Lease..">
    </textarea>
  </div>
</template>
<script> //Between Components, utilize Emit - Between Views, utilize saving into the Database
  export default {
    data() {
      return {
        message: ""
      }
    },
    methods: {
      sendUpMessage() {
        let messageToSendUp = this.message
        this.$emit("updatedDescription", messageToSendUp)
      }
    }
  }
</script>
<style scoped>
  .myInput{
    min-width: 150px;
    width: 195px;
    height: 100px;
    min-height: 80px;
    padding: 4px;
  }
  .myDescriptionP{
    margin:3px;
    padding:0px;
    font-weight:bolder;
  }
</style>