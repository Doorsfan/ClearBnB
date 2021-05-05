<template>
  <div class="mainBoxDiv">
    <div class="titleDiv">{{ lease.title }}</div>
    <div class="leaseBoxDiv">
      <img class="imageBox" :src="lease.imageURLs[0]"><div class="secondDiv"><p>{{ lease.description }}</p></div>
    </div>
  </div>
</template>
<script>
export default {
  props: ["lease"],
  data() {
    return {
    }
  },
  methods: {
    temp(){
      console.log("hi")
    }
  }
}
</script>
<style scoped>
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