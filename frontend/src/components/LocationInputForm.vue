<template>
  <div class="addResidenceDiv">
    <div class="innerDiv">
      <h3 class="addResidenceHeader">Add a residence</h3>
      <input
        type="text"
        placeholder="Location"
        @change="updateLocationArea"
        class="inputForLocationArea"
        v-model="location"
      />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      location: "",
    };
  },
  methods: {
    updateLocationArea() {
      let updatedLocationArea = location;
      this.$emit("updatedLocation", updatedLocationArea);
    },
  },
};
</script>
<style scoped>
.innerDiv {
  width: max-content;
  padding-left: 15px;
}
.addResidenceHeader {
  margin: 0px;
  margin-bottom: 3px;
  width: max-content;
}
.inputForLocationArea {
  height: 14px;
  padding: 3px;
  width: 80vw;
  max-width: 200px;
}
.addResidenceDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
</style>