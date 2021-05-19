<template>
  <div class="firstRow">
    <input @change="updateChosenHousing" required name="housingType" class="buttonForHouse" type="radio" id="House" value="House" v-model="firstRowChoice">
    <label class="labelForHouse" for="House">House</label>
    <input @change="updateChosenHousing" name="housingType" class="buttonForFlat" type="radio" id="Flat" value="Flat" v-model="firstRowChoice">
    <label class="labelForFlat" for="Flat">Flat</label>
    <input @change="updateChosenHousing" name="housingType" class="buttonForOther" type="radio" id="Other" value="Other" v-model="firstRowChoice">
    <label class="labelForOther" for="Other">Other</label>
  </div>
  <div class="secondRow">
    <input @change="updateChosenSize" required name="entireResidence" class="buttonForEntire" type="radio" id="Entire" value="Entire residence" v-model="secondRowChoice">
    <label class="labelForEntire" for="Entire">Entire residence</label>
    <input @change="updateChosenSize" name="entireResidence" class="buttonForPart" type="radio" id="Part" value="Part" v-model="secondRowChoice">
    <label class="labelForPart" for="Part">Part</label>
  </div>
</template>
<script>
//Implement so that it is a seperate section in the Radio Buttons for Entire residence/part
export default {
  emits: ['updateChosenHousing', 'updateChosenSize'],
  mounted(){
    if(this.$store.getters.getLeaseToBuild != null){
      this.firstRowChoice = this.$store.getters.getLeaseToBuild.typeOfHousing
      this.secondRowChoice = this.$store.getters.getLeaseToBuild.entireResidence == true ? 'Entire residence' : 'Part'
    }
  },
  data() {
    return {
      firstRowChoice: '',
      secondRowChoice: ''
    }
  },
  methods: {
    updateChosenHousing() {
      this.$emit("updateChosenHousing", this.firstRowChoice)
    },
    updateChosenSize(){
      this.$emit("updateChosenSize", this.secondRowChoice)
    }
  }
}
</script>
<style scoped>
  .labelForEntire{
    padding-right:10px;
  }
  .buttonForEntire{
    margin-left:-2px;
  }
  .labelForHouse{
    padding-right:0px;
  }
  .labelForFlat{
    padding-right: 20px;
  }
  .firstRow{
    width:210px;
    margin-left:auto;
    margin-right:auto;
  }
  .secondRow{
    width:210px;
    margin-left:auto;
    margin-right:auto;
  }
  label{
    margin-right: 5px;
    margin-left: 5px;
  }

</style>