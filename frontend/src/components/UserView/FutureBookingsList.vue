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
  color: black;
  font-weight: bold;
  text-decoration: none;
  text-decoration-color: white;
}
.cancelButton {
  padding: 5px;
  font-weight: bold;
  font-size: 20px;
  margin-top: 8px;
  background: rgba(255, 0, 0, 0.78);
  color: white;
  cursor: pointer;
  -webkit-text-stroke: solid 3px black;
  border-radius: 10px;
}

.firstImage {
  object-fit: cover;
  object-position: center;
  width: 50vw;
  max-width: 900px;
  height: 700px;
  max-height: 30vh;
  border-radius: 10px;
}
.mainBoxDiv {
  margin-left: auto;
  margin-right: auto;
  width: 75vw;
  padding: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  max-width: 1200px;
}
.titleDiv {
  text-align: center;
}
</style>
