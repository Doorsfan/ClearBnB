<template>
  <div>
    <div v-if="imageURL.length > 0" class="firstImageURL"><button @click="removeFirstImageURL" class="xSmall" type="button">X</button><img class="myImage firstImageSrc" :src="imageURL[0]"/>
    </div>
    <div v-if="imageURL.length > 1" class="secondImageURL"><button @click="removeSecondImageURL" class="xSmall" v-if="imageURL.length > 1" type="button">X</button>
      <img class="myImage secondImageSrc" :src="imageURL[1]"/>
    </div>
    <div v-if="imageURL.length > 2" class="thirdImageURL"><button @click="removeThirdImageURL" class="xSmall" v-if="imageURL.length > 2" type="button">X</button>
      <img class="myImage thirdImageSrc" :src="imageURL[2]"/>
    </div>
    <div v-if="imageURL.length > 3" class="fourthImageURL"><button @click="removeFourthImageURL" class="xSmall" v-if="imageURL.length > 3" type="button">X</button><img class="myImage fourthImageSrc" :src="imageURL[3]"/>
    </div>
    <div v-if="imageURL.length > 4" class="fifthImageURL"><button @click="removeFifthImageURL" class="xSmall" v-if="imageURL.length > 4" type="button">X</button><img class="myImage fifthImageSrc" :src="imageURL[4]"/>
    </div>
  </div>
</template>
<script>
export default {
  props: ["imageURL"],
  emits: ["removedFirstImage", "removedSecondImage", "removedThirdImage", "removedFourthImage", "removedFifthImage"],
  data() {
    return {
    }
  },
  methods: {
    removeFirstImageURL(){
      console.log("emited removedFirstImage");
      this.$emit("removedFirstImage", true)
    },
    removeSecondImageURL(){
      this.$emit("removedSecondImage", true);
    },
    removeThirdImageURL(){
      this.$emit("removedThirdImage", true);
    },
    removeFourthImageURL(){
      this.$emit("removedFourthImage", true);
    },
    removeFifthImageURL(){
      console.log("Should have emited");
      this.$emit("removedFifthImage", true);
    }
  }
}
</script>
<style scoped>
.myImage{
  width:150px;
  height:100px;
}
.xSmall {
  background:red;
  padding:2px;
  margin:2px;
  color:white;
  position:absolute;
}
</style>