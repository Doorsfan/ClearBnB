<template>
  <div class="mainBoxDiv">
    <router-link
      class="goToBookedItem"
      :to="{ path: '/BookingView', query: { id: pastBooking.leaseId } }"
    >
      <img class="firstImage" :src="pastBooking.bookedStay.imageURLs[0]" />
      <div class="titleDiv">{{ pastBooking.getLocation() }}</div>
      <div class="guestsDiv">{{ 'Guests: ' + pastBooking.getGuests() }}</div>
      <div class="dateDiv">
        {{ pastBooking.getStartDate() + ' - ' + pastBooking.getEndDate() }}
      </div>
      <div class="priceDiv">
        {{ 'Total price: ' + Math.round(pastBooking.getTotalPrice() * 1.15) }}
      </div>
    </router-link>
  </div>
</template>
<script>
export default {
  props: ['pastBooking'],
  data() {
    return {};
  },
  methods: {
    cancelBooking() {
      this.$emit('cancelBooking', this.pastBooking.id);
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
