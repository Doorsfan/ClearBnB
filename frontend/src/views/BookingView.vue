<template>
  <div class="booking-view-app-main">
    <div class="booking-view-container">
      <div>
        <div class="location-maxguests">
        <h1>{{ title }}</h1>
        <p> {{ location }} | Maximum {{ maxGuests }} guests</p>
        </div>
        <div class="booking-view-images-container">
          <img v-if="imageURLs.length > 0" class="booking-view-image-1" :src="imageURLs[0]"/> 
          <img v-if="imageURLs.length > 1" class="booking-view-image-2" :src="imageURLs[1]" />
          <img v-if="imageURLs.length > 2" class="booking-view-image-3" :src="imageURLs[2]" />
          <img v-if="imageURLs.length > 3" class="booking-view-image-4" :src="imageURLs[3]" />
          <img v-if="imageURLs.length > 4" class="booking-view-image-5" :src="imageURLs[4]" />
        </div>
        <BookingViewCarousel v-if="lease" :myLease="lease" />
        <div class="type-price">
          <p>{{ typeOfHousing }} | Per person per night: {{ Math.round(price * 1.15) }} Euro</p>
        </div>
        <div class="booking-view-description">
          <p> {{ description}} </p>
        </div>
        <div class="amenitiesTitle">Amenities</div>
        <div class="myAmenities">
          <div class="wifiDiv">
            <div v-if="amenities[0] == 'wifi: true'" class="hasWifi">Wifi</div>
          </div>
          <div class="kitchenDiv">
            <div v-if="amenities[1] == 'kitchen: true'" class="hasKitchen">Kitchen</div>
          </div>
          <div class="washerDiv">
            <div v-if="amenities[2] == 'washer: true'" class="hasWasher0">Washer</div>
          </div>
          <div class="heatingDiv">
            <div v-if="amenities[3] == 'heating: true'" class="hasHeating">Heating</div>
          </div>
          <div class="acDiv">
            <div v-if="amenities[1] == 'airConditioner: true'" class="hasKitchen">Air Conditioning</div>
          </div>
        </div>
      <!-- 
        Insert component for Amenities
       -->
        
       <!-- Edit with data from leases.json --> 
        <div class="guestsTitle">Guests</div>
          <select class="myGuests" v-model="selectedNumberOfGuests">
            <option value="" disabled>Guests</option>
            <option v-if="maxGuests > 0">1</option>
            <option v-if="maxGuests > 1">2</option>
            <option v-if="maxGuests > 2">3</option>
            <option v-if="maxGuests > 3">4</option>
            <option v-if="maxGuests > 4">5</option>
          </select><p v-if="maxGuests == 1" class="singularGuest">guest</p><p v-if="maxGuests > 1" class="manyGuests">guests</p>
      <div class="myDatePickerDiv">
        <DatepickerForBookingView v-if="lease" :myLease="lease"/>
      </div>
      </div>
    </div>
  </div>
</template>

<script setup="">
  import DatepickerForAvailableDates from '../components/AddLeaseView/DatepickerForAvailableDates.vue'
  import BookingViewCarousel from '../components/BookingView/BookingViewCarousel.vue'
  import DatepickerForBookingView from '../components/BookingView/DatepickerForBookingView.vue'
</script>
<script>
export default {
  mounted(){
    if(document.getElementsByClassName("sunIconInHeader").length > 0){
      document.getElementsByClassName("sunIconInHeader")[0].src = '/public/home_icon.png'
      document.getElementsByClassName("sunIconInHeader")[0].className = 'house_icon'
      document.getElementsByClassName("homeText")[0].style.display = 'block';
      document.getElementsByClassName("center")[0].style.height = '70px';
    }
  },
  watch: {
    lease(){
      this.title = this.lease.title
      this.location = this.lease.location
      this.maxGuests = this.lease.maxGuests
      this.imageURLs = this.lease.imageURLs
      this.typeOfHousing = this.lease.typeOfHousing
      this.price = this.lease.price
      this.description = this.lease.description
      this.amenities = this.lease.amenities
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
    selectedNumberOfGuests: '',
    amenities: '',
    chosenStartDate: '',
    chosenEndDate: ''
  }
},
methods: {
  async book(){
    //Mt6ZUN7dYDQ1a2zuwbTYx the id of the place to book
      let myUser = this.$store.getters.getCurrentUser;
      let emptyUser = new User('','');
      let filledUser = Object.assign(emptyUser,myUser);
      let myBooking = new Booking(filledUser.id, lease.id,
      "Abisko", "2021-05-27", "2021-05-30", 1, 45, lease);
      let secondRes = await fetch('/rest/bookings', {
        method: 'POST',
        body: JSON.stringify(myBooking)
      });
      let myProfit = new Profit(myBooking.getTotalPrice());
      let thirdRes = await fetch('/rest/profit/', {
        method: 'POST',
        body: JSON.stringify(myProfit)
      });

      let secondResponseAsJson = await secondRes.json();
      
      let filledAdminBooking = new AdminBooking(secondResponseAsJson['id'], filledUser.id, lease.id,
      lease.location, this.chosenStartDate, this.chosenEndDate, this.maxGuests, lease.getTotalPrice(), myLease);
      let adminRes = await fetch('/rest/adminBookings', {
        method: 'POST',
        body: JSON.stringify(filledAdminBooking)
      });
      let adminResponseAsJson = await adminRes.json();
  },
  async getMyLease(){
    let res = await fetch('/rest/leases/' + this.$route.query.id)
    let responseInJson = await res.json();
    this.lease = responseInJson
  }
},
async created() {
  await this.getMyLease();
  }
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&family=Lobster&family=Merriweather+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Raleway:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap');
  .myAmenities{
    margin-top: 3px;
    margin-bottom:10px;
  }
  .guestsTitle{
    font-weight: 700;
    padding: 10px;
  }
  .singularGuest, .manyGuests{
    width:max-content;
    display:inline-block;
  }
  .myGuests{
    width:max-content;
  }
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
  width: max-content;
  margin-left:auto;
  margin-right:auto;
}
.amenitiesTitle{
  font-weight: 700;
  padding: 10px;
}
.amenities p{
  padding: 10px 0 20px;
}

</style>

