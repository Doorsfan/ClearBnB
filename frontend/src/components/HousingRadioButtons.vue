<template>
  <div class="firstRow">
    <input @change="updateChosenHousing" class="buttonForHouse" type="radio" id="House" value="House" v-model="chosenHouseType">
    <label class="labelForHouse" for="House">House</label>
    <input @change="updateChosenHousing" class="buttonForFlat" type="radio" id="Flat" value="Flat" v-model="chosenHouseType">
    <label class="labelForFlat" for="Flat">Flat</label>
    <input @change="updateChosenHousing" class="buttonForOther" type="radio" id="Other" value="Other" v-model="chosenHouseType">
    <label class="labelForOther" for="Other">Other</label>
  </div>
  <div class="secondRow">
    <input @change="updateChosenHousing" class="buttonForEntire" type="radio" id="Entire" value="Entire residence" v-model="chosenHouseType">
    <label class="labelForEntire" for="Entire">Entire residence</label>
    <input @change="updateChosenHousing" class="buttonForPart" type="radio" id="Part" value="Part" v-model="chosenHouseType">
    <label class="labelForPart" for="Part">Part</label>
  </div>
</template>
<script>
export default {
  data() {
    return {
      chosenHouseType: ''
    }
  },
  methods: {
    updateChosenHousing() {
      console.log(this.chosenHouseType);
      this.$emit("chosenHouseType", this.chosenHouseType)
    }
  }
}
</script>
<style scoped>
  .firstRow{
    width:210px;
    margin-left:auto;
    margin-right:auto;
    padding-right:10px;
  }
  .secondRow{
    width:210px;
    margin-left:auto;
    margin-right:auto;
    padding-right:10px;
  }
  label{
    margin-right: 5px;
    margin-left: 5px;
  }

</style>