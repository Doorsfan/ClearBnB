<template>
  <button @click="bookLease" class="bookingButton" value="Book">Book</button>
</template>
<script>
export default {
  name: 'DetailsView',
  components: {

  },
  methods: {
    //Needs information from the DetailView to be correct, and maybe should
    // add Booking to User?
    async bookLease(){
      let booking = {
        userId: 1,
        leaseId: 1,
        location: "Los Angeles",
        startDate: "2021-04-05",
        endDate: "2021-05-05",
        guests: 1,
        totalPrice: 1000
      }
      let res = await fetch('/rest/bookings', {
        method: 'POST',
        body: JSON.stringify(booking)
      })
      console.log(await res.json());
    },
  }
}
</script>
<style scope>
</style>