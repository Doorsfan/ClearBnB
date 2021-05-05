<template>
  <div class="holdingDiv">
    <div class="divHoldingThePs">
      <p class="BedsP">Beds</p>
      <p class="MaxPeopleP">Max People</p>
      <p class="PriceP">Price</p>
    </div>
    <div class="divHoldingTheDivs">
        <input @change="sendUpAmountOfBeds" class="bedsInputField" v-model="Beds" placeholder="Beds" />
        <input @change="sendUpMaxPeople" class="peopleInputField" v-model="MaxPeople" placeholder="Max" />
        <input @change="sendUpNewPrice" class="priceInputField" v-model="Price" placeholder="Price" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      Beds: '',
      MaxPeople: '',
      Price: '',
    }
  },
  methods: {
    sendUpAmountOfBeds(){
      this.$emit("updateBeds", this.Beds)
    },
    sendUpMaxPeople(){
      this.$emit("updateMaxPeople", this.MaxPeople)
    },
    sendUpNewPrice(){
      this.$emit("updatePrice", this.Price)
    }
  }
}
</script>

<style scoped>
  p{
    margin:2px;
    width:max-content;
    display:inline;
  }
  input{
    width: 30px;
    display:inline;
    margin:2px;
  }
  .peopleInputField{
    margin-left:24px;
    margin-right:24px;
  }
</style>
