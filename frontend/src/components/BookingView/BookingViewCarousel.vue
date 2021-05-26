<template>
  <i @click="previousPicture" class="arrow left"></i>
  <div class="imageCarousel">
    <img
      v-if="myLease.imageURLs.length > 0"
      class="firstImage"
      :src="myLease.imageURLs[0]"
    />
    <img
      v-if="myLease.imageURLs.length > 1"
      class="secondImage"
      :src="myLease.imageURLs[1]"
    />
    <img
      v-if="myLease.imageURLs.length > 2"
      class="thirdImage"
      :src="myLease.imageURLs[2]"
    />
    <img
      v-if="myLease.imageURLs.length > 3"
      class="fourthImage"
      :src="myLease.imageURLs[3]"
    />
    <img
      v-if="myLease.imageURLs.length > 4"
      class="fifthImage"
      :src="myLease.imageURLs[4]"
    />
  </div>
  <i @click="nextPicture" class="arrow right"></i>
</template>
<script>
//Implement Interaction with Arrow Buttons again in regards to Carousel Component
export default {
  props: ["myLease"],
  data() {
    return {
      currentImage: 0,
      nextImage: 1,
    };
  },
  methods: {
    previousPicture() {
      let firstPicture = this.myLease.imageURLs[currentImage];
      let secondPicture = this.myLease.imageURLs[nextImage];
      this.myLease.imageURLs[nextImage] = firstPicture;
      this.myLease.imageURLs[currentImage] = secondPicture;
    },
    nextPicture() {
      let firstPicture = this.myLease.imageURLs[currentImage];
      let secondPicture = this.myLease.imageURLs[nextImage];
      this.myLease.imageURLs[currentImage] = secondPicture;
      this.myLease.imageURLs[nextImage] = firstPicture;
    },
  },
};
</script>
<style scoped>
* {
  font-family: "mukta";
}
.imageCarousel {
  position: relative;
  height: 180px;
  width: 150px;
  margin: 5px;
  display: inline-block;
}
img {
  height: 180px;
  width: 150px;
  position: absolute;
  left: 0;
  -webkit-transition: opacity 0.7s ease-in-out;
  -moz-transition: opacity 0.7s ease-in-out;
  -o-transition: opacity 0.7s ease-in-out;
  transition: opacity 0.7s ease-in-out;
}
@keyframes cyclingOpacity {
  0% {
    opacity: 1;
  }
  45% {
    opacity: 1;
  }
  55% {
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}
.firstImage {
  animation-name: cyclingOpacity;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  animation-duration: 0s;
  animation-direction: alternate;
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
  margin-bottom: 85px;
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
