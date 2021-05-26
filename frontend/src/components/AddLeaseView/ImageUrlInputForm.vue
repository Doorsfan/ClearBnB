<template>
  <div class="ImageURLSdiv">
    <p class="ImageURLStext">Image urls</p>
    <input
      v-model="imageURL"
      type="text"
      placeholder="Image URL.."
      class="imageURL"
    /><button
      v-if="imageURLs.length <= 4"
      class="addImageButton"
      type="button"
      @click="addImageUrl"
    >
      Add
    </button>
  </div>
</template>
<script>
export default {
  emits: ["updatedImgURLs"],
  mounted() {
    let myimageURLs = [];
    if (this.$store.getters.getLeaseToBuild != null) {
      for (let images of this.$store.getters.getLeaseToBuild.imageURLs) {
        myimageURLs.push(images);
      }
    }
    this.imageURLs = myimageURLs;
  },
  data() {
    return {
      imageURLs: [],
      imageURL: "",
    };
  },
  methods: {
    addImageUrl() {
      if (this.imageURL.length > 0) {
        let emptyArray = [];
        for (let image of this.imageURLs) {
          emptyArray.push(image);
        }
        emptyArray.push(this.imageURL);
        this.$emit("updatedImgURLs", emptyArray);
        this.imageURL = "";
      }
    },
  },
};
</script>
<style scoped>
.ImageURLSdiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.ImageURLStext {
  margin: 0px;
  text-align: center;
  margin-bottom: 5px;
  font-weight: bolder;
  width: 205px;
}
.addImageButton {
  background-color: #009db5;
  margin-left: 10px;
}
input {
  width: 120px;
  display: inline;
  font-size: 14px;
  padding: 2px;
}
button {
  display: inline;
  width: 40px;
}
</style>