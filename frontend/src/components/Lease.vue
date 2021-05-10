<template>
  <button @click="addTakenDates" value="Add Taken Dates">Add Taken Dates</button>
</template>
<script>
Date.prototype.addDays = function(days) {
  var date = new Date(this.valueOf());
  date.setDate(date.getDate() + days);
  return date;
}
export default {
  name: 'Lease',
  emits: ['updateTakenDates'],
  data() {
    return {
      title: '',
      location: '',
      description: '',
      typeOfHousing: '',
      startDate: '',
      endDate: '',
      PPPN: '',
      maxGuests: '',
      beds: '',
      amenities: [],
      imageURLs: [],
      takenDates: []
    }
  },
  methods: {
    //Run this method when a Lease gets loaded in from the DB to update the Disabled Dates based on the lease
    addTakenDates(startDate, endDate){
      startDate = new Date("2021/05/12")
      endDate = new Date("2021/06/08")
      let differenceInTime = Math.abs(endDate - startDate)
      let differenceInDays = Math.ceil(differenceInTime / (1000 * 60 * 60 * 24));
      let takenDayNr = 1;
      while(differenceInDays > 0){
        this.takenDates.push(startDate.addDays(takenDayNr))
        takenDayNr += 1;
        differenceInDays -= 1;
      }
      this.$emit("updateTakenDates", this.takenDates)
    }
  }
}
</script>
