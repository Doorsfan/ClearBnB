<template>
  <div>
    <div v-if="imageURL.length > 0" class="firstImageURL">
      <button @click="removeFirstImageURL" class="xSmall" type="button">
        X</button
      ><img class="myImage firstImageSrc" :src="imageURL[0]" />
    </div>
    <div v-if="imageURL.length > 1" class="secondImageURL">
      <button
        @click="removeSecondImageURL"
        class="xSmall"
        v-if="imageURL.length > 1"
        type="button"
      >
        X
      </button>
      <img class="myImage secondImageSrc" :src="imageURL[1]" />
    </div>
    <div v-if="imageURL.length > 2" class="thirdImageURL">
      <button
        @click="removeThirdImageURL"
        class="xSmall"
        v-if="imageURL.length > 2"
        type="button"
      >
        X
      </button>
      <img class="myImage thirdImageSrc" :src="imageURL[2]" />
    </div>
    <div v-if="imageURL.length > 3" class="fourthImageURL">
      <button
        @click="removeFourthImageURL"
        class="xSmall"
        v-if="imageURL.length > 3"
        type="button"
      >
        X</button
      ><img class="myImage fourthImageSrc" :src="imageURL[3]" />
    </div>
    <div v-if="imageURL.length > 4" class="fifthImageURL">
      <button
        @click="removeFifthImageURL"
        class="xSmall"
        v-if="imageURL.length > 4"
        type="button"
      >
        X</button
      ><img class="myImage fifthImageSrc" :src="imageURL[4]" />
    </div>
  </div>
</template>
<script>
export default {
  props: ['imageURL'],
  emits: [
    'removedFirstImage',
    'removedSecondImage',
    'removedThirdImage',
    'removedFourthImage',
    'removedFifthImage',
  ],
  data() {
    return {};
  },
  methods: {
    removeFirstImageURL() {
      this.$emit('removedFirstImage', true);
    },
    removeSecondImageURL() {
      this.$emit('removedSecondImage', true);
    },
    removeThirdImageURL() {
      this.$emit('removedThirdImage', true);
    },
    removeFourthImageURL() {
      this.$emit('removedFourthImage', true);
    },
    removeFifthImageURL() {
      this.$emit('removedFifthImage', true);
    },
  },
};
</script>
<style scoped>
*{
  font-family:'mukta';
}
img {
  min-width:200px;
  min-height:100px;
  width: 25vw;
  height: 25vh;
}
.xSmall {
  background: red;
  padding: 2px;
  margin: 2px;
  color: white;
  position: absolute;
}

@media only screen and (min-width: 400px) {
  img {
    width:300px;
    height: 150px;
  }
}
@media only screen and (min-width: 900px) {
  img {
    width:400px;
    height: 200px;
  }
}
@media only screen and (min-width: 1200px) {
  img {
    width:500px;
    height: 250px;
  }
}
@media only screen and (max-width: 400px) {
  img {
    width:200px;
    height: 100px;
  }
}
</style>
