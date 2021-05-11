<template>
</template>
<script>
export default class User{
  constructor(myBookings){
    this.myBookings = myBookings;
  }
  getBookings(){
    return this.myBookings
  }
  addBooknig(newBooking){
    this.myBookings.push(newBooking);
  }
}
</script>
<style scoped>
</style>