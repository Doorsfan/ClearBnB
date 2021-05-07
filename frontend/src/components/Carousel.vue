<template>
  <div class="imageCarousel">
    <img class="firstImage" :src="myLease.imageURLs[0]"/>
    <img class="secondImage" :src="myLease.imageURLs[1]"/>
  </div>
</template>
<script>
//Implement Interaction with Arrow Buttons again in regards to Carousel Component
export default {
  props: ["myLease"],
  data() {
    return {
    }
  }
}
</script>
<style scoped>
.imageCarousel{
  position:relative;
  height:180px;
  width:150px;
  margin:5px;
  display:inline-block;
}
img{
  height: 180px;
  width:150px;
  position:absolute;
  left:0;
  -webkit-transition: opacity 1s ease-in-out;
  -moz-transition: opacity 1s ease-in-out;
  -o-transition: opacity 1s ease-in-out;
  transition: opacity 1s ease-in-out;
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
  animation-duration: 10s;
  animation-direction: alternate;
}
</style>