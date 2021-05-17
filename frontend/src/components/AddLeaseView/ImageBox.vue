<template>
  <div v-if="imageURL.length > 0">
    <div class="firstImageURL">
      {{imageURL[0][0]}} <button @click="removeImageURL" class="xSmall" type="button">X</button>
    </div>
    <div class="secondImageURL">
      {{imageURL[0][1]}} <button @click="removeImageURL" class="xSmall" v-if="imageURL[0].length > 1" type="button">X</button>
    </div>
    <div class="thirdImageURL">
      {{imageURL[0][2]}} <button @click="removeImageURL" class="xSmall" v-if="imageURL[0].length > 2" type="button">X</button>
    </div>
    <div class="fourthImageURL">
      {{imageURL[0][3]}} <button @click="removeImageURL" class="xSmall" v-if="imageURL[0].length > 3" type="button">X</button>
    </div>
    <div class="fifthImageURL">
      {{imageURL[0][4]}} <button @click="removeImageURL" class="xSmall" v-if="imageURL[0].length > 4" type="button">X</button>
    </div>
  </div>
</template>
<script>
export default {
  props: ["imageURL"],
  data() {
    return {

    }
  },
  methods: {
    removeImageURL(){
      console.log("temp");
    }
  }
}
</script>
<style scoped>
.xSmall {
  background:red;
  padding:2px;
  margin:2px;
  color:white;
}
</style>