<template>
  <div>
    <p class="myDescription">Description:</p>
    <textarea
      required
      @change="sendUpMessage"
      class="descriptionInput"
      v-model="message"
      placeholder="Add a description to your Lease.."
    >
    </textarea>
  </div>
</template>
<script>
export default {
  emits: ['updatedDescription'],
  mounted() {
    if (this.$store.getters.getLeaseToBuild != null) {
      this.message = this.$store.getters.getLeaseToBuild.description;
    }
  },
  data() {
    return {
      message: '',
    };
  },
  methods: {
    sendUpMessage() {
      let messageToSendUp = this.message;
      this.$emit('updatedDescription', messageToSendUp);
    },
  },
};
</script>
<style scoped>
.descriptionInput {
  min-width: 220px;
  width: 60vw;
  height: 15vh;
  min-height: 80px;
  padding: 7.5px 30.4px;
  font-size: 18px;
  text-align: center;
}
.myDescription {
  margin: 3px;
  padding: 0px;
  font-weight: bolder;
}
</style>
