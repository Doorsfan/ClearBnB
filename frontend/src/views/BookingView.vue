<!-- PAUSED UNTIL OTHER COMPONENTS AND DB ARE IN ORDER -->
<template>
<div>
  <h1> {{ lease.title }}</h1>
  <h3> {{ lease.location }} | Maximum {{ lease.maxGuests }} guests</h3>
  <img v-bind:source="lease.imageURLs" alt="" />
  {{ lease.imageURLs }} <!-- How do I display img-urls from an array? -->
  <!-- <img src="lease.imageURLs[]" v-for="lease in leases" :key="lease.imageURLs">
    {{ lease.imageURLs }} -->
  <h3>{{ lease.typeOfHousing }} | Per person per night: {{ lease.pricePerNight }} SEK</h3> <!-- CHANGE TO PPPN IN JSON -->
  <p> {{ lease.description}} </p>
  <p><b>Amenities</b></p>
  <ul>
  <li>
    {{ lease.amenities }}
    </li>
    </ul> <!-- Amenities should be in an array in json; change the code when done -->

  </div>
</template>

<script>
import leases from '../../json_temp/leases.json'
export default  {
data()  {
  return  {
    lease: null
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

