<template>
  <div>
    <textarea v-model="message" placeholder="Add a description to your Lease.."></textarea>
  </div>
</template>
<script>
  import ref from 'vue'
  let message = ref("")
  export default {
    data() {
      return {
        message: ""
      }
    },
    methods: {
      getMessage(){
        return this.message
      }
    }
  }
</script>
<style scoped>
</style>