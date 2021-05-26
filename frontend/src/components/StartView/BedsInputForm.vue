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
*{
  font-family:'mukta';
}
.bedsInputForm {
  width: 100px;
  padding: 5px;
}
.bedsInputText {
  margin-bottom: 8px;
  padding-left: 2px;
}
</style>
