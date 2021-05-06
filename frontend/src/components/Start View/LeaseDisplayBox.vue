<template>
  <div class="mainBoxDiv">
    <div class="titleDiv">{{ lease.title }}</div>
    <Carousel :myLease="lease"/>
    <div class="secondDiv">
      <div class="textBox">
        <div class="locationDiv">Location: {{ lease.location }}</div>
        <div class="pricePerNightDiv">Price Per Night: {{ lease.pricePerNight }}</div>
        <div class="descriptionDiv">Description: {{ lease.description }}</div>
      </div>
      <router-link class="readMoreLink" to="/">Read more..</router-link>
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
.LeftArrowButton{
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
  z-index:2;
  position:absolute;
  margin-top:110px;
  margin-left:-17px;
}
.RightArrowButton{
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  z-index:2;
  position:absolute;
  margin-top:110px;
  margin-left:157px;
}
.imageBox{
  height:180px;
  width: 150px;
  margin-top:5px;
  margin-bottom:5px;
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
.leaseBoxDiv{
  height:109px;
  width:200px;
  display:inline;
}
.secondDiv{
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