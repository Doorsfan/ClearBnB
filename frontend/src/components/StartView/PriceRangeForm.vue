<template>
  <div class="firstPriceDiv">
    <p class="minPriceText">Min €</p>
    <input
      class="minPriceInputForm"
      type="text"
      v-model="minPriceWanted"
      placeholder="From €"
    />
  </div>
  <div class="secondPriceDiv">
    <p class="maxPriceText">Max €</p>
    <input
      class="maxPriceInputForm"
      type="text"
      v-model="maxPriceWanted"
      placeholder="To €"
    />
  </div>
</template>
<script>
export default {
  emits: ["updateMyMinPrice", "updateMyMaxPrice"],
  data() {
    return {
      minPriceWanted: "",
      maxPriceWanted: "",
    };
  },
  watch: {
    minPriceWanted() {
      this.$emit("updateMyMinPrice", this.minPriceWanted);
    },
    maxPriceWanted() {
      this.$emit("updateMyMaxPrice", this.maxPriceWanted);
    },
  },
};
</script>
<style scoped>
div {
  margin-left: 5px;
  margin-right: 5px;
}
@media only screen and (min-width: 1330px) {
  /*
  .minPriceText,
  .maxPriceText {
    padding-left: 3px;
  }
  
  .firstPriceDiv,
  .secondPriceDiv {
    width: max-content;
    display: inline-block;
  }
  /*
  .firstPriceDiv {
    padding-left: 10px;
  }
  */
  .secondPriceDiv {
    padding-left: 10px;
  }
}
.firstPriceDiv,
.secondPriceDiv {
  width: max-content;
  display: inline-block;
}
/*
input {
  width: 60px;
  margin: 3px;
}

.priceRangeFormDiv {
  display: inline;
  width: max-content;
  margin-left: 20px;
}

.maxPriceInputForm {
  width: 90px;
  padding: 1px;
  border-radius: 5px;
  border: 1px solid rgb(241, 232, 148);
}

.minPriceInputForm {
  width: 90px;
  padding: 1px;
  border-radius: 5px;
  border: 1px solid rgb(241, 232, 148);
}

.firstPriceDiv {
  margin-left: 5px;
}
*/
</style>
