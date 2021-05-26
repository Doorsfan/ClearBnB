<template>
  <div class="holdingDiv">
    <div class="divHoldingThePs">
      <p class="BedsP">Beds</p>
      <p class="MaxPeopleP">Max Guests</p>
      <p class="PriceP">Price</p>
    </div>
    <div class="divHoldingTheDivs">
      <input
        @change="sendUpAmountOfBeds"
        class="bedsInputField"
        v-model="Beds"
        placeholder="Beds"
        required
      />
      <input
        @change="sendUpMaxPeople"
        class="peopleInputField"
        v-model="MaxPeople"
        placeholder="Max"
        required
      />
      <input
        @change="sendUpNewPrice"
        class="priceInputField"
        v-model="Price"
        placeholder="Price"
        required
      />
    </div>
  </div>
</template>

<script>
export default {
  emits: ['updateBeds', 'updateMaxPeople', 'updatePrice'],
  mounted() {
    if (this.$store.getters.getLeaseToBuild != null) {
      this.Beds = this.$store.getters.getLeaseToBuild.beds;
      this.MaxPeople = this.$store.getters.getLeaseToBuild.maxGuests;
      this.Price = this.$store.getters.getLeaseToBuild.price;
    }
  },
  data() {
    return {
      Beds: '',
      MaxPeople: '',
      Price: '',
    };
  },
  methods: {
    sendUpAmountOfBeds() {
      this.$emit('updateBeds', this.Beds);
    },
    sendUpMaxPeople() {
      this.$emit('updateMaxPeople', this.MaxPeople);
    },
    sendUpNewPrice() {
      this.$emit('updatePrice', this.Price);
    },
  },
};
</script>

<style scoped>
.MaxPeopleP{
  margin-left:11.5vw;
  margin-right:11.5vw;
  width: 8vw;
  min-width:50px;
  max-width:200px;
}
.BedsP{
  min-width:50px;
  max-width:200px;
}
input {
  display: inline-block;
  text-align:center;
  padding: 0px;
  font-size:18px;
  min-width: 50px;
  max-width:200px;
  width:8vw;
  margin-top:3px;
}
.peopleInputField{
  margin-left:10vw;
  margin-right: 10vw;
}
p{
  margin-left:0px;
  margin-right:0px;
  width:max-content;
  display:inline-block;
}
@media only screen and (max-width: 450px) {
  input {
    padding: 0px;
  }
}
</style>
