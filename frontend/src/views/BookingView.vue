<template>
<div>
<!--  <h1>Testing, testing</h1> -->
<!-- Replace with correct values when the lease objects are done. -->
  <h1> {{ lease.title }}</h1>
  <h3> {{ lease.location }} | Maximum {{ lease.maxGuests }} guests</h3>
  <img v-bind:source="lease.imageURLs" alt="" />
  {{ lease.imageURLs }}
  <!-- <img src="lease.imageURLs[]" v-for="lease in leases" :key="lease.imageURLs">
    {{ lease.imageURLs }} -->
  <h3>{{ lease.typeOfHousing }} | Price per person per night: {{ lease.pricePerNight }} SEK</h3> <!-- CHANGE TO PPPN IN JSON -->
  <p> {{ lease.description}} </p>
  <p><b>Amenities</b></p>
  <ul id="amenities">
  <li v-for="lease in leases" :key="lease.amenities">
    {{ lease.amenities }}</li></ul>
  </div>
</template>

<script>
import leases from '../../json_temp/leases.json'
export default  {
data()  {
  return  {
    leases: leases
  }
},
created() {
  for(let lease of leases)  {
    if(lease.leaseId == this.$route.params.leaseId) {
      this.lease = lease
    }
  }
}
}
</script>

<style scoped>
img {
  width: 60%;
  margin-top: 15px;
}
</style>

