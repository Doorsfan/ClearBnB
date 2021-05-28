<template>
  <div class="booking-view-app-main">
    <div class="booking-view-container">
      <div class="mainDiv" v-if="!myLease">
        <div class="location-maxguests">
          <h1>Your Title Goes Here</h1>
          <p>Your Location Goes Here | Maximum guests go here</p>
        </div>
        <div class="booking-view-images-container">
          <img class="booking-view-image-1" src="/public/image_1_example.png" />
          <img class="booking-view-image-2" src="/public/image_2_example.png" />
          <img class="booking-view-image-3" src="/public/image_3_example.png" />
          <img class="booking-view-image-4" src="/public/image_4_example.png" />
          <img class="booking-view-image-5" src="/public/image_5_example.png" />
        </div>
        <div class="type-price">
          <p>
            Type of Housing Goes Here | Per person per night: Your Price in
            Euros
          </p>
        </div>
        <div class="booking-view-description">
          <p class="descriptionP">Your Description goes here</p>
        </div>
        <div class="amenitiesTitle">Your Amenities shows up below</div>
        <div class="myAmenities"></div>
        <div class="amountOfBeds">
          <p class="bedsText">The amount of Available Beds</p>
          Amount of Beds go here
        </div>
        <div class="DivForGuests">
          <div class="guestsTitle">Here you can choose your guests:</div>
          <select class="myGuests" v-model="selectedNumberOfGuests">
            <option value="" disabled>Guests</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
          </select>
          <p v-if="selectedNumberOfGuests <= 1" class="singularGuest">guest</p>
          <p v-if="selectedNumberOfGuests > 1" class="manyGuests">guests</p>
        </div>
        <div class="wholeOrPartialDiv">
          <p class="wholeOrPartialText">You get Access to:</p>
          <p class="exampleSize">Whole/Part of Residence goes here</p>
        </div>
        <div v-if="myLease" class="myDatePickerDiv">
          <PreviewDatePicker v-if="myLease.startDate && myLease.endDate"
            :leaseStartDate="myLease.startDate"
            :leaseEndDate="myLease.endDate"
          />
        </div>
        <div class="buttonsDiv">
          <div class="cancelButtonDiv">
            <router-link to="/addLease">
              <button class="cancelButton" type="button" value="Edit">
                Cancel
              </button>
            </router-link>
          </div>
        </div>
      </div>
      <div v-if="myLease">
        <div class="location-maxguests">
          <h1>{{ myLease.title }}</h1>
          <p v-if="myLease.location.length > 0 && myLease.maxGuests > 0">{{ myLease.location }} | Maximum {{ myLease.maxGuests }} guests</p>
          <p v-if="myLease.location.length == 0 && myLease.maxGuests > 0">Your Location | Maximum {{ myLease.maxGuests }} guests</p>
          <p v-if="myLease.location.length > 0 && myLease.maxGuests == 0">{{ myLease.location }} | Max Amount of Guests</p>
          <p v-if="myLease.location.length == 0 && myLease.maxGuests == 0">Your Location | Max Amount of Guests</p>
        </div>
        <div class="booking-view-images-container">
          <img
            v-if="myLease.imageURLs.length > 0"
            class="booking-view-image-1"
            :src="myLease.imageURLs[0]"
          />
          <img
            v-if="myLease.imageURLs.length > 1"
            class="booking-view-image-2"
            :src="myLease.imageURLs[1]"
          />
          <img
            v-if="myLease.imageURLs.length > 2"
            class="booking-view-image-3"
            :src="myLease.imageURLs[2]"
          />
          <img
            v-if="myLease.imageURLs.length > 3"
            class="booking-view-image-4"
            :src="myLease.imageURLs[3]"
          />
          <img
            v-if="myLease.imageURLs.length > 4"
            class="booking-view-image-5"
            :src="myLease.imageURLs[4]"
          />
        </div>
        <div class="type-price">
          <p>
            {{ myLease.typeOfHousing ? myLease.typeOfHousing : 'Type of Housing' }} | Per person per night:
            {{ myLease.price ? Math.round(myLease.price * 1.15 * 100) / 100 : 'X amount of '}} Euro
          </p>
        </div>
        <div class="booking-view-description">
          <p class="descriptionP">{{ myLease.description }}</p>
        </div>
        <div class="amenitiesTitle">Amenities</div>
        <div class="myAmenities">
          <div class="wifiDiv">
            <div v-if="myLease.amenities[0] == 'wifi: true'" class="hasWifi">
              Wifi
            </div>
          </div>
          <div class="kitchenDiv">
            <div
              v-if="myLease.amenities[1] == 'kitchen: true'"
              class="hasKitchen"
            >
              Kitchen
            </div>
          </div>
          <div class="washerDiv">
            <div
              v-if="myLease.amenities[2] == 'washer: true'"
              class="hasWasher0"
            >
              Washer
            </div>
          </div>
          <div class="heatingDiv">
            <div
              v-if="myLease.amenities[3] == 'heating: true'"
              class="hasHeating"
            >
              Heating
            </div>
          </div>
          <div class="acDiv">
            <div
              v-if="myLease.amenities[4] == 'airConditioner: true'"
              class="hasKitchen"
            >
              Air Conditioning
            </div>
          </div>
        </div>
        <div class="amountOfBeds">
          <p class="bedsText">Available Beds</p>
          {{ myLease.beds ? myLease.beds : 'Example Amount' }}
        </div>
        <div class="DivForGuests">
          <div class="guestsTitle">Guests</div>
          <select class="myGuests" v-model="selectedNumberOfGuests">
            <option value="" disabled>Guests</option>
            <option v-if="myLease.maxGuests > 0">1</option>
            <option v-if="myLease.maxGuests > 1">2</option>
            <option v-if="myLease.maxGuests > 2">3</option>
            <option v-if="myLease.maxGuests > 3">4</option>
            <option v-if="myLease.maxGuests > 4">5</option>
          </select>
          <p v-if="myLease.maxGuests == 1" class="singularGuest">guest</p>
          <p v-if="myLease.maxGuests > 1" class="manyGuests">guests</p>
        </div>
        <div class="wholeOrPartialDiv">
          <p class="wholeOrPartialText">You get Access to:</p>
          <p v-if="myLease.entireResidence" class="wholeResidenceText">
            The Whole Residence
          </p>
          <p v-if="!myLease.entireResidence" class="partResidenceText">
            A Part of The Residence
          </p>
        </div>
        <div class="myDatePickerDiv">
          <PreviewDatePicker
            :leaseStartDate="previewStartDate"
            :leaseEndDate="previewEndDate"
          />
        </div>
        <div class="buttonsDiv">
          <div class="cancelButtonDiv">
            <router-link to="/addLease">
              <button class="cancelButton" type="button" value="Edit">
                Cancel
              </button>
            </router-link>
          </div>
          <div class="publishButtonDiv">
            <button
              v-if="$store.getters.getLeaseToBuild.imageURLs.length > 0"
              @click="PublishLease"
              class="publishButton"
              type="button"
              value="Publish"
            >
              Publish
            </button>
          </div>
          <div
            v-if="$store.getters.getLeaseToBuild.imageURLs.length == 0"
            class="errorBox"
          >
            Cannot Publish a Lease without a image URL added!
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup="">
import PreviewCarousel from '../components/PreviewView/PreviewCarousel.vue';
import Datepicker from 'vue3-datepicker';
import PreviewDatePicker from '../components/PreviewView/PreviewDatePicker.vue';
</script>
<script>
import store from '../store.js';
import Lease from '../components/Lease.vue';
import PPPN from '../components/PPPN.js';
export default {
  name: 'Login',
  components: ['PreviewCarousel', 'DatepickerForAvailableDates'],
  methods: {
    async PublishLease() {
      let leaseToPublish = this.$store.getters.getLeaseToBuild;
      let myURLs = [];
      for (let image of leaseToPublish.imageURLs[0]) {
        myURLs.push(image);
      }
      myURLs.flat();
      let newURLs = [];
      for (let newImage of myURLs) {
        newURLs.push(newImage);
      }
      let myDateHelper = new PPPN();
      let correctStartDate = myDateHelper.getCorrectDateFormat(
        leaseToPublish.startDate
      );
      let correctEndDate = myDateHelper.getCorrectDateFormat(
        leaseToPublish.endDate
      );
      let myAmenities = [];
      for (let amenity of leaseToPublish.amenities) {
        myAmenities.push(amenity);
      }
      let completeLeash = new Lease(
        this.$store.getters.getCurrentUser.id,
        leaseToPublish.title,
        leaseToPublish.location,
        leaseToPublish.description,
        leaseToPublish.typeOfHousing,
        leaseToPublish.entireResidence,
        correctStartDate,
        correctEndDate,
        leaseToPublish.price,
        leaseToPublish.maxGuests,
        leaseToPublish.beds,
        myAmenities,
        newURLs
      );
      let res = await fetch('/rest/leases', {
        method: 'POST',
        body: JSON.stringify(completeLeash),
      });
      let response = await res.json();
    },
  },
  mounted() {
    this.myLease = this.$store.getters.getLeaseToBuild;
    if(this.myLease != null){
      if(this.myLease.startDate != null){
        this.previewStartDate = this.myLease.startDate; 
      }
      else{
        this.previewStartDate = new Date();
      }
      if(this.myLease.endDate != null){
        this.previewEndDate = this.myLease.endDate;
      }
      else{
        this.previewEndDate = new Date();
      }
    }
    
    if (this.myLease != null) {
      if (this.myLease.entireResidence == true) {
        $('.EntireOrPart').text('Entire Residence');
      } else {
        $('.EntireOrPart').text('Part');
      }
      if (this.myLease.amenities != '') {
        if (this.myLease.amenities[0].includes('true')) {
          $('.wifiText').text('Wifi: Yes');
        } else {
          $('.wifiText').text('Wifi: No');
        }
        if (this.myLease.amenities[1].includes('true')) {
          $('.kitchenText').text('Kitchen: Yes');
        } else {
          $('.kitchenText').text('Kitchen: No');
        }
        if (this.myLease.amenities[2].includes('true')) {
          $('.washerText').text('Washer: Yes');
        } else {
          $('.washerText').text('Washer: No');
        }
        if (this.myLease.amenities[3].includes('true')) {
          $('.heatingText').text('Heating: Yes');
        } else {
          $('.heatingText').text('Heating: No');
        }
        if (this.myLease.amenities[4].includes('true')) {
          $('.AirConditionerText').text('Air Conditioning: Yes');
        } else {
          $('.AirConditionerText').text('Air Conditioning: No');
        }
      }
    }
  },
  data() {
    return {
      myLease: this.$store.getters.getLeaseToBuild,
      selectedNumberOfGuests: '',
      previewStartDate: '',
      previewEndDate: '',
    };
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&family=Lobster&family=Merriweather+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Raleway:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap');

* {
  text-align: center;
  font-family: 'mukta';
}
.cancelButtonDiv,
.publishButtonDiv {
  width: max-content;
  display: inline-block;
}
.cancelButtonDiv {
  margin-right: 10px;
  margin-top: 10px;
}
.publishButtonDiv {
  margin-left: 10px;
}
.cancelButton {
  width: 100px;
  height: 40px;
  border-radius: 10px;
  background-color: rgba(246, 69, 37, 0.842);
  color: white;
  border: 1px solid grey;
  cursor: pointer;
  margin-top: 10px;
  font-size: 18px;
}
.publishButton {
  width: 100px;
  height: 40px;
  border-radius: 10px;
  background-color: #029ebb;
  color: white;
  border: 1px solid grey;
  cursor: pointer;
  margin-top: 10px;
  font-size: 18px;

}
.PriceToPayInTotal {
  margin-top: 10px;
}
.SpecialErrorBox {
  margin-bottom: 5px;
  text-decoration: underline;
}
.errorBox {
  background-color: red;
  color: white;
  padding: 7px;
  outline: 1px solid black;
  margin-top: 8px;
  margin-bottom: 10px;
}
.hiddenLink {
  display: none;
}
.BookingButtonDiv {
  padding-top: 20px;
}
.wholeOrPartialText {
  width: max-content;
  display: inline-block;
  margin-right: 5px;
  margin-bottom: 8px;
}
.wholeResidenceText {
  width: max-content;
  display: inline-block;
}
.partResidenceText {
  width: max-content;
  display: inline-block;
}
.bedsText {
  font-weight: bolder;
}
.myAmenities {
  margin-top: 3px;
  margin-bottom: 10px;
}
.guestsTitle {
  font-weight: 700;
  padding: 10px;
}
.singularGuest,
.manyGuests {
  width: max-content;
  display: inline-block;
}
.myGuests {
  width: max-content;
}
.bookingButton {
  padding: 5px;
  border-radius: 5px;
}
.booking-view-app-main {
  background-image: url(https://images.unsplash.com/photo-1573088870079-67b4526aa950?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80);
  background-size: cover;
  background-position: center;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
.DivForGuests {
  margin-bottom: 20px;
}
select {
  border-radius: 4px;
  width: 50px;
  padding: 3px;
  background-color: rgba(234, 241, 241);
  margin-right: 10px;
  padding: 7.5px 30.4px;
  outline:none;
}

.booking-view-container {
  background-color: rgba(218, 224, 224, 0.8);
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  width: 90%;
  margin: 0 auto;
  padding: 50px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: rgb(10, 10, 10);
  margin-top: 30px;
  margin-bottom: 30px;
}

.location-maxguests {
  font-weight: 700;
  padding: 30px 0 30px;
}
.location-maxguests h1 {
  padding: 0 0 10px;
}

.booking-view-images-container {
  display: inline-block;
  align-items: center;
}

.booking-view-image-1 {
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
.booking-view-image-2 {
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
.booking-view-image-3 {
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
.booking-view-image-4 {
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

.booking-view-image-5 {
  width: 50%;
  height: 300px;
  object-fit: cover;
  object-position: center;
  margin: 0 10px 10px 10px;
  box-shadow: 5px 5px 5px 0 rgb(136, 154, 160);
  border-radius: 4px;
}

.booking-view-image-5:hover {
  transform: scale(1.08);
}
.type-price {
  font-weight: 700;
  padding: 10px 0 20px;
}

.booking-view-description {
  text-align: justify;
  padding: 0 90px;
  line-height: 160%;
  font-size: 110%;
}
.myDatePickerDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.amenitiesTitle {
  font-weight: 700;
  padding: 10px;
}
.amenities p {
  padding: 10px 0 20px;
}

@media only screen and (min-width: 900px) {
  .booking-view-image-1,
  .booking-view-image-2,
  .booking-view-image-3,
  .booking-view-image-4,
  .booking-view-image-5 {
    height: 150px;
    width: 350px;
  }
}
@media only screen and (max-width: 900px) {
  .booking-view-image-1,
  .booking-view-image-2,
  .booking-view-image-3,
  .booking-view-image-4,
  .booking-view-image-5 {
    height: 150px;
    width: 350px;
  }
}
@media only screen and (max-width: 700px) {
  .booking-view-image-1,
  .booking-view-image-2,
  .booking-view-image-3,
  .booking-view-image-4,
  .booking-view-image-5 {
    height: 150px;
    width: 300px;
  }
}
@media only screen and (max-width: 550px) {
  .booking-view-image-1,
  .booking-view-image-2,
  .booking-view-image-3,
  .booking-view-image-4,
  .booking-view-image-5 {
    height: 150px;
    width: 200px;
  }
}
@media only screen and (max-width: 300px) {
  .booking-view-image-1,
  .booking-view-image-2,
  .booking-view-image-3,
  .booking-view-image-4,
  .booking-view-image-5 {
    height: 100px;
    width: 130px;
  }
  * {
    font-size: 14px;
    text-align: center;
  }
  #app > main > div > div > div > div.location-maxguests {
    padding-left: 10px;
    padding-right: 10px;
  }
  .descriptionP {
    margin: 0px;
    padding: 0px;
  }
  .mainDiv > * {
    padding: 10px;
  }
  .cancelButtonDiv,
  .publishButtonDiv {
    width: max-content;
    display: block;
    padding:0px;
    margin-left:auto;
    margin-right:auto;
  }
}
</style>
