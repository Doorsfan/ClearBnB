<template>
  <div class="mainDiv">
  <h3 class="addResidenceHeader">Add a residence</h3>
  <p class="myTitle">Title</p>
  <input type="text" required placeholder="Title" v-model="title" />
  <p class="myLocation">Location</p>
  <LocationInputForm v-model="location" @updatedLocation="updateLocation" />
  <DescriptionForm v-model="description" @updatedDescription="updateDescription" />
  <InputForPricingBedAndPeople @updateBeds="updateNrOfBeds" @updateMaxPeople="updateNrOfMaxGuests" @updatePrice="updatePrice" />
  <HousingRadioButtons @updateChosenHousing="updateChosenHousing" @updateChosenSize="updateChosenSize"/>
  <AddAmenities @updateWifi="updateWifi" @updateKitchen="updateKitchen" @updateWasher="updateWasher" @updateHeating="updateHeating" @updateAC="updateAC" />
  <ImageUrlInputForm @updatedImgURLs="updateIMGUrls" />
  <ImageBox @removedFirstImage="removeFirstIMGinURLs" @removedSecondImage="removeSecondIMGinURLs" :imageURL="imageURLs"/>
  <DatepickerForAvailableDates @updatedLeaseStartDate="updateStartDate" @updatedLeaseEndDate="updateEndDate" 
  @updatedDisabledDays="updateDisabledDays" />
  <router-link class="previewButton" to="preview">Preview</router-link>
  </div>
