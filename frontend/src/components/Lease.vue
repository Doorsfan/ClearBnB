<template>
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
      let differenceInTime = Math.abs(endDate - startDate)
      differenceInDays = Math.ceil(differenceInTime / (1000 * 60 * 60 * 24));
      console.log("The days of this booking was: ", differenceInDays);
      while(differenceInDays > 0){
        this.takenDates.push("taken date");
        differenceInDays -= 1;
      }
    }
  }
}
</script>
