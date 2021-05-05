<template>
  <div class="holdingDiv">
    <div class="divHoldingThePs">
      <p>Beds</p>&nbsp;
      <p>Max People</p>&nbsp;
      <p>Price</p>&nbsp;
    </div>
    <div class="divHoldingTheDivs">
      <div class="divForHoldingBeds">
        <input @change="sendUpAmountOfBeds" class="bedsInputField" v-model="Beds" placeholder="Beds" />
      </div>
      <div class="divForHoldingPeople">
        <input @change="sendUpMaxPeople" class="peopleInputField" v-model="MaxPeople" placeholder="Max" />
      </div>
      <div class="divForHoldingPrice">
        <input @change="sendUpNewPrice" class="priceInputField" v-model="Price" placeholder="Price" /> PPPN
      </div>
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
  .bedsInputField{
    width: 33px;
    padding:2px;
    margin: 0px;
    margin-left: 50px;
  }
  .peopleInputField{
    width: 26px;
    padding:2px;
  }
  .priceInputField{
    width: 40px;
    padding:2px;
  }
  .holdingDiv{
    width:max-content;
    margin-left:auto;
    margin-right:auto;
  }
  p{
    width:max-content;
    display:inline;
    margin:5px;
  }
  .divForHoldingBeds, .divForHoldingPeople, .divForHoldingPrice{
    width:70px;
    text-align:left;
    display:inline;
  }
  .divHoldingTheDivs{
    text-align:left;
  }
  .divForHoldingPrice{
    margin-left: 47px;
  }
  .divForHoldingPeople{
    margin-left: 35px;
  }
  .divForHoldingBeds{
    padding-left:2px;
  }
  .divHoldingThePs{
    margin-bottom:2px;
  }
</style>
