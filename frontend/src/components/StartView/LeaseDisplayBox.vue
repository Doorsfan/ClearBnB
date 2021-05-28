<template>
  <div class="leaseBox">
    <router-link
      class="linkToDetailsPage"
      :to="{ path: '/BookingView', query: { id: lease.id } }"
    >
      <div class="mainBoxDivForLeases">
        <div class="titleDiv">
          <p class="leaseTitleText">{{ lease.title }}</p>
        </div>
        <Carousel :myLease="lease" />
        <div class="locationDiv"><b>Location:</b> {{ lease.location }}</div>
        <div class="startsFromDiv">
          <p class="startsP"><b>Available from:</b> {{ lease.startDate }}</p>
        </div>
        <div clas="endsOnDiv">
          <p class="endsP"><b>Available to:</b> {{ lease.endDate }}</p>
        </div>
        <div class="pricePerNightDiv">
          <p class="priceP">
            <b>Price Per Night:</b> {{ Math.round(lease.price * 1.15) }}
          </p>
        </div>
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
import Carousel from '../Carousel.vue';
</script>
<script>
export default {
  props: ['lease'],
  data() {
    return {};
  },
  methods: {
    previousPicture(event) {
      if (this.lease.imageURLs.length > 1) {
        let oldImage = this.lease.imageURLs[0];
        let newImage = this.lease.imageURLs[1];
        this.lease.imageURLs[0] = newImage;
        this.lease.imageURLs[1] = oldImage;
      }
    },
    nextPicture(event) {
      if (this.lease.imageURLs.length > 1) {
        let oldImage = this.lease.imageURLs[0];
        let newImage = this.lease.imageURLs[1];
        this.lease.imageURLs[0] = newImage;
        this.lease.imageURLs[1] = oldImage;
      }
    },
  },
};
</script>
<style scoped>
* {
  font-family: 'mukta';
}
.mainBoxDivForLeases {
  height: max-content;
}
a {
  font-family: 'mukta';
  color: black;
  text-decoration: none;
}
a:visited {
  color: black;
}
.titleDiv {
  font-weight: bold;
}
.leaseBox {
  background-color: rgba(255, 255, 255, 0.8);
  min-width: max-content;
  min-width: 240px;
  width: 80vw;
  padding: 10px;
  text-align: center;
  margin: 0px;
  margin-top: 10px;
  margin-bottom: 10px;
  display: inline-block;
  min-height: max-content;
  border-radius:17px;
}
.leftArrowBox {
  display: inline-block;
  position: relative;
  top: -285px;
  left: -28vw;
  height: 43px;
  width: 43px;
  background-color: white;
  z-index: 1;
  transform: rotate(270deg);
  border-radius: 25px;
  padding-top: 5px;
  border: solid black 1px;
}
.leaseTitleText {
  font-size: 2rem;
}
.locationDiv,
.startsP,
.endsP,
.priceP {
  font-size: 1.5rem;
}
.rightArrowBox {
  display: inline-block;
  position: relative;
  top: -285px;
  left: 28.5vw;
  height: 43px;
  width: 43px;
  background-color: white;
  border-radius: 25px;
  z-index: 5;
  transform: rotate(90deg);
  padding-top: 5px;
  border: solid black 1px;
}
.rightArrowBox:hover,
.leftArrowBox:hover,
.arrow:hover,
.leftUpper:hover,
.rightUpper:hover,
.leftBottom:hover,
.rightBottom:hover {
  cursor: pointer;
}
.arrow {
  border: solid black;
  border-width: 6.1px 6.1px 6.1px 0;
  display: inline-block;
}
.leftUpper,
.rightUpper {
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  position: relative;
  left: 0px;
  top: 5px;
}
.leftBottom,
.rightBottom {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
  position: relative;
  left: 0px;
  top: 5px;
}

@media only screen and (max-width: 450px) {
  .leaseBox {
    margin: 0px;
    padding: 0px;
    padding: 15px;
    width: 90vw;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .rightArrowBox, .leftArrowBox{
    top: -270px;
  }
}

@media only screen and (max-width: 325px) {
  .rightArrowBox, .leftArrowBox{
    top: -310px;
  }
}
@media only screen and (max-width: 300px) {
  .rightArrowBox, .leftArrowBox{
    top: -380px;
  }
}
</style>
