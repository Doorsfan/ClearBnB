<template>
  <div class="booking-view-app-main">
    <div class="booking-view-container">
      <div>
        <div class="location-maxguests">
        <h1>{{ lease.title }}</h1>
        <p> {{ lease.location }} | Maximum {{ lease.maxGuests }} guests</p>
        </div>
        <div class="booking-view-images-container">
          <img class="booking-view-image" :src="lease.imageURLs[0]" /> 
          <img class="booking-view-image" :src="lease.imageURLs[1]" /> 
        </div>
      <!-- <Carousel :myLease="lease" /> -->
      <!-- <div class="lease-images">
        {{lease.imageURLs[0]}} {{lease.imageURLs[1]}}
      </div> -->
        <div class="type-pppn">
          <p>{{ lease.typeOfHousing }} | Per person per night: {{ lease.PPPN }} SEK</p>
        </div>
        <div class="booking-view-description">
          <p> {{ lease.description}} </p>
        </div>
        <div class="amenities">Amenities
        <p>*** Insert component ***</p></div>
      <!-- 
        Insert component for Amenities
       -->

        <p>
       <!-- Edit with data from leases.json --> 
          <select v-model="selectedNumberOfGuests"> 
            <option disabled value="">Select number of guests</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
          </select> guests
        </p>
      
      <!-- Insert DatePickerOnStartPage -->
      </div>
    </div>
  </div>
</template>

<script setup="">
  import Carousel from '../components/Carousel.vue'
</script>

<script>
import leases from '../../json_temp/leases.json'
import DatePickerOnStartpage from '../components/StartView/DatePickerOnStartPage.vue'
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
  // for(let amenity of lease.amenities) {
  //  if(lease.amenity === true)
  //  this.amenity = amenity
  //}
}
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&family=Lobster&family=Merriweather+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Raleway:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap');

  .booking-view-app-main  {
  background-image: url(https://images.unsplash.com/photo-1573088870079-67b4526aa950?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80);
  background-size: cover;
  background-position: center;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

select  {
  border-radius: 4px;
  width: 50px;
  padding: 3px;
  background-color:rgba(234, 241, 241);
  margin-right: 10px;
}

image {
  width: 100%;
  object-fit: cover; 
  object-position: center;
} 

.booking-view-container{
  background-color: rgba(218, 224, 224, 0.8);
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  width: 80%;
  margin: 0 auto;
  padding: 30px;
  font-family: Merriweather Sans;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: rgb(10, 10, 10);
}

.location-maxguests{
  font-weight: 700;
  padding: 30px 0 20px;
}
.location-maxguests h1 {
  padding: 30px 0 10px;
}

.booking-view-images-container{
  display: inline-block;
  align-items: center;
  max-width: 80%;
}

.booking-view-image{
  max-height: 260px;
  margin: 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}

.type-pppn{
  font-weight: 700;
  padding: 10px 0 20px;
}

.booking-view-description{
  text-align: justify;
  padding: 0 50px;
  line-height: 140%;
}
.amenities{
  font-weight: 700;
}
.amenities p{
  padding: 10px 0 20px;
}

</style>

