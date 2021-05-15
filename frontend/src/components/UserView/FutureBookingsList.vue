<template>
  <div class="mainBoxDiv">
    <router-link class="goToBookedItem" :to="{ path: '/BookingView', query: { leaseId: myBooking.leaseId }}">
      <img class="firstImage" :src="myBooking.bookedStay.imageURLs[0]"/>
      <div class="titleDiv">{{ myBooking.getLocation() }}</div>
      <div class="guestsDiv">{{ "Guests: " + myBooking.getGuests() }}</div>
      <div class="dateDiv">{{ myBooking.getStartDate() + " - " + myBooking.getEndDate() }}</div>
      <div class="priceDiv">{{ "Total price: " + myBooking.getTotalPrice() }}</div>
    </router-link>
  </div>
</template>
<script setup="">
  import Carousel from '../Carousel.vue'
</script>
<script>
export default {
  props: ["myBooking"],
  created(){
    //console.log(this.myBooking.bookedStay);
  },
  data() {
    return {
    }
  },
  methods: {
  }
}
</script>
<style scoped>
.readMoreLink{
  margin-left:150px;
}
.goToBookedItem:visited{
  color:blue;
}
.titleDiv{
  font-weight:bolder;
}
.firstImage{
  width:100px;
  height:100px;
}
.mainBoxDiv{
  margin-left:auto;
  margin-right:auto;
  width:500px;
  margin-top:10px;
  margin-bottom:10px;
}
.titleDiv{
  text-align:center;
}

.divHoldingLocationAndPrice{
  display:inline;
  height:166px;
  width:300px;
  margin:0px;
  padding:0px;
  position:absolute;
}
.textBox{
  margin:2px;
  margin-left: 30px;
  margin-top:5px;
  word-wrap:break-word; 
  width: 230px;
  overflow-y:hidden;
  overflow-x:hidden;
  padding:3px;
  height:143px;
  margin-bottom:10px;
}
.descriptionDiv{
  height:124px;
}
</style>