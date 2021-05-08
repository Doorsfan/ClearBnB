<template>
  <div>
    <div class="startPageDateDiv">
      <LocationInputForm @updateMyLocation="updateMyLocation" />
      <DatePickerOnStartPage @updateStartDate="updateSearchStartDate" @updateEndDate="updateSearchEndDate"/>
      <BedsInputForm @updateMyAmountOfBeds="updateMyAmountOfBeds"/>
      <PriceRangeForm @updateMyMinPrice="updateMyMinPrice" @updateMyMaxPrice="updateMyMaxPrice"/>
    </div>
    <LeaseDisplayBox v-for="(leaseItem, index) of relevantLeases"
      :key="index"
      :lease="leaseItem"/>
  </div>
</template>
<script>
import LeaseDisplayBox from "../components/StartView/LeaseDisplayBox.vue"
import Lease from '../components/Lease.vue'
import DatePickerOnStartPage from '../components/StartView/DatePickerOnStartPage.vue'
import LocationInputForm from '../components/StartView/LocationInputForm.vue'
import BedsInputForm from '../components/StartView/BedsInputForm.vue'
import PriceRangeForm from '../components/StartView/PriceRangeForm.vue'

  //Load in all the leases of the page from the DB here
  let originalListOfAllLeases = [
      {
    "ownerId": "rmarkie5@list-manage.com",
    "title": "Entire Flat",
    "location": "Holiday Resort",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ipsum mi, finibus ac placerat congue, placerat a enim. Phasellus eleifend, nulla et tincidunt pharetra, metus magna venenatis magna, quis mollis turpis tortor eget quam. Curabitur porta enim non augue auctor, vel bibendum sem iaculis. Nam orci mi, hendrerit quis pulvinar sed, rutrum nec lorem. Pellentesque euismod purus fringilla, mollis odio quis, fringilla purus. Aliquam erat volutpat. Aliquam id neque vel massa mattis hendrerit. Vestibulum finibus egestas eleifend. Etiam ut erat rutrum, maximus odio porta, euismod est.",
    "typeOfHousing": "Flat",
    "startDate": "2021-08-01",
    "endDate": "2021-09-30",
    "PPPN": 220,
    "maxGuests": 4,
    "beds": 4,
    "amenities": {
      "wifi": true,
      "kitchen": true,
      "washer": true,
      "heating": false,
      "airConditioner": true
    },
    "imageURLs": [
      "https://images.unsplash.com/photo-1613377739358-92aca92e9e13?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
      "https://images.unsplash.com/photo-1618660920685-4505debb785a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80"
    ]
  },
  {
          "ownerId": "balder0@fda.gov",
          "title": "Lovely cottage",
          "location": "Bellevue Island",
          "description": "Cosy 4 room cottage in central Bellevue. The cottage is located in the wonderful district, St Palmas with city nearby as well as green parks, local shops, and restaurants. Within walking distance to the heart of Bellevue. You will have the whole cottage for yourself.",
          "typeOfHousing": "Entire Residence",
          "startDate": "2021-05-27",
          "endDate": "2021-07-30",
          "PPPN": 45,
          "maxGuests": 4,
          "beds": 3,
          "amenities": {
            "wifi": true,
            "kitchen": true,
            "washer": true,
            "heating": true,
            "airConditioner": false
          },
          "imageURLs": [
            "https://images.unsplash.com/photo-1588046130717-0eb0c9a3ba15?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1438&q=80",
            "https://images.unsplash.com/photo-1598204326847-aeedbc139508?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1534&q=80"
          ]
        }];
