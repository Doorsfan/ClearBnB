<template>
  <div class="holdingDiv">
    <div class="divHoldingThePs">
      <p class="BedsP">Beds</p>
      <p class="MaxPeopleP">Max Guests</p>
      <p class="PriceP">Price</p>
    </div>
    <div class="divHoldingTheDivs">
        <input @change="sendUpAmountOfBeds" class="bedsInputField" v-model="Beds" placeholder="Beds" required/>
        <input @change="sendUpMaxPeople" class="peopleInputField" v-model="MaxPeople" placeholder="Max" required/>
        <input @change="sendUpNewPrice" class="priceInputField" v-model="Price" placeholder="Price" required/>
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
  .MaxPeopleP{
    margin-left: 24px;
    margin-right: 32px;
  }
  p{
    margin: 2px;
    width: max-content;
    display: inline;
  }
  .BedsP{
    margin: 0px;
    margin-left: 4px;
  }
  .divHoldingThePs{    
    margin: 0px;
    padding: 0px;
    text-align: left;
  }
  input{
    width: 30px;
    display: inline;
    margin: 2px;
  }
  .peopleInputField{
    margin-left: 42px;
    margin-right: 42px;
  }
</style>
