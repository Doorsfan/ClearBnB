<template>
  <div class="mainDiv">
    <LoginForm />
  </div>
</template>
<script>
import LoginForm from '../components/LoginView/LoginForm.vue';
export default {
  components: {
    LoginForm,
  },
  data() {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
.mainDiv {
  font-family: 'mukta';
  background-image: url('../../public/Login-signup.jpg');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  height: max-content;
  background-attachment: fixed;
  overflow-x: hidden;
}
</style>
