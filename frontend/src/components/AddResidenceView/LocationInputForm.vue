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
  width: 205px;
  text-align:center;
}
.addResidenceHeader {
  margin: 0px;
  margin-bottom: 3px;
  width:205px;
  text-align:center;
}
.inputForLocationArea {
  height: 14px;
  width:180px;
  margin-left:12.5px;
  margin-right:12.5px;
}
.addResidenceDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
</style>