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
p {
  color: #029ebb;
}
.firstPriceDiv, .secondPriceDiv{
  display:block;
  text-align: center;
}
@media only screen and (min-width: 1550px) {
  .firstPriceDiv, .secondPriceDiv{
    display:inline-block;
  }
}
.firstPriceDiv{
  margin-left:10px;
}
.secondPriceDiv{
  margin-left:10px;
}
input {
  width: 150px;
  margin: 3px;
  padding: 7.5px 30.4px;
  font-size:18px;
  text-align:center;
}
@media only screen and (max-width: 400px) {
  .firstPriceDiv,.secondPriceDiv{
    display:block;
  }
  .secondPriceDiv{
    margin-left:0px;
  }
}
</style>
