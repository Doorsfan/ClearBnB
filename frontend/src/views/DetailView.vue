<template>
  <Lease @updateTakenDates="updateTakenDates"/>
  <br/>
  <div class="datePickerDiv">
    <div class="checkInDiv">
      <p class="checkInText">Check In</p>
      <DatePickerForLease :lower-limit="minimumEndDate" v-model="fromDate" v-for="(leaseItem, index) of myLease" :key="index" :relevantLease="leaseItem"/>
    </div>
    <div class="checkOutDiv">
      <p class="checkOutText">Check Out</p>
      <DatePickerForLease :lower-limit="minimumEndDate" v-model="toDate" v-for="(leaseItem, index) of myLease" :key="index" :relevantLease="leaseItem"/>
    </div>
  </div>
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
  methods:{
    updateTakenDates(newTakenDates){
      let relevantLease = this.myLease[0];
      for(let myDate of newTakenDates){
        relevantLease.takenDates.push(myDate);
      }
    }
  },
  data() {
    return {
      minimumEndDate: new Date(),
      fromDate: new Date(),
      toDate: new Date().addDays(1),
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
        takenDates: []
        }
      ]
    }
  }
}
</script>
<style scoped>
p{
  margin:0px;
  margin:3px;
  text-align:center;
}
.checkInDiv,.checkOutDiv{
  width: max-content;
  display:inline-block;
  margin:5px;
}

</style>