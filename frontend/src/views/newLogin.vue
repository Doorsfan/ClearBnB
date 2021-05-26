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
  mounted() {
  if (document.getElementsByClassName("house_icon").length > 0) {
      document.getElementsByClassName("house_icon")[0].src =
        "/src/assets/clearbnb-logo.png";
      document.getElementsByClassName("house_icon")[0].className =
        "sunIconInHeader";
      document.getElementsByClassName("homeText")[0].style.display = "none";
      document.getElementsByClassName("center")[0].style.height = "210px";
    }
    this.$store.dispatch('saveLatestRoute', this.$route.path);
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
