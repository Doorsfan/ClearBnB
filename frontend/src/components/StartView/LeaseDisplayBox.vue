<template>
  <div class="leaseBox">
    <router-link class="linkToDetailsPage" :to="{ path: '/BookingView', query: { id: lease.id }}">
      <div id="foo" v-on:click="select($event)" class="mainBoxDiv">
        <div class="titleDiv"><p class="leaseTitleText">{{ lease.title }}</p></div>
        <Carousel  :myLease="lease"/>
        <div class="locationDiv"><b>Location:</b> {{ lease.location }}</div>
        <div class="startsFromDiv"><b>Available from:</b> {{ lease.startDate }}</div>
        <div clas="endsOnDiv"><b>Available to:</b> {{ lease.endDate }}</div>
        <div class="pricePerNightDiv"><b>Price Per Night:</b> {{ Math.round(lease.price * 1.15) }}</div>
      </div>
    </router-link>
  <div @click="previousPicture" class="leftArrowBox" :id="lease.id">
    <div @click="previousPicture" class="innerLeftArrowBox" :id="lease.id">
      <i @click="previousPicture" class="arrow leftUpper" :id="lease.id"></i>
      <i @click="previousPicture" class="arrow leftBottom" :id="lease.id"></i>
    </div>
  </div>
  <div @click="nextPicture" class="rightArrowBox" :id="lease.id">
    <div @click="nextPicture" class="innerRightArrowBox" :id="lease.id">
      <i @click="nextPicture" class="arrow rightUpper" :id="lease.id"></i>
      <i @click="nextPicture" class="arrow rightBottom" :id="lease.id"></i>
    </div>
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
    previousPicture(event){
      if(this.lease.imageURLs.length > 1){
        let oldImage = this.lease.imageURLs[0];
        let newImage = this.lease.imageURLs[1];
        this.lease.imageURLs[0] = newImage;
        this.lease.imageURLs[1] = oldImage;
      }
    },
    nextPicture(event){
      if(this.lease.imageURLs.length > 1){
        let oldImage = this.lease.imageURLs[0];
        let newImage = this.lease.imageURLs[1];
        this.lease.imageURLs[0] = newImage;
        this.lease.imageURLs[1] = oldImage;
      }
    }
  }
}
</script>
<style scoped>
a {
  font-family: 'mukta';
  color:black;
  text-decoration:none;
}
a:visited{
  color:black;
}
.titleDiv{
  font-weight: bold;

}
.leaseBox{
  background-color:rgba(218, 224, 224, 0.8);
  min-width:max-content;
  min-width:280px;
  padding: 10px;
  text-align:center;
  margin: 5px;
  display:inline-block;
  height:340px;
}
.leftArrowBox{
  display:inline-block;
  position:relative;
  top:-180px;
  left:-90px;
  height:35px;
  width:35px;
  background-color:lightblue;
  z-index:5;
  transform:rotate(270deg);
  border-radius:25px;
  padding-top:5px;
}

.rightArrowBox{
  display:inline-block;
  position:relative;
  top:-180px;
  left:90px;
  height:35px;
  width:35px;
  background-color:lightblue;
  border-radius:25px;
  z-index:5;
  transform:rotate(90deg);
  padding-top:5px;
}
.rightArrowBox:hover, .leftArrowBox:hover, .arrow:hover, .leftUpper:hover, .rightUpper:hover,
.leftBottom:hover, .rightBottom:hover{
  cursor:pointer;
}
.arrow {
  border: solid black;
  border-width: 6.1px 6.1px 6.1px 0;
  display: inline-block;
}
.leftUpper, .rightUpper {
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  position:relative;
  left:0px;
  top:5px;
}
.leftBottom, .rightBottom {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
  position:relative;
  left:0px;
  top:5px;
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