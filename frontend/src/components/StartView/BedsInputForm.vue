<template>
  <p class="bedsInputText">Beds</p>
  <input
    class="bedsInputForm"
    type="text"
    v-model="wantedBeds"
    placeholder="Beds"
  />
</template>
<script>
export default {
  emits: ['updateMyAmountOfBeds'],
  data() {
    return {
      wantedBeds: '',
    };
  },
  watch: {
    wantedBeds() {
      this.$emit('updateMyAmountOfBeds', this.wantedBeds);
    },
  },
};
</script>
<style scoped>
p {
  color: #029ebb;
  font-size: 20px;
}
* {
  font-family: 'mukta';
}
.bedsInputForm {
  width: 150px;
  padding: 7.5px 30.4px;
  text-align: center;
  font-size: 18px;
  margin: 3px;
  margin-left: auto;
  margin-right: auto;
}
.bedsInputText {
  width: max-content;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
}
</style>
