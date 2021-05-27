<template>
  <div class="ImageURLSdiv">
    <p class="ImageURLStext">Image urls</p>
    <input
      v-model="imageURL"
      type="text"
      placeholder="Image URL.."
      class="imageURL"
    />
    <div class="buttonDiv"><button
      v-if="imageURLs.length <= 4"
      class="addImageButton"
      type="button"
      @click="addImageUrl"
    >
      Add
    </button>
    </div>
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
      imageURL: '',
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
.buttonDiv{
  width:max-content;
  display:inline-block;
}
*{
  font-family:'mukta';
}
.ImageURLSdiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
.ImageURLStext {
  margin: 0px;
  margin-top: 30px;
  text-align: center;
  margin-bottom: 5px;
  font-weight: bolder;
}
button {
  display: inline;
  width: 40px;
}
.addImageButton {
  width: 60px;
  height: 40px;
  padding:5px;
  background-color: #029ebb;
  color: white;
  border: 1px solid grey;
  border-radius: 2px;
  cursor: pointer;
  font-size: 18px;
  text-decoration:none;
  margin-left:10px;
}
input {
  min-width:150px;
  max-width:300px;
  width:30vw;
  display: inline;
  font-size: 14px;
  padding: 7.5px 30.4px;
}
@media only screen and (max-width: 500px){
  .addImageButton {
    border-radius:10px;
  }
}
@media only screen and (max-width: 300px) {
  input {
    padding: 0px;
  }
}

</style>
