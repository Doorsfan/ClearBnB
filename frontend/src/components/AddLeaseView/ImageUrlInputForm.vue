<template>
  <div class="ImageURLSdiv">
    <p class="ImageURLStext">Image urls</p>
    <form @submit.prevent="addImageUrl">
      <input
        v-model="imageURL"
        type="text"
        placeholder="Image URL.."
      />
      <button class="addImageButton" type="submit">Add</button>
    </form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      imageURLs: [],
      imageURL: "",
    };
  },
  methods: {
    addImageUrl() {
      this.imageURLs.push(this.imageURL);
      this.$emit("updatedImgURLs", this.imageURLs);
      this.imageURL = "";
    },
  },
};
</script>
<style scoped>
.ImageURLSdiv {
  width: 210px;
  margin-left: auto;
  margin-right: auto;
}
.ImageURLStext {
  margin: 0px;
  text-align: center;
  margin-bottom: 5px;
  font-weight: bolder;
  width:205px;
}
.addImageButton {
  background-color: lightgreen;
  margin-left: 10px;
}
input {
  width: 120px;
  display:inline;
}
button {
  display:inline;
  width:40px;
}
</style>