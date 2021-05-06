<template>
  <div class="mainBoxDiv">
    <button class="LeftArrowButton" @click="previousImage"></button>
    <button class="RightArrowButton" @click="nextImage"></button>
    <div class="titleDiv">{{ lease.title }}</div>
    <div class="leaseBoxDiv">
      <img class="imageBox" :src="lease.imageURLs[currentImage]">
      <div class="secondDiv"><p>{{ lease.description }}</p></div>
    </div>
  </div>
</template>
<script>
export default {
  props: ["lease"],
  data() {
    return {
      currentImage: 0
    }
  },
  methods: {
    previousImage(){
      if(this.currentImage == 0){
        this.currentImage = (this.lease.imageURLs.length - 1)
      }
      else{
        this.currentImage -= 1
      }
    },
    nextImage(){
      if(this.currentImage == (this.lease.imageURLs.length - 1)){
        this.currentImage = 0;
      }
      else{
        this.currentImage += 1;
      }
    },
    temp(){
      console.log("hi")
    }
  }
}
</script>
<style scoped>
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
  height:109px;
  width:300px;
  margin:0px;
  padding:0px;
  position:absolute;
}
p{
  margin:2px;
  margin-left: 30px;
  margin-top:5px;
  word-wrap:break-word; 
  height:180px;
  overflow:scroll;
}
</style>