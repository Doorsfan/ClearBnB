<template>
</template>
<script>
export default class Lease {
  constructor(ownerId, title, location, description, typeOfHousing, entireResidence, startDate, endDate, price, maxGuests, beds, amenities, imageURLs){
    this.ownerId = ownerId
    this.title = title
    this.location = location
    this.description = description
    this.typeOfHousing = typeOfHousing
    this.entireResidence = entireResidence
    this.startDate = startDate
    this.endDate = endDate
    this.price = price
    this.maxGuests = maxGuests
    this.beds = beds
    this.amenities = amenities
    this.imageURLs = imageURLs
  }
  getleaseId(){
    return this.leaseId;
  }
  setleaseId(newLeaseId){
    this.leaseId = newLeaseId
  }
  getOwnerId(){
    return this.ownerId
  }
  setOwnerId(newOwnerId){
    this.ownerId = newOwnerId
  }
  getTitle(){
    return this.title
  }
  setTitle(newTitle){
    this.title = newTitle
  }
  getLocation(){
    return this.location
  }
  setLocation(newLocation){
    this.location = newLocation
  }
  getDescription(){
    return this.description
  }
  setDescription(newDescription){
    this.description = newDescription
  }
  getTypeOfHousing(){
    return this.typeOfHousing
  }
  setTypeOfHousing(newTypeOfHousing){
    this.typeOfHousing = newTypeOfHousing
  }
  
  getEntireResidence(){
    return this.entireResidence
  }
  setEntireResidence(isEntireResidence){
    this.entireResidence = isEntireResidence
  }
  getStartDate(){
    return this.startDate
  }
  setStartDate(newStartDate){
    this.startDate = newStartDate
  }
  getEndDate(){
    return this.endDate
  }
  setEndDate(newEndDate){
    this.endDate = newEndDate
  }
  getPrice(){
    return this.price
  }
  setPrice(newPrice){
    this.price = newPrice
  }
  getBeds(){
    return this.beds
  }
  setBeds(newBeds){
    this.beds = newBeds
  }
  getMaxGuests(){
    return this.maxGuests
  }
  setMaxGuests(newMaxGuests){
    this.maxGuests = newMaxGuests
  }
  getAmenities(){
    return this.amenities
  }
  setAmenities(newAmenities){
    this.amenities = newAmenities
  }
  getImageURLs(){
    return this.imageURLs
  }
  setImageURLs(newImageURLs){
    this.imageURLs = newImageURLs
  }
  addImageURL(newImageURL){
    this.imageURLs.push(newImageURL)
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