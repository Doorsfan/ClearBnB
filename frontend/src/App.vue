<template>
  <div>
    <LocationForm @changedLocation="filterBasedOnLocation"/>
  </div>
</template>

<script setup>
  import LocationForm from './components/DateForm.vue'
</script>
<script>
export default {
  data() {
    return {
      relevantLeases: [],
      allLeases: []
    }
  },
  methods:{
    filterBasedOnLocation(newLocation){
      this.relevantLeases = []
      for(let lease of allLeases){
        if(lease.location.includes(newLocation)){
          this.relevantLeases.push(lease);
        }
      }
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
