<template>
</template>

<script>
export default class Lease {
  constructor(title, location, description, typeOfHousing, startDate, endDate, pPPN, maxGuests, beds, amenities, imageURLs){
    this.title = title
    this.location = location
    this.description = description
    this.typeOfHousing = typeOfHousing
    this.startDate = startDate
    this.endDate = endDate
    this.pPPN = pPPN
    this.maxGuests = maxGuests
    this.beds = beds
    this.amenities = amenities
    this.imageURLs = imageURLs
  }
  data() {
    return {
      title: this.title,
      Description: this.location,
      image: ''
    }
  }
}
</script>
