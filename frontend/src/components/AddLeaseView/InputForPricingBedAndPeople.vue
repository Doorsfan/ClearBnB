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
p{
  width:max-content;
  display:inline-block;
}
.MaxPeopleP{
  margin-left:50px;
  margin-right:50px;
}
input {
  display: inline-block;
  text-align:center;
  padding: 7.5px 30.4px;
  font-size:18px;
  width:90px;
  margin:10px;
  margin-top:3px;
}

</style>
