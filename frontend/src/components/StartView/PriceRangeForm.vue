<template>
  <div class="firstPriceDiv">
    <p class="minPriceText">Min Price</p>
    <input
      class="minPriceInputForm"
      type="text"
      v-model="minPriceWanted"
      placeholder="Min Price"
    />
  </div>
  <div class="secondPriceDiv">
    <p class="maxPriceText">Max Price</p>
    <input
      class="maxPriceInputForm"
      type="text"
      v-model="maxPriceWanted"
      placeholder="Max Price"
    />
  </div>
</template>
<script>
export default {
  emits: ['updateMyMinPrice', 'updateMyMaxPrice'],
  data() {
    return {
      minPriceWanted: '',
      maxPriceWanted: '',
    };
  },
  watch: {
    minPriceWanted() {
      this.$emit('updateMyMinPrice', this.minPriceWanted);
    },
    maxPriceWanted() {
      this.$emit('updateMyMaxPrice', this.maxPriceWanted);
    },
  },
};
</script>
<style scoped>
*{
  font-family:'mukta';
}
@media only screen and (min-width: 1330px) {
  p {
    margin-bottom: 6px;
  }
  .minPriceText,
  .maxPriceText {
    padding-left: 3px;
  }
  .firstPriceDiv,
  .secondPriceDiv {
    width: max-content;
    display: inline-block;
  }
  .firstPriceDiv {
    padding-left: 10px;
  }
  .secondPriceDiv {
    padding-left: 10px;
  }
}
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
  width: 100px;
  padding: 5px;
}
.minPriceInputForm {
  width: 100px;
  padding: 5px;
}
.firstPriceDiv {
  margin-left: 5px;
}
</style>
