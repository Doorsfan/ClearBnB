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
    addTakenDates(startDate, endDate){
      let testEndDate = new Date("2021/05/28")
      let testStartDate = new Date("2021/05/04")
      let differenceInTime = Math.abs(testEndDate - testStartDate)
      let differenceInDays = Math.ceil(differenceInTime / (1000 * 60 * 60 * 24));
      console.log("The days of this booking was: ", differenceInDays);
      let takenDayNr = 1;
      while(differenceInDays > 0){
        this.takenDates.push(testStartDate.addDays(takenDayNr))
        takenDayNr += 1;
        differenceInDays -= 1;
      }
      console.log("After adding the days, the disabled days were: ", this.takenDates);
    }
  },
  watch: {
    takenDates(){
      this.$emit("updateTakenDates", this.takenDates)
    }
  }
}
</script>
