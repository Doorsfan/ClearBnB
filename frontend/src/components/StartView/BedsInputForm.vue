<template>
    <p class="bedsInputText">Beds</p>
    <input class="bedsInputForm" type="text" v-model="wantedBeds" placeholder="Beds">
</template>
<script>
export default {
  emits: ['updateMyAmountOfBeds'],
  data() {
    return {
      wantedBeds: ''
    }
  },
  watch: {
    wantedBeds(){
      this.$emit("updateMyAmountOfBeds", this.wantedBeds)
    }
  }
}
</script>
<style scoped>
.bedsInputForm{
  width:48px;
  padding:1px;
  border-radius: 5px;
  border: 1px solid rgb(241, 232, 148);
}
.bedsInputText{
  margin-bottom: 8px;
  padding-left:2px;
  color: #007b8e;
  font-weight: 700;
}
</style>