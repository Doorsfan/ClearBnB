<template>
  <img class="firstImage" :src="lease.imageURLs[0]">
  <img v-if="lease.imageURLs.length > 1" class="secondImage" :src="lease.imageURLs[1]">
  <img v-if="lease.imageURLs.length > 2" class="thirdImage" :src="lease.imageURLs[2]">
  <img v-if="lease.imageURLs.length > 3" class="fourthImage" :src="lease.imageURLs[3]">
  <img v-if="lease.imageURLs.length > 4" class="fifthImage" :src="lease.imageURLs[4]">
</template>
<script>
//Implement Interaction with Arrow Buttons again in regards to Carousel Component
export default {
  props: ["lease"],
  data() {
    return {

    }
  },
  methods: {
    previousPicture(){
      let firstPicture = this.lease.imageURLs[0]
      let secondPicture = this.lease.imageURLs[1]
      this.lease.imageURLs[1] = firstPicture
      this.lease.imageURLs[0] = secondPicture
    },
    nextPicture(){
      let firstPicture = this.lease.imageURLs[0]
      let secondPicture = this.lease.imageURLs[1]
      this.lease.imageURLs[0] = secondPicture
      this.lease.imageURLs[1] = firstPicture
    }
  }
}
</script>
<style scoped>
.mainDiv{
  margin-top:10px;
  margin-bottom:10px;
  background-color:red;
}
img{
  left:0;
  -webkit-transition: opacity 0.7s ease-in-out;
  -moz-transition: opacity 0.7s ease-in-out;
  -o-transition: opacity 0.7s ease-in-out;
  transition: opacity 0.7s ease-in-out;
  width:300px;
  height:200px;
}
@keyframes cyclingOpacity {
  0% {
  opacity:1;
  }
  45% {
  opacity:1;
  }
  55% {
  opacity:0;
  }
  100% {
  opacity:0;
  }
}
.secondImage {
  animation-name: cyclingOpacity;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  animation-duration: 15s;
  animation-direction: alternate;
}
.thirdImage {
  animation-name: cyclingOpacity;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  animation-duration: 30s;
  animation-direction: alternate;
}
.fourthImage {
  animation-name: cyclingOpacity;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  animation-duration: 45s;
  animation-direction: alternate;
}
.fifthImage {
  animation-name: cyclingOpacity;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  animation-duration: 60s;
  animation-direction: alternate;
}
.arrow {
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  margin-bottom:150px;
}
.left {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
}
.right {
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
}
</style>