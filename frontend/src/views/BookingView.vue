<template>
  <div class="booking-view-container">
    <div>
      <div class="location-maxguests">
      <h2>{{ lease.title }}</h2>
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
      <p><b>Amenities</b></p>
      <p>*** Insert component ***</p>
      <!-- 
        Insert component Amenities.vue
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
  <footer>Insert footer component</footer>
</template>

<script setup="">
  import Carousel from '../components/Carousel.vue'
</script>

<script>
import leases from '../../json_temp/leases.json'
import DatePickerOnStartpage from '../components/DatePickerOnStartPage.vue'
export default  {
data()  {
  return  {
    image: { backgroundImage: "url(https://images.unsplash.com/photo-1573088870079-67b4526aa950?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80);" },
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

<style>
@import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&family=Lobster&family=Merriweather+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Raleway:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap');

/* header{
  height: 60px;
  background: linear-gradient(
    rgb(121, 167, 182),
    rgb(218, 248, 248)
  );
  padding: 20px;
  font-family: Merriweather Sans;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  text-decoration: none;
  font-weight: 700;
  line-height: 140%;
  border-bottom: solid 2px rgb(218, 248, 248);
}
a {
  text-decoration: none;
  color: #2c3e50;
} */

#app {
  background-color: rgb(218, 248, 248);
  font-family: Merriweather Sans;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-image: url(https://images.unsplash.com/photo-1573088870079-67b4526aa950?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80);
  background-size: cover;
  background-position: center;

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
  background-color: rgba(234, 241, 241, 0.7);
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80%;
  margin: auto;
  padding: 10px 30px;
}

.location-maxguests{
  line-height: 60%;
  font-weight: 700;
}

.booking-view-images-container{
  display: inline-block;
  align-items: center;
  max-width: 80%;
}

.booking-view-image{
  max-height: 260px;
  margin: 10px;
  box-shadow: 5px 5px 5px 0 rgb(131, 154, 161);
  border-radius: 4px;
}

.type-pppn{
  line-height: 60%;
  padding: 0;
  font-weight: 700;
}

.booking-view-description{
  text-align: justify;
  padding: 0 50px;
  line-height: 140%;
}

footer{
  height: 40px;
}


</style>

