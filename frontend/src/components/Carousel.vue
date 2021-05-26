<template>
  <div class="imageCarousel">
    <img class="firstImage" :src="myLease.imageURLs[0]" />
    <img class="secondImage" :src="myLease.imageURLs[1]" />
  </div>
</template>
<script>
//Implement Interaction with Arrow Buttons again in regards to Carousel Component
export default {
  props: ["myLease"],
  data() {
    return {};
  },
  methods: {
    previousPicture() {
      let firstPicture = this.myLease.imageURLs[0];
      let secondPicture = this.myLease.imageURLs[1];
      this.myLease.imageURLs[1] = firstPicture;
      this.myLease.imageURLs[0] = secondPicture;
    },
    nextPicture() {
      let firstPicture = this.myLease.imageURLs[0];
      let secondPicture = this.myLease.imageURLs[1];
      this.myLease.imageURLs[0] = secondPicture;
      this.myLease.imageURLs[1] = firstPicture;
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
  height: 30vh;
  max-height:500px;
  width: 50vw;
  max-width:1200px;
  margin: 5px;
  display: inline-block;
}
img {
  object-fit: cover;
  object-position:center;
  height: 30vh;
  max-height:500px;
  width: 52vw;
  max-width:1200px;
  margin-left:auto;
  margin-right:auto;
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
@media only screen and (max-width: 300px) {
  img {
    margin-left: 0px;
  }
}
</style>
