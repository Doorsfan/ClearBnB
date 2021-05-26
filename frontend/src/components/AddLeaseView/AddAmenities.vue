<template>
  <p class="amenitiesText">Amenities:</p>
  <div class="AddAmenitiesDiv">
    <div class="firstDiv">
      <p class="wifiText">Wifi:</p>
      <input
        @change="updateWifi"
        type="checkbox"
        v-model="hasWifi"
        class="wifiBox"
      />
      <p class="kitchenText">Kitchen:</p>
      <input
        @change="updateKitchen"
        type="checkbox"
        v-model="hasKitchen"
        class="kitchenBox"
      />
    </div>
    <div class="secondDiv">
      <p class="washerText">Washer:</p>
      <input
        @change="updateWasher"
        type="checkbox"
        v-model="hasWasher"
        class="washerBox"
      />
      <p class="heatingText">Heating:</p>
      <input
        @change="updateHeating"
        type="checkbox"
        v-model="hasHeating"
        class="heatingBox"
      />
    </div>
    <div class="thirdDiv">
      <p class="airConditionerText">airConditioner:</p>
      <input
        @change="updateAC"
        type="checkbox"
        v-model="hasAirConditioner"
        class="airConditionerBox"
      />
    </div>
  </div>
</template>
<script>
export default {
  //Åtgärd:
  // Parprogrammera från och med idag 13:00, sprida kunskap, sprida kompetens, öka produktivitet
  // Sprint 2 - Delegera annan Scrum Master, Mjukt förslag: Syftet är att en person som är aktiv men inte är lika produktiv ska kunna kan få en större förankring i projektet - vilket kan hjälpa produktiviteten av arbetet
  //
  emits: [
    "updateWifi",
    "updateKitchen",
    "updateWasher",
    "updateHeating",
    "updateAC",
  ],
  mounted() {
    if (this.$store.getters.getLeaseToBuild != null) {
      if (
        this.$store.getters.getLeaseToBuild.amenities.includes("wifi: true")
      ) {
        this.hasWifi = true;
      } else {
        this.hasWifi = false;
      }
      if (
        this.$store.getters.getLeaseToBuild.amenities.includes("kitchen: true")
      ) {
        this.hasKitchen = true;
      } else {
        this.hasKitchen = false;
      }
      if (
        this.$store.getters.getLeaseToBuild.amenities.includes("washer: true")
      ) {
        this.hasWasher = true;
      } else {
        this.hasWasher = false;
      }
      if (
        this.$store.getters.getLeaseToBuild.amenities.includes("heating: true")
      ) {
        this.hasHeating = true;
      } else {
        this.hasHeating = false;
      }
      if (
        this.$store.getters.getLeaseToBuild.amenities.includes(
          "airConditioner: true"
        )
      ) {
        this.hasAirConditioner = true;
      } else {
        this.hasAirConditioner = false;
      }
    }
  },
  data() {
    return {
      hasWifi: false,
      hasKitchen: false,
      hasWasher: false,
      hasHeating: false,
      hasAirConditioner: false,
    };
  },
  methods: {
    updateWifi() {
      this.$emit("updateWifi", this.hasWifi);
    },
    updateKitchen() {
      this.$emit("updateKitchen", this.hasKitchen);
    },
    updateWasher() {
      this.$emit("updateWasher", this.hasWasher);
    },
    updateHeating() {
      this.$emit("updateHeating", this.hasHeating);
    },
    updateAC() {
      this.$emit("updateAC", this.hasAirConditioner);
    },
  },
};
</script>
<style scoped>
.airConditionerText {
  margin-left: -20px;
}
.amenitiesText {
  margin: 3px;
  margin-top: 8px;
  font-weight: bolder;
}
.firstDiv,
.secondDiv,
.thirdDiv {
  text-align: center;
}
.thirdDiv {
  margin: 3px;
}
.wifiBox {
  margin-right: 20px;
}
.washerBox {
  margin-right: 20px;
}
.heatingBox {
  margin-right: 32px;
}
input {
  text-align: left;
}
div {
  text-align: left;
}
p {
  width: max-content;
  display: inline-block;
}
input {
  margin: 5px;
  margin-left: 3px;
}
</style>