export default {
  components: {
    DatePickerOnStartPage,
    LeaseDisplayBox,
    LocationInputForm,
    BedsInputForm,
    PriceRangeForm
  },
  data() {
    return {
      relevantLeases: originalListOfAllLeases, //An array of Lease objects
      allLeases: originalListOfAllLeases,
      startDate: this.getTodayInCorrectFormat(),
      endDate: '',
      validStartDate: false,
      validEndDate: false,
      shouldAddLease: false,
      myLocation: '',
      removedBasedOnDate: [],
      removedBasedOnLocation: [],
      myAmountOfBeds: '',
      myMinPrice: '',
      myMaxPrice: ''
    }
  },
  methods:{
    checkWhichFilterToRun(){
      // 5 ATTRIBUTE SECTION - DONE
      if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myLocation.length > 0 && this.myMinPrice.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnDateBedsLocationAndAllPrices()
        return
      } //Date + Beds + Location + Min Price + Max Price - Done
      // 4 ATTRIBUTE SECTION - Done
      else if(this.endDate.length > 0 && this.myLocation.length > 0 && this.myMaxPrice.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnDateLocationMaxAndMinPrice()
        return
      } //Date + Location + Max Price + Min price - Done
      else if(this.myLocation.length > 0 && this.myAmountOfBeds.length > 0 && this.myMaxPrice.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnLocationBedsMaxAndMinPrice()
        return
      } //Location + Beds + Max Price + Min Price - Done
      else if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myMaxPrice.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnDateBedsMaxPriceAndMinPrice()
        return
      } //Date + Beds + Max Price + Min Price - Done
      else if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myLocation.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnDateBedsLocationAndMaxPrice()
        return
      } //Date + Beds + Location + Max Price - Done
      else if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myLocation.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnDateBedsLocationAndMinPrice()
        return
      } //Date + Beds + Location + Min Price - Done

      //3 COMPONENT SECTION - DONE
      else if(this.myAmountOfBeds > 0 && this.myLocation.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnBedsLocationAndMinPrice()
        return
      } // Beds + Location + Min Price - Done
      else if(this.myLocation.length > 0 && this.myAmountOfBeds > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnLocationBedsAndMaxPrice()
        return
      } // Location + Beds + Max Price - Done
      else if(this.myLocation.length > 0 && this.myMinPrice.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnLocationMinAndMaxPrice()
        return
      } //Location + Min Price + Max Price - Done
      else if(this.endDate.length > 0 && this.myMinprice.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnDateMinAndMaxPrice()
        return
      } //Date + Min Price + Max Price - Done
      else if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myLocation.length > 0){
        this.filterBasedOnDateBedsAndLocation()
        return
      } //Date + Beds + Location - Done
      else if(this.myMinPrice.length > 0 && this.myMaxPrice.length > 0 && this.myAmountOfBeds > 0){
        this.filterBasedOnMinPriceMaxPriceAndBeds()
        return
      } //Min Price + Max Price + Beds - Done
      else if(this.endDate.length > 0 && this.myMaxPrice.length > 0 && this.myLocation.length > 0){
        this.filterBasedOnDateMaxPriceAndLocation()
        return
      } //Date + Max Price + Location - Done
      else if(this.endDate.length > 0 && this.myMinPrice.length > 0 && this.myLocation.length > 0){
        this.filterBasedOnDateMinPriceAndLocation()
        return
      } //Date + Min Price + Location - Done
      else if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnDateBedsAndMaxPrice()
        return
      } //Date + Beds + Max Price - Done
      else if(this.endDate.length > 0 && this.myAmountOfBeds > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnDateBedsAndMinPrice()
        return
      } //Date + Beds + Min Price - Done

      // 2 PART COMPONENTS - Done
      else if(this.myMinPrice.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnMinAndMaxPrice()
        return
      } // Min Price + Max Price - Done
      else if(this.myAmountOfBeds > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnBedsAndMaxPrice()
        return
      } //Beds + Max Price - Done
      else if(this.myAmountOfBeds > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnBedsAndMinPrice()
        return
      } //Beds + Min Price - Done
      else if(this.myLocation.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnLocationAndMaxPrice()
        return
      } //Location + Max Price - Done
      else if(this.myLocation.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnLocationAndMinPrice()
        return
      } //Location + Min Price - Done
      else if(this.endDate.length > 0 && this.myMaxPrice.length > 0){
        this.filterBasedOnDateAndMaxPrice()
        return
      } //Date + Max price - Done
      else if(this.endDate.length > 0 && this.myMinPrice.length > 0){
        this.filterBasedOnDateAndMinPrice()
        return
      } //Date + Min price - Done
      else if(this.myAmountOfBeds > 0 && this.myLocation > 0){
        this.filterBasedOnBedsAndLocation()
        return
      } //Beds + Location - Done
      else if(this.myAmountOfBeds > 0 && this.endDate.length > 0){
        this.filterBasedOnBedsAndDate()
        return
      } //Beds + Date - Done
      else if(this.endDate.length > 0 && this.myLocation.length > 0){
        this.filterBasedOnDateAndLocation()
        return
      } //Date + Location - Done

      // 1 COMPONENT PART - DONE
      else if(this.myMinPrice.length > 0){
        this.filterBasedOnMinPrice()
        return
      } // Min Price - Done
      else if(this.myMaxPrice.length > 0){
        this.filterBasedOnMaxPrice()
      } // Max Price - Done
      else if(this.myAmountOfBeds > 0){
        this.filterBasedOnBeds()
        return
      } // Beds - Done
      else if(this.endDate.length > 0){
        this.filterBasedOnDate()
        return
      } // Date - Done
      else if(this.myLocation.length > 0){
        this.filterBasedOnLocation()
        return
      } // Location - Done
      else{
        this.relevantLeases = this.allLeases;
      }
    },
    filterBasedOnLocationMinAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMinPrice <= lease.PPPN && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnLocationBedsAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && lease.beds == this.myAmountOfBeds && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnBedsLocationAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.beds == this.myAmountOfBeds && lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMinprice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateMinAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate >= this.endDate && this.myMinPrice <= lease.PPPN && this.myMaxprice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnLocationBedsMaxAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && lease.beds == this.myAmountOfBeds && this.myMaxPrice >= lease.PPPN && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateLocationMaxAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate >= this.endDate && lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMaxPrice >= lease.PPPN && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsMaxPriceAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate >= this.endDate && this.myAmountOfBeds == lease.beds && this.myMaxPrice >= lease.PPPN && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnMinPriceMaxPriceAndBeds(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(this.myMinPrice <= lease.PPPN && this.myMaxPrice >= lease.PPPN && this.myAmountOfBeds == lease.beds){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnMinAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(this.myMinPrice <= lease.PPPN && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateMaxPriceAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate >= this.endDate && this.myMaxPrice >= lease.PPPN && lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateMinPriceAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate >= this.endDate && this.myMinPrice <= lease.PPPN && lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate <= this.endDate && this.myAmountOfBeds == lease.beds && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate <= this.endDate && this.myAmountOfBeds == lease.beds && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate <= this.endDate && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && lease.endDate <= this.endDate && this.myMaxPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnBedsAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(this.myAmountOfBeds == lease.beds && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnBedsAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(this.myAmountOfBeds == lease.beds && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnLocationAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnLocationAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsLocationAndAllPrices(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.beds == this.myAmountOfBeds && lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMinPrice <= lease.PPPN && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsLocationAndMaxPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.beds == this.myAmountOfBeds && lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMaxPrice >= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsLocationAndMinPrice(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.beds == this.myAmountOfBeds && lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && this.myMinPrice <= lease.PPPN){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateBedsAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.beds == this.myAmountOfBeds && lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase()) && lease.startDate <= this.startDate && this.endDate <= lease.endDate){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnBedsAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.beds == this.myAmountOfBeds && lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnBedsAndDate(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.beds == this.myAmountOfBeds){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDateAndLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate && lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnBeds(){
      this.relevantLeases = []
      for (let lease of this.allLeases){
        if(lease.beds == this.myAmountOfBeds){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnDate(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.startDate <= this.startDate && this.endDate <= lease.endDate){
          this.relevantLeases.push(lease)
        }
      }
    },
    filterBasedOnLocation(){
      this.relevantLeases = []
      for(let lease of this.allLeases){
        if(lease.location.toLowerCase().includes(this.myLocation.toLowerCase())){
          this.relevantLeases.push(lease)
        }
      }
    },
    updateMyAmountOfBeds(newAmountOfBeds){
      if(Number.isNaN(Number(newAmountOfBeds))){
        return
      }
      this.myAmountOfBeds = Number(newAmountOfBeds);
      this.checkWhichFilterToRun()
    },
    updateMyLocation(newLocation){
      this.myLocation = newLocation
      this.checkWhichFilterToRun()
    },
    updateSearchEndDate(newDate){
      this.endDate = newDate
      this.checkWhichFilterToRun()
    },
    updateSearchStartDate(newDate){
      this.startDate = newDate
      this.checkWhichFilterToRun()
    },
    updateMyMinPrice(newMinPrice){
      this.myMinPrice = newMinPrice
      this.checkWhichFilterToRun()
    },
    updateMyMaxPrice(newMaxPrice){
      this.myMaxPrice = newMaxPrice
      this.checkWhichFilterToRun()
    },
    getTodayInCorrectFormat(){
      let today = new Date()
      let month = today.getMonth() + 1;
      let day = today.getDate();
      let zeroBeforeMonth = '';
      let zeroBeforeDay = '';
      if(month < 10){
        zeroBeforeMonth += '0' + month
        month = zeroBeforeMonth
      }
      if(day < 10){
        zeroBeforeDay += '0' + day
        day = zeroBeforeDay
      }
      return today.getFullYear() + '-' + month + '-' + day
    },
  }
}
</script>
<style scoped>
.startPageDateDiv{
  width:max-content;
  margin-left:auto;
  margin-right:auto;
}
</style>
