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
      location: '',
    };
  },
  methods: {
    updateLocationArea() {
      let updatedLocationArea = location;
      this.$emit('updatedLocation', this.location);
    },
  },
};
</script>
<style scoped>
input {
  text-align:center;
  padding: 7.5px 30.4px;
  font-size:18px;
}
.residenceInnerDiv {
  width: max-content;
  margin: auto;
  text-align: center;
}
.addResidenceHeader {
  margin: 0px;
  margin-bottom: 3px;
  width: 205px;
  text-align: center;
}

.addResidenceDiv {
  width: max-content;
  margin-left: auto;
  margin-right: auto;
}
</style>
