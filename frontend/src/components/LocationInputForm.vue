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
        this.$emit("updatedLocation", updatedLocationArea)
      }
    }
  }
</script>
<style scoped>

</style>