<template>
  <div class="mainDiv">
    <i @click="previousPicture" class="arrow left"></i>
    <div class="imageCarousel">
      <img class="firstImage" :src="lease.imageURLs[0]"/>
      <img class="secondImage" :src="lease.imageURLs[1]"/>
    </div>
    <i @click="nextPicture" class="arrow right"></i>
  </div>
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
.imageCarousel{
  position:relative;
  height:300px;
  width:300px;
  margin:5px;
  display:inline-block;
}
.mainDiv{
  margin-top:10px;
  margin-bottom:10px;
}
img{
  height: 300px;
  width:300px;
  position:absolute;
  left:0;
  -webkit-transition: opacity 0.7s ease-in-out;
  -moz-transition: opacity 0.7s ease-in-out;
  -o-transition: opacity 0.7s ease-in-out;
  transition: opacity 0.7s ease-in-out;
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