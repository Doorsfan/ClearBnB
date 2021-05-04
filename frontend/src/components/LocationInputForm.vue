<template>
  <div>
    <textarea @change="updateLocationArea" class="inputForLocationArea" v-model="location"></textarea>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        location: ""
      }
    },
    methods: {
      updateLocationArea(){
        let updatedLocationArea = location
        
      }
    }
  }
</script>
<style scoped>

</style>