</template>
<script setup="">
import DatepickerForAvailableDates from '../components/AddLeaseView/DatepickerForAvailableDates.vue'
import DescriptionForm from '../components/AddLeaseView/DescriptionForm.vue'
import HousingRadioButtons from '../components/AddLeaseView/HousingRadioButtons.vue'
import InputForPricingBedAndPeople from '../components/AddLeaseView/InputForPricingBedAndPeople.vue'
import ImageUrlInputForm from '../components/AddLeaseView/ImageUrlInputForm.vue'
import LocationInputForm from '../components/AddLeaseView/LocationInputForm.vue'
import AddAmenities from '../components/AddLeaseView/AddAmenities.vue'
import Lease from '../components/Lease.vue'
import ImageBox from '../components/AddLeaseView/ImageBox.vue'
import store from '../store.js'
</script>
<script>
export default {
  name: 'AddResidence',
  components: ['DatepickerForAvailableDates', 'DescriptionForm', 'HousingRadioButtons', 'InputForPricingBedAndPeople','LocationInputForm', 'AddAmenities'],
  watch: {
    title(){
      ;
    },
    lease(){
      console.log("lease was changed");
    }
  },
  beforeMount(){
    if(this.$store.getters.getLeaseToBuild != null){
      let latestLease = this.$store.getters.getLeaseToBuild
      this.title = latestLease.title
      this.location = latestLease.location
      this.description = latestLease.description
    }
  },
  mounted(){
    if(this.$store.getters.getLeaseToBuild != null){
      console.log(this.$store.getters.getLeaseToBuild)
      this.title = this.$store.getters.getLeaseToBuild.title
      this.lease = this.$store.getters.getLeaseToBuild
    }
  },
  data() {
    return {
      user: this.$store.getters.getCurrentUser,
      lease: this.$store.getters.getLeaseToBuild == null ? new Lease(this.$store.getters.getCurrentUser.id, '','','','','','','','','','','','') : this.$store.getters.getLeaseToBuild,
      title: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.title,
      location: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.location,
      description: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.description,
      typeOfHousing: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.typeOfHousing,
      size: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.entireResidence,
      startDate: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.startDate,
      endDate: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.endDate,
      price: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.price,
      maxGuests: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.maxGuests,
      beds: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.beds,
      amenities: this.$store.getters.getLeaseToBuild == null ? '' : this.$store.getters.getLeaseToBuild.amenities,
      hasWifi: false,
      hasKitchen: false,
      hasWasher: false,
      hasHeating: false,
      hasAirConditioner: false,
      imageURLs: [],
      disabledDays: []
    }
  },
  methods: {
    removeFirstIMGinURLs(){
      this.imageURLs = emptyArray.filter(function(value, index, arr){ return index >= 1 });
      $('.firstImageURL').remove();
      
    },
    removeSecondIMGinURLs(){
      let emptyArray = []
      for(let image of this.imageURLs){
        emptyArray.push(image);
      }
      this.imageURLs = emptyArray.filter(function(value, index, arr){ return index >= 2 })
      $('.secondImageURL').remove();
      console.log("this.imageURLs is now: ", this.imageURLs);
      
    },
    updateIMGUrls(newImageURLs){
      this.imageURLs.push(newImageURLs);
      console.log("new image URLs was: ", newImageURLs)
      
    },
    updateTitle(newTitle){
      this.title = newTitle
      
    },
    updateLocation(newLocation){
      this.location = newLocation
      this.lease.setLocation(this.location);
      
    },
    updateDescription(newDescription){
      this.description = newDescription
      this.lease.setDescription(this.description)
      
    },
    updateChosenHousing(chosenHousing){
      this.typeOfHousing = chosenHousing
      this.lease.setTypeOfHousing(this.typeOfHousing);
      
    },
    updateChosenSize(chosenSize){
      console.log("UpdateChosen SIze was called with the parameter of : ", chosenSize)
      this.size = chosenSize
      if(this.size == "Part"){
        this.lease.setEntireResidence(false);
      }
      else{
        this.lease.setEntireResidence(true);
      }
      
    },
    updateStartDate(newStartDate){
      this.startDate = newStartDate
      this.lease.setStartDate(this.startDate);
      
    },
    updateEndDate(newEndDate){
      this.endDate = newEndDate
      this.lease.setEndDate(this.endDate);
      
    },
    updatePrice(updatedPrice){
      this.price = updatedPrice
      this.lease.setPrice(this.price);
      
    },
    updateNrOfMaxGuests(maxPeople){
      this.maxPeople = maxPeople
      this.lease.setMaxGuests(this.maxPeople);
      
    },
    updateNrOfBeds(amountOfBeds){
      this.nrOfBeds = amountOfBeds
      this.lease.setBeds(this.nrOfBeds);
      
    },
    updateWifi(hasWifi){
      this.hasWifi = hasWifi
      this.updateAmenities()
      
    },
    updateKitchen(hasKitchen){
      this.hasKitchen = hasKitchen;
      this.updateAmenities()
      
    },
    updateWasher(hasWasher){
      this.hasWasher = hasWasher;
      this.updateAmenities()
      
    },
    updateHeating(hasHeating){
      this.hasHeating = hasHeating;
      this.updateAmenities()
      
    },
    updateAC(hasAirConditioner){
      this.hasAirConditioner = hasAirConditioner;
      this.updateAmenities()
      
    },
    updateDisabledDays(newDisabledDays){
      this.disabledDays = newDisabledDays
      
    },
    updateAmenities(){
      this.amenities = [];
      if(this.hasWifi){
        this.amenities.push("wifi: true")
      }
      else{
        this.amenities.push("wifi: false")
      }
      if(this.hasKitchen){
        this.amenities.push("kitchen: true")
      }
      else{
        this.amenities.push("kitchen: false")
      }
      if(this.hasWasher){
        this.amenities.push("washer: true")
      }
      else{
        this.amenities.push("washer: false")
      }
      if(this.hasHeating){
        this.amenities.push("heating: true")
      }
      else{
        this.amenities.push("heating: false")
      }
      if(this.hasAirConditioner){
        this.amenities.push("airConditioner: true")
      }
      else{
        this.amenities.push("airConditioner: false")
      }
      
    }
  }
}
</script>
<style scoped>
.special{
  height:100px;
}
.mainDiv{
  text-align: center;
  height: max-content;
}
.addResidenceHeader{
  margin: 8px;
}
div{
  margin:5px;
  width: 210px;
  padding: 0px;
  margin-left: auto;
  margin-right: auto;
}
textarea{
  width:210px;
}
.addResidenceDiv{
  width: 210px;
}
.myTitle, .myLocation{
  width: 210px;
  margin: 0px;
  padding: 0px;
  font-weight: bolder;
  margin-top: 3px;
  margin-bottom: 3px;
}
.myTitle{
  text-align:center;
  width:200px;
}
.previewButton, .previewButton:visited{
  display: block;
  width: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
  background-color:rgb(243, 189, 10);
  color:black;
  border: 2px solid rgb(200, 159, 23);
  border-radius: 25px;
  text-decoration: none;
  font-weight: bolder;
  padding: 5px;
  padding-top: 3px;
  font-size: 13px;
}

</style>