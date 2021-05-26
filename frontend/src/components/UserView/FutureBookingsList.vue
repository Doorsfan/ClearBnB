<template>
  <div class="mainBoxDiv">
    <router-link
      class="goToBookedItem"
      :to="{ path: '/BookingView', query: { id: futureBooking.leaseId } }"
    >
      <img class="firstImage" :src="futureBooking.bookedStay.imageURLs[0]" />
      <div class="titleDiv">{{ futureBooking.getLocation() }}</div>
      <div class="guestsDiv">{{ 'Guests: ' + futureBooking.getGuests() }}</div>
      <div class="dateDiv">
        {{ futureBooking.getStartDate() + ' - ' + futureBooking.getEndDate() }}
      </div>
      <div class="priceDiv">
        {{ 'Total price: ' + Math.round(futureBooking.getTotalPrice() * 1.15) }}
      </div>
    </router-link>
    <div class="buttonDiv">
      <button @click="cancelBooking" class="cancelButton">
        Cancel Booking
      </button>
    </div>
  </div>
</template>
<script setup="">
import Carousel from '../Carousel.vue';
</script>
<script>
export default {
  props: ['futureBooking'],
  emits: ['cancelBooking'],
  data() {
    return {};
  },
  methods: {
    cancelBooking() {
      this.$emit('cancelBooking', this.futureBooking.id);
    },
  },
};
</script>
<style scoped>
* {
  font-family: 'mukta';
  color:black;
  text-decoration:none;
  font-weight:bold;
}
.cancelButton {
  padding: 5px;
  font-weight: bolder;
  font-size: 20px;
  margin-top: 8px;
  background: rgba(255, 0, 0, 0.78);
  color: white;
  cursor: pointer;
  -webkit-text-stroke: solid 3px black;
}

.titleDiv {
  font-weight: bolder;
}
.firstImage {
  width: 150px;
  height: 100px;
  border-radius:10px;
}
.mainBoxDiv {
  margin-left: auto;
  margin-right: auto;
  width: max-content;
  padding: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: 1px solid black;
  background:white;
  border-radius:10px;
}
.titleDiv {
  text-align: center;
}
</style>
