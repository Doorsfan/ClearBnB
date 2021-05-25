<template>
  <div class="leaseBox">
    <router-link class="linkToDetailsPage" :to="{ path: '/BookingView', query: { id: lease.id }}">
      <div id="foo" v-on:click="select($event)" class="mainBoxDiv">
        <div class="titleDiv"><p class="leaseTitleText">{{ lease.title }}</p></div>
        <Carousel :myLease="lease"/>
        <div class="locationDiv"><p><b>Location:</b> {{ lease.location }}</p></div>
        <div class="startsFromDiv"><p><b>Available from:</b> {{ lease.startDate }}</p></div>
        <div clas="endsOnDiv"><p><b>Available to:</b> {{ lease.endDate }}</p></div>
        <div class="pricePerNightDiv"><p><b>Price PPPN:</b> {{ Math.round(lease.price * 1.15) }} €</p></div>
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
p {
  font-size: 20px;
  line-height: 120%;
}
a {
  color:rgb(20, 20, 20);
  text-decoration:none;
}
a:visited{
  color:rgb(20, 20, 20);
}
.leaseTitleText{
  font-weight: 700;
  margin-top: 10px;
}
.leaseBox{
  background-color:rgba(238, 230, 184, 0.9);
  border: 1px solid rgb(238, 230, 184);
  min-width:max-content;
  width:28vw;
  padding: 20px;
  text-align:center;
  margin: 10px;
  display:inline-block;
  border-radius: 7px;
  box-shadow: 5px 5px 5px 0 rgb(148, 141, 119);
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