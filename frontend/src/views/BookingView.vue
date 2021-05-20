<template>
  <div class="booking-view-app-main">
    <div class="booking-view-container">
      <div>
        <div class="location-maxguests">
        <h1>{{ title }}</h1>
        <p> {{ location }} | Maximum {{ maxGuests }} guests</p>
        </div>
        <div class="booking-view-images-container">
          <img class="booking-view-image-1" :src="imageURLs[0]" /> 
          <img class="booking-view-image-2" :src="imageURLs[1]" />
          <img class="booking-view-image-3" :src="imageURLs[2]" />
          <img class="booking-view-image-4" :src="imageURLs[3]" />
        </div>
      <!-- <Carousel :myLease="lease" /> -->
      <!-- <div class="lease-images">
        {{lease.imageURLs[0]}} {{lease.imageURLs[1]}}
      </div> -->
        <div class="type-price">
          <p>{{ typeOfHousing }} | Per person per night: {{ Math.round(price * 1.15) }} Euro</p>
        </div>
        <div class="booking-view-description">
          <p> {{ description}} </p>
        </div>
        <div class="amenities">Amenities
        <p>*** Insert component ***</p></div>
      <!-- 
        Insert component for Amenities
       -->

        
       <!-- Edit with data from leases.json --> 
          <select v-model="selectedNumberOfGuests"> 
            <option disabled value="">Select number of guests</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
          </select>guests
    
      <div class="myDatePickerDiv">
      <DatepickerForAvailableDates />
      </div>
      </div>
    </div>
  </div>
</template>

<script setup="">
  import DatepickerForAvailableDates from '../components/AddLeaseView/DatepickerForAvailableDates.vue'
</script>
<script>
export default  {
  watch: {
    lease(){
      this.title = this.lease.title
      this.location = this.lease.location
      this.maxGuests = this.lease.maxGuests
      this.imageURLs = this.lease.imageURLs
      this.typeOfHousing = this.lease.typeOfHousing
      this.price = this.lease.price
      this.description = this.lease.description
    }
  },
data()  {
  return  {
    title: 'test',
    location: '',
    maxGuests: '',
    imageURLs: ['',''],
    typeOfHousing: '',
    price: '',
    description: '',
    lease: '',
    selectedNumberOfGuests: ''
  }
},
methods: {
  async getMyLease(){
    let res = await fetch('/rest/leases/' + this.$route.query.id)
    let responseInJson = await res.json();
    this.lease = responseInJson
  }
},
async created() {
  await this.getMyLease();
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

.booking-view-container{
  background-color: rgba(218, 224, 224, 0.8);
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  width: 90%;
  margin: 0 auto;
  padding: 50px;
  font-family: Merriweather Sans;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: rgb(10, 10, 10);
}

.location-maxguests{
  font-weight: 700;
  padding: 30px 0 30px;
}
.location-maxguests h1 {
  padding: 0 0 10px;
}

.booking-view-images-container{
  display: inline-block;
  align-items: center;
}

.booking-view-image-1{
  width: 50%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-1:hover {
  transform: scale(1.08);
}
.booking-view-image-2{
  width: 30%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-2:hover {
  transform: scale(1.08);
}
.booking-view-image-3{
  width: 30%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-3:hover {
  transform: scale(1.08);
}
.booking-view-image-4{
  width: 50%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}
.booking-view-image-4:hover {
  transform: scale(1.08);
}

.type-price{
  font-weight: 700;
  padding: 10px 0 20px;
}

.booking-view-description{
  text-align: justify;
  padding: 0 90px;
  line-height: 160%;
  font-size: 110%;
}
.myDatePickerDiv{
  width: 400px;
}
.amenities{
  font-weight: 700;
  padding: 10px;
}
.amenities p{
  padding: 10px 0 20px;
}

</style>

