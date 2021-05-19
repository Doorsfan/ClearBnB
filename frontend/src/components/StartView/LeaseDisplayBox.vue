<template>
  <div class="mainBoxDiv">
    <div class="titleDiv">{{ lease.title }}</div>
    <Carousel :myLease="lease"/>
    <div class="divHoldingLocationAndPrice">
      <div class="textBox">
        <div class="locationDiv"><b>Location:</b> {{ lease.location }}</div>
        <div class="startsFromDiv"><b>Available from:</b> {{ lease.startDate }}</div>
        <div clas="endsOnDiv"><b>Available to:</b> {{ lease.endDate }}</div>
        <div class="pricePerNightDiv"><b>Price Per Night:</b> {{ Math.round(lease.price * 1.15) }}</div>
        <div class="descriptionDiv"><b>Description:</b> {{ lease.description }}</div>
      </div>
      <router-link class="readMoreLink" :to="{ path: '/BookingView', query: { leaseId: lease.id }}">Read More..</router-link>
    </div>
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
  }
}
</script>
<style scoped>
.readMoreLink{
  margin-left:150px;
}
.readMoreLink:visited{
  color:blue;
}
.titleDiv{
  font-weight:bolder;
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