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
}
.cancelButton {
  padding: 2px;
  font-weight: bolder;
  font-size: 20px;
  margin-top: 8px;
  background: rgba(255, 182, 193, 0.863);
  color: red;
  cursor: pointer;
}
.readMoreLink {
  margin-left: 150px;
}
.goToBookedItem:visited {
  color: blue;
}
.titleDiv {
  font-weight: bolder;
}
.firstImage {
  width: 100px;
  height: 100px;
}
.mainBoxDiv {
  margin-left: auto;
  margin-right: auto;
  width: max-content;
  padding: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: 1px solid black;
  background: #eee;
}
.titleDiv {
  text-align: center;
}

.divHoldingLocationAndPrice {
  display: inline;
  height: 166px;
  width: 300px;
  margin: 0px;
  padding: 0px;
  position: absolute;
}
.textBox {
  margin: 2px;
  margin-left: 30px;
  margin-top: 5px;
  word-wrap: break-word;
  width: 230px;
  overflow-y: hidden;
  overflow-x: hidden;
  padding: 3px;
  height: 143px;
  margin-bottom: 10px;
}
.descriptionDiv {
  height: 124px;
}
</style>
