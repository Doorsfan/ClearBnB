<template>
  <LeaseDisplayBox v-for="(leaseItem, index) of leases"
      :key="index"
      :lease="leaseItem"/>
</template>

<script setup="">
import LeaseDisplayBox from "./components/Start View/LeaseDisplayBox.vue"
import Lease from './components/Lease.vue'
</script>
<script>
export default {
  name: "App",
  components: {
    Lease,
    LeaseDisplayBox,
  },
  data() {
    return {
      leases: [
        {
    "ownerId": "tsongest2@howstuffworks.com",
    "title": "Two-bedroom vila not far from the beach",
    "location": "Bellevue Island",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ipsum mi, finibus ac placerat congue, placerat a enim. Phasellus eleifend, nulla et tincidunt pharetra, metus magna venenatis magna, quis mollis turpis tortor eget quam. Curabitur porta enim non augue auctor, vel bibendum sem iaculis. Nam orci mi, hendrerit quis pulvinar sed, rutrum nec lorem. Pellentesque euismod purus fringilla, mollis odio quis, fringilla purus. Aliquam erat volutpat. Aliquam id neque vel massa mattis hendrerit. Vestibulum finibus egestas eleifend. Etiam ut erat rutrum, maximus odio porta, euismod est.",
    "typeOfHousing": "Vila",
    "startDate": "2021-05-27",
    "endDate": "2021-07-30",
    "pricePerNight": 75,
    "maxGuests": 4,
    "beds": 4,
    "amenities": {
      "wifi": true,
      "kitchen": true,
      "washer": true,
      "heating": true,
      "airConditioner": false
    },
    "imageURLs": [
      "https://images.unsplash.com/photo-1566195992011-5f6b21e539aa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=667&q=80",
      "https://images.unsplash.com/photo-1595521624742-47e90260edab?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80"
    ]
  },
        {
          ownerId: "balder0@fda.gov",
          title: "Lovely cottage",
          location: "Bellevue Island",
          description: "Cosy 4 room cottage in central Bellevue. The cottage is located in the wonderful district, St Palmas with city nearby as well as green parks, local shops, and restaurants. Within walking distance to the heart of Bellevue. You will have the whole cottage for yourself.",
          typeOfHousing: "Cottage",
          startDate: "2021-05-27",
          endDate: "2021-07-30",
          pricePerNight: 45,
          maxGuests: 4,
          beds: 3,
          amenities: {
            wifi: true,
            kitchen: true,
            washer: true,
            heating: true,
            airConditioner: false
          },
          imageURLs: [
          "https://images.unsplash.com/photo-1588046130717-0eb0c9a3ba15?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1438&q=80",
          "https://images.unsplash.com/photo-1598204326847-aeedbc139508?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1534&q=80"
          ]
        }
      ],
    }
  }
}
</script>
<style>
</style>