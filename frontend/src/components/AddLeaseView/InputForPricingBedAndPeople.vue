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
* {
  padding: 0px;
  margin: 0px;
  text-align: left;
  font-family:'mukta';
}
.MaxPeopleP {
  width: 30px;
  margin-left: 22px;
  margin-right: 10px;
}
p {
  margin: 2px;
  width: max-content;
  display: inline;
}
.BedsP {
  margin: 0px;
  margin-left: 4px;
}
.divHoldingThePs {
  margin: 0px;
  padding: 0px;
}
input {
  width: 30px;
  display: inline-block;
  margin-top: 0px;
}
.peopleInputField {
  margin-left: 42px;
  margin-right: 42px;
}
.bedsInputField {
  font-size: 14px;
  margin: 1px;
  margin-left: 3px;
  margin-top: 2px;
  padding: 2px;
  width: 40px;
}
.peopleInputField {
  font-size: 14px;
  margin: 1px;
  margin-top: 2px;
  padding: 2px;
  width: 35px;
  margin-left: 40px;
}
.priceInputField {
  font-size: 14px;
  margin: 1px;
  margin-top: 2px;
  padding: 2px;
  width: 40px;
  margin-left: 40px;
}
</style>
