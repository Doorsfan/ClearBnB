<template>
  <router-link class="linkToDetailsPage" :to="{ path: '/BookingView', query: { id: lease.id }}">
  <div id="foo" v-on:click="select($event)" class="mainBoxDiv">
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
    </div>
  </div>
  </router-link>
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
div{
  color:black;
}
.mainBoxDiv{
  background-color: rgba(218, 224, 224, 0.8);
  padding:20px;
  width:500px;
}
.linkToDetailsPage{
  text-decoration:none;
}
.linkToDetailsPage:visited{
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