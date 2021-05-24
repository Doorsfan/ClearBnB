<template>
  <div class="leaseBox">
    <router-link class="linkToDetailsPage" :to="{ path: '/BookingView', query: { id: lease.id }}">
      <div id="foo" v-on:click="select($event)" class="mainBoxDiv">
        <div class="titleDiv"><p class="leaseTitleText">{{ lease.title }}</p></div>
        <Carousel :myLease="lease"/>
        <div class="locationDiv"><b>Location:</b> {{ lease.location }}</div>
        <div class="startsFromDiv"><b>Available from:</b> {{ lease.startDate }}</div>
        <div clas="endsOnDiv"><b>Available to:</b> {{ lease.endDate }}</div>
        <div class="pricePerNightDiv"><b>Price Per Night:</b> {{ Math.round(lease.price * 1.15) }}</div>
      </div>
    </router-link>
  </div>
</template>
<script setup="">
  import Carousel from '../Carousel.vue'
</script>
<script>
export default {
  props: ["lease"],
  data() {
    return {
    }
  },
  methods: {
    select: function(event) {
      console.log(event.target);
    }
  }
}
</script>
<style scoped>
a {
  color:black;
  text-decoration:none;
}
a:visited{
  color:black;
}
.leaseBox{
  background-color:rgba(218, 224, 224, 0.8);
  min-width:max-content;
  width:28vw;
  padding: 10px;
  text-align:center;
  margin: 5px;
  display:inline-block;
}

@media only screen and (max-width: 400px) {
  .leaseBox{
    width:245px;
  }
  p{
    width:150px;
    text-align:center;
    margin-left:auto;
    margin-right:auto;
  }
}
</style>