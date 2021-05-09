<template>
  <button @click="addTakenDates" value="Add Taken Dates">Add Taken Dates</button>
</template>
<script>
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
      /*
      while(differenceInDays > 0){
        this.takenDates.push("taken date");
        differenceInDays -= 1;
      }*/
    }
  }
}
</script>
