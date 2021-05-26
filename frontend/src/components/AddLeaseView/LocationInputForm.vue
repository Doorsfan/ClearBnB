<template>
  <div class="addResidenceDiv">
    <div class="residenceInnerDiv">
      <input
        required
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
  emits: ["updatedLocation"],
  mounted() {
    if (this.$store.getters.getLeaseToBuild != null) {
      this.location = this.$store.getters.getLeaseToBuild.location;
    }
  },
  data() {
    return {
      location: "",
    };
  },
  methods: {
    updateLocationArea() {
      let updatedLocationArea = location;
      this.$emit("updatedLocation", this.location);
    },
  },
};
</script>
<style scoped>
.residenceInnerDiv {
  width: 205px;
  text-align: center;
}
.addResidenceHeader {
  margin: 0px;
  margin-bottom: 3px;
  width: 205px;
  text-align: center;
}
.inputForLocationArea {
  height: 26px;
  padding: 3px;
  width: 180px;
  margin-left: 12.5px;
  margin-right: 12.5px;
}
.addResidenceDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
</style>