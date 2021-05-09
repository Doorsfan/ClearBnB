<template>
  <Lease />
  <DatePickerForLease v-for="(leaseItem, index) of myLease" :key="index" :relevantLease="leaseItem"/>
</template>
<script>
import DatePickerForLease from '../components/DetailView/DatePickerForLease.vue'
import Lease from '../components/Lease.vue'
export default {
  name: 'DetailView',
  components:
  {
    DatePickerForLease,
    Lease
  },
  data() {
    return {
      myLease: [
        {
        title: 'Cozy Cottage',
        location: 'Los Angeles',
        description: 'Lorem Ipsum',
        typeOfHousing: 'House',
        startDate: '2021-05-07',
        endDate: '2021-08-08',
        PPPN: '1000',
        maxGuests: '3',
        beds: '3',
        amenities: [],
        imageURLs: [],
        takenDates: [new Date("2021-05-05"), new Date("2021-05-06"), new Date("2021-05-07")]
        }
      ]
    }
  }
}
</script>
<style scoped>

</style>