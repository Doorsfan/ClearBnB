<template>
  <div class="priceRangeFormDiv">
    Min Price: <input class="minPriceInputForm" type="text" v-model="minPriceWanted" placeholder="Min Price"> Max Price: <input class="maxPriceInputForm" type="text" v-model="maxPriceWanted" placeholder="Max Price">
  </div>
</template>
<script>
export default {
  emits: ['updateMyMinPrice', 'updateMyMaxPrice'],
  data() {
    return {
      minPriceWanted: '',
      maxPriceWanted: ''
    }
  },
  watch: {
    minPriceWanted(){
      this.$emit("updateMyMinPrice", this.minPriceWanted)
    },
    maxPriceWanted(){
      this.$emit("updateMyMaxPrice", this.maxPriceWanted)
    }
  }
}
</script>
<style scoped>
input{
  width:60px;
  margin:3px;
}
.priceRangeFormDiv{
  display:inline;
  width:max-content;
  margin-left:20px;
}
</style>